package main

import "fmt"

func main() {
	fmt.Print(maxProduct([]int{-4, -3, -2}))
}

func maxProduct(nums []int) int {
	minAns, maxAns, res := 1, 1, nums[0]
	for _, v := range nums {
		preMax := maxAns
		maxAns = max(maxAns*v, max(minAns*v, v))
		minAns = min(minAns*v, min(preMax*v, v))
		res = max(res, maxAns)
	}
	return res
}

func min(i, j int) int {
	if i < j {
		return i
	}
	return j
}

func max(i, j int) int {
	if i > j {
		return i
	}
	return j
}
