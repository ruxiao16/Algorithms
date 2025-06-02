package com.xiao.algorithms.leetcode.onefifty;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RemoveDupFromSortedArrayII {

	public int removeDuplicates(int[] nums) {
		if (nums.length <= 2) {
			return nums.length;
		}

		int index = 2;
		// if index and i are moving together
		for (int i = 2; i < nums.length; i++) {
			if (nums[i] != nums[index-2]) {
				nums[index++] = nums[i];
			}
		}
		return index;
	}

	public static void main(String[] args) {
		RemoveDupFromSortedArrayII removeDup = new RemoveDupFromSortedArrayII();

		int[] testArr1 = {1, 1, 2, 2, 3, 3, 4};
		removeDup.removeDuplicates(testArr1);
		String result = Arrays.stream(testArr1)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining( " "));
		System.out.println(result);

		int[] testArr2 = {1, 1, 1, 2, 2, 3};
		int index2 = removeDup.removeDuplicates(testArr2);
		String result2 = Arrays.stream(testArr2)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining( " "));
		System.out.println(result2 + " and index: " + index2);
	}
}
