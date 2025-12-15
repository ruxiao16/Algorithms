package com.xiao.algorithms.neetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {

	//in fact the prerequisites already represent a graph (adjacency matrix), we just need to detect a cycle
	// in the graph. We can do dfs and use a set to keep track of visited nodes

	private Map<Integer, List<Integer>> preMap = new HashMap<>();
	private Set<Integer> visiting = new HashSet<>();

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		for (int i = 0; i < numCourses; i++) {
			preMap.put(i, new ArrayList<>());
		}

		for (int[] prereq : prerequisites) {
			preMap.get(prereq[0]).add(prereq[1]);
		}

		for (int c = 0; c < numCourses; c++) {
			if (!dfs(c)) {
				return false;
			}
		}
		return true;
	}

	private boolean dfs(int crs) {
		if (visiting.contains(crs)) {
			// cycle detected
			return false;
		}
		// no pre-reqs, base case
		if (preMap.get(crs).isEmpty()) {
			return true;
		}

		// we are visiting the current course
		visiting.add(crs);
		// this visits all the pre-req of the current course
		for (int pre : preMap.get(crs)) {
			if (!dfs(pre)) {
				return false;
			}
		}

		// if no cycle detected now, meaning current course can be completed
		visiting.remove(crs);
		// then we set its pre reqs to empty so future dependency on this course do not have to do the dfs search again,
		// -> earlier return with no pre-reqs, base case
		preMap.put(crs, new ArrayList<>());
		return true;
	}


	// Implementation using BFS (Kahn's Algorithm)
	// We count the in-degree (number of prerequisites) for each course.
	// Courses with 0 in-degree are added to the queue (can be taken).
	public boolean canFinishBfs(int numCourses, int[][] prereqs) {
		List<List<Integer>> adj = new ArrayList<>();
		int[] inDegree = new int[numCourses];

		for (int i = 0; i < numCourses; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] prereq: prereqs) {
			// prereq[1] is the prerequisite for prereq[0]
			adj.get(prereq[1]).add(prereq[0]);
			inDegree[prereq[0]]++;
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < numCourses; i++) {
			//inDegree[i] zero means these course have no pre-reqs, so they can be taken first
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		int finishedCount = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			finishedCount ++;

			for (int neighbor : adj.get(current)) {
				inDegree[neighbor]--;
				//All prerequisites for this course are finished
				if (inDegree[neighbor] == 0) {
					queue.offer(neighbor);
				}
			}
		}
		return finishedCount == numCourses;
	}
}
