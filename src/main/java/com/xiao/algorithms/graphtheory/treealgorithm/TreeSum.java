/*
	Time complexity o(n)
 */
package com.xiao.algorithms.graphtheory.treealgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeSum {

	public static class TreeNode {
		int value;
		List<TreeNode> children = new ArrayList<>();

		public TreeNode(int value) {
			this.value = value;
		}

		public List<TreeNode> getChildren() {
			return children;
		}

		public void addChild(TreeNode... nodes) {
			Collections.addAll(children, nodes);
		}
	}

	public static int treeSum(TreeNode node) {
		if (node == null) return 0; // empty tree
		if (node.getChildren().isEmpty()) {
			return node.value;
		}
		int sum = 0;
		// dfs
		for (TreeNode eachChild: node.getChildren()) {
			sum += treeSum(eachChild);
		}
		return sum;
	}

	public static void main(String[] args) {
		TreeNode root = makeTree();
		System.out.printf("Tree sum: %d\n", treeSum(root));
	}

	private static TreeNode makeTree() {
		TreeNode root = new TreeNode(5);

		TreeNode node4 = new TreeNode(4);
		TreeNode node3 = new TreeNode(3);
		root.addChild(node4, node3);

		TreeNode node1 = new TreeNode(1);
		TreeNode nodem6 = new TreeNode(-6);
		node4.addChild(node1, nodem6);

		TreeNode node0 = new TreeNode(0);
		TreeNode node7 = new TreeNode(7);
		TreeNode nodem4 = new TreeNode(-4);
		node3.addChild(node0, node7, nodem4);

		TreeNode node2 = new TreeNode(2);
		TreeNode node9 = new TreeNode(9);
		node1.addChild(node2, node9);

		TreeNode node8 = new TreeNode(8);
		node7.addChild(node8);

		return root;
	}
}
