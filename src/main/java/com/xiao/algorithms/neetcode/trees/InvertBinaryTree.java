package com.xiao.algorithms.neetcode.trees;

import static com.xiao.algorithms.neetcode.trees.TreeNode.levelOrderPrettyPrint;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvertBinaryTree {

	public TreeNode invertTree(TreeNode root) {
//		invertTreeRecursion(root);
		invertTreeBFS(root);
		return root;
	}

	private void invertTreeBFS(TreeNode node) {
		if (node == null) {
			return;
		}

		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.push(node);
		while (!queue.isEmpty()) {
			TreeNode currNode = queue.pop();
			TreeNode temp = currNode.left;
			currNode.left = currNode.right;
			currNode.right = temp;

			if (currNode.left != null) {
				queue.push(currNode.left);
			}
			if (currNode.right != null) {
				queue.push(currNode.right);
			}
		}
	}

	// also a DFS
	private void invertTreeRecursion(TreeNode node) {
		if (node == null) {
			return;
		}

		TreeNode tmp = node.left;
		node.left = node.right;
		node.right = tmp;

		invertTreeRecursion(node.left);
		invertTreeRecursion(node.right);
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(1);
		TreeNode left1 = new TreeNode(2);
		TreeNode right1 = new TreeNode(3);
		root.left = left1;
		root.right = right1;

		TreeNode left2 = new TreeNode(4);
		TreeNode right2 = new TreeNode(5);
		TreeNode left3 = new TreeNode(6);
		TreeNode right3 = new TreeNode(7);

		left1.left = left2;
		left1.right = right2;

		right1.left = left3;
		right1.right = right3;

		levelOrderPrettyPrint(root);

		InvertBinaryTree driver = new InvertBinaryTree();
		driver.invertTree(root);
		levelOrderPrettyPrint(root);
	}
}
