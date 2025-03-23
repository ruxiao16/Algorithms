package com.xiao.algorithms.leetcode.arrayandstring;

import java.util.Arrays;

public final class RemoveElementSolution {

	/*
		example
		[3, 2, 3, 2, 5, 3], removing 3
		Two indices, front and end. Front indice should point to the target value, if swap happens, we increment it looking for
		the next target value, the search termiantes when the two indices equal or cross each other

		end indice points to the next available slot, if swap happens, we decrement it looking for another indices with
		value not equal to the target value
		every time we do a swap.

		front = 0, end = 5
		[3, 2, 3, 2, 5, 3] -> decrement end because we need to find an available spot

		front = 0, end = 4
		swap nums[0] and nums[4] -> increment front and decrement end
		[5, 2, 3, 2, 3, 3]

		front = 1, end = 3 -> increment front
		[5, 2, 3, 2, 3, 3]

		front = 2, end = 3
		swap nums[2] and nums[3] -> increment front end decrement end
		[5, 2, 2, 3, 3, 3]

		front = 3, end = 2
		terminate
	 */

//	public int removeElement(int[] nums, int val) {
//		int front = 0;
//		int end = nums.length -1;
//
//		int numOfTargets = 0;
//
//		for (int num : nums) {
//			if (num == val) {
//				numOfTargets++;
//			}
//		}
//
//		if (numOfTargets != 0 && numOfTargets == nums.length) {
//			// everything is removed
//			return 0;
//		}
//		if (numOfTargets == 0) {
//			// nothing is removed
//			return nums.length;
//		}
//
//
//		if (nums.length == 1) {
//			if (nums[0] == val) return 0;
//			return 1;
//		}
//
//
//		while (front < end) {
//			if (nums[front] == val && nums[end] != val) {
//				int temp = nums[front];
//				nums[front] = nums[end];
//				nums[end] = temp;
//				front++;
//				end--;
//			} else if (nums[front] == val && nums[end] == val) {
//				end--;
//			} else if (nums[front] != val && nums[end] == val) {
//				front++;
//				end--;
//			} else {
//				front++;
//			}
//		}
//
//		System.out.println("front is " + front + " end is "+ end + " num of target is "+ numOfTargets);
//		return nums.length - numOfTargets;
//	}


	/*
		[3, 2, 3, 2, 5, 3]
	    i =0 j = 0, no swap, j++

        [3, 2, 3, 2, 5, 3]
		i = 0, j = 1 swap i++, j++

		[2, 3, 3, 2, 5, 3]
		i = 1, j = 2 no swap, j++

		[2, 3, 3, 2, 5, 3]
		i = 1, j = 3 swap i++, j++

		[2, 2, 3, 3, 5, 3]
		i = 2, j = 4 swap i++, j++

		[2, 2, 5, 3, 3, 3]
		i = 3, j = 5 no swap terminate


	 */
	public int removeElement(int[] nums, int val) {
		// this is essentially a two pointer method, both start from the beginning,
		// with one pointer always moving while the other only moves if swap happens
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != val) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
			}
		}
		return i;
	}

	public static void main(String[] args) {
		RemoveElementSolution solution = new RemoveElementSolution();
		int[] testArr1 = new int[]{3, 2, 3, 2, 5, 3};


		int result = solution.removeElement(testArr1, 3);
		System.out.println(Arrays.toString(testArr1));
		System.out.println(result);


		int[] testArr2 = new int[]{0,1,2,2,3,0,4,2};
		int result2 = solution.removeElement(testArr2, 2);
		System.out.println(Arrays.toString(testArr2));
		System.out.println(result2);

		int[] testArr3 = new int[]{2,2,3};
		int result3 = solution.removeElement(testArr3, 2);
		System.out.println(Arrays.toString(testArr3));
		System.out.println(result3);
	}
}
