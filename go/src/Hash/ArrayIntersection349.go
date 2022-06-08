package Hash

func intersection(nums1 []int, nums2 []int) []int {
	cache := make(map[int]bool)
	res := []int{}
	for _, v := range nums1 {
		cache[v] = true
	}
	for _, v := range nums2 {
		if _, ok := cache[v]; ok {
			res = append(res, v)
			delete(cache, v)
		}
	}
	return res
}
