package main

import "sort"

func merge(nums1 []int, m int, nums2 []int, n int) {
	i, j, k := m-1, n-1, n+m-1
	for i >= 0 && j >= 0 {
		if nums1[i] > nums2[j] {
			nums1[k] = nums1[i]
			i--
		} else {
			nums1[k] = nums2[j]
			j--
		}
		k--
	}
	for ; j >= 0; j-- {
		nums1[k] = nums2[j]
		k--
	}
}

func merge2(nums1 []int, m int, nums2 []int, _ int) {
	copy(nums1[m:], nums2)
	sort.Ints(nums1)
}
