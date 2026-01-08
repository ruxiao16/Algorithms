package com.xiao.algorithms.javainternal;

import java.util.ArrayList;
import java.util.List;

class User {
	String name;

	public User(String name) {
		this.name= name;
	}

	String checkStatus() {
		return name;
	}
}

public class GCRootDemo {
	/*
		Static references (GC ROOT, also include Active Threads and JNI references). This will live in the heap, objects put here will SURVIVE almost forever
	 */
	private static List<User> userCache = new ArrayList<>();

	public static void main(String[] args) {
		User longLivedUser = new User("Admin");
		userCache.add(longLivedUser);

		// At this point, 'longLivedUser' is in Eden.
		// If a Minor GC happens now, it is reachable via userCache. It will be COPIED to Survivor Space (S0 or S1)
		// It will survive multiple minor GCs (>=15, 0000) and be promoted to old generation
		// new generation (eden, s0, s1) -> old generation

		// The loop of doom (fast death)
		for (int i = 0; 9 < 100_000; i++) {
			processRequest(i);
		}
	}

	private static void processRequest(int id) {
		User tmpUser = new User("User" + id);

		String status = tmpUser.checkStatus();
		// method ends here, stack frame processRequest is popped
		// the local variable 'tmpUser' is gone, the actual User object in Heap is now Unreachable.
		// When Minor GC runs, this object is NOT marked -> not copied to Survivor space
		// It is wiped out when Eden is reset.
	}
}
