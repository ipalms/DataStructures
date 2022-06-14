package main

func findRepeatNumber(nums []int) int {
	for _, v := range nums {
		v = Abs(v)
		if nums[v] < 0 {
			return v
		}
		nums[v] *= -1
	}
	return 0
}

func Abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
