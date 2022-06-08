package Hash

func subarraySum(nums []int, k int) int {
	cache := make(map[int]int)
	sum, count := 0, 0
	cache[0] = 1
	for _, v := range nums {
		sum += v
		if val, ok := cache[sum-k]; ok {
			count += val
		}
		cache[sum]++
	}
	return count
}
