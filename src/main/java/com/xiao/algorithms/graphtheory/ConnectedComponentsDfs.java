/*
 find all the connected components in an undirected graph.
 if the graph is directed, check out Tarjan's algorithm.
 */
package com.xiao.algorithms.graphtheory;

import static com.xiao.algorithms.common.Utils.addUndirectedEdge;
import static com.xiao.algorithms.common.Utils.createGraph;

import java.util.Arrays;
import java.util.List;

public class ConnectedComponentsDfs {

	private static boolean[] visited;
	private static int[] components;
	private static int count;

	private static int[] findConnectedComponents(List<List<Integer>> graph) {
		int size = graph.size();
		visited = new boolean[size];
		// we use this to color the graph, nodes belonging to the same connected
		// components are marked with the same int value
		components = new int[size];
		count = 0;
		for (int i = 0; i < size; i++) {
			if (!visited[i]) {
				count++;
				dfs(graph, i);
			}
		}
		return components;
	}

	private static void dfs(List<List<Integer>> graph, int at) {
		visited[at] = true;
		components[at] = count;
		for (int neighbor : graph.get(at)) {
			if (!visited[neighbor]) {
				dfs(graph, neighbor);
			}
		}
	}


	public static void main(String[] args) {
		final int n = 11;
		List<List<Integer>> graph = createGraph(n);
		// Setup a graph with five connected components:
		// {0,1,7}, {2,5}, {4,8}, {3,6,9}, {10}
		addUndirectedEdge(graph, 0, 1 );
		addUndirectedEdge(graph, 1, 7);
		addUndirectedEdge(graph, 7, 0);
		addUndirectedEdge(graph, 2, 5);
		addUndirectedEdge(graph, 4, 8);
		addUndirectedEdge(graph, 3, 6);
		addUndirectedEdge(graph, 6, 9);

		System.out.println(Arrays.toString(findConnectedComponents(graph)));
	}
}