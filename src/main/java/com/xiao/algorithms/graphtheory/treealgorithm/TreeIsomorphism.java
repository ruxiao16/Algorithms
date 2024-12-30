/*
	Determines if two unrooted trees are isomorphic
	-> different forms but the same tree
 */
package com.xiao.algorithms.graphtheory.treealgorithm;

import static com.xiao.algorithms.common.Utils.addUndirectedEdge;
import static com.xiao.algorithms.common.Utils.createGraph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.xiao.algorithms.common.TreeNode;

public class TreeIsomorphism {

	public static boolean treesAreIsomorphic(List<List<Integer>> tree1, List<List<Integer>> tree2) {
		if (tree1.isEmpty() || tree2.isEmpty()) {
			throw new IllegalArgumentException("Empty tree input");
		}

		// find centers -> this ensures we start with the same node for both trees.
		List<Integer> centers1 = TreeCenter.findTreeCenters(tree1);
		List<Integer> centers2 = TreeCenter.findTreeCenters(tree2);

		// root the tree based on the center node,
		TreeNode rootedTree1 = RootingTree.rootTree(tree1, centers1.get(0));
		String tree1Encoding = encode(rootedTree1);

		// we check encoding of both (if there are two centers) of tree2 to see
		// which one matches
		for (int center : centers2) {
			TreeNode rootedTree2 = RootingTree.rootTree(tree2, center);
			String tree2Encoding = encode(rootedTree2);
			if (tree1Encoding.equals(tree2Encoding)) {
				return true;
			}
		}
		return false;
	}

	// Constructs the canonical form representation of a tree as a string
	public static String encode(TreeNode node) {
		if (node == null) {
			return "";
		}
		// for each node, we maintain a list of labels for the subtrees
		List<String> labels = new LinkedList<>();
		for (TreeNode child : node.getChildren()) {
			labels.add(encode(child));
		}
		// we don't proceed until all the children nodes are taken care of
		// sort the results on the current node is important as it guarantees
		// uniqueness.
		Collections.sort(labels);
		StringBuilder sb = new StringBuilder();
		for (String label : labels) {
			sb.append(label);
		}
		// for the leaf node, its child is null, so it will be "()"
		return "(" + sb.toString() + ")";
	}

	public static void main(String[] args) {
		simpleIsomorphismTest();
		testEncodingTreeFromSlides();
	}

	private static void simpleIsomorphismTest() {
		List<List<Integer>> tree1 = createGraph(5);
		addUndirectedEdge(tree1, 2, 0);
		addUndirectedEdge(tree1, 3, 4);
		addUndirectedEdge(tree1, 2, 1);
		addUndirectedEdge(tree1, 2, 3);

		List<List<Integer>> tree2 = createGraph(5);
		addUndirectedEdge(tree2, 1, 0);
		addUndirectedEdge(tree2, 2, 4);
		addUndirectedEdge(tree2, 1, 3);
		addUndirectedEdge(tree2, 1, 2);

		System.out.println("Trees are isomorphic: " + treesAreIsomorphic(tree1, tree2));
	}

	private static void testEncodingTreeFromSlides() {
		List<List<Integer>> tree = createGraph(10);
		addUndirectedEdge(tree, 0, 2);
		addUndirectedEdge(tree, 0, 1);
		addUndirectedEdge(tree, 0, 3);
		addUndirectedEdge(tree, 2, 6);
		addUndirectedEdge(tree, 2, 7);
		addUndirectedEdge(tree, 1, 4);
		addUndirectedEdge(tree, 1, 5);
		addUndirectedEdge(tree, 5, 9);
		addUndirectedEdge(tree, 3, 8);

		TreeNode root0 = RootingTree.rootTree(tree, 0);

		if (!encode(root0).equals("(((())())(()())(()))")) {
			System.out.println("Tree encoding is wrong: " + encode(root0));
		}
	}
}
