package com.xiao.algorithms.neetcode.trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import com.xiao.algorithms.datastructures.binarysearchtree.BinarySearchTree;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {}

	TreeNode(int val) { this.val = val; }

	TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
	}


	public static void levelOrderPrettyPrint(TreeNode root) {
		System.out.println("Level order pretty print");
		if (root == null) {
			System.out.println("Empty tree");
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		int nodesInCurrentLvl = 1;

		while(nodesInCurrentLvl > 0) {
			int nodesInNextLvl = 0;
			for (int i = 0; i< nodesInCurrentLvl; i++) {
				TreeNode currNode = queue.poll();
				System.out.print(currNode.val);
				System.out.print(",");
				if (currNode.left != null) {
					queue.offer(currNode.left);
					nodesInNextLvl++;
				}
				if( currNode.right != null) {
					queue.offer(currNode.right);
					nodesInNextLvl++;
				}
			}
			System.out.println();
			nodesInCurrentLvl = nodesInNextLvl;
		}
	}
}
