package com.xiao.algorithms.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridSearch {

	private static class Candidate {
		int startColIdx;
		int endColIdx;

		public Candidate(int startColIdx, int endColIdx) {
			this.startColIdx = startColIdx;
			this.endColIdx = endColIdx;
		}
	}
	public static String gridSearch(List<String> G, List<String> P) {
		if (G == null || G.isEmpty() || P == null || P.isEmpty()) {
			return "NO";
		}

		String targetFirstRow = P.get(0);
		int rowsToSearch = P.size() - 1;

		for (int i = 0; i < G.size(); i++) {
			// identify all potential candidates within a row
			List<Candidate> candidates = findAllCandidates(G.get(i), targetFirstRow);

			// found the first row
			for (Candidate candidate: candidates) {
				int targetRow = 1;
				int nextRowToCheck = i+1;
				int endColIdx = candidate.endColIdx;
				int startColIdx = candidate.startColIdx;
				int endRowToCheck = nextRowToCheck + rowsToSearch;
				int matchedRows = 1;

				// check subsequent rows, increase match count for matched rows
				for (; nextRowToCheck < endRowToCheck && nextRowToCheck != G.size(); nextRowToCheck++) {
					String currentRow = G.get(nextRowToCheck);
					if (currentRow.substring(startColIdx, endColIdx).equals(P.get(targetRow))) {
						matchedRows++;
					}
					targetRow++;
				}

				if (matchedRows == P.size()) {
					return "YES";
				}
			}
		}

		return "NO";
	}

	// guess we can just use a List<List<int>> to represent but a custom type is also fine
	private static List<Candidate> findAllCandidates(String currentRow, String targetStr) {
		List<Candidate> candidates = new ArrayList<>();
		int index = currentRow.indexOf(targetStr);
		while (index != -1) {
			candidates.add(new Candidate(index, index + targetStr.length()));
			index = currentRow.indexOf(targetStr, index+1);
		}
		return candidates;
	}


	public static void main(String[] args) {
		List<String> biggerList1 = Arrays.asList(
			"7283455864", "6731158619", "8988242643", "3830589324", "2229505813",
				"5633845374", "6473530293", "7053106601", "0834282956", "4607924137"
		);
		List<String> smallerList1 = Arrays.asList(
				"9505", "3845", "3530"
		);

		System.out.println(gridSearch(biggerList1, smallerList1));

		List<String> biggerList2 = Arrays.asList(
				"400453592126560", "114213133098692", "474386082879648", "522356951189169", "887109450487496",
				"252802633388782", "502771484966748", "075975207693780", "511799789562806", "404007454272504",
				"549043809916080", "962410809534811", "445893523733475", "768705303214174", "650629270887160"
		);
		List<String> smallerList2 = Arrays.asList(
				"99", "99"
		);
		System.out.println(gridSearch(biggerList2, smallerList2));

		List<String> biggerList3 = Arrays.asList(
				"123412", "561212", "123634", "781288"
		);
		List<String> smallerList3 = Arrays.asList(
				"12", "34"
		);

		System.out.println(gridSearch(biggerList3, smallerList3));
	}
}
