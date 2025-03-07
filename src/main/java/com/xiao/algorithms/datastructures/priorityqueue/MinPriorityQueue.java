/*
	A pq is when poll() always return the greatest or the smallest depending on which
	queue it is. Usually implemented by max heap or min heap. A max heap is a tree
	where the parent node is always larger than the children node. A min heap is a tree
	where the parent node is always smaller than the children node.

	We will demonstrate a min PQ here using a binary heap. Note that binary tree here is represented
	using an array (list) where 0th element is the root, 1st element is the left child of root, 2nd element is the
	right child of root. So 2*n+1 is the left child and 2*n+2 is the right child where n is the index of a node in an array
 */


package com.xiao.algorithms.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinPriorityQueue<T extends Comparable<T>> {
	// A dynamic list to keep track the elements inside the heap
	private List<T> heap = null;

	public MinPriorityQueue() {
		this(1);
	}

	public MinPriorityQueue(int sz) {
		heap = new ArrayList<>(sz);
	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public T peek() {
		if (heap.isEmpty()) return null;
		return heap.get(0);
	}

	public T poll() {
		if (heap.isEmpty()) return null;
		return removeAt(0);
	}


	// PQ construction O(n)
	public MinPriorityQueue(Collection<T> elems) {
		int heapSize = elems.size();
		heap = new ArrayList<T>(heapSize);
		heap.addAll(elems);
		// Heapify process O(n)
		// http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
		for (int i = Math.max(0, (heapSize/2)-1); i >= 0; i--) {
			sink(i);
		}
	}

	/*
	Heapify process, the idea is to start at the second to the last layer

    3 layers size 1+2+4=7 (7/2)-1 = 2
    4 layers 1+2+4+8=15 (15/2)-1 = 6
    5 layers 1+2+4+8+16=31 (31/2)-1= 14

    Example  [13, 15, 28, 9, 8, 6, 99] (7/2) -1 = 2

		    13
		   /  \
		  15  18
		 / \  / \
		9  8 6  99

		After sink(2), we have

		    13
		   /  \
		  15   6
		 / \  / \
		9  8 18  99

		sink(1)

		    13
		   /  \
		  8    6
		 / \  / \
		9  15 18 99

		sink(0) first iteration

		    6
		   /  \
		  8    13
		 / \   / \
		9  15 18 99

		sink(0) second iteration
		    6
		   /  \
		  8    18
		 / \   / \
		9  15 13 99

	 */


	private void sink(int k) {
		int heapSize = size();
		while (true) {
			int left = 2 * k + 1;
			int right = 2 * k + 2;
			int smallest = left; // assuming left is the smallest node of the two children

			// set smallest to right if it is smaller
			// we are picking the smaller of two here because we min heap invariant requires the parent
			// node to be smaller than children nodes
			if (right < heapSize && less(right, left)) {
				smallest = right;
			}

			// stop if we are outside the bounds of the tree or stop early if we cannot sink k anymore
			// cannot sink k because k is larger than both of the children nodes
			if (left >= heapSize || less(k, smallest)) break;

			//Move down the tree following the smallest node
			swap(smallest, k);
			k = smallest; // this set the k to the next pair of nodes we have to check against as a node swims down
		}
	}


	private boolean less(int i, int j) {
		T node1 = heap.get(i);
		T node2 = heap.get(j);
		return node1.compareTo(node2) <= 0;
	}

	private void swap(int i, int j) {
		T elemI = heap.get(i);
		T elemJ = heap.get(j);

		heap.set(i, elemJ);
		heap.set(j, elemI);
	}

	private void swim(int k) {
		int parent = (k - 1)/2;

		// keep swimming while the node have not reached the root
		// and parent is still larger than the node
		while ((k > 0) && less(k, parent)) {
			swap(k, parent);
			k = parent;
			// index of the new parent
			parent = (k-1)/2;
		}
	}

	public void add(T elem) {
		if (elem == null) throw new IllegalArgumentException();

		heap.add(elem);
		int idxOfLastElem = size() -1;
		swim(idxOfLastElem);
	}

	public boolean remove(T element) {
		if (element == null) return false;
		// linear search, O(n), we can improve this by using a hashmap to store value, indices for faster operation
		for (int i = 0; i < heap.size(); i++) {
			if (element.equals(heap.get(i))) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	// remove a node at a particular index O(log(n))
	private T removeAt(int i) {
		if (isEmpty()) return null;
		int indexOfLastElem = heap.size() - 1;

		T removedVal = heap.get(i);
		swap(i, indexOfLastElem);
		//remove from the array, note that the value to be removed is now at the end
		heap.remove(indexOfLastElem);

		// target value is the last value, nothing extra needs to be done
		if (i == indexOfLastElem) return removedVal;

		T elem = heap.get(i);
		// attempt to sink
		sink(i);

		// if value didn't sink, we try to swim it up
		if (elem.equals(heap.get(i))) swim(i);
		return removedVal;
	}

	public void prettyPrint() {
		if (heap == null || isEmpty()) {
			System.out.println("Empty array, cannot form a tree.");
			return;
		}

		int startIndex = 0;
		int nodesInLevel = 1;

		while (startIndex < size()) {
			for (int i = 0; i < nodesInLevel && startIndex < size(); i++) {
				System.out.print(heap.get(startIndex));
				startIndex++;
				if (i < nodesInLevel - 1 && startIndex < size()) {
					System.out.print(" "); // Space between nodes on the same level
				}
			}
			System.out.println(); // New line for the next level
			nodesInLevel *= 2; // Double nodes for the next level
		}
	}


	public static void main(String[] args) {
		List<Integer> newArray = Arrays.asList(13, 15, 28, 9, 8, 6, 99);
		MinPriorityQueue<Integer> minPQ = new MinPriorityQueue<>(newArray);
		minPQ.prettyPrint();
		System.out.println();

		minPQ.remove(13);
		minPQ.prettyPrint();
		System.out.println();

		minPQ.add(1);
		minPQ.prettyPrint();
		System.out.println();

		minPQ.poll();
		minPQ.prettyPrint();
	}
}
