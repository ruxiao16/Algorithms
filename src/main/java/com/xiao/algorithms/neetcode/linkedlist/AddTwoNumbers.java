package com.xiao.algorithms.neetcode.linkedlist;

import static com.xiao.algorithms.neetcode.linkedlist.ListNode.prettyPrint;

public class AddTwoNumbers {
	public ListNode addTwoNumbersOldAttempt(ListNode l1, ListNode l2) {
		int extra = 0;
		ListNode head = l1;
		ListNode l1End = null;

		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		while (l1 != null && l2 != null) {
			int sum = l1.val+ l2.val + extra;
			extra = sum/10;
			int left = sum%10;

			l1.val = left;
			// store the end node of l1 in case l1 is shorter than l2
			if (l1.next == null) {
				l1End = l1;
			}
			l1 = l1.next;
			l2 = l2.next;
		}

		// l1 has element left append rest of the l1
		while (l1 != null) {
			int sum = l1.val + extra;
			extra = sum/10;
			l1.val = sum%10;
			l1 = l1.next;

			// reach the end
			if (l1 == null) {
				if (extra > 0) {
					l1 = new ListNode(extra);
					l1.next = null; // for safety
					return head;
				}
			}
		}

		// l2 has elements left,
		ListNode newL2 = l2;
		while (l2 != null) {
			int sum = l2.val + extra;
			extra = sum/10;
			l2.val = sum%10;
			l2 = l2.next;

			// reached the end
			if (l2 == null) {
				if (extra > 0) {
					l2 = new ListNode(extra);
					l2.next = null;
					l1End.next = newL2;
					return head;
				}
			}
		}

		// two list are equal length
		if (extra > 0) {
			l1End.next = new ListNode(extra);
		}

		return head;
	}


	// the trick is creates a subsitute of zero for the null pointers,
	// so we can keep advancing our pointers for the longer list
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode();
		ListNode curr = dummy;

		int carry = 0;

		while (l1 != null || l2 != null || carry != 0) {
			int val1 = (l1 == null) ? 0 : l1.val;
			int val2 = (l2 == null) ? 0 : l2.val;

			int sum = val1 + val2 + carry;
			int left = sum % 10;
			carry = sum/10;

			curr.next = new ListNode(left); // create a new node based on the sum
			curr = curr.next; // advance

			//advance l1 and l2
			l1 = (l1 != null) ? l1.next : null;
			l2 = (l2 != null) ? l2.next : null;
		}

		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode list1Node1 = new ListNode(1);
		ListNode list1Node2 = new ListNode(6);
		ListNode list1Node3 = new ListNode(5);
		list1Node1.next = list1Node2;
		list1Node2.next = list1Node3;

		ListNode list2Node1 = new ListNode(4);
		ListNode list2Node2 = new ListNode(5);
		ListNode list2Node3 = new ListNode(6);
		list2Node1.next = list2Node2;
		list2Node2.next = list2Node3;

		AddTwoNumbers driver =  new AddTwoNumbers();
		ListNode result = driver.addTwoNumbers(list1Node1, list2Node1);
		prettyPrint(result);
	}
}
