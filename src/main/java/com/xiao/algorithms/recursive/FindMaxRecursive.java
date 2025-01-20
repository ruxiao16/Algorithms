// We use a wrapper class to return multiple values
package com.xiao.algorithms.recursive;


public class FindMaxRecursive {

	public static void main(String[] args) {
		int[] arr = {2, 1, 3, -4, 2};
		findMax(arr);
		System.out.println("max value is "+ result.maxVal + " at index " + result.maxIndex);
	}


	private static class Result {
		Integer maxIndex;
		Integer maxVal;
		Result() {
			maxIndex = null;
			maxVal = null;
		}
	}

	private static Result result;

	private static Result findMax(int[] arr) {
		result = new Result(); // clear
		return findMax(0, arr);
	}
	private static Result findMax(int index, int[] arr) {
		if (index == arr.length) {
			return new Result();
		}

		findMax(index+1, arr);

		// unwinding
		// first real value after returning from the end
		if (result.maxIndex == null) {
			result.maxVal = arr[index];
			result.maxIndex = index;
		} else {
			if (arr[index] > result.maxVal) {
				result.maxVal = arr[index];
				result.maxIndex = index;
			}
		}

		return result;
	}
}
