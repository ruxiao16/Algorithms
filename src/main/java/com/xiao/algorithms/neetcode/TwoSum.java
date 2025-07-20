package com.xiao.algorithms.neetcode;

import java.util.HashMap;

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return new int[0];
	}

	// one pass
	public int[] twoSumHashMap(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int val = nums[i];
			int diff = target - val;

			if (map.containsKey(diff)) {
				return new int[] {map.get(diff), i};
			}
			map.put(val, i );
		}
		return new int[] {};
	}
}
