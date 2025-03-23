package com.xiao.algorithms.leetcode.arrayandstring;

import java.util.Arrays;

public class MaxProfit {

	// time constraints
//	public int solution(int[] prices) {
//		int max = 0;
//		for (int i = 0; i < prices.length; i++) {
//			for (int j = i+1; j < prices.length; j++) {
//				int diff = prices[j] - prices[i];
//				if (diff > max) {
//					max = diff;
//				}
//			}
//		}
//		return max;
//	}

	public int solution(int[] prices) {
		int leastSoFar = Integer.MAX_VALUE;
		int overallProfit = 0;
		int profitIfSoldToday = 0;

		for (int i = 0; i < prices.length; i++) {
			// if we find a new buy value which is smaller, update the least so far
			if (prices[i] < leastSoFar) {
				leastSoFar = prices[i];
			}
			profitIfSoldToday = prices[i] - leastSoFar;
			if (profitIfSoldToday > overallProfit ) {
				overallProfit = profitIfSoldToday;
			}
		}
		return overallProfit;
	}


	public static void main(String[] args) {

		MaxProfit maxProfit = new MaxProfit();

		int[] testArr1 = new int[] {7,1,5,3,6,4};
		int result = maxProfit.solution(testArr1);
		System.out.println(Arrays.toString(testArr1));
		System.out.println(result);


		int[] testArr2 = new int[] {7,6,4,3,1};
		int result1 = maxProfit.solution(testArr2);
		System.out.println(Arrays.toString(testArr2));
		System.out.println(result1);
	}

}
