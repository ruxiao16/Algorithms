package com.xiao.algorithms.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class Investment {
	public static long maxValue(int n, List<List<Integer>> rounds) {
		// Write your code here
      /*
      [1, 2, 100]
      [2, 5, 100]
      [3, 4, 100]
      so looks like n dictates number of columns while rounds.size() dictates number of rows of the
      */
		if (n == 0) {
			return 0;
		}
		if (rounds == null || rounds.size() == 0) {
			return 0;
		}

		// using a list<list> may be easier here
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> prevRow = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			prevRow.add(0);
		}

		for (List<Integer> round: rounds){
			int startIdx = round.get(0);
			int endIdx = round.get(1);
			int amount = round.get(2);
			List<Integer> newRow = new ArrayList<>(prevRow);

			for (int i = startIdx-1; i < endIdx; i++) {
				newRow.set(i, newRow.get(i) + amount);
			}
			result.add(newRow);
			prevRow = newRow;
		}

//		for(List<Integer> row : result){
//			for(Integer val : row){
//				System.out.print(val + " ");
//			}
//			System.out.println();
//		}

		return result.stream()
				.flatMap(List::stream)
				.max(Integer::compareTo)
				.orElse(0);
	}



	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(List.of(1,2,10));
		input.add(List.of(2,4,5));
		input.add(List.of(3,5,12));

		System.out.println("max value is " + maxValue(5, input));

		List<List<Integer>> input1 = new ArrayList<>();
		input1.add(List.of(2,3,603));
		input1.add(List.of(1,1,286));
		input1.add(List.of(4,4,882));
		System.out.println("max value is " + maxValue(4, input1));
	}
}
