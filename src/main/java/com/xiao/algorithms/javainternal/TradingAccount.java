package com.xiao.algorithms.javainternal;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class TradingAccount {

	// 1. Creates the tool (the Screwdriver)
	// This happens Once, it learns the offset of the "balance" field in memory
	private static final AtomicLongFieldUpdater<TradingAccount> balanceUpdater =
			AtomicLongFieldUpdater.newUpdater(TradingAccount.class, "balance");

	// the Field (The Screw)
	// Must be volatile so threads see changes immediately
	private volatile long balance;

	public boolean withdraw(long amount) {
		long currentBalance;
		long newBalance;

		//The spin loop -> this is busy spinning, essentially it is hogging a CPU
		do {
			currentBalance = balanceUpdater.get(this); // Read current
			newBalance = currentBalance - amount;

			if (newBalance < 0) {
				return false;
			}

		} while (!balanceUpdater.compareAndSet(this, currentBalance, newBalance));

		return true;
	}
	// Why not just use AtomicLong balance = new AtomicLong(0) -> this is because we avoid creating
	// an extra object AtomicLong per TradingAccount instance.

	/*
	When the JVM loads the TradingAccount class, it decides exactly how to lay out the data in memory.
	Let's imagine the memory layout looks like this for every instance of TradingAccount:
	Bytes 0-11: Object Header (Mark word, Class pointer)
	Bytes 12-15: (Padding/Alignment)
	Bytes 16-23: long balance (The field we care about)
	Bytes 24+: Other fields...
	The Setup (Static Init)
	When you run this line once:
	AtomicLongFieldUpdater.newUpdater(TradingAccount.class, "balance");
	The Updater asks the JVM (specifically the Unsafe class backend): "Hey, for the class TradingAccount, how many bytes away from the start of the object is the field named balance?"
	The JVM answers: "It is at offset 16."
	The balanceUpdater stores that number: 16. It doesn't know about Instance A or Instance B yet. It just knows "The screw is always 16mm from the top."
	The Execution
	When you call:
	balanceUpdater.compareAndSet(instanceA, current, new)
	The Updater does simple pointer arithmetic:
	Get the memory address of instanceA (e.g., 0x00AA).
	Add the stored offset (16).
	Target Address = 0x00AA + 16 = 0x00BA.
	Execute the hardware atomic instruction (CAS) on address 0x00BA.
	When you call it on instanceB (address 0x00CC):
	Target Address = 0x00CC + 16 = 0x00DC.
	Execute CAS on 0x00DC.
	Why this saves memory
	AtomicLong approach:
	Account object (Header + Reference to AtomicLong)
	AtomicLong object (Header + value)
	Result: Two object headers per account, plus a pointer references.
	Updater approach:
	Account object (Header + raw long value)
	static Updater object (Created only once per Class, not per account).
	Result: One object header per account. The long is embedded directly inside.
	This technique effectively allows Java developers to perform "C-style" pointer arithmetic safely, reducing the object graph significantly.

	 */
}
