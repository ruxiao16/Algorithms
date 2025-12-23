package com.xiao.algorithms.neetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {
	private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	int ROWS;
	int COLS;


	public void solve(char[][] board) {
		ROWS = board.length;
		COLS = board[0].length;

		capture(board);

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (board[r][c] == 'T') {
					board[r][c] = 'O';
				} else {
					board[r][c] = 'X';
				}
			}
		}
	}

	// the observation is that regions of zero that is not connected to a border zero will be surrounded.

	// So we do bfs from border zeros and mark the next zero they encounter as "T", including the starting ones.
	// The trick here is that initially all neighbors of starting zeros are pushed in, but in the next iteration,
	// only the zero neighbors are processed.

	// Then at the end we transverse the matric to flip back those T's to 0 and rest as X
	private void capture(char[][] board) {
		// init a queue and push all boarder cells that contain 'O'
		Queue<int[]> q = new LinkedList<>();
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (r == 0 || r == ROWS-1 || c == 0 || c == COLS-1
					&& board[r][c] == 'O') {
					q.offer(new int[]{r,c});
				}
			}
		}

		while (!q.isEmpty()) {
			int[] coord = q.poll();
			int r = coord[0], c = coord[1];

			// only zero's neighbors are pushing in
			if (board[r][c] == 'O') {
				board[r][c] = 'T';

				for (int[] dir : dirs) {
					int nr = r + dir[0], nc = c + dir[1];

					if (nr >= 0 && nc >=0 && nr < ROWS && nc < COLS) {
						q.add(new int[]{nr, nc});
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		SurroundedRegions driver = new SurroundedRegions();
		char[][] board = {
				{'X', 'X', 'X', 'X'},
				{'X', 'O', 'O', 'X'},
				{'X', 'O', 'O', 'X'},
				{'X', 'X', 'X', 'O'},
		};

		driver.solve(board);

		for (char[] row : board) {
			for (char eachChar : row) {
				System.out.print(eachChar + ""+ " ");
			}
			System.out.println();
		}
	}
}
