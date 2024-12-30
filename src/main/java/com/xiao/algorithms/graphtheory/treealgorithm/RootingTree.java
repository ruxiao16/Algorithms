/*
Time complexity o(v+e)
 */
package com.xiao.algorithms.graphtheory.treealgorithm;

import static com.xiao.algorithms.common.Utils.addUndirectedEdge;
import static com.xiao.algorithms.common.Utils.createGraph;

import java.util.List;

import com.xiao.algorithms.common.TreeNode;

public class RootingTree {

	public static TreeNode rootTree(List<List<Integer>> graph, int rootId) {
		TreeNode root = new TreeNode(rootId,null);
		return buildTree(graph, root);
	}
	private static TreeNode buildTree(List<List<Integer>> graph, TreeNode node) {
		for (int childId : graph.get(node.getId())) {
			// Avoiding adding an edge pointing back to the parent
			// if current node's child is also current node's parent, then
			// we are going back
			TreeNode parent = node.getParent();
			if (parent!= null && parent.getId() == childId) {
				continue;
			}
			TreeNode childNode = new TreeNode(childId, node);
			node.addChildren(childNode);
			//dfs
			buildTree(graph, childNode);
		}
		return node;
	}


	public static void main(String[] args) {
		List<List<Integer>> sevenNodesGraphs = createGraph(7);
		addUndirectedEdge(sevenNodesGraphs, 0, 2);
		addUndirectedEdge(sevenNodesGraphs, 0, 1);
		addUndirectedEdge(sevenNodesGraphs, 0,5);
		addUndirectedEdge(sevenNodesGraphs, 2, 3);
		addUndirectedEdge(sevenNodesGraphs,4, 5);
		addUndirectedEdge(sevenNodesGraphs, 5,6);

		// Rooted at 0 the tree should look like:
		//           0
		//      2    1     5
		//    3          4    6
		TreeNode root = rootTree(sevenNodesGraphs, 0);

		// Layer 0: [0]
		System.out.println(root);

		// Layer 1: [2, 1, 5]
		System.out.println(root.getChildren());

		// Layer 2: [1, 3]
		System.out.println(root.getChildren().get(0).getChildren() +
				"    " + root.getChildren().get(2).getChildren());

	}
}
