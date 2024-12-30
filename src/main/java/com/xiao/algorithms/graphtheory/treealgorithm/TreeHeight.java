/*
	Time complexity o(n)
 */
package com.xiao.algorithms.graphtheory.treealgorithm;

public class TreeHeight {
	public static class TreeNode {
		int value;
		TreeNode left, right;
		public TreeNode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public static int treeHeight(TreeNode node) {
		// node == null is checking the leaf node for use in a sense
		// since two null leaf nodes of 7 as an example. Once we reach
		// them, treeHeight(7.left) and treeHeight(7.right) both return -1
		// then max(-1,-1) = -1, -1+1 = 0.
		if (node == null ) return -1;
		// another way to look at is that the tree height is essentially the max
		// height between my right and left subtree, plus 1 (myself)
		return Math.max(treeHeight(node.left), treeHeight(node.right)) +1;
	}

	public static void main(String[] args) {
		TreeNode root = makeTree();
		System.out.printf("Tree height: %d\n", treeHeight(root));
	}

	//        0
	//       / \
	//      1   2
	//     / \ / \
	//    3  4 5  6
	//   / \
	//  7   8
	// / \
	//nul nul
	private static TreeNode makeTree() {
		TreeNode node0 = new TreeNode(0);

		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		node0.left = node1;
		node0.right = node2;

		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		node1.left = node3;
		node1.right = node4;

		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		node2.left = node5;
		node2.right = node6;

		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		node3.left = node7;
		node3.right = node8;

		return node0;
	}
}
