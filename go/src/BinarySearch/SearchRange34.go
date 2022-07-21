package main

func searchRange(nums []int, target int) []int {
	if len(nums) == 0 {
		return []int{-1, -1}
	}
	l, r := 0, len(nums)-1
	l1, r1 := 0, len(nums)-1
	for l1 < r1 {
		mid := l1 + (r1-l1)/2
		if nums[mid] < target {
			l1 = mid + 1
		} else {
			r1 = mid
		}
	}
	if nums[l1] != target {
		return []int{-1, -1}
	}
	for l < r {
		mid := l + (r-l+1)/2
		if nums[mid] > target {
			r = mid - 1
		} else {
			l = mid
		}
	}
	return []int{l1, l}
}
