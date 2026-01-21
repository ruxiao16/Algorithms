package com.xiao.algorithms.neetcode.twopointers;

public class ContainerWithMostWater {


	// start two pointer, calculate the area = min(startPtr, maxPtr) * (maxPtr - startPtr)
	// move the smaller of the two pointers inward

	// the intuition is that We calculate the area consisting of left and right, and H(left) is the smaller one
	// Since H(left) is the shorter side, it is the bottleneck. so the area is capped at H(left).
	// If we keep left and try to pair it with any other line to the inside (right -1, right -2), the width would decrease.
	// and the area cannot be possibly larger than what we had because width decreases and Height stays the same (or gets lower), the
	// area MUST be smaller.
	// Therefore, left has already found its maximum possible area with its furthest possible partner (right), we can safely discard left

	public int maxArea(int[] heights) {
		int right = heights.length-1;
		int maxArea = -1;
		int left = 0;

		while (left < right) {
			int leftH= heights[left];
			int rightH = heights[right];
			int minH = Math.min(leftH, rightH);

			int currArea = minH * (right - left);
			if (currArea > maxArea) {
				maxArea = currArea;
			}

			if (minH == leftH) {
				left++;
			}
			else {
				right --;
			}

		}
		return maxArea;
	}


	public static void main(String[] args) {
		ContainerWithMostWater driver =  new ContainerWithMostWater();
		int[] heights = new int[] {1,7,2,5,4,7,3,6};

		System.out.println("max area is " + driver.maxArea(heights));
	}
}
