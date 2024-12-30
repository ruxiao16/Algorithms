package com.xiao.algorithms.linkedlist;

import java.util.Iterator;
import java.util.Objects;

public class DoublyLinkedList<T> implements Iterable<T> {

	private static class Node<T> {
		private T data;
		private Node<T> prev, next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	// Empty this linked list, 0(n)
	public void clear() {
		Node<T> trav = head;
		while (trav != null) {
			Node<T> next = trav.next;
			// clean up the current node
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		head = tail = trav = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	// Add an element to the tail of the linked list, O(1)
	public void add(T elem) {
		addLast(elem);
	}

	public void addLast(T elem) {
		if (isEmpty()) {
			head = tail = new Node<> (elem, null, null);
		} else {
			tail.next = new Node<>(elem, tail, null );
			tail = tail.next;
		}
		size++;
	}

	// Add an element to the beginning of the linked list O(1)
	public void addFirst(T elem) {
		if (isEmpty()) {
			head = tail = new Node<> (elem, null, null);
		} else {
			head.prev = new Node<>(elem, null, head);
			head = head.prev;
		}
		size ++;
	}

	public void addAt(int index, T data) throws Exception {
		if (index < 0 || index > size) {
			throw new Exception("Illegal index");
		}
		if (index == 0) {
			addFirst(data);
			return;
		}

		if (index == size) {
			addLast(data);
			return;
		}

		Node<T> temp = head;
		// iterate to the node that is before where the insertion happens
		for (int i = 0; i < index -1; i++) {
			temp = temp.next;
		}
		// at this moment, for our new node, the previous is the temp, and next is the temp.next
		Node<T> newNode = new Node<>(data, temp, temp.next);
		// update the node being "shuffled" back's prev link
		temp.next.prev = newNode;
		temp.next = newNode;
		size++;
	}

	// check the value of the first node if it exists, O(1)
	public T peekFirst() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		return head.data;
	}

	public T peekLast() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		return tail.data;
	}

	// remove the first value at the head of the linked list,
	public T removeFirst() {
		// cant remove data from any empty list
		if (isEmpty()) throw new RuntimeException("Empty list");

		T temp = head.data;
		if (size == 1) {
			head = tail = null;
			--size;
			return temp;
		}
		// store head in a temp
		// navigate to the next, set its prev to null, set it as the new head
		head = head.next;
		head.prev = null;
		--size;
		return temp;
	}

	public T removeLast() {
		if (isEmpty()) throw new RuntimeException("Empty list");

		T temp = tail.data;
		if (size == 1) {
			head = tail = null;
			--size;
			return temp;
		}
		// navigate to the previous, set its next to null, set it as the new tail
		tail = tail.prev;
		tail.next = null;
		--size;
		return temp;
	}

	// remove an arbitrary node from the linked list, O(1)
	private T remove(Node<T> node) {
		// If the node to remove is somewhere either at the head or the tail handle these independently
		if (node.prev == null) return removeFirst();
		if (node.next == null) return removeLast();

		// navigate to the node (annotated as node 1) before the node we want to remove,
		// also navigate to the node (annotated as node 2) after the node we want to remove
		// update node 1's next to node 2
		// update node 2's prev to node 1

//		Node node1 = head;
//		while (node1.next.data != node.data) {
//			node1 = node1.next;
//		}
//		Node node2 = tail;
//		while (node2.prev.data != node.data) {
//			node2 = node2.prev;
//		}
//		node1.next = node2;
//		node2.prev = node1;

		//Make teh pointers of adjacent nodes kip over 'node'
		node.next.prev = node.prev;
		node.prev.next = node.next;

		//Temporarily store the data we want to return
		T data = node.data;
		//clean up
		node.data = null;
		node = node.prev = node.next = null;

		--size;
		return data;
	}

	// Remove a node at a particular index, O(n)
	public T removeAt(int index)  {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}

		int i;
		Node<T> trav;
		// search from the front of the list -> below impl is a binary search. we know where it is at, so we move the
		// pointer either from the head or the tail, depending on which one is closer to the target
		if (index < size/2) {
			for (i = 0, trav = head; i != index; i++) {
				trav = trav.next;
			}
			// search from the back of the list
		} else {
			for (i = size-1, trav = tail; i != index; i--) {
				trav = trav.prev;
			}
		}
		return remove(trav);
	}

	// Remove a particular value in the linked list, O(n)
	public boolean remove(Object obj) {
		Node<T> trav;

		// Support searching for null
		for (trav = head; trav != null; trav = trav.next) {
			// This handles even if obj is null
			if (Objects.equals(obj, trav.data)) {
				remove(trav);
				return true;
			}
		}
		return false;
	}

	// Find the index of a particular value in the linked list
	public int indexOf(Object obj) {
		Node<T> trav = head;
		int i;
		for (i = 0; i < size; i++) {
			if (Objects.equals(obj, trav.data)) {
				return i;
			}
			trav = trav.next;
		}
		// not found
		return -1;
	}

	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}



	@Override
	public Iterator<T> iterator() {
		return new Iterator<>() {
			private Node<T> trav = head;
			@Override
			public boolean hasNext() {
				return trav != null;
			}

			@Override
			public T next() {
				T data = trav.data;
				trav = trav.next;
				return data;
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> trav = head;
		while (trav != null) {
			sb.append(trav.data);
			if (trav.next != null) {
				sb.append(", ");
			}
			trav = trav.next;
		}
		sb.append(" ]");
		return sb.toString();
	}
}
