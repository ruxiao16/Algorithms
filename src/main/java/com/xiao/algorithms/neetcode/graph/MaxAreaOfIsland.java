package com.xiao.algorithms.neetcode.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxAreaOfIsland {

	private int maxArea = 0;
	private int area = 0;
	private boolean[][] visited;
	private int numOfRows;
	private int numOfCols;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, 1, -1};

	public int maxAreaOfIsland(int[][] grid) {
		numOfRows = grid.length;
		numOfCols = grid[0].length;
		visited = new boolean[numOfRows][numOfCols];

		maxArea =0;

//		for (int i = 0; i < numOfRows; i++) {
//			for (int j = 0; j < numOfCols; j++) {
//				if (!visited[i][j] && grid[i][j] == 1) {
//					area = 0; // reset each island's area
//					dfs(i, j, grid);
//				}
//				// after reach dfs, update the max area
//				if (area > maxArea) {
//					maxArea = area;
//				}
//			}
//		}
		rowQueue = new ArrayDeque<>();
		colQueue = new ArrayDeque<>();

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfCols; j++) {
				area = 0;
				bfs(i, j, grid);
				if (area > maxArea) {
					maxArea = area;
				}
			}
 		}
		return maxArea;
	}


	private void dfs(int row, int col, int[][] grid) {
		if (row >= numOfRows || col >= numOfCols) return;
		if (row < 0 || col < 0) return;
		if (grid[row][col] == 0) return;
		if (visited[row][col]) return;

		//else a new island
		area++;
		visited[row][col] = true;
		dfs(row-1, col, grid);
		dfs(row+1, col, grid);
		dfs(row, col-1, grid);
		dfs(row, col+1, grid);
	}

	private Deque<Integer> rowQueue;
	private Deque<Integer> colQueue;

	private void bfs(int startRow, int startCol, int[][] grid) {
		// encounter water
		if (grid[startRow][startCol] == 0) {
			return;
		}

		if (visited[startRow][startCol]) {
			return;
		}

		//otherwise a new node with land mass
		visited[startRow][startCol] = true;
		area++;
		rowQueue.add(startRow);
		colQueue.add(startCol);

		while (!rowQueue.isEmpty()) {
			int row = rowQueue.pop();
			int col = colQueue.pop();
			int newRow;
			int newCol;

			for (int i = 0; i < 4; i++) {
				newRow = row + dr[i];
				newCol = col + dc[i];

				if (newRow < 0 || newCol <0) continue;
				if (newRow >= numOfRows || newCol >= numOfCols) continue;
				if (visited[newRow][newCol]) continue;
				if (grid[newRow][newCol] == 0) continue;

				// otherwise we found another land mass
				visited[newRow][newCol] = true;
				rowQueue.add(newRow);
				colQueue.add(newCol);
				area++;
			}
		}
	}


	public static void main(String[] args) {
		MaxAreaOfIsland driver = new MaxAreaOfIsland();

//		int[][] grid = {
//				{1, 1, 1, 1, 0},
//				{1, 1, 0, 1, 0},
//				{1, 1, 0, 0, 1},
//				{0, 0, 1, 0, 1}
//		};
//		System.out.println("max area is " + driver.maxAreaOfIsland(grid));
		int[][] grid1 = {
				{0,1,1,0,1},
				{1,0,1,0,1},
				{0,1,1,0,1},
				{0,1,0,0,1}
		};


		System.out.println("max area is " + driver.maxAreaOfIsland(grid1));
	}
}
