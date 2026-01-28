package slidingwindow

import "testing"

func maxSubStringLengthBrute(s string) int {
	res := 0

	for i := 0; i < len(s); i++ {
		// use map to mimic a set, where byte is the key, bool is the value
		// note that bool is not necessary here but just a placeholder, it can be replaced with struct{} to save space
		charSet := make(map[byte]bool)
		for j := i; j < len(s); j++ {
			if charSet[s[j]] {
				break
			}
			charSet[s[j]] = true
		}
		if len(charSet) > res {
			res = len(charSet)
		}
	}
	return res
}

func maxSubStringSlidingWindow(s string) int {
	res := 0
	charSet := make(map[byte]bool)
	l := 0

	for r := 0; r < len(s); r++ {
		for charSet[s[r]] == true {
			delete(charSet, s[l])
			l++
		}
		// adding
		charSet[s[r]] = true
		if r-l+1 > res {
			res = r - l + 1
		}
	}
	return res
}

func TestMaxSubstring(t *testing.T) {
	res := maxSubStringSlidingWindow("abcabcbb")

	if res != 3 {
		t.Errorf("Expected 3, got %d", res)
	}
}
