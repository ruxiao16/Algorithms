package com.xiao.algorithms.neetcode.linkedlist;

import static com.xiao.algorithms.neetcode.linkedlist.ListNode.prettyPrint;

public class MergeSortedLinkedList {

	public ListNode mergeTwoLists(ListNode ptr1, ListNode ptr2) {

		if (ptr1 == null) {
			return ptr2;
		}
		if (ptr2 == null) {
			return ptr1;
		}

		ListNode startPtr = ptr1;
		ListNode currPtr;

		// take the right pointer as the start since it contains the smaller start val
		if (ptr2.val <= ptr1.val) {
			startPtr = ptr2;
			currPtr = ptr2;
			ptr2 = ptr2.next;
		} else {
			// redundant but makes our intention clear
			startPtr = ptr1;
			currPtr = ptr1;

			ptr1 = ptr1.next;
		}

		while (ptr1 != null && ptr2 != null) {
			// if right subset has smaller value,
			// 1, we take it
			// 2. advance its pointer
			if (ptr2.val <= ptr1.val) {
				currPtr.next = ptr2;
				ptr2 = ptr2.next;
			}
			else {
				currPtr.next = ptr1;
				ptr1 = ptr1.next;
			}
			// important, advance the currPtr
			currPtr = currPtr.next;
		}

		// left over
		if (ptr1 != null) {
			currPtr.next = ptr1;
		} else {
			currPtr.next = ptr2;
		}
		return startPtr;
	}

	public ListNode mergeTwoListsSimplified(ListNode list1, ListNode list2) {
		// creates a dummy node, then "append" to it as we go over the two lists
		ListNode dummy = new ListNode(0);
		ListNode node = dummy; // as the current node

		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				node.next = list1;
				list1 = list1.next;
			} else {
				node.next = list2;
				list2 = list2.next;
			}
			node = node.next;

			if (list1 != null) {
				node.next = list1;
			} else {
				node.next = list2;
			}
		}
		// clever, returns the next of the dummy node we created.
		return dummy.next;
	}

	public ListNode mergeTwoListsRecursively(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		if (list1.val <= list2.val) {
			list1.next = mergeTwoListsRecursively(list1.next, list2);
			return list1;
		} else {
			list2.next = mergeTwoListsRecursively(list1, list2.next);
			return list2;
		}
	}

	public static void  main(String[] args) {
		ListNode first = new ListNode(2);
		ListNode second = new ListNode(2);
		ListNode third = new ListNode(4);

		first.next = second;
		second.next = third;

		ListNode fourth = new ListNode(1);
		ListNode fifth = new ListNode(3);
		ListNode sixth = new ListNode(5);

		fourth.next = fifth;
		fifth.next = sixth;

		MergeSortedLinkedList driver = new MergeSortedLinkedList();
		ListNode returnNode = driver.mergeTwoLists(first, fourth);

		prettyPrint(returnNode);
	}
}
