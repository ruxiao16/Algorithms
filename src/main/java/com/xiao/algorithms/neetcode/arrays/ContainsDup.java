package com.xiao.algorithms.neetcode.arrays;

import java.util.Arrays;


public class ContainsDup {

	public boolean hasDuplicate(int[] nums) {
		if (nums.length <= 1) {
			return false;
		}

		Arrays.sort(nums);

		for (int i = 0; i < nums.length-1; i ++) {
			if (nums[i] == nums[i+1]) {
				return true;
			}
		}
		return false;
	}


	public static void main(String[] args) {
		ContainsDup testDriver = new ContainsDup();

		int[] nums = new int[]{1,2,3,4};
		System.out.println(testDriver.hasDuplicate(nums));
	}
}
