package com.xiao.algorithms.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeRightSideView {

	public List<Integer> solution(TreeNode root) {
		List<Integer> rightSideView = new ArrayList<>();

		if (root == null) {
			return rightSideView;
		}

		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int numOfNodesInCurrentLvl = 1;

		while (numOfNodesInCurrentLvl > 0) {
			int numOfNodesInNextLvl = 0;
			boolean firstAdd = true;
			for (int i= 0; i < numOfNodesInCurrentLvl; i++) {

				TreeNode node = queue.pop();
				// first pop in a level is always the rightmost node, gurranteed by we are adding rightmost node first
				if (firstAdd) {
					rightSideView.add(node.val);
					firstAdd = false;
				}

				// making sure the right child is added first
				if (node.right != null) {
					queue.offer(node.right);
					numOfNodesInNextLvl++;
				}

				if (node.left != null) {
					queue.offer(node.left);
					numOfNodesInNextLvl++;
				}
			}
			numOfNodesInCurrentLvl = numOfNodesInNextLvl;
		}
		return rightSideView;
	}



	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(4);

		BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
		List<Integer> result = solution.solution(root);
		result.forEach(System.out::println);

	}
}
