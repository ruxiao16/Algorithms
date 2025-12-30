package com.xiao.algorithms.neetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ConnectedComponents {

	// brute force solution, first construct a adj matrix
	// If we start DFS from an unvisited node, we will visit all nodes in its connected component
	// Every time we start DFS from a new unvisited node, we’ve found one new component
	public int countComponents(int n, int[][] edges) {
		int res = 0;
		// construct adj list
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n ; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n];

		for (int i = 0 ; i< n ; i++ ) {
			if (!visited[i]) {
//				dfs(visited, map, i);
				bfs(visited, map, i);
				res++;
			}
		}
		return res;
	}

	private void dfs(boolean[] visited, Map<Integer, List<Integer>> map, int node) {
		visited[node] = true;
		for (int neighbor : map.get(node)) {
			if (!visited[neighbor]) {
				dfs(visited, map, neighbor);
			}
		}
	}

	private void bfs(boolean[] visited, Map<Integer, List<Integer>> map, int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);

		while(!queue.isEmpty()) {
			int currNode = queue.poll();
			visited[currNode] = true;

			for (int nextNode : map.get(currNode)) {
				if (!visited[nextNode]) {
					queue.add(nextNode);
				}
			}
		}
	}

	public static void main(String[] args) {
		ConnectedComponents driver = new ConnectedComponents();

		int[][] edges1 = {
				{0, 1},
				{0, 2}
		};
		System.out.println(driver.countComponents(3, edges1));
	}
}
