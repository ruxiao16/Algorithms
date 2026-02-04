package TwoPointers

import (
	"slices"
	"testing"
)

func threeSumBrute(nums []int) [][]int {
	slices.Sort(nums)

	// 2. Create the "Set".
	// We use [3]int (Array) as the key because Arrays ARE comparable.
	// We use bool as the value (dummy value).
	uniqueSet := make(map[[3]int]bool)

	n := len(nums)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for k := j + 1; k < n; k++ {
				if nums[i]+nums[j]+nums[k] == 0 {
					triplet := [3]int{nums[i], nums[j], nums[k]}
					// add to map (set hebaviors handles duplicate automatically, if already exist overwrite
					uniqueSet[triplet] = true
				}
			}
		}
	}

	// convert the set back to a List of List (slices of slices
	var result [][]int
	for triplet := range uniqueSet {
		// triplet[:] is a shorthand for creating a slice from an array
		//  [3]int is an array while []int is a slice
		// var a [3]int    =vs==> var s []int
		result = append(result, triplet[:])
	}
	return result
}

// O(n^2) complexity
func threeSumTwoPointers(nums []int) [][]int {
	slices.Sort(nums)

	var result [][]int

	for i := 0; i < len(nums)-2; i++ {
		if nums[i] > 0 {
			// the rest of entries are positive, cannot sum to zero
			break
		}

		// avoid the dupe as combination is already found
		// [-3, -3, 1, 2, 3, 4] => we skip the second -3 as we have already found its combination (or it doesnt exist)
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}

		target := -nums[i]
		l := i + 1
		r := len(nums) - 1

		for l < r {
			sum := nums[l] + nums[r]
			if sum > target {
				r--
			} else if sum < target {
				l++
			} else {
				result = append(result, []int{nums[i], nums[l], nums[r]})
				l++
				r--
				// making sure to skip the combinations already found in the middle
				// [-2, 0, 0, 2, 2] => target is 2, we found solution at l = 1, and r = 4 ==> l =2, r=3, we need to skip it as it yields the same result
				for l < r && nums[l] == nums[l-1] {
					l++
				}
			}
		}

	}
	return result
}

func TestThreeSum(t *testing.T) {
	res := threeSumTwoPointers([]int{-1, 0, 1, 2, -1, -4})
	t.Logf("Resulting Triplets: %v", res)
}
