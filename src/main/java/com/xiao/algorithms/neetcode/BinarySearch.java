package com.xiao.algorithms.neetcode;

public class BinarySearch {

	public int binarySearch(int[] nums, int target) {

		int left = 0;
		int right = nums.length - 1;

		while (true) {
			// there can be a potential bug here in java because of int overflow, to resolve that
			// we can do l + ((r-l)/2) -> no overflow
			int mid = (left + right)/2;

			if (target == nums[mid]) {
				return mid;
			}

			if (target > nums[mid]) {
				left = mid + 1; // excluding
				if (left > right) {
					return -1;
				}
			}
			else {
				right = mid - 1; // including
				if (right < left) {
					return -1;
				}
			}
		}
	}


	public int binarySearch1(int[] nums, int target) {
		int l = 0, r = nums.length - 1;

		while (l <= r) {
			int mid  = l + ((r-l)/2);
			if (nums[mid] > target) {
				r = mid - 1;
			} else if (nums[mid] < target) {
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public int binarySearchRecursive(int l, int r, int[] nums, int target) {
		if (l > r) return -1;
		int mid  = l + (r-l)/2;

		if (nums[mid] == target) return mid;

		return (nums[mid] < target) ?
				binarySearchRecursive(mid +1, r, nums, target):
				binarySearchRecursive(l, mid -1, nums, target);
	}

	public int searchHelper(int[] nums, int target) {
		return binarySearchRecursive(0, nums.length-1, nums, target);
	}


	public static void main(String[] args) {
		BinarySearch driver = new BinarySearch();

		int[] testArr1 = new int[] {1, 2, 3, 4, 5};

		System.out.println(driver.binarySearch(testArr1, 5));

		System.out.println(driver.binarySearch(testArr1, 10));
	}
}
