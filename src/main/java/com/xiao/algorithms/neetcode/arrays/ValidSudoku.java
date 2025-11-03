package com.xiao.algorithms.neetcode.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku {

	public boolean isValidSudoku(char[][] board) {
		Map<Integer, Set<Character>> cols = new HashMap<>();
		Map<Integer, Set<Character>> rows = new HashMap<>();
		Map<String, Set<Character>> squares = new HashMap<>();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (board[r][c] == '.') continue;

				// the trick is that each square container 9 units, and they can all be mapped to one square,
				// -> there are 9 squares in total
				String squareKey = (r/3) + "," + (c/3);

				// computeIfAbsent, if the key does not exist, a new HashSet is instantiated and put in the map.
				// Then the value is stored in their corresponding row, column, and squares Map.
				// Else return the existing HashSet at this row and check to see if the number
				// is already in the set.
				// Basically, we have three trackers (maps) that check to see if any of the number is already present in that
				// row, col, or square
				if (rows.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c]) ||
						cols.computeIfAbsent(c, k -> new HashSet<>()).contains(board[r][c]) ||
						squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(board[r][c])
				) {
					return false;
				}

				rows.get(r).add(board[r][c]);
				cols.get(r).add(board[r][c]);
				squares.get(squareKey).add(board[r][c]);
			}
		}
		return true;
	}
}
