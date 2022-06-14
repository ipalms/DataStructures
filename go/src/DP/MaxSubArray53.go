package main

func maxSubArray(nums []int) int {
	sum, ans := 0, nums[0]
	for _, v := range nums {
		if sum > 0 {
			sum += v
		} else {
			sum = v
		}
		if ans < sum {
			ans = sum
		}
	}
	return ans
}
