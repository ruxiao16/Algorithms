package com.xiao.algorithms.recursive;

import java.util.Arrays;

public class MergeSort {



	public static void main(String[] args) {
		int[] testArr = new int[] {9, 7, 5, 6, 2, 4};
		System.out.println(Arrays.toString(mergeSort(testArr)));
	}

	private static int[] mergeSort(int[] arr) {
		return divide(0, arr.length -1, arr);
	}

	/**
				         [0, 1, 2, 3, 4, 5]
				          /           \
					   [0, 1, 2]     [3, 4, 5]
	                   /    \           /  \
	               [0,1]    [2]      [3,4]  [5]
	                 / \                 / \
	              [0]  [1]             [3]  [4]

	 */
	private static int[] divide(int lo, int hi, int[] arr) {
		if (lo == hi) {
			return new int[] {arr[lo]};
		}

		int mid = (lo + hi)/2;
		int[] left = divide(lo, mid, arr);
		int[] right = divide(mid+1, hi, arr);

		return merge(left, right);
	}

	// O(n)
	private static int[] merge(int[] left, int[] right) {
		//left and right is already sorted, at lowest call stack they are all one element per arr
		int l = 0;
		int r = 0;
		int[] result = new int[left.length + right.length];
		int i = 0;
		while (l < left.length || r < right.length) {
			// reach the end of left array, add rest of the right array
			if (l == left.length) {
				result[i] = right[r];
				r++;
			} else if (r == right.length) {
				result[i] = left[l];
				l++;
			} else if (left[l] <= right[r]) {
				result[i] = left[l];
				l++;
			} else {
				result[i] = right[r];
				r++;
			}
			i++;
		}
		return result;
	}
}
