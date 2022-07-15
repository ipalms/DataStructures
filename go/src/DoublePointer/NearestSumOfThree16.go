package main

import "sort"

func main() {
	threeSumClosest([]int{-1, 2, 1, -4}, 1)
}

func threeSumClosest(nums []int, target int) int {
	sort.Ints(nums)
	n := len(nums)
	res := nums[0] + nums[1] + nums[n-1]
	for i := 0; i < n-2; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		for j, k := i+1, n-1; j < k; {
			sum := nums[i] + nums[j] + nums[k]
			if sum == target {
				return target
			}
			if sum > target {
				k--
			} else {
				j++
			}
			//注意找的结果值不是diff，而是sum。所以比较时用abs(diff)，保留的值依旧为sum
			if abs(res-target) > abs(sum-target) {
				res = sum
			}
		}
	}
	return res
}

func min1(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}
