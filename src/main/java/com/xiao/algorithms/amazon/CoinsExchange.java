/*
 problem statement
 A user manages a sequence of coins with various denominations.
 The process involves systematically exchanging coins to reduce their overall count. Following a set of rules.
 1. Count the # of coins with each denomination, and write the denomination on a paper
 2. Remove all denomination from the paper that has less than two coins
 3. If there is at least one value written on the paper, find the smallest denomination on the paper and let it be x
 4. Find the two left most coins with denomination x, and let their positions be i and j
 5. Pick up the two coins and remove them for one coin with a denomination of y = 2*x and put that coin at position j
 (the position of the second coin picked)

 The user wants to know the final value of all the denomination in the line

 Formally given an array of coins of size n, representing the denomination of each coin in the line, find the final coin's
 orders and values

 example
 n = 7
 coins = [3, 4, 1, 2, 2, 1, 1]

 denomination freq
 1: [3, 6, 7]
 2: [4, 5]
 3: [1]
 4: [2]
the denomination picked is 1 coins[3] and coins[6]
new list is then [3, 4, 2, 2, 2, 1]

1: [6]
2: [3, 4, 5]
3: [1]
4: [2]
the denomination picked is 2 coins[3] and coins[4]
new list is then [3, 4, 4, 2, 1]

1:[5]
2:[4]
3:[1]
4:[2,3]
the denomination picked is 4 coins[2] and coins[3]
new list is then [3, 8, 2, 1]
 */

package com.xiao.algorithms.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CoinsExchange {

	public static void main(String[] args) {
		int[] coins = {3, 1, 3, 1};

		List<Integer> result = reduceCoins(coins);
		System.out.println(result);
		int[] coins2  = {3, 4, 1, 2, 2, 1, 1};
		result = reduceCoins(coins2);

		System.out.println(result);
	}

	public static List<Integer> reduceCoins(int[] coins) {
		List<Integer> coinList = new ArrayList<>();
		for (int coin : coins) {
			coinList.add(coin);
		}

		while (true) {
			Map<Integer, List<Integer>> denominationMap = new TreeMap<>();

			// Step 1: Count denominations and store indices
			for (int i = 0; i < coinList.size(); i++) {
				int denomination = coinList.get(i);
				denominationMap.putIfAbsent(denomination, new ArrayList<>());
				//  coins = [3, 4, 1, 2, 2, 1, 1], two left most coins (denomination 1) will be added first
				denominationMap.get(denomination).add(i);
			}

			denominationMap.entrySet().removeIf(entry -> entry.getValue().size() < 2);

			// Step 3: Check if the process can continue
			if (denominationMap.isEmpty()) {
				break;
			}

			// Step 4: Find the smallest denomination
			// Step 2: Remove denominations with less than two coins, tree Map makes sure than the smallest denomination is the first
			int smallestDenomination = denominationMap.keySet().iterator().next();
			List<Integer> positions = denominationMap.get(smallestDenomination);

			// Step 5: Replace two coins with one of double the denomination
			int i = positions.get(0);
			int j = positions.get(1);

			coinList.remove(j); // remove j first to avoid index out of bound
			coinList.remove(i);

			coinList.add(--j, 2 * smallestDenomination);
		}

		return coinList;
	}
}
