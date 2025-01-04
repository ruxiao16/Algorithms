/*
	 r (row), c (column)
	 given (r,c)
	 left -> west  (r, c-1)
	 right -> east (r, c+1)
	 up -> north (r-1, c)
	 down -> south (r+1, c)
	 then direction vectors for north, south, east and west can be defined as
	 dr = [-1, +1, 0, 0]
	 dc = [0, 0, +1, -1]

	 then to move from north in respect of (r,c), it can be done with
	 for (i = 0; i < 4; i++);
		rr = r + dr[i]
		cc = c + dc[i]
		# skip invalid cells, Assume R and C for the # of rows and columns
		if rr < 0 or cc < 0: continue
		if rr >= R or cc >=C: continue
	 */
package com.xiao.algorithms.graphtheory;

import java.util.ArrayDeque;
import java.util.Deque;

public class BfsShortestPathGrid {
	private final static char START = 'S';
	private final static char NO_PASS = '#';
	private final static char CAN_PASS = '.';
	private final static char EXIT = 'E';

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, 1, -1};
	private char[][] maze;
	private int numOfRows, numOfCols;
	private int startingRow, startingCol;
	// use two queues to store the row, col instead of a single
	// queue with a wrapper object
	private Deque<Integer> rowQueue;
	private Deque<Integer> colQueue;

	private boolean[][] visited;
	int nodesLeftInLayer = 1;
	int nodesInNextLayer = 0;

	BfsShortestPathGrid(char[][] maze, int startingRow, int startingCol) {
		numOfRows = maze.length;
		if (numOfRows == 0) {
			throw new IllegalArgumentException("invalid maze");
		}
		numOfCols = maze[0].length;
		if (numOfCols == 0 ) {
			throw new IllegalArgumentException("invalid maze");
		}
		this.maze = maze;
		this.startingCol = startingCol;
		this.startingRow = startingRow;
		rowQueue = new ArrayDeque<>();
		colQueue = new ArrayDeque<>();
		visited = new boolean[numOfRows][numOfCols];
	}



	private int solve() {
		boolean reachedEnd = false;
		int numOfMoves = 0;

		rowQueue.offer(startingRow);
		colQueue.offer(startingCol);
		visited[startingRow][startingCol] = true;

		while (!rowQueue.isEmpty()) { // or colQueue > 0
			int row = rowQueue.pop();
			int col = colQueue.pop();

			if (maze[row][col] == EXIT) {
				reachedEnd = true;
				break;
			}
			exploreNeighbors(row, col);
			nodesLeftInLayer--;
			if (nodesLeftInLayer == 0) {
				// we use nodesLeftInLayer and NodesInNextLayer to keep track of
				// number of moves. in fact, number of move corresponds to one layer
				// being fully explored.
				nodesLeftInLayer = nodesInNextLayer;
				nodesInNextLayer = 0;
				numOfMoves++;
			}
		}
		if (reachedEnd) return numOfMoves;
		return -1;
	}

	private void exploreNeighbors(int row, int col) {
		int newRow;
		int newCol;
		for (int i = 0; i < 4; i++) {
			newRow = row + dr[i];
			newCol = col + dc[i];
			//reach the border
			if (newRow < 0 || newCol < 0) continue;
			if (newRow >= numOfRows || newCol >= numOfCols) continue;

			if (visited[newRow][newCol]) continue; // already visited
			if (maze[newRow][newCol] == NO_PASS) continue; // blockage

			rowQueue.offer(newRow);
			colQueue.offer(newCol);
			visited[newRow][newCol] = true;
			nodesInNextLayer ++;
		}
	}
	public static void main(String[] args) {
		char[][] maze = generateMap();
		BfsShortestPathGrid solver = new BfsShortestPathGrid(maze, 0, 2);
		int moveCnt = solver.solve();
		System.out.println("number of moves " + moveCnt);
	}
	private static char[][] generateMap() {
		char[][] arr = new char[5][7];
		arr[0][0] = CAN_PASS;
		arr[0][1] = CAN_PASS;
		arr[0][2] = START;
		arr[0][3] = NO_PASS;
		arr[0][4] = CAN_PASS;
		arr[0][5] = CAN_PASS;
		arr[0][6] = CAN_PASS;

		arr[1][0] = CAN_PASS;
		arr[1][1] = NO_PASS;
		arr[1][2] = CAN_PASS;
		arr[1][3] = CAN_PASS;
		arr[1][4] = CAN_PASS;
		arr[1][5] = NO_PASS;
		arr[1][6] = CAN_PASS;

		arr[2][0] = CAN_PASS;
		arr[2][1] = NO_PASS;
		arr[2][2] = CAN_PASS;
		arr[2][3] = CAN_PASS;
		arr[2][4] = CAN_PASS;
		arr[2][5] = CAN_PASS;
		arr[2][6] = CAN_PASS;

		arr[3][0] = CAN_PASS;
		arr[3][1] = CAN_PASS;
		arr[3][2] = NO_PASS;
		arr[3][3] = NO_PASS;
		arr[3][4] = CAN_PASS;
		arr[3][5] = CAN_PASS;
		arr[3][6] = CAN_PASS;

		arr[4][0] = NO_PASS;
		arr[4][1] = CAN_PASS;
		arr[4][2] = NO_PASS;
		arr[4][3] = EXIT;
		arr[4][4] = CAN_PASS;
		arr[4][5] = NO_PASS;
		arr[4][6] = CAN_PASS;

		// Print the array to verify
		printArray(arr);
		return arr;
	}

	private static void printArray(char[][] arr) {
		for (char[] row : arr) {
			for (char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
}
