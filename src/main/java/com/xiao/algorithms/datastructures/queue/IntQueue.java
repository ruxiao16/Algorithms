/*
	An integer only queue which is extremely quick and lightweight.
	It can outperform java.util.ArrayDeque (Java's fastest queue implementation) by a factor of 40+!
	See the benchmark test below. The downside is you need to know an upper bound on the number of
	elements that will be inside the queue at any given time for this queue to work
 */
package com.xiao.algorithms.datastructures.queue;

import java.util.ArrayDeque;

public class IntQueue implements Queue<Integer> {
	private int[] data;
	private int front, end;
	private int size;

	public IntQueue(int maxSize) {
		front = end = size = 0;
		data = new int[maxSize];
	}

	@Override
	public void offer(Integer value) {
		if (isFull()) {
			throw new RuntimeException("Queue too small!");
		}
		data[end++] = value;
		size++;
		end = end % data.length; // this reset the end to zero-> first entry, when the last spot is taken
	}

	@Override
	public Integer poll() {
		if (size == 0) {
			throw new RuntimeException("Queue is empty");
		}
		size--;
		front = front % data.length;
		return data[front++];
	}

	@Override
	public Integer peek() {
		if (isEmpty()) {
			throw new RuntimeException("Queue is empty");
		}
		front = front % data.length;
		return data[front];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private boolean isFull() {
		return size == data.length;

	}

	private int endIs() {
		return end;
	}

	public int frontIs() {
		return front;
	}

	public static void main(String[] args) {
		IntQueue q = new IntQueue(5);
		q.offer(1);
		q.offer(2);
		q.offer(3);
		q.offer(4);
		q.offer(5);

		System.out.println("front is " + q.frontIs());
		System.out.println("end is " + q.endIs());

		System.out.println(q.poll()); // 1
		System.out.println(q.poll()); // 2
		System.out.println(q.poll()); // 3
		System.out.println(q.poll()); // 4

		System.out.println("front is " + q.frontIs());
		System.out.println("end is " + q.endIs());

		System.out.println(q.isEmpty());

		q.offer(1);
		q.offer(2);
		q.offer(3);

		System.out.println("front is " + q.frontIs());
		System.out.println("end is " + q.endIs());

		benchMarkTest();
	}

	private static void benchMarkTest() {
		int n = 10000000;
		IntQueue intQ = new IntQueue(n);

		// IntQueue times at around 0.0324 seconds
		long start =  System.nanoTime();
		for (int i = 0; i < n; i++) intQ.offer(i);
		for (int i = 0; i < n; i++) intQ.poll();
		long end = System.nanoTime();
		System.out.println("IntQueue Time: " + (end - start) / 1e9);

		// ArrayQueue times at around 1.438 seconds
		// ArrayQueue is slower when you give it an initial capacity
		ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
		start = System.nanoTime();
		for (int i = 0; i < n; i++) arrayDeque.offer(i);
		for (int i = 0; i < n; i++) arrayDeque.poll();
		end = System.nanoTime();
		System.out.println("ArrayDeque Time: " + (end - start) / 1e9);
	}
}
