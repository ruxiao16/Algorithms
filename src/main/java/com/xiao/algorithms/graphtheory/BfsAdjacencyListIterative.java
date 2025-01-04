package com.xiao.algorithms.graphtheory;

import static com.xiao.algorithms.common.Utils.addUndirectedEdge;
import static com.xiao.algorithms.common.Utils.createGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class BfsAdjacencyListIterative {
	Integer[] prev;
	int size;

	List<List<Integer>> graph;

	public BfsAdjacencyListIterative(List<List<Integer>> graph) {
		if (graph == null) throw new IllegalArgumentException("Graph cannot be null");
		size = graph.size();
		this.graph = graph;
	}

	/**
	 * Reconstructs the path (of nodes) from 'start' to 'end' inclusive. If the edges are unweighted
	 * then this method returns the shortest path from 'start' to 'end'
	 *
	 * @return An array of nodes indexes of the shortest path from 'start' to 'end'. If 'start' and
	 *     'end' are not connected then an empty array is returned.
	 */
	public List<Integer> reconstructPath(int start, int end) {
		bfs(graph, start);
		List<Integer> path = new ArrayList<>();
		// with our example, the prev array will look roughly like
		//   0    1  2  3  4  5  6  7  8  9  10  11  12  13
		// [nul, 10, x, 7, x, x, 7, 0, 9, 0, 9,  0,  x,  x]
		// Then say we want to find the shortest path from node 0 to node 1,
		// we start from 1 and backtrack 1 -> 10 -> 9 -> 0 (null)
		for (Integer at = end; at != null; at = prev[at])  {
			path.add(at);
		}
		Collections.reverse(path);
		if (path.get(0) == start) return path;
		path.clear();
		return path;
	}

	private void bfs(List<List<Integer>> graph, int start) {
		prev = new Integer[size];
		boolean[] visited = new boolean[size];
		Deque<Integer> queue = new ArrayDeque<>(size);

		//visit the "start" node and add it to the queue.
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int node = queue.poll();
			List<Integer> neighbors = graph.get(node);
			for (Integer neighbor : neighbors) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					// this keeps track what the previous node is. It is possible that multiple nodes
					// can arrive here but it does matter as visited node won't be visited again
					// ex. prev[1] = 10 meaning we arrive node 1 from node 10
					prev[neighbor] = node;
					queue.offer(neighbor);
				}
			}
		}
	}

	public static void main(String[] args) {
		final int n = 13;

		List<List<Integer>> graph = createGraph(n);

		addUndirectedEdge(graph, 0, 7);
		addUndirectedEdge(graph, 0, 9);
		addUndirectedEdge(graph, 0, 11);
		addUndirectedEdge(graph, 7, 11);
		addUndirectedEdge(graph, 7, 6);
		addUndirectedEdge(graph, 7, 3);
		addUndirectedEdge(graph, 6, 5);
		addUndirectedEdge(graph,3, 4);
		addUndirectedEdge(graph, 2, 3);
		addUndirectedEdge(graph, 2, 12);
		addUndirectedEdge(graph, 12, 8);
		addUndirectedEdge(graph, 8,1 );
		addUndirectedEdge(graph, 1, 10);
		addUndirectedEdge(graph, 10, 9);
		addUndirectedEdge(graph, 9, 8);

		BfsAdjacencyListIterative solver = new BfsAdjacencyListIterative(graph);

		List<Integer> path = solver.reconstructPath( 0, 1);
		System.out.printf("The shortest path from %d to %d is: [%s]\n", 0, 1, formatPath(path));
	}

	private static String formatPath(List<Integer> path) {
		return path.stream().map(Object::toString).collect(Collectors.joining(" -> "));
	}
}
