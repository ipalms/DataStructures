package main

func maxSubArrayLen(nums []int, k int) int {
	prefixSum := make(map[int]int)
	prefixSum[0] = -1
	sum, max := 0, 0
	for i, v := range nums {
		sum += v
		if res, ok := prefixSum[sum]; ok {
			if i-res > max {
				max = i - res
			}
		}
		if _, ok := prefixSum[sum]; !ok {
			prefixSum[sum] = i
		}
	}
	return max
}
