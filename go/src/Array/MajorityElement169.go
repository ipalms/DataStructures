package main

func majorityElement(nums []int) int {
	times, val := 0, 0
	for _, v := range nums {
		if times == 0 {
			times++
			val = v
		} else if v == val {
			times++
		} else {
			times--
		}
	}
	return val
}
