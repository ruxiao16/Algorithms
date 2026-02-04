package arrays

func topKFrequent(nums []int, k int) []int {
	// must use slice, as arr res := [len(nums)+1]int is not legal as size has to be known ahead of time for arr in golang
	buckets := make([][]int, len(nums)+1)
	countMap := make(map[int]int)

	// count the occurences
	//for i := 0; i < len(nums); i++ {
	//	if countMap[nums[i]] == 0 {
	//		countMap[nums[i]] = 1
	//	} else {
	//		countMap[nums[i]]++
	//	}
	//}
	// optimized counting
	for _, num := range nums {
		// in go, if key doesnt exist, it returns 0, so we dont have to do the countMap[nums[i]] == 0 check
		countMap[num]++
	}

	for val, freq := range countMap {
		buckets[freq] = append(buckets[freq], val)
	}

	var res []int // start empty, it is nil;
	// alternatively we can make a slice of size k fill with zeros res := make([]int, k) -> then later on res[index] = num
	// but we would then keep track of k

	for i := len(buckets) - 1; i >= 0; i-- {
		for _, num := range buckets[i] {
			res = append(res, num) // append grows the slice automatically
			if len(res) == k {
				return res
			}
		}
	}

	return res
}
