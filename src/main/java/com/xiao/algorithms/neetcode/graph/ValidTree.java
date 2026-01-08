package com.xiao.algorithms.neetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidTree {

	public boolean validTree(int n, int[][] edges) {
		// transform to adj list
		Map<Integer, List<Integer>> adjList = new HashMap();
		for (int i = 0; i < n; i++) {
			adjList.put(i, new ArrayList<>());
		}

		for (int[] edge : edges) {
			adjList.get(edge[0]).add(edge[1]);
			adjList.get(edge[1]).add(edge[0]);
		}

		Set<Integer> visited = new HashSet<>();

		if (!dfs(0, -1, adjList, visited)) {
			return false;
		}

		// not a single component, not connected
		if (visited.size() != n) {
			return false;
		}
		return true;
	}

	private boolean dfs(int cur, int prev, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
		if (visited.contains(cur)) {
			return false;
		}

		visited.add(cur);

		for (int neighbor : adjList.get(cur)) {
			// skip the parent node to avoid revisiting it
			if (neighbor == prev) {
				continue;
			}
			if (!dfs(neighbor, cur, adjList, visited)) { // cycle detection
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		ValidTree driver = new ValidTree();

		int[][] graph1 = {
				{0, 1}, {0,2}, {0, 3}, {1,4}
		};

		System.out.println(driver.validTree(5, graph1));
	}
}
