package com.xiao.algorithms.neetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TopKElements {
	// taken [1,1,1,2,2,3,4,4] as an example,
	// we will have freq array of
	// indices -> 0    1     2      3    4    5  6   7   8
	//           [[], [3], [2,4],  [1],  [], [], [], [], []]
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		List<Integer>[] freq = new List[nums.length + 1]; // freq indice is the freq, and its value is a list of element with that freq

		for (int i = 0; i < freq.length; i++) {
			freq[i] = new ArrayList<>();
		}

		for (int n : nums) {
			count.put(n, count.getOrDefault(n, 0) + 1); // count the freq, key -> number, val-> freq
		}
		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			freq[entry.getValue()].add(entry.getKey());
		}

		int[] res = new int[k];
		int index = 0;

		// returning k elements, basically iterating thru each sub list, add all the elements up to k
		for (int i = freq.length -1; i > 0 && index < k; i--) {
			for (int n : freq[i]) {
				res[index++] = n;
				if (index == k) {
					return res;
				}
			}
		}

		return res;
	}


	public static void main(String[] args) {
		TopKElements solution = new TopKElements();
		System.out.println(Arrays.toString(solution.topKFrequent(new int[] {1, 1, 1, 2, 2, 3, 4, 4}, 3)));
	}
}
