package main

import "sort"

func threeSum(nums []int) [][]int {
	sort.Ints(nums)
	length := len(nums)
	//不确定的二维数组长度就用 0 作为第二个参数，第二个参数为多大就预先初始化多大的空间
	res := make([][]int, 0)
	for i := 0; i < length-2; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		if nums[i]+nums[i+1]+nums[i+2] > 0 {
			break
		}
		for l, r := i+1, length-1; l < r; {
			sum := nums[i] + nums[l] + nums[r]
			if sum == 0 {
				res = append(res, []int{nums[i], nums[l], nums[r]})
				l++
				r--
				for l < r && nums[l] == nums[l-1] {
					l++
				}
				for l < r && nums[r] == nums[r+1] {
					r--
				}
			} else if sum < 0 {
				l++
			} else {
				r--
			}
		}
	}
	return res
}
