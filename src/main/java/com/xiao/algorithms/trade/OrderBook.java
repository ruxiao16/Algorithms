package com.xiao.algorithms.trade;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook {

	public static void main(String[] args) {

		// The ASK side (sell orders): We want the LOWEST price first.
		// A standard PriorityQueue is a MIN-HEAP by default.
		PriorityQueue<Order> asks = new PriorityQueue<>(Comparator.comparingDouble(o-> o.price));

		asks.add(new Order(101, 150.50, 100));
		asks.add(new Order(102, 150.25, 200)); // this should be the ebst ask
		asks.add(new Order(103, 150.75, 50));

		System.out.println("Best ask (lowest sell price):" + asks.peek());

		PriorityQueue<Order> bids = new PriorityQueue<>(Comparator.comparingDouble((Order o) -> o.price).reversed());

		bids.add(new Order(201, 149.50, 100));
		bids.add(new Order(202, 150.75, 200)); // This should be the best bid
		bids.add(new Order(203, 149.25, 50));

		System.out.println("Best Bid (Highest Buy Price): " + bids.peek());

		if (bids.peek() != null && asks.peek() != null && bids.peek().price >= asks.peek().price) {
			System.out.println("\nTrade Occurs!");
			Order filledBid = bids.poll(); // O(log n) removal
			Order filledAsk = asks.poll(); // O(log n) removal
			System.out.println("Filled bid: " + filledBid);
			System.out.println("Filled ask: " + filledAsk);
		} else {
			System.out.println("\nNo trade. Spread is too wide.");
		}

		System.out.println("\nRemaining Best Bid: " + bids.peek());
		System.out.println("Remaining Best Ask: " + asks.peek());
	}
}
