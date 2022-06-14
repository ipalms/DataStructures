package main

func findMaxLength(nums []int) int {
	prefixSum := make(map[int]int)
	prefixSum[0] = -1
	sum, max := 0, 0
	for i, v := range nums {
		if v == 0 {
			v = -1
		}
		sum += v
		if res, ok := prefixSum[sum]; ok {
			if i-res > max {
				max = i - res
			}
		} else {
			prefixSum[sum] = i
		}
	}
	return max
}
