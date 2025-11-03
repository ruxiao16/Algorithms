package com.xiao.algorithms.neetcode.twopointers;

public class IsPalindrome {

	public boolean isPalindrome(String s) {
		s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		System.out.println("modified string is " +s);
//		return isPalindromeTwoPointers(s);

		return isPalindromeRecursive(s);
	}


	private boolean isPalindromeTwoPointers(String s) {
		int start = 0;
		int end  = s.length() - 1;

		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}

		return true;
	}

	// recursion, probably uses more space as more strings need to be created
	private boolean isPalindromeRecursive(String s) {
		// even number of characters will eventually have s.length = 0.
		if (s.length() <= 1) {
			return true;
		}

		if (s.charAt(0) != s.charAt(s.length()-1)) {
			return false;
		}

		return isPalindromeRecursive(s.substring(1, s.length()-1));
	}


	public static void main(String[] args) {
		IsPalindrome driver = new IsPalindrome();

		System.out.println(driver.isPalindrome("Was it a car or a cat I saw?"));
		System.out.println(driver.isPalindrome("tab a cat"));
		System.out.println(driver.isPalindrome("No lemon, no melon"));
	}
}
