package com.xiao.algorithms.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LevelOrderTraversal {

	public List<List<Integer>> solution(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}

		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int numOfNodesInCurrentLvl = 1;
		List<List<Integer>> result = new ArrayList<>();


		while (numOfNodesInCurrentLvl > 0) {
			int numOfNodesInNextLvl = 0;
			List<Integer> storage = new ArrayList<>();

			for (int i = 0; i < numOfNodesInCurrentLvl; i++) {
				TreeNode currNode = queue.pop();
				storage.add(currNode.val);

				if (currNode.left != null) {
					queue.offer(currNode.left);
					numOfNodesInNextLvl++;
				}

				if (currNode.right != null) {
					queue.offer(currNode.right);
					numOfNodesInNextLvl++;
				}
			}
			numOfNodesInCurrentLvl = numOfNodesInNextLvl;
			result.add(storage);
		}
		return result;
	}
}
