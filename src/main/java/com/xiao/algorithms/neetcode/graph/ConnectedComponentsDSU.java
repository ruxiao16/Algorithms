package com.xiao.algorithms.neetcode.graph;

class DSU {
	int[] parent;
	int[] rank;

	public DSU(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;// initially everyone is its own parent
			rank[i] = i; // and everyone has the same rank
		}
	}

	// find its root parent
	public int find(int node) {
		int cur = node; // set the result to itself initially
		while (cur != parent[cur]) { // not itself
			parent[cur] = parent[parent[cur]]; // path compression -> an optimize, still works without it
			cur = parent[cur]; // go up the chain until we reach the root parent
		}
		return cur;
	}

	public boolean union(int u, int v) {
		int pu = find(u);
		int pv = find(v);
		if (pu == pv) return false; // already in the same union

		// another optimization, keep the tree balanced, adding smaller subset to the larger one
		if (rank[pv] > rank[pu]) {
			parent[pu] = pv; // pv is the parent of pu now
			rank[pv] += rank[pu];
		}
		else {
			parent[pv] = pu;
			rank[pu] += rank[pv];
		}
		return true;
	}
}


public class ConnectedComponentsDSU {
	public int countComponents(int n, int[][] edges) {
		DSU dsu = new DSU(n);
		int res = n;
		for (int[] edge : edges) {
			if (dsu.union(edge[0], edge[1])) {
				res--;
			}
		}
		return res;
	}
}
