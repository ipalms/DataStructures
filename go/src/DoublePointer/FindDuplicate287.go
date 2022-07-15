package main

func findDuplicate(nums []int) int {
	slow, fast := nums[0], nums[nums[0]]
	for slow != fast {
		slow = nums[slow]
		fast = nums[nums[fast]]
	}
	find := 0
	for slow != find {
		slow = nums[slow]
		find = nums[find]
	}
	return find
}
