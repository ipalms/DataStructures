package main

func checkSubarraySum(nums []int, k int) bool {
	prefixSum := make(map[int]int)
	prefixSum[0] = -1
	sum := 0
	for i, v := range nums {
		sum = (sum + v) % k
		if res, ok := prefixSum[sum]; ok {
			if i-res >= 2 {
				return true
			}
		} else {
			prefixSum[sum] = i
		}
	}
	return false
}
