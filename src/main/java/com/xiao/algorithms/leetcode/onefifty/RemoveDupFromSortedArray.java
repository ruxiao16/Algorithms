package com.xiao.algorithms.leetcode.onefifty;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RemoveDupFromSortedArray {

	public int removeDuplicates(int[] nums) {
		// treemap gurantees the natural order of the key, hashmap doesnt do that
		TreeMap<Integer, Integer> lookupMap = new TreeMap<>();

		for (int num : nums) {
			lookupMap.merge(num, 1, Integer::sum);
		}

		int arrCounter = 0;
		for (Integer key : lookupMap.keySet()) {
			nums[arrCounter++] = key;
		}

		return lookupMap.keySet().size();
	}

	public static void main(String[] args) {
		RemoveDupFromSortedArray sortedArray = new RemoveDupFromSortedArray();
		int[] testArr1 = new int[] {0,0,1,1,1,2,2,3,3,4};


		int numOfItems = sortedArray.removeDuplicates(testArr1);
//		for (int i = 0; i < numOfItems; i++) {
//			System.out.print(testArr1[i]);
//			System.out.print(" ");
//		}

		String result = Arrays.stream(testArr1)
						.limit(numOfItems)
				        .mapToObj(String::valueOf)
				 		.collect(Collectors.joining( " "));
		System.out.println(result);
	}
}
