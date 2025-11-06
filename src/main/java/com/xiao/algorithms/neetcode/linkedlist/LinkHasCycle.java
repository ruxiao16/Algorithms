package com.xiao.algorithms.neetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkHasCycle {

	// requires O(n) space. time is O(n)
	public boolean hasCycleUsingSet(ListNode head) {
		Set<ListNode> existCheck = new HashSet<>();
		while (head != null) {
			if (existCheck.contains(head)) {
				return true;
			}
			existCheck.add(head);
			head = head.next;
		}

		return false;
	}

	// the fast pointer travels 2 while slow pointer travels 1. They will eventually meet if there is a cycle
	// because say the gap between them is 10, slow pointer moves 1 then gap is 11, fast pointer moves 2 and gap is 9.
	// Eventually fast point catches up and meet at a node. If there is no cycle, fast point will reach the end first
	public boolean hasCycleTwoPointers(ListNode head) {
		ListNode fastPointer = head;
		ListNode slowPointer = head;

		while (fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;

			if (fastPointer == slowPointer) {
				return true;
			}
		}

		return false;
	}


	public static void main(String[] args) {
		ListNode first = new ListNode(0);
		ListNode second = new ListNode(1);
		ListNode third = new ListNode(2);
		ListNode fourth = new ListNode(3);

		first.next = second;
		second.next = third;
		third.next = fourth;
		fourth.next = second;


		LinkHasCycle driver = new LinkHasCycle();
		System.out.println(driver.hasCycleUsingSet(first));

		System.out.println(driver.hasCycleTwoPointers(first));
	}
}
