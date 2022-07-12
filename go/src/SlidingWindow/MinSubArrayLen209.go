package main

import "math"

func minSubArrayLen(target int, nums []int) int {
	sum, i, j, res := 0, 0, 0, math.MaxInt
	for j < len(nums) {
		sum += nums[j]
		for sum >= target {
			res = min(res, j-i+1)
			sum -= nums[i]
			i++
		}
		j++
	}
	if res == math.MaxInt {
		return 0
	}
	return res
}

func min(i, j int) int {
	if i < j {
		return i
	}
	return j
}
