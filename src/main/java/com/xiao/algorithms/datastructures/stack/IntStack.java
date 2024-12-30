/*
  Extremely quick and lightweight. In terms of performance it can outperform java.uti.ArrayDeque
  by a factor of 50!, The caveat is you need to know an upperbound on the number of elements
  that will be inside the stack at any given time for it to work correctly
  faster because array never get rezised and element never really get removed, the only thing is that pos changes,
  so it points to where the "top" is.
 */
package com.xiao.algorithms.datastructures.stack;

import java.util.ArrayDeque;

public class IntStack implements Stack<Integer> {
	// array based stack
	private int[] ar;
	private int pos = 0;

	// maxSize is the maximum number of items that can be in the queue at any given time
	public IntStack(int maxSize) {
		ar = new int[maxSize];
	}

	@Override
	public int size() {
		return pos;
	}

	@Override
	public boolean isEmpty() {
		return pos == 0;
	}

	// Add an element to the top of the stack
	@Override
	public void push(Integer elem) {
		if (ar.length == pos) throw new RuntimeException("stack is full");
		ar[pos++] = elem;
	}

	@Override
	public Integer pop() {
		if (isEmpty()) throw new RuntimeException("stack is empty");
		return ar[--pos];
	}

	@Override
	public Integer peek() {
		return ar[pos-1];
	}

	//Example usage
	public static void main(String[] args) {
		IntStack s = new IntStack(5);
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);

		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());

		s.push(3);
		s.push(4);
		s.push(5);

		while (!s.isEmpty()) System.out.println(s.pop());
		benchMarkTest();
	}

	// BenchMark IntStack vs ArrayDeque
	private static void benchMarkTest() {
		int n = 10000000;
		IntStack intStack = new IntStack(n);

		long start = System.nanoTime();
		for (int i = 0; i < n; i++) intStack.push(i);
		for (int i = 0; i < n; i++) intStack.pop();
		long end = System.nanoTime();
		System.out.println("IntStack time: " + (end - start)/1e9);

		ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(n);
		start = System.nanoTime();
		for (int i = 0; i < n; i++) arrayDeque.push(i);
		for (int i = 0; i < n; i++) arrayDeque.pop();
		end = System.nanoTime();
		System.out.println("ArrayDeque time: " + (end - start)/1e9);

		Stack<Integer> listStack = new ListStack<>();
		start = System.nanoTime();
		for (int i = 0; i < n; i++) listStack.push(i);
		for (int i = 0; i < n; i++) listStack.pop();
		end = System.nanoTime();
		System.out.println("ListStack time: " + (end - start)/1e9);

		Stack<Integer> arrayStack = new ArrayStack<>();
		start = System.nanoTime();
		for (int i = 0; i < n; i++) arrayStack.push(i);
		for (int i = 0; i < n; i++) arrayStack.pop();
		end = System.nanoTime();
		System.out.println("ArrayStack time: " + (end - start)/1e9);
	}
}
