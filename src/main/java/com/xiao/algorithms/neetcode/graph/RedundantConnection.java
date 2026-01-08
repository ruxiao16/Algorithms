package com.xiao.algorithms.neetcode.graph;


class DSU1 {
	int[] parent;
	int[] rank;

	public DSU1(int numOfNodes) {
		// the input is 1 based, so we add 1 here to accomodate that, otherwise
		// we have [0, 1, 2, 3] for 4 nodes while if we do find(4) we run into issues because
		// there is no parent[4].
		// So essentially we init our array as [0, 1, 2, 3, 4] for a 4 node set up.
		// and we dont use parent[0]
		parent = new int[numOfNodes+1];
		rank = new int[numOfNodes+1];
		for (int i = 0; i < numOfNodes; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	// find its root parent
	public int find(int node) {
		int cur = node;// set the result to itself initially
		while (cur != parent[cur]) { // not itself
			parent[cur] = parent[parent[cur]];
			cur = parent[cur];
		}
		return cur;
	}

	public boolean union(int u, int v ){
		int pu = find(u);
		int pv = find(v);

		// already joined
		if (pu == pv) {
			return false;
		}

		// else we join them, we take the higher rank root node as the parent
		if (rank[pv] > rank[pu]) {
			parent[pu] = pv;
			rank[pv] += rank[pu];
		} else {
			parent[pv] = pu;
			rank[pu] += rank[pv];
		}
		return true;
	}

}

public class RedundantConnection {

	public int[] findRedundantConnection(int[][] edges) {
		int numOfNodes = edges.length;

		DSU1 dsu = new DSU1(numOfNodes);

		for (int[] edge : edges) {
			// cycle detected
			if (!dsu.union(edge[0], edge[1])) {
				return edge;
			}
		}
		return new int[0];// not found
	}

	public static void main(String[] args) {
		int[][] testArray =  {
				{1, 2},
				{1, 3},
				{3, 4},
				{2, 4}
		};

		RedundantConnection driver = new RedundantConnection();
		int[] result = driver.findRedundantConnection(testArray);
		System.out.println(result[0] + ", "+ result[1]);

		int[][] testArray1 =  {
				{1, 2},
				{1, 3},
				{1, 4},
				{3, 4},
				{4, 5}
		};

		int[] result1 = driver.findRedundantConnection(testArray1);
		System.out.println(result1[0] + ", "+ result1[1]);
	}
}
