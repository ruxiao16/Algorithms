package com.xiao.algorithms.neetcode.tries;

import java.util.HashMap;
import java.util.Map;

public class PrefixTree {

	Node root;

	class Node {
		Character value;
		Map<Character, Node> childrenNodes;
		boolean isWord;

		public Node(Character value) {
			this.value = value;
			childrenNodes = new HashMap<>();
		}
	}

	public PrefixTree() {
		root = new Node(null);
		root.isWord = false;
	}

	public void insert(String word) {
		Node currNode = root;
		for (int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			// we found the char and advance
			if (currNode.childrenNodes.containsKey(currChar)) {
				currNode = currNode.childrenNodes.get(currChar);
			}
			// we don't find the char, creating a new one and advance
			else {
				Node newNode = new Node(currChar);
				currNode.childrenNodes.put(currChar, newNode);
				currNode = newNode;
			}

			if (i == word.length() - 1) {
				currNode.isWord = true;
			}
		}
	}

	public boolean search(String word) {
		Node currNode = root;
		for (int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			if (currNode.childrenNodes.containsKey(currChar)) {
				currNode = currNode.childrenNodes.get(currChar);
			}
			else {
				return false;
			}
		}

		return currNode.isWord;
	}

	public boolean startsWith(String prefix) {
		Node currNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			char currChar = prefix.charAt(i);
			if (currNode.childrenNodes.containsKey(currChar)) {
				currNode = currNode.childrenNodes.get(currChar);
			} else {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		PrefixTree prefixTree = new PrefixTree();
		prefixTree.insert("dog");

		prefixTree.insert("doge");
		prefixTree.insert("doll");
		System.out.println(prefixTree.search("dog"));
		System.out.println(prefixTree.search("do"));
		System.out.println(prefixTree.search("doll"));
		System.out.println(prefixTree.search("doge"));
		prefixTree.insert("do");
		System.out.println(prefixTree.search("do"));
		System.out.println(prefixTree.startsWith("dod"));
	}
}
