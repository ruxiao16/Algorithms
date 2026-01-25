package com.xiao.algorithms.neetcode.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

//	brute force, use a set to keep track of the current max length.
	public int lengthOfLongestSubstring(String s) {
		Set<Character> tracker = new HashSet<>();
		int maxLen = tracker.size();

		for (int i = 0; i < s.length(); i++) {
			tracker.add(s.charAt(i));
			for (int j = i+1; j < s.length(); j++) {
				if (!tracker.add(s.charAt(j))) {
					// find a duplicate
					break;
				}
			}
			maxLen = Math.max(maxLen, tracker.size());
			tracker.clear();
		}
		return maxLen;
	}

	// intuition, we keep one window that always has unique characters.
	// We expand the window by moving the right pointer. If we ever see a repeated char,
	// we shrink the window from the left until the duplicate is removed -> this is necessary because duplication may be in
	// the middle somewhere between l and r, for example given abcabcbb, once we are at the 3rd b, l should be at 3,
	// -> then we have to keep the string continuous, meaning we remove a at index 3 then b at index 4, before proceeding
	// time complexity O(n) space O(n)
	public int slidingWindow(String s) {
		Set<Character> tracker = new HashSet<>();
		int l = 0;
		int maxLen = 0;

		for (int r = 0; r < s.length(); r++) {
			while (tracker.contains(s.charAt(r))) {
				// important step, removing the left until we get to our duplicate
				// in our example, this is the first encounter until we get to the 3rd b
				tracker.remove(s.charAt(l));
				l++;
			}
			tracker.add(s.charAt(r));
			maxLen = Math.max(maxLen, r-l+1);
		}

		return maxLen;
	}

	public static void main(String[] args) {
		LongestSubstring driver = new LongestSubstring();
		System.out.println(driver.lengthOfLongestSubstring("zxyzxyz"));
		System.out.println(driver.lengthOfLongestSubstring("xxxx"));
	}
}

