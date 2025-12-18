package com.xiao.algorithms.neetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottingFruit {

	// just need one pass bfs,
	// we do need to process only the current nodes
	// after every level, min+1
	// check the grid at the end to see if there is any fresh fruit left
	public int orangesRotting(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int minutes = 0;
		int freshCount = 0;

		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 2) {
					queue.add(new int[]{i, j});
				} else if (grid[i][j] == 1) {
					freshCount++;
				}
			}
		}

//		if (queue.isEmpty()) {
//			return -1; // no rotting orange to begin with
//		}

		// Edge Case: If no fresh oranges exist, 0 minutes required
		if (freshCount == 0) {
			return 0;
		}

		// top, left, bot,right
		int[][] dirs = {{-1,0}, {0,-1}, {1, 0}, {0, 1}};

		while (!queue.isEmpty()) {
			// # of nodes in current level
			int numOfNodes = queue.size();
			boolean infectedSomething = false; // Track if this level actually did anything

			for (int i = 0; i < numOfNodes; i++) {
				int[] coord = queue.poll();
				int row = coord[0];
				int col = coord[1];
				for (int[] dir : dirs) {
					int nextRow = row + dir[0];
					int nextCol = col + dir[1];

					if (nextRow < 0 || nextCol < 0 || nextRow >= m || nextCol >= n
							|| grid[nextRow][nextCol] != 1)  {
						continue; // we use 2 to indicate we have visited them as well beside being rotten
					}

					grid[nextRow][nextCol] = 2; // mark it as rottern, more importantly mark it as visited
					queue.add(new int[] {nextRow, nextCol});
					freshCount--;
					infectedSomething = true;
				}
			}
			// last round we may not have infected anything
			if (infectedSomething) {
				minutes++;
			}
		}


		return freshCount == 0 ? minutes : -1;
	}


	public static void main(String[] args) {
		RottingFruit driver = new RottingFruit();

		int[][] grid1 = {
				{1,1,0},
				{0,1,1},
				{0,1,2}
		};

		System.out.println(" time it takes " + driver.orangesRotting(grid1));
	}
}
