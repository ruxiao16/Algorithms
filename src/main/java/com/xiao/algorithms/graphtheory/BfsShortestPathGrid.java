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
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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

	// use to backtrack and reconstruct path
	int[] prev;
	int exitRow = -1;
	int exitCol = -1;

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
		prev = new int[numOfCols* numOfRows];
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
				exitRow = row;
				exitCol = col;
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
		if (reachedEnd) {
			reconstructPath();
			return numOfMoves;
		}

		return -1;
	}

	private void reconstructPath() {
		int exitIdx = exitRow*numOfCols + exitCol;
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();

		rowList.add(exitIdx/numOfCols);
		colList.add(exitIdx%numOfCols);
		// TODO
		// here 0 represent the starting and ending index, we should probably
		// initialize the whole array to -1, and only mark the starting point as 0
		// we should also store starting row and column as global variable
		while (exitIdx != 0) {
			// exitIdx = 4*7+3 = 31
			// exitIdx = prev[31] = 32  -> row = 32/7 = 4; col = 32%7 = 4
			// exitIdx = prev[32] = 25  -> row = 25/7 = 3; col = 25%7 = 4
			exitIdx = prev[exitIdx];
			rowList.add(exitIdx/numOfCols);
			colList.add(exitIdx%numOfCols);
		}

		for (int i = rowList.size()-1 ; i >= 0; i--) {
			System.out.print(rowList.get(i) + "," + colList.get(i) + " -> ");
		}
		System.out.println();
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

			// instead of maintaining a 2 d matrix, we use a 1d array prev to capture
			// where the previous node is. For example, the grid is 5x7, then moving from
			// (0,2) to (1,2), we map the cell (1,2) as the  1*7+2 = 9th index on the 1 d array,
			// and it will have a value of 0*7+2 = 2, meaning to arrive node 9, it is from node 2.
			prev[newRow*numOfCols + newCol] = row*numOfCols + col;
			visited[newRow][newCol] = true;
			nodesInNextLayer ++;
		}
	}
	public static void main(String[] args) {
		char[][] maze = generateMap();
		BfsShortestPathGrid solver = new BfsShortestPathGrid(maze, 0, 0);
		int moveCnt = solver.solve();
		System.out.println("number of moves " + moveCnt);
		solver.printPrev();
	}
	private static char[][] generateMap() {
		char[][] arr = new char[5][7];
		arr[0][0] = START;
		arr[0][1] = CAN_PASS;
		arr[0][2] = CAN_PASS;
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

	private void printPrev() {
		for (int value: prev) {
			System.out.print(value + " ");
		}
		System.out.println();
	}
}
