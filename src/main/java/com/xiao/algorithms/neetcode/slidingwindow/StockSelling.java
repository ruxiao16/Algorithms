package com.xiao.algorithms.neetcode.slidingwindow;

public class StockSelling {

	// intuition, using two pointers, l =0, r = 1, we keep track of the current minimum, and current max profit
	// at start this will be prices[0] and prices[1] - prices[0] iff it is a non-zero value.
	// We then advance r, and simultaneously we calculate the new max and if we encounter a lower price, we move l to r,
	// And the intuition is that, we have to move to new lower price because mathmatically, there is no way a new max can be found
	// by staying on l, and by the time we reach a new low, all the combinations between l and r are already calculated and max profit
	// between the two is calculated
	public int maxProfit(int[] prices) {
		int l = 0;
		int r = 1;
		int min = prices[0];
		int maxProfit = 0;

		while (r < prices.length) {
			int diff = prices[r] - min;
			if (diff > maxProfit) {
				maxProfit = diff;
			}
			if (prices[r] < prices[l]) {
				min = prices[r];
				l = r;
			}
			r++;
		}
		return maxProfit;
	}
}
