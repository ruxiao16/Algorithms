package com.xiao.algorithms.neetcode.linkedlist;

import static com.xiao.algorithms.neetcode.linkedlist.DoublyLinkedNode.prettyPrint;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

	Map<Integer, DoublyLinkedNode> lookupMap;
	int capacity;
	DoublyLinkedNode lRUPtr;
	DoublyLinkedNode mRUPtr;


	public LruCache(int capacity) {
		this.capacity = capacity;
		lookupMap = new HashMap<>();

		lRUPtr = new DoublyLinkedNode(-1, -1);
		lRUPtr.prev = null;
		mRUPtr = new DoublyLinkedNode(-1, -1);
		mRUPtr.next = null;
		mRUPtr.prev = lRUPtr;
		lRUPtr.next = mRUPtr;
	}

	public int get(int key) {
		if (lookupMap.containsKey(key)) {
			moveToTheEnd(lookupMap.get(key));

			return lookupMap.get(key).val;
		}
		else {
			return -1;
		}
	}

	public void put(int key, int value) {
		//existing node, replace the value
		if (lookupMap.containsKey(key)) {
			DoublyLinkedNode existingNode = lookupMap.get(key);
			existingNode.val = value;

			// if only one node, nothing needs to be done
			if (lookupMap.size() > 1) {
				// move the updated node to the end (MRU)
				moveToTheEnd(existingNode);
			}
		}

		// new node,
		else {
			// if capacity is not exceeded
			if (lookupMap.size() < capacity) {
				DoublyLinkedNode newEndNode = new DoublyLinkedNode(key, value);
				appendToTheEnd(newEndNode);
				lookupMap.put(key, newEndNode);
			} else {
				// take out the front node (LRU); remove from the map and insert new one in the map
				int removedKey = removeFront();
				DoublyLinkedNode newEndNode = new DoublyLinkedNode(key, value);
				appendToTheEnd(newEndNode);

				lookupMap.remove(removedKey);
				lookupMap.put(key, newEndNode);
			}
		}
	}

	// move an existing node to the end
	private void moveToTheEnd(DoublyLinkedNode node) {
		DoublyLinkedNode nodeBefore = node.prev;
		DoublyLinkedNode nodeAfter = node.next;
		DoublyLinkedNode prevLastNode = mRUPtr.prev;

		// node to be moved is already at the end, do nothing
		if (nodeAfter == mRUPtr) return;

		nodeBefore.next = nodeAfter;
		nodeAfter.prev = nodeBefore;

		// link the new end node to the previous last node
		prevLastNode.next = node;
		node.prev = prevLastNode;
		// link the new end node to the mRUPtr
		node.next = mRUPtr;
		mRUPtr.prev = node;
	}

	private void appendToTheEnd(DoublyLinkedNode node) {
		// this logic can be extracted out since it is identical to that of moveToTheEnd
		DoublyLinkedNode prevLastNode = mRUPtr.prev;

		prevLastNode.next = node;
		node.prev = prevLastNode;

		node.next = mRUPtr;
		mRUPtr.prev = node;
	}

	private int removeFront() {
		DoublyLinkedNode frontNode = lRUPtr.next;
		DoublyLinkedNode newFront = frontNode.next;

		lRUPtr.next = newFront;
		newFront.prev = lRUPtr;

		// for safety?
		frontNode.prev = null;
		frontNode.next = null;

		return frontNode.key;
	}


	public static void main(String[] args) {
		LruCache cache = new LruCache(3);
		cache.put(1, 10);
		cache.put(2, 20);
		cache.put(3, 30);

		System.out.println("original list : ");
		DoublyLinkedNode head = cache.lRUPtr;
		prettyPrint(head);

		int result1st = cache.get(2);
		System.out.println("accessing key value 2: "+ result1st);
		prettyPrint(head);

		cache.put(4, 40);
		System.out.println("inserting new key value 4: "+ result1st);
		prettyPrint(head);

		int result2nd = cache.get(1);
		System.out.println("accessing key value 1: "+ result2nd);
		prettyPrint(head);
	}
}
