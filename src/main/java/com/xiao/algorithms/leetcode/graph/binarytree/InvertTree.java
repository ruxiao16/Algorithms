package com.xiao.algorithms.leetcode.graph.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

import com.xiao.algorithms.leetcode.TreeNode;

public class InvertTree {

	public TreeNode solution(TreeNode root) {
		dfs(root);
		return root;
	}
	// dfs thru the tree, in order? then we swap each node's children
	public void dfs(TreeNode root) {
		if (root == null) {
			return;
		}

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		dfs(root.left);
		dfs(root.right);
	}

	public void levelOrderPrint(TreeNode root) {
		if (root == null) {
			System.out.println("Empty tree");
		}

		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int currentLvlSize = 1;

		while (currentLvlSize > 0) {
			int nextLevelSize = 0;
			for (int i = 0; i < currentLvlSize; i ++) {
				TreeNode node = queue.pop();
				System.out.print(node.val + " ");
				if (node.left != null) {
					queue.offer(node.left);
					nextLevelSize++;
				}
				if (node.right != null) {
					queue.offer(node.right);
					nextLevelSize++;
				}
			}
			System.out.println();
			currentLvlSize = nextLevelSize;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);

		InvertTree solution = new InvertTree();
		solution.solution(root);

		solution.levelOrderPrint(root);
	}
}
