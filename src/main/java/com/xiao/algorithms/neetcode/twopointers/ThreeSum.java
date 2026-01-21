package com.xiao.algorithms.neetcode.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

	// brute force, the important thing is to first sort, then use set to filter out duplicate ones
	//[-1,0,1,2,-1,-4] -> [-4,-1,-1,0,1,2]
	public List<List<Integer>> threeSumBruteForce(int[] nums) {
		Arrays.sort(nums);
		Set<List<Integer>> res = new HashSet<>();

		for(int i = 0; i < nums.length; i ++) {
			for (int j = i+1; j < nums.length; j++) {
				for (int k = j+1; k < nums.length; k++) {
					if (nums[i]+ nums[j]+ nums[k] == 0) {
						res.add(Arrays.asList(nums[i],nums[j], nums[k]));
					}
				}
			}
		}

		return new ArrayList<>(res);
	}

	// two pointer solution -> sort (n)log(n), two pointers -> n^2 nlogn + n^2 = n^2
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < nums.length-2; i++) {
			// we sort the array already, if nums[i] is positive, it is not possible to have a sum zero with rest of the array;
			if (nums[i] > 0) break;
			// avoid the dupes as the combination is already found
			if (i > 0 && nums[i] == nums[i-1]) continue;

			int target = -nums[i];
			int left = i +1;
			int right = nums.length-1;

			while (left < right) {
				if (nums[left] + nums[right] < target) {
					left ++;
				} else if (nums[left] +nums[right] > target) {
					right --;
				} else {
					// we found the target
					res.add(Arrays.asList(nums[i], nums[left], nums[right]));
					left++;
					right--;
					while (left < right && nums[left] == nums[left-1]) {
						left++; // skip the duplicate combo for a number that is in the middle
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		ThreeSum driver = new ThreeSum();
		int[] nums = new int[] { -1, 0, 1, 2, -1, -4};

		List<List<Integer>> res = driver.threeSum(nums);
		for (List<Integer> each : res) {
			for (Integer eachNum: each) {
				System.out.print(eachNum +" ");
			}
			System.out.println();
		}
	}
}
