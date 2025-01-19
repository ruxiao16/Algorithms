package com.xiao.algorithms.recursive;

public class SumOddsRecursive {

	public static void main(String[] args) {
		int[] arr = {2, 5, 4, 7, 3};
		int result = sumOdds( arr);
		System.out.println(result);

		int[] oddOnlyArr = {1 , 7, 3};
		result = productEvens(oddOnlyArr);
		System.out.println(result);

		int[] oddAndEvenArr = {1 , 2, 3, 4, 5};
		result = productEvens(oddAndEvenArr);
		System.out.println(result);
	}

	public static int sumOdds(int[] arr) {
		return sumOdds(0, arr);
	}
	private static int sumOdds(int index, int[] arr) {
		// reach the end
		if (index == arr.length) {
			return 0;
		}

		int value = 0;
		if (arr[index] % 2 != 0) {
			value = arr[index];
		}

		return value + sumOdds(++index, arr);
	}

	private static Integer product;

	public static int productEvens(int[] arr) {
		product = productEvens(0, arr);
		// handles both when arr is empty or null
		if (product == null) {
			return 0;
		}
		return product;
	}
	private static Integer productEvens(int index, int[] arr) {
		// reach the end
		if (index == arr.length) {
			return null;
		}
		// we use index + 1 here because ++index will have impact on the unwinding part
		product = productEvens(index+1, arr);
		// recursion begins to unwind

		// this handles the situation when all entries are odd
		// then null is kept returning back to the caller.
		// act as if odd entries are skipped or product is propagated to the caller
		if (arr[index] % 2 != 0) {
			return product;
		}
		// Even case, we only have to handle when there is no product yet
		if (product == null) {
			return arr[index];
		}
		return arr[index] * product;
	}
}
