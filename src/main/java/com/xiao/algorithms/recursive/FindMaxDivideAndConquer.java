package com.xiao.algorithms.recursive;

public class FindMaxDivideAndConquer {

	public static void main(String[] args) {

		System.out.println(findMaxElement(new int[]{4,1,5,3}));
		System.out.println(findMaxElement(new int[]{}));
		System.out.println(findMaxElement(new int[]{4,1,99,5,3}));
		System.out.println(findMaxElement(new int[]{100,1,5,3,67,-23,82}));
	}


	public static Integer findMaxElement(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		return findMaxDivideAndConquer(0, arr.length-1, arr);
	}
	private static int findMaxDivideAndConquer(int start, int end, int[] arr) {
		// base case, as the call rewind, max(a, b) returns the current level max to the caller
		if (start == end) {
			return arr[start];
		}
		int mid = (start + end)/2;
		return Math.max(findMaxDivideAndConquer(start, mid, arr), findMaxDivideAndConquer(mid+1, end, arr));
	}
}
