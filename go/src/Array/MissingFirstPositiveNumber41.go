package main

func firstMissingPositive(nums []int) int {
	size := len(nums)
	for i := 0; i < size; i++ {
		for nums[i] > 0 && nums[i] <= size && nums[nums[i]-1] != nums[i] {
			nums[nums[i]-1], nums[i] = nums[i], nums[nums[i]-1]
		}
	}
	for i, v := range nums {
		if i+1 != v {
			return i + 1
		}
	}
	return size + 1
}
