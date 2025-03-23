package com.xiao.algorithms.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AveragePerLevel {

	public List<Double> avgPerLevel(TreeNode root) {
		if (root == null) {
			throw new IllegalArgumentException("root is null");
		}

		List<Double> avgList = new ArrayList<>();
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int nodesInCurrentLvl = 1;

		// more of a level order search
		while (nodesInCurrentLvl > 0) {
			int nodeInNextLvl = 0;
			double sumPerLvl = 0.0;

			for (int i = 0; i < nodesInCurrentLvl; i++) {
				TreeNode currNode = queue.pop();
				sumPerLvl += currNode.val;
				if (currNode.left != null) {
					queue.offer(currNode.left);
					nodeInNextLvl++;
				}
				if (currNode.right != null) {
					queue.offer(currNode.right);
					nodeInNextLvl++;
				}
			}
			avgList.add(sumPerLvl/nodesInCurrentLvl);
			nodesInCurrentLvl = nodeInNextLvl;
		}
		return avgList;
	}


	public static void main(String[] args) {
		//test cases
	}
}
