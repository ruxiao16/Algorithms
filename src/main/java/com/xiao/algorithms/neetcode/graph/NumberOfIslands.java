package com.xiao.algorithms.neetcode.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfIslands {

	// Input: grid = [
	//    ["0","1","1","1","0"],
	//    ["0","1","0","1","0"],
	//    ["1","1","0","0","0"],
	//    ["0","0","0","0","0"]
	//  ]
	//Output: 1

	// idea, use bfs, mark nodes as they are visited

	private boolean[][] visited;
	private Deque<Integer> rowQueue;
	private Deque<Integer> colQueue;
	// represent up down right and left
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, 1, -1};

	int numOfRows;
	int numOfCols;
	int numberOfIsland = 0;

	public int numIslands(char[][] grid) {
		numOfRows = grid.length;
		numOfCols = grid[0].length;

		visited = new boolean[numOfRows][numOfCols];
		rowQueue = new ArrayDeque<>();
		colQueue = new ArrayDeque<>();

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfCols; j++) {
				bfs(i, j, grid);
			}
		}
		return numberOfIsland;
	}

	private void bfs(int startRow, int startCol, char[][] grid) {
		if (grid[startRow][startCol] == '0') {
			return;
		}

		if (visited[startRow][startCol]){
			return;
		}

		// otherwise a new node with land mass
		visited[startRow][startCol] = true;
		rowQueue.add(startRow);
		colQueue.add(startCol);

		while(!rowQueue.isEmpty()) {
			int row = rowQueue.pop();
			int col = colQueue.pop();
			int newRow;
			int newCol;

			for (int i = 0; i < 4; i++) {
				newRow = row + dr[i];
				newCol = col + dc[i];

				if(newRow <0 || newCol < 0) continue;
				if(newRow >= numOfRows || newCol >= numOfCols) continue;
				if(grid[newRow][newCol] == '0') continue; // this is probably unnecessary
				if(visited[newRow][newCol]) continue;

				visited[newRow][newCol] = true;
				rowQueue.add(newRow);
				colQueue.add(newCol);
			}
		}
		numberOfIsland++;
	}

	public static void main(String[] args) {
		char[][] grid = {
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}
		};

		char[][] grid1 = {
				{'0','1','1','1','0'},
				{'0','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}
		};


		NumberOfIslands driver = new NumberOfIslands();
		System.out.println("num of islands are " + driver.numIslands(grid1));

	}
}
