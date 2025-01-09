package com.xiao.algorithms.datastructures.dynamicarray;

import java.util.Arrays;
import java.util.Iterator;

public class IntArray implements Iterable<Integer> {
	private static final int DEFAULT_CAP = 1 << 3;

	public int[] arr;
	public int len = 0;
	public int capacity = 0;

	//Init the arr with default capacity
	public IntArray() {
		this(DEFAULT_CAP);
	}

	public IntArray(int capacity) {
		if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		this.capacity = capacity;
		arr = new int[capacity];
	}

	// given an arr make it a dynamic arr
	public IntArray(int[] array) {
		if (array == null) throw new IllegalArgumentException("Array cannot be null");
		arr = Arrays.copyOf(array, array.length);
		capacity = len = array.length;
	}

	// returns the size of the array
	public int size() {
		return len;
	}

	public int getCapacity() {
		return capacity;
	}

	// returns true/false on whether the array is empty
	public boolean isEmpty() {
		return len == 0;
	}

	// to get/
	public int get(int index) {
		return arr[index];
	}

	public void set(int index, int elem) {
		arr[index] = elem;
	}

	// Add an element to this array
	public void add(int elem) {
		if (len + 1 >= capacity) {
			if (capacity == 0) capacity = 1;
			else capacity *=2;// double the size
			arr = Arrays.copyOf(arr, capacity); // pads with extra 0/null elements
		}
		arr[len++] = elem;
	}

	// removes the element at the specified index in this list.
	// if possible, avoid calling this method as it takes O(n) time to remove
	// an element (since you have to reconstruct the array)
	public void removeAt(int rmIndex) {
		// for example, removeAt(3) for [1,2,3,4,5,6,7]
		// then the below code will copy from arr starting at rmIndex+1,
		// to the destination array at 3 with 7-3-1 = 3 elements, this translates to [5, 6, 7]
		System.arraycopy(arr, rmIndex+1, arr, rmIndex, len - rmIndex -1);
		--len;
		--capacity;
	}

	public boolean remove(int elem) {
		for (int i = 0; i < len; i++) {
			if (arr[i] == elem) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	// reverse the content of this array
	public void reverse() {
		for (int i = len-1; i > len/2; i--) {
			int temp = arr[i];
			arr[i] = arr[len-i-1];
			arr[len-i-1] = temp;
		}
	}

	// perform a binary search on this array to find an element in O(log(n)) time
	// make sure this array is sorted
	public int binarySearch(int key) {
		int index = Arrays.binarySearch(arr, 0, len, key);
		// if (index <0) index = -index-1; // if not found this will tell you where to insert
		return index;
	}

	// sort this array
	public void sort() {
		Arrays.sort(arr, 0, len);
	}


	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < len;
			}

			@Override
			public Integer next() {
				return arr[index++];
			}
		};
	}

	@Override
	public String toString() {
		if (len == 0) return "[]";
		else {
			StringBuilder sb = new StringBuilder(len).append("[");
			for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
			return sb.append(arr[len - 1] + "]").toString();
		}
	}

	// Example usage
	public static void main(String[] args) {

		IntArray ar = new IntArray(50);
		ar.add(3);
		ar.add(7);
		ar.add(6);
		ar.add(-2);

		ar.sort(); // [-2, 3, 6, 7]

		// Prints [-2, 3, 6, 7]
		for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

		// Prints [-2, 3, 6, 7]
		System.out.println(ar);
	}
}
