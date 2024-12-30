package com.xiao.algorithms.graphtheory.treealgorithm;

import static com.xiao.algorithms.common.Utils.addUndirectedEdge;
import static com.xiao.algorithms.common.Utils.createGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TreeCenterLongestPathImpl {

	public static class DfsResult {
		// The distance of the furthest node (from where the dfs started)
		int distance;
		// The index of the furthest node
		int index;

		public DfsResult(int dist, int index) {
			this.distance = dist;
			this.index = index;
		}
	}

	private static DfsResult dfs (List<List<Integer>> graph, boolean[] visited, int[] prev, int at, int parent) {
		// Already visited this node
		// This will also be our base case, by the time
		// we reach the leaf node, its parent is already visited
		if (visited[at]) return new DfsResult(0, parent);

		visited[at] = true;

		// Remember where we came from to rebuild path later on
		prev[at] = parent;

		int bestDist = 0;
		int index = -1;
		List<Integer> edges = graph.get(at);

		for (int to : edges) {
			DfsResult result = dfs(graph, visited, prev, to, at);
			int dist = result.distance + 1;
			if (dist > bestDist) {
				bestDist = dist;
				// this ensures that the furthest node is propagated back
				index = result.index;
			}
		}

		return new DfsResult(bestDist, index);
	}

	public static List<Integer> findTreeCenters(List<List<Integer>> graph) {
		List<Integer> centers = new ArrayList<>();
		if (graph == null) return centers;

		int n = graph.size();
		boolean[] visited = new boolean[n];
		int[] prev = new int[n];

		//Do dfs to find the furthest node from the tart
		DfsResult result = dfs(graph, visited, prev, 0, -1);
		int furthestNode1 = result.index;
		System.out.println("furthest node is "+ furthestNode1);
		System.out.println("prev is "+ Arrays.toString(prev));

		// singleton
		if (furthestNode1 == -1) {
			centers.add(0);
			return centers;
		}

		// Do another DFS, but this time from the furthest node.
		Arrays.fill(visited, false);
		Arrays.fill(prev, 0);

		result = dfs(graph, visited, prev, furthestNode1, -1);
		int furthestNode2 = result.index;
		System.out.println("furthest node is "+ furthestNode2);
		System.out.println("prev is "+ Arrays.toString(prev));

		List<Integer> path = new LinkedList<>();
		for (int i = furthestNode2; i != -1; i = prev[i]) {
			path.add(i);
		}

		if (path.size() % 2 == 0) {
			centers.add(path.get(path.size()/2 - 1));
		}
		centers.add(path.get(path.size()/2));
		return centers;
	}

	public static void main(String[] args) {
		List<List<Integer>> graph = createGraph(9);
		addUndirectedEdge(graph, 0, 1);
		addUndirectedEdge(graph, 1, 2);
		addUndirectedEdge(graph, 2, 3);
		addUndirectedEdge(graph, 2, 6);
		addUndirectedEdge(graph, 3, 4);
		addUndirectedEdge(graph, 3, 5);
		addUndirectedEdge(graph, 6, 7);
		addUndirectedEdge(graph, 6, 8);


		List<Integer> centers = findTreeCenters(graph);
		System.out.println("centers are "+ centers);

	}
}
