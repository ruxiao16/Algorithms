package com.xiao.algorithms.recursive;

public class MultiplicationRecursive {

	public static void main(String[] args) {
		int result = multiplyRecursive(3, 4);
		System.out.println("product is: "+ result);
	}

	private static int multiplyRecursive(int a, int b) {
		if (a == 0) return 0;
		// cannot use a-- here because a-- happens after the function call.
		// so 3 will be passed in to the next call, and so on and forth -> never decremented
		return multiplyRecursive(--a, b) +b;
	}
}
