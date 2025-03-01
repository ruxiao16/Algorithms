/*
 An AVL tree is a balanced BST, allows for logarithmic (log(n)) insertion, deletion and search.

 Balance factor  BF(node) = H(node.right) - H(node.left)

 The invariant in the AVL is that balance factor is always either -1, 0, or +1
 Node information to store
 1. the actual value we are storing in the node
 2. A value storing this node's balance factor
 3. The height of this node in the tree
 4. Pointer to the left/right child nodes


 BF can be adjusted using tree rotations
 for example
*                   20                                   18
*                  /  \                                /   \
*                18    25    --right rotate->         11    20
*               / \                                  /  \   /  \
*              11  19                               10  13 19   25
              / \
             10  13
can be rotated to the right by,
linking left child of 20 to the right child of 18 (right child of 18 is greater than 18)
18 is now the new parent node and its right child is 20 (whose new left child 19 remains greater than 18)

Note that binary search tree invariant remains intact because 10 < 11 < 13 < 18 < 19 < 20 < 25

There are four distinct case
1 left left -> perform right rotation
2 left right -> perform left then right rotation (first rotation turns left right to left left, then we just do a right rotation like in step 1)
3 right right -> perform left rotation
4 right left -> perform right then left rotation
*/

package com.xiao.algorithms.datastructures.binarysearchtree;

public class AvlTree<T extends Comparable<T>> {

	public class Node {
		public int bf; // balance factor
		public T value;
		public int height; // the height of this node in the tree
		public Node left;
		public Node right;

		public Node(T value) {
			this.value = value;
		}
	}

	public Node root; // root of the AVL tree

	private int nodeCount = 0;

	// The height of a rooted tree is the number of edges between the tree's root
	// and its furthest leaf. This means a tree containing a single node has a height of 0.
	public int height() {
		if (root == null) return 0;
		return root.height;
	}

	public int size() {
		return nodeCount;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(T value) {
		return contains(root, value);
	}

	// recursive call
	private boolean contains(Node node, T value) {
		// reach the end and no value found
		if (node == null) {
			return false;
		}

		int cmp = value.compareTo(node.value);
		if (cmp < 0) {
			return contains(node.left, value);
		}
		if (cmp > 0) {
			return contains(node.right, value);
		}
		// found the value
		return true;
	}

	// Insert a value to the AVL tree. The value must not be null
	public boolean insert(T value) {
		if (value == null) return false;
		if (!contains(value)) {
			root = insert(root, value);
			nodeCount++;
			return true;
		}
		return false;
	}


	// Insert a value inside the AVL tree
	private Node insert(Node node, T value) {
		// base case
		if (node == null) return new Node(value);

		int cmp = value.compareTo(node.value);
		if (cmp < 0) {
			node.left = insert(node.left, value);
		} else {
			// cmp wont be zero because contains checks that
			node.right = insert(node.right, value);
		}

		// update balance factor and height values, this is done on the callback
		update(node);
		// re-balance tree
		return balance(node);
	}

	// update a node's height and bf
	private void update(Node node) {
		int leftNodeHeight = (node.left == null) ?  -1: node.left.height;
		int rightNodeHeight = (node.right == null) ? -1: node.right.height;

		// Update this node's height
		node.height =  1 + Math.max(leftNodeHeight, rightNodeHeight);

		// update balance factor
		node.bf = rightNodeHeight - leftNodeHeight;
	}

	private Node balance(Node node) {
		//left heavy subtree
		if (node.bf == -2) {
			// left left case
			if (node.left.bf <= 0) {
				return leftLeftCase(node);
			} else {
				return leftRightCase(node);
			}
			// right heavy subtree
		} else if (node.bf == +2) {
			// right right
			if (node.right.bf >= 0) {
				return rightRightCase(node);
			} else {
				// right left case
				return rightLeftCase(node);
			}
		}
		// Node has a balance factor of {-1, 0, 1} which is fine
		return node;
	}

	private Node leftLeftCase(Node node) {
		return rightRotation(node);
	}

	private Node leftRightCase(Node node){
		node.left = leftRotation(node.left);
		return leftLeftCase(node);
	}

	private Node rightRightCase(Node node) {
		return leftRotation(node);
	}

	private Node rightLeftCase(Node node) {
		node.right = rightRotation(node.right);
		return rightRightCase(node);
	}

	private Node leftRotation(Node node) {
		Node newParent = node.right;
		node.right = newParent.left;
		newParent.left = node;
		update(node);
		update(newParent);
		return newParent;
	}

	private Node rightRotation(Node node) {
		Node newParent = node.left;
		node.left = newParent.right;
		newParent.right = node;
		update(node);
		update(newParent);
		return newParent;
	}

	private boolean remove(T elem) {
		if (elem == null) return false;

		if (contains(root, elem)) {
			root = remove(root, elem);
			nodeCount--;
			return true;
		}
		return false;
	}

	// the impl is the same as what is BinarySearchTree, the only difference is the update and rebalance
	private Node remove(Node node, T elem) {
		if (node == null) return null;

		int cmp = elem.compareTo(node.value);

		// left subtree, value we are looking for is smaller than the current value
		if (cmp < 0) {
			node.left = remove(node.left, elem);
		} else if (cmp > 0) {
			node.right = remove(node.right, elem);
		} else {
			// only a right subtree or no subtree at all. We just swap the node we wish to remove
			// with the right child
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				// two subtrees, as a heuristic, we will remove from the subtree with the greatest height in hope that this may help with rebalancing
				if (node.left.height > node.right.height) {
					// Swap the value of the successor into the node
					T successorVal = findMax(node.left);
					node.value = successorVal;

					// remove the duplicate, note that this will fall into one of the 3 case eventually, no subtree or one subtree
					node.left = remove(node.left, successorVal);
				} else {
					T successorVal = findMin(node.right);
					node.value = successorVal;

					node.right = remove(node.right, successorVal);
				}
			}
		}

		update(node); // update bf and height value
		return balance(node);
	}

	private T findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node.value;
	}

	private T findMax(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node.value;
	}
}
