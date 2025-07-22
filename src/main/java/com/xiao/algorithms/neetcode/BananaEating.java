package com.xiao.algorithms.neetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BananaEating {
	// example
	// Input: piles = [1,4,3,2], h = 9
	// with rate of 2, you can eat the bananas in 6 hours, less than 9 hours
	// with rate of 1, we can eat the bananas in 10 hours, greater than 10
	//10/9 = 1.1 banas per hour

	// Input: piles = [25,10,23,4], h = 4
	// the rate has to be 25 banans/hr

	// h is always greater than or equals to the length of the piles
	// The upper bound (eating speed) for the answer equals to the value of the largets pile,
	// Then the lower bound is length of the
	// So the solution is somewhere between   1 <= answer <= piles.max
	// -> we can then use binary search to search this range
	public int minEatingSpeed(int[] piles, int h) {
		int max = Arrays.stream(piles).max().getAsInt();
		int min = 1;

		// we stop searching once
		List<Integer> candidates = new ArrayList<>();

		while (min <= max) {
			int speed = (max + min) / 2;
			Double sum = 0.0;
			for (int i = 0; i < piles.length; i++) {
				sum += Math.ceil(((double) piles[i]) / speed);
			}
			if (sum > h) {
				min = speed + 1;
			} else if (sum <= h) {
				// everything smaller than h is our potential solutions
				max = speed - 1;

				// this actually doesn't matter, with binary search, we will eventually narrow down to the smallest speed
				candidates.add(speed);
			}
		}

		// find the min of our solutions
		return Collections.min(candidates);
	}


	public static void main(String[] args) {
		BananaEating driver = new BananaEating();
		System.out.println(driver.minEatingSpeed(new int[]{1, 4, 3, 2}, 9));

		System.out.println(driver.minEatingSpeed(new int[]{25, 10, 23, 4}, 4));
	}
}
