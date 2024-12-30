package com.xiao.algorithms.datastructures.stack;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StackTest {

	private static List<Stack<Integer>> inputs() {
		List<Stack<Integer>> stacks = new ArrayList<>();
		stacks.add(new ListStack<>());
		return stacks;
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testEmptyStack(Stack<Integer> stack) {
		assertThat(stack.isEmpty()).isTrue();
		assertThat(stack.size()).isEqualTo(0);
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testPeekOnEmpty(Stack<Integer> stack) {
		assertThrows(Exception.class, ()-> stack.peek());
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testPollOnEmpty(Stack<Integer> stack) {
		assertThrows(Exception.class, ()->  stack.pop());
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testPush(Stack<Integer> stack) {
		stack.push(2);
		assertThat(stack.size()).isEqualTo(1);
	}

	@ParameterizedTest
	@MethodSource("inputs")
	void testPoll(Stack<Integer> stack) {
		stack.push(1);
		assertThat(stack.pop()).isEqualTo(1);
		assertThat(stack.size()).isEqualTo(0);
	}

}
