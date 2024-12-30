package com.xiao.algorithms.graphtheory.treealgorithm;

import static com.xiao.algorithms.common.Utils.addUndirectedEdge;
import static com.xiao.algorithms.common.Utils.createGraph;

import java.util.ArrayList;
import java.util.List;

public class TreeCenter {
	// undirected graph
	public static List<Integer> findTreeCenters(List<List<Integer>> tree) {
		final int n = tree.size();
		int[] degree = new int[n];

		// find all leaf nodes
		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; i ++) {
			// tree.get(i).size() returns number of connected nodes
			// 1 means a leaf node, only connect to another node, has to be a leaf node
			degree[i] = tree.get(i).size();
			// == 0 means that this is a single node tree
			if (degree[i] == 0 || degree[i] == 1) {
				leaves.add(i);
				degree[i] = 0;
			}
		}

		// like peeling an onion, layer by layer
		// remove leaf nodes and decreases the degree of each neighboring node
		// adding new leaf node progressively until only the centers remain
		int processedLeafs = leaves.size();
		while (processedLeafs < n) {
			List<Integer> newLeaves = new ArrayList<>();
			for (int node : leaves) {
				for (int neighbor : tree.get(node)) {
					// since we are removing the current node (leaf node), the degree of all
					// neighboring nodes must go down 1, and if they have degree of 1, they are
					// the new leaf nodes
					if (--degree[neighbor] == 1) {
						newLeaves.add(neighbor);
					}
				}
				// remove a leaf node
				degree[node] = 0;
			}
			processedLeafs += newLeaves.size();
			leaves = newLeaves;// one layer peels, replaced with the new outermost layer, with degree of 1
		}
		return leaves;
	}

	public static void main(String[] args) {
		// Centers are 0,1
		List<List<Integer>> graph3 = createGraph(2);
		addUndirectedEdge(graph3, 0, 1);
		System.out.println(findTreeCenters(graph3));


		/*
				8 -- 6 -- 7
					 |
		   0 -- 1 -- 2 -- 3 -- 4
						  |
						  5
		 */
		List<List<Integer>> graph = createGraph(9);
		addUndirectedEdge(graph, 0, 1);
		addUndirectedEdge(graph, 2, 1);
		addUndirectedEdge(graph, 2, 3);
		addUndirectedEdge(graph, 3, 4);
		addUndirectedEdge(graph, 5, 3);
		addUndirectedEdge(graph, 2, 6);
		addUndirectedEdge(graph, 6, 7);
		addUndirectedEdge(graph, 6, 8);

		// Centers are 2
		System.out.println(findTreeCenters(graph));
	}
}
