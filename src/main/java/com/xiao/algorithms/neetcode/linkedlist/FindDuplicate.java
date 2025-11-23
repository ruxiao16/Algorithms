package com.xiao.algorithms.neetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class FindDuplicate {

	public int findDuplicateHashset(int[] nums) {
		Set<Integer> lookUpSet = new HashSet<>();

		for (int num : nums) {
			if (lookUpSet.contains(num)) {
				return num;
			}
			lookUpSet.add(num);
		}

		return -1;// not found
	}


	public int findDuplicateFloyd(int[] nums) {
		int slow = 0, fast = 0;
		// [1,3,4,2,2]
		while (true) {
			slow = nums[slow];
			fast = nums[nums[fast]]; // advance twice
			if (slow == fast) {
				break;
			}
		}

		int slow2 = 0;
		while (true) {
			slow = nums[slow];
			slow2 = nums[slow2];
			if (slow == slow2) {
				return slow;
			}
		}
	}

	// without modifying array and using O(1) extra space



}
