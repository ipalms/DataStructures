package main

func nextPermutation(nums []int) {
	l := len(nums)
	first := l - 2
	for first >= 0 && nums[first] >= nums[first+1] {
		first--
	}
	if first != -1 {
		second := l - 1
		for nums[second] <= nums[first] {
			second--
		}
		nums[first], nums[second] = nums[first], nums[second]
	}
	for i, j := first+1, l-1; i < j; {
		nums[i], nums[j] = nums[j], nums[i]
		i++
		j--
	}
}
