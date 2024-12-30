package com.xiao.algorithms.datastructures.queue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueTest {

	private static List<Queue<Integer>> inputs() {
		List<Queue<Integer>> queues = new ArrayList<>();
		queues.add(new LinkedQueue<>());
		queues.add(new IntQueue(2));
		queues.add(new ArrayQueue<>(2));
		return queues;
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testEmptyQueue(Queue<Integer> queue) {
		assertThat(queue.isEmpty()).isTrue();
		assertThat(queue.size()).isEqualTo(0);
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testPollOnEmpty(Queue<Integer> queue) {
		assertThrows(Exception.class, ()-> queue.poll());
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testPeekOnEmpty(Queue<Integer> queue) {
		assertThrows(Exception.class, ()-> queue.peek());
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testOffer(Queue<Integer> queue) {
		queue.offer(2);
		assertThat(queue.size()).isEqualTo(1);
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testPoll(Queue<Integer> queue) {
		queue.offer(2);
		assertThat(queue.poll()).isEqualTo(2);
		assertThat(queue.size()).isEqualTo(0);
	}
}
