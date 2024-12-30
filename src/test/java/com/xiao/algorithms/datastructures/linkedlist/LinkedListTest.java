package com.xiao.algorithms.datastructures.linkedlist;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xiao.algorithms.linkedlist.DoublyLinkedList;

public class LinkedListTest {
	private static final int LOOPS = 10000;
	private static final int TEST_SZ = 400;
	private static final int NUM_NULLS = TEST_SZ / 5;
	private static final int MAX_RAND_NUM = 250;

	DoublyLinkedList<Integer> list;

	@BeforeEach
	public void setup() {
		list = new DoublyLinkedList<>();
	}

	@Test
	void testEmptyList() {
		assertThat(list.isEmpty()).isTrue();
		assertThat(list.size()).isEqualTo(0);
	}

	@Test
	void testRemoveFirstOfEmpty() {
		assertThrows(Exception.class, () -> list.removeFirst());
	}

	@Test
	void testRemoveLastOfEmpty() {
		assertThrows(Exception.class, () -> list.removeLast());
	}

	@Test
	void testPeekFirstOfEmpty() {
		assertThrows(Exception.class, () -> list.peekFirst());
	}

	@Test
	void testPeekLastOfEmpty() {
		assertThrows(Exception.class, () -> list.peekLast());
	}

	@Test
	void testAddFirst() {
		list.addFirst(3);
		assertThat(list.size()).isEqualTo(1);
		list.addFirst(5);
		assertThat(list.size()).isEqualTo(2);
	}

	@Test
	void testAddLast() {
		list.addLast(3);
		assertThat(list.size()).isEqualTo(1);
		list.addLast(5);
		assertThat(list.size()).isEqualTo(2);
	}

	@Test
	void testAddAt() throws Exception {
		list.addAt(0, 1);
		list.addAt(1,2);
		list.addAt(1, 3);
		list.addAt(2,4);
		list.addAt(1,8);
		assertThat(list.size()).isEqualTo(5);
	}

	@Test
	void testRemoveFirst() {
		list.addFirst(3);
		assertThat(list.removeFirst()).isEqualTo(3);
		assertTrue(list.isEmpty());
	}

	@Test
	void testRemoveLast() {
		list.addFirst(4);
		assertThat(list.removeLast()).isEqualTo(4);
		assertTrue(list.isEmpty());
	}

	@Test
	void testPeekFirst() {
		list.addFirst(4);
		assertThat(list.peekFirst()).isEqualTo(4);
		assertThat(list.size()).isEqualTo(1);
	}

	@Test
	void testPeekLast() {
		list.addLast(4);
		assertThat(list.peekLast()).isEqualTo(4);
		assertThat(list.size()).isEqualTo(1);
	}


	@Test
	public void testRemoving() {
		DoublyLinkedList<String> strs = new DoublyLinkedList<>();
		strs.add("a");
		strs.add("b");
		strs.add("c");
		strs.add("d");
		strs.add("e");
		strs.add("f");
		strs.remove("b");
		strs.remove("a");
		strs.remove("d");
		strs.remove("e");
		strs.remove("c");
		strs.remove("f");
		assertThat(strs.size()).isEqualTo(0);
	}

}
