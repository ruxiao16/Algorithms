package com.xiao.algorithms.datastructures.dynamicarray;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.google.common.truth.Truth.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class IntArrayTest {

	// The @MethodSource annotation relies on a method to supply the arguments for a parameterized test.
	// This method must return a compatible type, which includes:Stream<T> Collection<T>, List<T>, or an array (T[]),
	// but a Stream<T> is the most flexible and preferred option in JUnit 5.
	private static Stream<IntArray> input() {
		// default capacity is 8
		IntArray intArr = new IntArray();
		for (int i = 0; i < 8;i++ ) {
			intArr.add(i);
		}
		return Stream.of(intArr);
	}

	@Test
	void testNegativeSize() {
		assertThrows(Exception.class, () -> new IntArray(-1));
	}

	@Test
	void testDefaultArrCapacity() {
		IntArray arr = new IntArray();
		assertThat(arr.getCapacity()).isEqualTo(1<<3);
	}

	@Test
	void testNonDefaultArrCapacity() {
		IntArray arr = new IntArray(5);
		assertThat(arr.getCapacity()).isEqualTo(5);
	}

	@Test
	void testSet() {
		IntArray arr = new IntArray(2);
		arr.set(0, 10);
		assertThat(arr.get(0)).isEqualTo(10);
	}

	@Test
	void testIdxOutOfBoundWhenSet() {
		IntArray arr = new IntArray(2);
		assertThrows(IndexOutOfBoundsException.class, () -> arr.set(2,10));
	}


	@ParameterizedTest
	@MethodSource("input")
	void testCapacityDoubles(IntArray arr) {
		arr.add(8);
		assertThat(arr.getCapacity()).isEqualTo(1<<4);
		assertThat(arr.get(8)).isEqualTo(8);
	}

	@ParameterizedTest
	@MethodSource("input")
	void testRemoveAt(IntArray arr) {
		arr.removeAt(3);
		assertThat(arr.len).isEqualTo((1<<3)-1);
		assertThat(arr.get(3)).isEqualTo(4);
	}

	@ParameterizedTest
	@MethodSource("input")
	void testRemove(IntArray arr) {
		arr.remove(3);
		assertThat(arr.len).isEqualTo((1<<3)-1);
		assertThat(arr.get(3)).isEqualTo(4);
	}

	@ParameterizedTest
	@MethodSource("input")
	void testReverse(IntArray arr) {
		arr.reverse();
		assertThat(arr.len).isEqualTo(1<<3);
		assertThat(arr.get(0)).isEqualTo(7);
	}

}
