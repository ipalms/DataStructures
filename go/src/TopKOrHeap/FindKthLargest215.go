package main

func findKthLargest(nums []int, k int) int {
	heapify(nums, k)
	for i := k; i < len(nums); i++ {
		if nums[i] <= nums[0] {
			continue
		}
		nums[0], nums[i] = nums[i], nums[0]
		shifDown(nums, 0, k)
	}
	return nums[0]
}

func heapify(nums []int, len int) {
	for i := len / 2; i >= 0; i-- {
		shifDown(nums, i, len)
	}
}

func shifDown(nums []int, i, len int) {
	tmp := nums[i]
	for k := 2*i + 1; k < len; k = 2*k + 1 {
		if k+1 < len && nums[k] > nums[k+1] {
			k++
		}
		if nums[k] >= tmp {
			break
		} else {
			nums[i] = nums[k]
			i = k
		}
	}
	nums[i] = tmp
}
