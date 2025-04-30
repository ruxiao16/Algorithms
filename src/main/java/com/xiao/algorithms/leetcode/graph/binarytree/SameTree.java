package com.xiao.algorithms.leetcode.graph.binarytree;

import com.xiao.algorithms.leetcode.TreeNode;

public class SameTree {

	public boolean solution(TreeNode p, TreeNode q) {

		if (p == null && q == null) {
			return true;
		}
		else if (p == null) {
			return false; // reaching here, implies q is not null
		}
		else if (q == null) {
			return false; // reaching here, implies p is not null
		}

		if (p.val != q.val) {
			 return false;
		}


		return solution(p.right, q.right) && solution(p.left, q.left);
	}



	public static void main(String[] args) {
		SameTree solution = new SameTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);



		System.out.println("Tree trees are the same: " + solution.solution(root, root1));
	}

}
