package main

func removeDuplicates(nums []int) int {
	pre := 0
	for _, v := range nums {
		if nums[pre] != v {
			pre++
			nums[pre] = v
		}
	}
	return pre + 1
}

func removeDuplicatesK(nums []int, k int) int {
	pre := 0
	for _, v := range nums {
		if pre < k || nums[pre-k] != v {
			nums[pre] = v
			pre++
		}
	}
	return pre + 1
}
