package com.xiao.algorithms.neetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

	class Node {
		public int val;
		public List<Node> neighbors;
		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}
		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}
		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	// problem statement:
	// nodes values are from 1 to n where n = total # of nodes
	// index of each node within the adj list is the same as the node's value (1-indexed)
	public Node cloneGraph(Node node) {
		if (node == null) return null;
		// store current visiting nodes and its clone
		Map<Node, Node> oldToNew = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();

		queue.add(node);
		oldToNew.put(node, new Node(node.val));

		while (!queue.isEmpty()) {
			Node curr = queue.poll();
			for (Node nei : curr.neighbors) {
				// have not visited
				if (!oldToNew.containsKey(nei)) {
					queue.add(nei);
					oldToNew.put(nei, new Node(nei.val));
				}
				// create the mapping, alternatively, we can probably store all the neighbors in arraylist and set it in one go
				oldToNew.get(curr).neighbors.add(oldToNew.get(nei));
			}
		}
		return oldToNew.get(node);
	}


	private Node cloneGraphDfs(Node node) {
		Map<Node, Node> map = new HashMap<>();
		return dfs(node, map);
	}

	private Node dfs(Node node, Map<Node, Node> oldToNew) {
		if (node == null) return null;

		// already visited
		if (oldToNew.containsKey(node)) {
			return oldToNew.get(node); // return the copy
		}

		Node copy = new Node(node.val);
		oldToNew.put(node, copy);

		for (Node nei : node.neighbors) {
			copy.neighbors.add(dfs(nei, oldToNew));
		}

		return copy;
	}


}
