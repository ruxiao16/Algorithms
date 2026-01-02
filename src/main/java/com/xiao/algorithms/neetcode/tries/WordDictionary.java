package com.xiao.algorithms.neetcode.tries;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
	Node root;

	class Node {
		Map<Character, Node> childrenNodes;
		boolean isWord;

		public Node() {
			childrenNodes = new HashMap<>();
		}
	}

	public WordDictionary() {
		root = new Node();
		root.isWord = false;
	}

	public void addWord(String word) {
		Node currNode = root;
		for (char eachChar : word.toCharArray()) {
			currNode.childrenNodes.putIfAbsent(eachChar, new Node());
			currNode = currNode.childrenNodes.get(eachChar);
		}
		currNode.isWord = true;
	}

	// using dfs; a brutal force solution will be to create an arraylist to store all the words, and when search,
	// for each word, we compare character by chracter, treat . as equal, --> can exit early if length do not match
	public boolean search(String word) {
		return dfs(word, 0, root);
	}

	public boolean dfs(String word, int idx, Node node) {
		Node currNode = node;
		for (int i = idx; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c == '.') {
				// search all possible nodes
				for (Map.Entry<Character, Node> child : currNode.childrenNodes.entrySet()) {
					if (dfs(word, i +1, child.getValue())) {
						return true;
					}
				}
				// if not found, we return false, return true earlier will be executed if any of the
				// word in the dict matches -> at this point means all path have return false;
				return false;
			}
			else {
				if (!currNode.childrenNodes.containsKey(c)) {
					return false;
				}
				//advance to the next node
				currNode = currNode.childrenNodes.get(c);
			}
		}
		return currNode.isWord;
	}


	public static void main(String[] args) {
		WordDictionary dictionary = new WordDictionary();
		dictionary.addWord("day");
		dictionary.addWord("bay");
		dictionary.addWord("may");
		System.out.println(dictionary.search("say"));
		System.out.println(dictionary.search("day"));
		System.out.println(dictionary.search(".ay"));
		System.out.println(dictionary.search("b.."));
		System.out.println(dictionary.search("..a"));
	}
}
