package com.xiao.algorithms.neetcode.twopointers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {


	public int[] twoSumBruteForce(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] + nums[j]== target) {
					return new int[] {i, j};
				}
			}
		}
		return new int[]{};
	}

	public int[] twoSumTwoPointers(int[] nums, int target) {

		int[][] numsWithIndices = new int[nums.length][2];
		// we need to return indices, we store them as value, idx
		for (int i = 0; i < nums.length; i++) {
			numsWithIndices[i] = new int[]{nums[i], i};
		}
		Arrays.sort(numsWithIndices, Comparator.comparingInt(a -> a[0]));

		int left = 0;
		int right = nums.length -1;

		while (left < right) {
			int sum = numsWithIndices[left][0] + numsWithIndices[right][0];

			if (sum < target) {
				left++;
			} else if (sum > target) {
				right--;
			} else {
				return new int[] {numsWithIndices[left][1], numsWithIndices[right][1]};
			}
		}

		return new int[]{};
	}

	// use hashmap, one pass
	public int[] twoSumOnePass(int[] nums, int target) {
		Map<Integer, Integer> lookupMap = new HashMap<>();

		for (int i =0 ; i < nums.length; i++) {
			int diff = target - nums[i];

			if (lookupMap.containsKey(diff)) {
				// math.min max not really needed, as we guranteed the entries in the map appears first from the array
				return new int[] {Math.min(i, lookupMap.get(diff)), Math.max(i, lookupMap.get(diff))};
			}

			lookupMap.put(nums[i], i);
		}

		return new int[]{};
	}


	public static void main(String[] args) {
		int[] nums1 = new int[]{3, 4, 5, 6};

		TwoSum driver = new TwoSum();
		int[] res1 = driver.twoSumOnePass(nums1, 7);

		System.out.println(res1[0] +" ,  "+ res1[1]);

		int[] nums2 = new int[]{5, 5};

		int[] res2 = driver.twoSumOnePass(nums2, 10);

		System.out.println(res2[0] +" ,  "+ res2[1]);
	}
}
