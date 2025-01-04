package com.xiao.algorithms.common;

import java.util.ArrayList;
import java.util.List;

public final class Utils {
	private Utils() {
		// empty on purpose
	}
	public static List<List<Integer>> createGraph(int size) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			graph.add(new ArrayList<>());
		}
		return graph;
	}

	public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
		graph.get(from).add(to);
		graph.get(to).add(from);// undirected edge, add both end
	}


	 static class Edge {
		int from, to, cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static List<List<Edge>> createWeightedEmptyGraph(int n) {
		List<List<Edge>> graph = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		return graph;
	}

	public static void addDirectedEdgeWeighted(List<List<Edge>> graph, int u, int v, int cost) {
		graph.get(u).add(new Edge(u, v, cost));
	}

	// Add an undirected edge between nodes 'u' and 'v'.
	public static void addUndirectedEdgeWeighted(List<List<Edge>> graph, int u, int v, int cost) {
		addDirectedEdgeWeighted(graph, u, v, cost);
		addDirectedEdgeWeighted(graph, v, u, cost);
	}
}
