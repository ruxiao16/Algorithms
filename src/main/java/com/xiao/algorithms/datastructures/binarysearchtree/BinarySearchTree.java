/*
 A BT (binary tree) is a tree where every node has no more than two child
 A BST, is a BT that left subtree has smaller elements and right subtree
 has larger elements
 */
package com.xiao.algorithms.datastructures.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {
	private class Node {
		T data;
		Node left;
		Node right;

		public Node(Node left, Node right, T elem) {
			this.data = elem;
			this.left = left;
			this.right = right;
		}
	}
	// Tracks the # of nodes in this BST
	private int nodeCount = 0;

	// This BST is a rooted tree so we maintain a handle on the root node
	private Node root = null;

	// check if this BTS is empty
	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return nodeCount;
	}

	// Add an element to this binary tree, returns ture if we successfully perform an insertion
	public boolean add(T elem) {
		if (contains(elem)) {
			return false;
		} else {
			root = add(root, elem);
			nodeCount++;
			return true;
		}
	}

	// private method to recursively add a value in the binary tree
	/*
	  inserting 16, it will be to the right of 16
				7
			  /   \
			 5    20
			/ \   / \
		   4   6 15
		        /
		       10
	 */
	private Node add(Node node, T elem) {
		//Base case: found a leaf node
		if (node == null) {
			node = new Node(null, null, elem);
		} else  {
			// pick a subtree to insert element
			if (elem.compareTo(node.data) < 0) {
				node.left = add(node.left, elem);
			} else {
				// this node here will be the current node before recursive down,
				// on the way back, it is being returned back to the previous
				// node --> the parent
				// for the example given, before the base case, we will have
				// 15.right = add(null, 16) then add(null, 16) returns the new leaf node 16
				// 15 is returned back to 20 as the call stack unwinds

				// Or alternatively, each call stack will have its own node, on which the comparison
				// is being made and recursive calls being made. When the stack unwinds, these nodes
				// are being returned.
				node.right = add(node.right, elem);
			}
		}

		return node;
	}

	public boolean remove(T elem) {
		// Make sure the node we want to remove actually exists
		if (contains(elem)) {
			root = remove(root, elem);
			nodeCount--;
			return true;
		}
		return false;
	}

	/*
	 *                7
	 *              /   \
	 *            5      20
	 *          /        /  \
	 *        4        18    25
	 *       /        / \      \
	 *      2        11  19    33
	 *     / \        \       /
	 *    1   3        14     28
                      / \      \
                     12  15    31
         take remove 11 as an example, the call stack will be
         remove(7, 11) -> 7.right = remove(20, 11) -> 20.left = remove(18, 11) -> 18.left = remove(11, 11)
         -> found a match, since 11.left == null, we return 11.right which is 14. Then on the call back
         18.left = 14 (we have essentially removed 11 from the tree by changing the link)
	*/

	private Node remove(Node node, T elem) {
		if (node == null) return null;

		int cmp = elem.compareTo(node.data);

		// The value we are looking for is smaller than the current value, dig into left subtree
		if (cmp < 0) {
			node.left = remove(node.left, elem);
		} else if(cmp > 0) {
			node.right = remove(node.right, elem);
		} else {
			// found the node we want to remove

			// Case: only a right subtree or no subtree at all.
			// Swap the node we wish to remove with its right child--> successor
			// Note, for the case when the node has no subtree at all, we simply just return null to the parent node -> which
			// is the same as removing the current node
			if (node.left == null) {
				// basically the right node of the removed node becomes the new
				// right node of the removed node's parent node
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				// when removing a node from a binary tree with two links.
				// The successor of the node being removed can either be the largest
				// value in the left subtree or the smallest value in the right subtree.
				// In this impl we will use the largest value in the left subtree

				// The largest value in the left subtree satisfies BST invariant since it:
				// 1. is larger than everything in the left subtree.
				// 2. is smaller than everything in the right subtree because it was found in the left subtree
				// The smallest value in the right subtree
				// 1. is smaller than everything in right subtree
				// 2. is larger than everything in left subtree

				// finding the largest value in the left subtree, we just have to traverse the
				// left subtree and dig right as far as possible.
				Node tmp = findMax(node.left);

				// Swap the data
				node.data = tmp.data;

				// Go in the left subtree and remove the rightmost node we found and swapped data with.
				// note that this will fall into one of the first two conditions right away because
				// this rightmost node will have at most one child (one subtree)
				node.left = remove(node.left, tmp.data);
			}
		}
		return node;
	}


	// find the rightmost node (which has the largest value)
	private Node findMax(Node node) {
		while (node.right != node) {
			node = node.right;
		}
		return node;
	}

	public boolean contains(T elem) {
		return contains(root, elem);
	}

	// private recursive method to find an element in the tree
	private boolean contains(Node node, T elem) {
		// base case, reached bottom, value not found
		if (node == null) return false;

		int cmp = elem.compareTo(node.data);

		// dig into the left subtree because the value we are looking
		// for is smaller than the current value
		if (cmp < 0) return contains(node.left, elem);

		else if (cmp > 0) return contains(node.right, elem);

		// we found the value we were looking for
		else return true;
	}


	public void preOrder() {
		System.out.println("Pre order transversal");
		preOrder(root);
	}
	private void preOrder(Node node ) {
		if(node == null) {
			return;
		}
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}


	public void inOrder() {
		System.out.println("In order transversal");
		inOrder(root);
	}

	private void inOrder(Node node) {
		if(node == null) {
			return;
		}
		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}

	public void postOrder() {
		System.out.println("Post order transversal");
		postOrder(root);
	}
	private void postOrder(Node node) {
		if(node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);
	}

	private void levelOrderTransversal() {
		System.out.println("Level order transversal");
		if (root == null) {
			System.out.println("Empty tree");
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);

		while(!queue.isEmpty()) {
			Node currNode = queue.poll();
			System.out.print(currNode.data);
			System.out.print(",");
			if (currNode.left != null) {
				queue.offer(currNode.left);
			}
			if (currNode.right != null) {
				queue.offer(currNode.right);
			}
		}
		System.out.println();
	}

	private void levelOrderPrettyPrint() {
		System.out.println("Level order pretty print");
		if (root == null) {
			System.out.println("Empty tree");
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);

		int nodesInCurrentLvl = 1;

		while(nodesInCurrentLvl > 0) {
			int nodesInNextLvl = 0;
			for (int i = 0; i< nodesInCurrentLvl; i++) {
				Node currNode = queue.poll();
				System.out.print(currNode.data);
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

	public static void main(String[] args) {
//		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//		bst.add(7);
//		bst.add(5);
//		bst.add(20);
//		bst.add(4);
//		bst.add(6);
//
//		bst.preOrder();
//
//		bst.inOrder();
//
//		bst.postOrder();
//
//		bst.levelOrderTransversal();

		testRemoval();
	}


	/*
	 *                7
	 *              /   \
	 *            5      20
	 *          /        /  \
	 *        4        18    25
	 *       /        / \      \
	 *      2        11  19    33
	 *     / \        \       /
	 *    1   3        14     28
                      / \      \
                     12  15    31
                     * */
	private static void testRemoval() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.add(7);
		bst.add(5);
		bst.add(20);
		bst.add(4);
		bst.add(2);
		bst.add(1);
		bst.add(3);
		bst.add(18);
		bst.add(25);
		bst.add(11);
		bst.add(19);
		bst.add(33);
		bst.add(14);
		bst.add(28);
		bst.add(12);
		bst.add(15);
		bst.add(31);

		bst.levelOrderPrettyPrint();

		bst.remove(11);
		bst.levelOrderPrettyPrint();
	}

}
