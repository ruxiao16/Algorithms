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
		for (char currChar : word.toCharArray()) {
			// insert the character if we dont find it
			currNode.childrenNodes.putIfAbsent(currChar, new Node(currChar));
			// advance currNode
			currNode = currNode.childrenNodes.get(currChar);
		}
		currNode.isWord = true;
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
		System.out.println(prefixTree.search("dog"));// true
		System.out.println(prefixTree.search("do"));//false
		System.out.println(prefixTree.search("doll"));//true
		System.out.println(prefixTree.search("doge"));//true
		prefixTree.insert("do");
		System.out.println(prefixTree.search("do"));//true
		System.out.println(prefixTree.startsWith("dod"));//false
	}
}
