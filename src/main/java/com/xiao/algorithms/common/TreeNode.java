package com.xiao.algorithms.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode {
	private int id;
	private TreeNode parent;
	private List<TreeNode> children;

	public TreeNode(int id) {
		this(id, /* parent= */ null);
	}

	public TreeNode(int id, TreeNode parent) {
		this.id = id;
		this.parent = parent;
		children = new ArrayList<>();
	}

	public void addChildren(TreeNode... nodes) {
		Collections.addAll(children, nodes);
	}

	public int getId() {
		return id;
	}

	public TreeNode getParent() {
		return parent;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	// Only checks id equality not subtree equality.
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TreeNode) {
			return getId() == ((TreeNode) obj).getId();
		}
		return false;
	}
}
