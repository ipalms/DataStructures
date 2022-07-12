package main

func moveZeroes(nums []int) {
	for i, j := 0, 0; j < len(nums); j++ {
		if nums[j] != 0 {
			if nums[i] == 0 {
				nums[i] = nums[j]
				nums[j] = 0
			}
			i++
		}
	}
}
