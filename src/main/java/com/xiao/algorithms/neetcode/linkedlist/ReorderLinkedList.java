package com.xiao.algorithms.neetcode.linkedlist;

import static com.xiao.algorithms.neetcode.linkedlist.ListNode.prettyPrint;

import java.util.ArrayList;
import java.util.List;

public class ReorderLinkedList {

	// brute force, using an array. space O(n), time O(n)
	public void reorderList(ListNode head) {

		if (head == null) return;

		List<ListNode> arr = new ArrayList<>();

		while (head != null) {
			arr.add(head);
			head = head.next;
		}

		int startPtr = 0;
		int endPtr = arr.size() - 1;

		while (endPtr > startPtr) {
			// with this, two items will be appended everytime (note the first node is already present)
			// so for odd number of nodes -> while condition is negated and we are done
			// for even number of nodes, the if condition is met, break
			arr.get(startPtr).next = arr.get(endPtr);
			startPtr ++;
			if (startPtr > endPtr) {
				break;
			}
			arr.get(endPtr).next = arr.get(startPtr);
			endPtr--;
		}
		arr.get(startPtr).next = null;
	}

	// takeaway, use two pointers slow/fast, use reverse linked list,
	public void reorderListInSpace(ListNode head) {
		if (head == null) return;

		ListNode fastPtr = head;
		ListNode slowPtr = head.next;

		while (fastPtr != null && fastPtr.next != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		// at this time slowPtr should be at the midway point,
		// 1->2->3->4->5, mid pt will be 3, sever the second half and reverse then we have 1->2->3   null<-4<-5

		ListNode second = slowPtr.next; // points to the start of second half
		// sever the first half, 1-2-3-> null  also use later inside the for loop to set null<-4-5 on the first iteration
		ListNode prev = slowPtr.next = null;
		// reverse the second half
		while (second != null) {
			ListNode tmp = second.next;
			second.next = prev;
			prev = second; // advance the pointers
			second = tmp;
		}

		ListNode first = head;
		second = prev;

		while (second != null) {
			ListNode tmp1 = first.next;
			ListNode tmp2 = second.next;
			first.next = second;
			second.next = tmp1;
			first = tmp1;
			second = tmp2;
		}
	}
	public static void main(String[] args) {
		ListNode first = new ListNode(0);
		ListNode second = new ListNode(1);
		ListNode third = new ListNode(2);
		ListNode forth = new ListNode(3);
		ListNode fifth = new ListNode(4);
		ListNode sixth = new ListNode(5);
		ListNode seventh = new ListNode(6);

		first.next = second;
		second.next = third;
		third.next = forth;
		forth.next = fifth;
		fifth.next = sixth;
		sixth.next = seventh;

		ReorderLinkedList driver = new ReorderLinkedList();
		driver.reorderList(first);

		prettyPrint(first);
	}
}
