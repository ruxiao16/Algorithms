package com.xiao.algorithms.neetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

public class IslandsAndTreasure {
	private int numOfCols;
	private int numOfRows;



	private int[] dr = new int[]{-1, 1, 0, 0};
	private int[] dc = new int[]{0, 0, 1, -1};


	public void islandsAndTreasure(int[][] grid) {
		numOfRows = grid.length;
		numOfCols = grid[0].length;

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfCols; j++) {

				if (grid[i][j] == -1) {
					continue;
				} else if (grid[i][j] == 0) {
					continue;
				} else {
					grid[i][j] = bfs(i, j, grid);
				}
			}
		}
	}

	// this is a (m*n) complexity, and we do it over every single node, then it is
	// (m*n) ^2
	private int bfs(int startRow, int startCol, int[][] grid) {
		int distance = 0;
		Queue<Integer> rowQueue = new LinkedList<>();
		Queue<Integer> colQueue = new LinkedList<>();
		boolean[][] isVisited = new boolean[numOfRows][numOfCols];

		isVisited[startRow][startCol] = true;
		rowQueue.add(startRow);
		colQueue.add(startCol);

		int currentLvlNodes = 1;

		while (!rowQueue.isEmpty()) {
			int size = rowQueue.size();

			// iterate only through the nodes currently in this level
			for (int i =0; i< size; i++) {
				Integer currRow = rowQueue.poll();
				Integer currCol = colQueue.poll();

				if (grid[currRow][currCol] == 0) {
					return distance;
				}

				for (int k = 0; k < 4; k++) {
					int nextRow = currRow + dr[k];
					int nextCol = currCol + dc[k];

					if (nextCol < 0 || nextRow < 0) continue;
					if (nextRow >= numOfRows || nextCol >= numOfCols) continue;
					if (isVisited[nextRow][nextCol]) continue;
					if (grid[nextRow][nextCol] == -1) continue;

					rowQueue.add(nextRow);
					colQueue.add(nextCol);
					isVisited[nextRow][nextCol] = true;
				}
			}
			// we should only add distance once we finish exploring the entire level
			distance++;
		}
		// at the end, couldnt reach treasure from the starting pos given
		return 2147483647;
	}

	// multi src approach, only one pass is needed so m*n. the difference here is that
	// we start from treasure 1, mark its neighbors and place them in the queue, record distance.
	// Then we start from treasure 2, search its neighbors, record distance.
	// we then proceed with neigbors of treasure 1, search their neighbors place them in the queue, record dist
	// this works because the treasure (src)  always manage to reach the unexplored node
	// that is closest to it first -> it travels the least to get there. and later on when another source gets there, it is already marked.
	public void islandsAndTreasureMultiSrc(int[][] grid) {
		Queue<int[]> queue = new LinkedList<>();
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i ++) {
			for (int j = 0; j < n; j ++) {
				if (grid[i][j] == 0) {
					queue.add(new int[]{i, j});
				}
			}
		}

		if (queue.isEmpty()) return; //no treasure

		int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
		while (!queue.isEmpty()) {
			int[] node  = queue.poll();
			int row = node[0];
			int col = node[1];
			for (int[] dir : dirs) {
				int r = row + dir[0];
				int c = col + dir[1];
				// grid[r][c] != Integer.MAX_VALUE this guard against a node that is either -1
				// or visisted already, it will be a different number other than MAX_VALUE
				if (r >= m || c >= n || r < 0 || c <0 || grid[r][c] != Integer.MAX_VALUE) {
					continue;
				}
				queue.add(new int[] {r, c});
				grid[r][c] = grid[row][col] + 1;
			}

		}
	}



	// solving it using bfs, bfs will find us the shortest distance to the treasure if it is reachable
	public static void main(String[] args) {
		int[][] grid1 = {
				{2147483647,-1,0,2147483647},
				{2147483647,2147483647,2147483647,-1},
				{2147483647,-1,2147483647,-1},
				{0,-1,2147483647,2147483647}
		};

		IslandsAndTreasure driver = new IslandsAndTreasure();
//		driver.islandsAndTreasure(grid1);

		driver.islandsAndTreasureMultiSrc(grid1);

		for (int i = 0; i < grid1.length; i++){
			for (int j = 0; j < grid1[0].length; j++) {
				System.out.print(grid1[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
