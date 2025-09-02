package com.xiao.algorithms.neetcode.graph;

public class MaxAreaOfIsland {

	private int maxArea = 0;
	private int area = 0;
	private boolean[][] visited;
	private int numOfRows;
	private int numOfCols;

	public int maxAreaOfIsland(int[][] grid) {
		numOfRows = grid.length;
		numOfCols = grid[0].length;
		visited = new boolean[numOfRows][numOfCols];

		maxArea =0;

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfCols; j++) {
				if (!visited[i][j] && grid[i][j] == 1) {
					area = 0; // reset each island's area
					dfs(i, j, grid);
				}
				// after reach dfs, update the max area
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
