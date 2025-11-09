package com.xiao.algorithms.neetcode.linkedlist;

public class ListNode {
	int val;

	ListNode next;

	ListNode() {}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public static void prettyPrint(ListNode head) {
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
