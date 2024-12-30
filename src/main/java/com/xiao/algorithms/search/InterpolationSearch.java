/*
 * An implementation of interpolation search
 *
 * <p>Time Complexity: O(log(log(n))) if data is uniform O(n) in worst case
 */
package com.xiao.algorithms.search;

public class InterpolationSearch {
	// see https://www.geeksforgeeks.org/interpolation-search/ for proof
	public static int interpolationSearch(int[] nums, int val) {
		int lo = 0, mid = 0, hi = nums.length -1;
		while (nums[lo] <= val && nums[hi] >= val) {
			mid = lo + ((val - nums[lo]) * (hi - lo)) / (nums[hi] - nums[lo]);
			if (nums[mid] < val) {
				lo = mid + 1;
			} else if (nums[mid] > val) {
				hi = mid -1;
			} else return mid;
		}
		if (nums[lo] == val) return lo;
		return -1;
	}

	public static void main(String[] args) {

		int[] values = {10, 20, 25, 35, 50, 70, 85, 100, 110, 120, 125};

		// Since 25 exists in the values array the interpolation search
		// returns that it has found 25 at the index 2
		System.out.println(interpolationSearch(values, 25));

		// 111 does not exist so we get -1 as an index value
		System.out.println(interpolationSearch(values, 111));
	}
}
