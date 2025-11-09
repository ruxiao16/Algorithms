package com.xiao.algorithms.neetcode.linkedlist;

import static com.xiao.algorithms.neetcode.linkedlist.ListNode.prettyPrint;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseLinkedList {


	// space complexity O(N), time complexity O(N)
	public ListNode reverseList(ListNode head) {
		// use a stack
		Deque<ListNode> stack = new ArrayDeque<>();

		if (head == null) return null;

		while (head.next != null) {
			stack.push(head);
			head = head.next;
		}

		// now the head is the end node
		// newHead and head are two separate variables that initially point to the same object
		// As head is used to transverse down the stack, it does not impact where newHead is pointing to.
		ListNode newHead = head;

		while (!stack.isEmpty()) {
			ListNode nextNode = stack.pop();
			head.next = nextNode;
			head = nextNode;
		}
		// now the head is at the end again, set its next to null
		head.next = null;

		return newHead;
	}

	// two pointers: Space complexity O(1), time O(N)
	public ListNode reverseListInSpace(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		ListNode temp;

		while ( curr != null) {
			// store where next is before it gets updated with the reverse below
			temp = curr.next;
			curr.next = prev;

			prev = curr;
			curr = temp;
		}
		// quick run down given an array of [2, 3], append null before and after then we have
		// null -> 2 -> 3 -> null
		// before iteration, prev = null, curr = 2
		// after first iteration, temp = 3, prev = 2, current = 3 (and link between null and 2 are reversed)
		// so null <- 2 -> 3 -> null
		// second iteration, temp = null, prev = 3, curr = null (and link between 2 and 3 are reversed)
		// we can see that it ends when curr = null and our end node is pointed by prev
		return prev;
	}

	public static void  main(String[] args) {
		ListNode first = new ListNode(0);
		ListNode second = new ListNode(1);
		ListNode third = new ListNode(2);
		ListNode fourth = new ListNode(3);

		first.next = second;
		second.next = third;
		third.next = fourth;

		prettyPrint(first);

		ReverseLinkedList driver = new ReverseLinkedList();
		ListNode firstRev = driver.reverseList(first);
		prettyPrint(firstRev);

		ListNode secondRev =  driver.reverseListInSpace(firstRev);
		prettyPrint(secondRev);

	}


}
