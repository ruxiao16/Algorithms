package com.xiao.algorithms.neetcode.twopointers;

//sorted input, O(1) space requirement
// The intuition/proof is that
//If nums[0] + nums[n-1] > target, then we know nums[n - 1] can not possibly be included in any pairs.
//Because nums[n - 1] is the largest element in the array. Even by adding it with nums[0], which is the smallest element, we still exceed the target.
public class TwoSumII {

	public int[] twoSum(int[] numbers, int target) {

		int left = 0;
		int right = numbers.length-1;

		while (left < right) {
			int sum = numbers[left] + numbers[right];

			if (sum > target) {
				right--;
			} else if (sum < target) {
				left++;
			} else {
				return new int[] {numbers[left], numbers[right]};
			}
		}
		return new int[]{};
	}
}
