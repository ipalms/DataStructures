package main

import "sort"

func merge(intervals [][]int) [][]int {
	res := make([][]int, 0)
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	id := -1
	for _, v := range intervals {
		if id == -1 || v[0] > res[id][1] {
			res = append(res, v)
			id++
		} else {
			res[id][1] = Max(res[id][1], v[1])
		}
	}
	return res
}

func Max(i, j int) int {
	if i >= j {
		return i
	}
	return j
}
