package TwoPointers

import "testing"

func maxAreaBrute(heights []int) int {
	res := 0
	for i := 0; i < len(heights); i++ {
		for j := i + 1; j < len(heights); j++ {

			area := (j - i) * min(heights[i], heights[j])

			if area > res {
				res = area
			}
		}
	}
	return res
}

func min(a int, b int) int {
	if b < a {
		return b
	}
	return a
}

// left and right pointer, intuition is to move the lower of the two so either
// l++ or r-- => the reason is that if heights[l] is smaller, moving r inward can only
// generate smaller because heights[l] is either the bottleneck or new heights[r++] is even smaller.
// So essentially we have found the maximum possible area with the left ptr => we can safely discard it
func maxAreaTwoPtrs(heights []int) int {
	res := 0
	l := 0
	r := len(heights) - 1

	for l < r {
		area := min(heights[l], heights[r]) * (r - l)
		if area > res {
			res = area
		}

		if heights[l] < heights[r] {
			l++
		} else {
			r--
		}
	}
	return res
}

func TestMaxSubstring(t *testing.T) {
	input := []int{1, 7, 2, 5, 4, 7, 3, 6}
	res := maxAreaTwoPtrs(input)

	if res != 36 {
		t.Errorf("expect 36 getting %d", res)
	}
}
