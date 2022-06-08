package Hash

func twoSum(nums []int, target int) []int {
	cache := make(map[int]int)
	for i, v := range nums {
		if i2, ok := cache[target-v]; ok {
			return []int{i, i2}
		}
		cache[v] = i
	}
	return []int{}
}
