package com.xiao.algorithms.neetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopKElements {
	// taken [1,1,1,2,2,3,4,4] as an example,
	// we will have freq array of
	// indices -> 0    1     2      3
	//           [[], [3], [2,4],  [1]]
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		//we need to add 1 here is that say we have an input of
		//[7, 7], we need freq to contain 3 indices (0, 1, 2) to count all freq, having two elements only give us
		// [0, 1] which is not enough
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

		// start with the high freq first
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

	public int[] topKFrequentAgain(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		// array of lists,
		List<Integer>[] freq = new List[nums.length+1];
		for (int i = 0; i < nums.length; i++) {
			freq[i] = new ArrayList<>();
		}

		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			freq[entry.getValue()].add(entry.getKey());
		}

		int[] res = new int[k];
		int idx = 0;

		// start with the highest freq first
		for (int i = freq.length -1; i > 0 && idx < k; i--) {
			for (int n : freq[i]) {
				res[idx++] = n;
				if (idx == k) {
					return res;
				}
			}

		}
		return res;
	}

	public int[] topKFrequentSort(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();

		// count using map
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0)+1);
		}

		// sort with array list<int[]>
		List<int[]> arr = new ArrayList<>();
		for (Map.Entry<Integer, Integer > entry : count.entrySet()) {
			arr.add(new int[] {entry.getValue(), entry.getKey()}); // freq is the first element in the int[]
		}
		arr.sort((a, b) -> b[0] - a[0]);


		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] =arr.get(i)[1];
		}

		return res;
	}

	public static void main(String[] args) {
		TopKElements solution = new TopKElements();
		System.out.println(Arrays.toString(solution.topKFrequent(new int[] {1, 1, 1, 2, 2, 3, 4, 4}, 3)));

		System.out.println(Arrays.toString(solution.topKFrequentSort(new int[] {1, 1, 1, 2, 2, 3, 4, 4}, 2)));
	}
}
