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
}
