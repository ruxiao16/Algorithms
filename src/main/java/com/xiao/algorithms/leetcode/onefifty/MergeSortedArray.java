package com.xiao.algorithms.leetcode.onefifty;

import java.util.Arrays;

public class MergeSortedArray {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int p1 = m - 1;
		int p2 = n - 1;
		int pMerge = m + n - 1;

		while (p2 >= 0) {
			if (p1 >= 0 && nums1[p1] > nums2[p2]) {
				nums1[pMerge--] = nums1[p1--];
			} else {
				nums1[pMerge--] = nums2[p2--];
			}
		}
	}


	public static void main(String[] args) {
		MergeSortedArray solution = new MergeSortedArray();

		int[] nums1 = new int[] {2,5,6,0,0,0};
		int[] nums2 = new int[] {1,2,3};
		solution.merge(nums1, 3, nums2, 3);

		System.out.println(Arrays.toString(nums1));
//
//		int[] nums3 = new int[] {1,2,3,0,0,0};
//		int[] nums4 = new int[] {2,5,6};
//		solution.merge(nums3, 3, nums4, 3);
//		System.out.println(Arrays.toString(nums3));


		int[] nums5 = new int[] {0};
		int[] nums6 = new int[] {1};
		solution.merge(nums5, 0, nums6, 1);
		System.out.println(Arrays.toString(nums5));
	}

}
