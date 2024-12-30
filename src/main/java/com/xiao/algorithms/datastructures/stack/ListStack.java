package com.xiao.algorithms.datastructures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class ListStack<T> implements Iterable<T>, Stack<T> {
	private LinkedList<T> list = new LinkedList<>();

	public ListStack() {}

	public ListStack(T firstElem) {
		push(firstElem);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void push(T elem) {
		list.addLast(elem);
	}

	@Override
	public T pop() {
		if (isEmpty()) throw new EmptyStackException();
		return list.removeLast();
	}

	@Override
	public T peek() {
		if (isEmpty()) throw new EmptyStackException();
		return list.peekLast();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	// Searches for the element starting from top of the stack returns -1 if the element is not present in the stack
	public int search(T elem) {
		return list.indexOf(elem);
	}
}
