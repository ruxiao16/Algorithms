package com.xiao.algorithms.leetcode.onefifty;

import java.util.stream.Collectors;

public class IsPalindrome {

	public boolean isPalindrome(String s) {
		if (s.length() <= 1) {
			return true;
		}

		String cleaned = s.chars().filter(Character::isLetterOrDigit)
				.map(Character::toLowerCase)
				.mapToObj(c -> String.valueOf((char)c))
				.collect(Collectors.joining());

		// either recursive or iterative
		for (int i = 0; i < cleaned.length()/2; i++) {
			if (cleaned.charAt(i) != cleaned.charAt(cleaned.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		IsPalindrome isPalindrome = new IsPalindrome();

		String testStr1 = "A man, a plan, a canal: Panama";
		System.out.println(isPalindrome.isPalindrome(testStr1));

		String testStr2 = "race a car";
		System.out.println(isPalindrome.isPalindrome(testStr2));

		String testStr3 = " ";
		System.out.println(isPalindrome.isPalindrome(testStr3));

		String testStr4 = "0P";
		System.out.println(isPalindrome.isPalindrome(testStr4));
	}

}
