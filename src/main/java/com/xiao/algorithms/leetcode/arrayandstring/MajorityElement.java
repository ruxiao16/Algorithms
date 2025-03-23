package com.xiao.algorithms.leetcode.arrayandstring;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
//	public int solution(int[] nums) {
//		if (nums.length == 1) {
//			return nums[0];
//		}
//
//		Map<Integer, Integer> countMap = new HashMap<>();
//		for (int num : nums) {
//			countMap.merge(num, 1, Integer::sum);
//		}
//
//		return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
//	}

	public int solution(int[] nums) {
		// easier solution because requirement is that majority item is more than n/2
		// so we just sort the array and take the middle element
		Arrays.sort(nums);
		return nums[nums.length/2];
	}

	public static void main(String[] args) {
		MajorityElement me = new MajorityElement();

		System.out.println(me.solution(new int[]{2,2,1,1,1,2,}));
	}
}
