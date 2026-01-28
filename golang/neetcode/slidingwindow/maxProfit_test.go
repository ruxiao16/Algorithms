package slidingwindow

import (
	"fmt"
	"testing"
)

func maxProfitBrutalForce(prices []int) int {
	res := 0
	for i := 0; i < len(prices); i++ {
		buy := prices[i]
		for j := i + 1; j < len(prices); j++ {
			sell := prices[j]
			res = max(res, sell-buy)
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func maxProfitSlidingWindow(prices []int) int {
	maxProfit := 0
	l := 0
	r := 1
	lowest := prices[0]

	for r < len(prices) {
		diff := prices[r] - lowest
		if diff > maxProfit {
			maxProfit = diff
		}
		if prices[r] < prices[l] {
			lowest = prices[r]
			l = r
		}
		r++
	}
	return maxProfit
}

func TestMaxProfit(t *testing.T) {
	input := []int{7, 1, 5, 3, 6, 4}
	res := maxProfitBrutalForce(input)

	if res != 5 {
		t.Errorf("Expected 5, got %d", res)
	}

	res1 := maxProfitSlidingWindow(input)
	fmt.Printf("result1 is %d", res1)
}
