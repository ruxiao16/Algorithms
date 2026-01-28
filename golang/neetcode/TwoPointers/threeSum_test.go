package TwoPointers

import "slices"

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
