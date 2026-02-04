package com.xiao.algorithms.neetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

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
	private Queue<int[]> queue;
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
		numberOfIsland = 0;
		//bfs
		queue = new LinkedList<>();

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfCols; j++) {
				if (!visited[i][j] && grid[i][j] == '1') {
					bfs(i, j, grid);
					numberOfIsland++;
				}
			}
		}

		//dfs
//		for (int i = 0; i < numOfRows; i++) {
//			for (int j = 0; j < numOfCols; j++) {
//				if (grid[i][j] == '1' && !visited[i][j]) {
//					numberOfIsland++;
//					dfs(i, j, grid);
//				}
//			}
//		}
		return numberOfIsland;
	}

	private void bfs(int startRow, int startCol, char[][] grid) {
		visited[startRow][startCol] = true;

		queue.add(new int[]{startRow, startCol});

		while(!queue.isEmpty()) {
			int[] coordinates = queue.poll();
			int newRow;
			int newCol;

			for (int i = 0; i < 4; i++) {
				newRow = coordinates[0] + dr[i];
				newCol = coordinates[1] + dc[i];

				if(newRow <0 || newCol < 0 || newRow >= numOfRows || newCol >= numOfCols) continue;
				if(grid[newRow][newCol] == '0') continue;
				if(visited[newRow][newCol]) continue;

				visited[newRow][newCol] = true;
				queue.add(new int[]{newRow, newCol});
			}
		}
	}

	// the purpose of dfs is just to mark nodes as visited
	private void dfs(int row, int col, char[][] grid) {
		if (row < 0 || col < 0) return; // reach beyond the border
		if (row >= numOfRows || col >= numOfCols) return;

		if (grid[row][col] == '0') {
			return;
		}

		if (visited[row][col]) {
			return;
		}

		visited[row][col] = true;
		dfs(row -1, col, grid);
		dfs(row +1, col, grid);
		dfs(row, col -1, grid);
		dfs(row, col +1, grid);
	}

	public static void main(String[] args) {
		NumberOfIslands driver = new NumberOfIslands();

		char[][] grid = {
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','1'},
				{'0','0','1','0','1'}
		};

		char[][] grid1 = {
				{'0','1','1','1','0'},
				{'0','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}
		};

		System.out.println("num of islands are " + driver.numIslands(grid));

		System.out.println("num of islands are " + driver.numIslands(grid1));

	}
}
