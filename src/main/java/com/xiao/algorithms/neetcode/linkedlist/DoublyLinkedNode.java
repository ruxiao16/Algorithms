package com.xiao.algorithms.neetcode.linkedlist;

public class DoublyLinkedNode {
	public int key;
	public int val;
	public DoublyLinkedNode prev;
	public DoublyLinkedNode next;

	public DoublyLinkedNode() {
		// empty on purpose
	}

	public DoublyLinkedNode(int key, int val) {
		this.key = key;
		this.val = val;
	}

	public static void prettyPrint(DoublyLinkedNode head) {
		while(head != null) {
			if (head.next != null) {
				System.out.print(head.val + "->");
			}
			else {
				System.out.print(head.val);
			}
			head = head.next;
		}
		System.out.println();
	}
}
