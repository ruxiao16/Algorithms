package com.xiao.algorithms.recursive;

public class PalindromeRecursive {
	private static StringBuffer strBuffer;

	public static void  main(String[] args) {
		System.out.println(reverseString("fire"));
		System.out.println(reverseString("123"));
		System.out.println(reverseString("retaw"));
		System.out.println(reverseString(""));
	}
	private static String reverseString(String input) {
		strBuffer = new StringBuffer();
		if (input == null) {
			return "";
		}
		return reverseString(0, input);
	}
	private static String reverseString(int charIdx, String input) {
		if (charIdx == input.length()) {
			return "";
		}

		reverseString(charIdx+1, input);

		// unwinding
		char currChar = input.charAt(charIdx);
		strBuffer.append(currChar);
		return strBuffer.toString();
	}


	public static boolean isPalindrome(String input) {
		return reverseString(input).equals(input);
	}
}
