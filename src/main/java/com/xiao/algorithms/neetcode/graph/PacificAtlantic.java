package com.xiao.algorithms.neetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlantic {
	private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private int M;
	private int N;
	// a brute force solution, will be to iterate thru every node, using bfs, until we reach the border c <0 || r < 0 for pacific
	// and reach the board c>=n || c >= m for atlantic, each bfs we check to see if both are reached, if yes, we stop transversing
	// if at the end of BFS, borders are not reached -> not possible. This will take (m*n) * (m*n) time, each node needs m*n

	// alternatively, we can start with the boarder cells, and reverse the transverse. we can probably keep a size 2 boolean arr to indicate
	// if the cell is flooded with pacific or/and atlantic water
	 public List<List<Integer>> pacificAtlantic(int[][] heights) {
		 M = heights.length;
		 N = heights[0].length;
		 boolean[][] pacific = new boolean[M][N];
		 boolean[][] atlantic = new boolean[M][N];

		 Queue<int[]> pacificQueue = new LinkedList<>();
		 Queue<int[]> atlanticQueue = new LinkedList<>();

		 // adding top and bottom border
		 for (int c = 0; c < N; c++) {
			pacificQueue.add(new int[]{0, c});
			atlanticQueue.add(new int[]{M-1, c});
		 }
		// adding left and right border, it is okay the corners ones are present in both queues as well
		// as being twice in the same queue.
		 for (int r = 0; r < M; r++) {
			pacificQueue.add(new int[]{r, 0});
			atlanticQueue.add(new int[]{r, N-1});
		 }

		 bfs(pacificQueue, pacific, heights);
		 bfs(atlanticQueue, atlantic, heights);

		 List<List<Integer>> res = new ArrayList<>();
		 for (int i = 0; i < M; i++) {
			 for (int j = 0; j < N; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					res.add(Arrays.asList(i, j));
				}
			 }
		 }
		 return res;
	 }

	 private void bfs(Queue<int[]> q, boolean[][] ocean, int[][] heights) {
		 while (!q.isEmpty()) {
			 int[] coordinates = q.poll();
			 int row = coordinates[0];
			 int col = coordinates[1];

			 ocean[row][col] = true; //flooded

			 for (int[] dir : dirs) {
				 int nextRow = row + dir[0];
				 int nextCol = col + dir[1];

				 if (nextRow < 0 || nextCol < 0 || nextRow >= M || nextCol >= N
						 || heights[nextRow][nextCol] < heights[row][col] //
						 || ocean[nextRow][nextCol] // already visited
				 ) {
					 continue;
				 }
				 q.add(new int[]{nextRow, nextCol});
			 }
		 }
	 }

	 // dfs is very similar, we just start from top bottom border dfs(top boarder), dfs(bottom border)
	// and start from left border and right border, two for loops.
 	//private void dfs(int r, int c, boolean[][] ocean, int[][] heights)

	 public static void main(String[] args) {
		 PacificAtlantic driver = new PacificAtlantic();
		 int[][] heights = {
				 {4, 2, 7, 3, 4},
				 {7, 4, 6, 4, 7},
				 {6, 3, 5, 3, 6}
		 };

		 List<List<Integer>> res =  driver.pacificAtlantic(heights);

		 for (List<Integer> eachResult : res) {
			 for (Integer num : eachResult) {
				 System.out.print(num + " ");
			 }
			 System.out.println();
		 }

	 }
}
