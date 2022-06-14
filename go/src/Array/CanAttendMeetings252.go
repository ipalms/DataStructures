package main

import "sort"

func main() {
	intervals := [][]int{{1, 3}, {2, 6}, {8, 10}, {15, 18}}
	println(canAttendMeetings(intervals))
}

func canAttendMeetings(intervals [][]int) bool {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] > intervals[j][0]
	})
	pre := -1
	for _, v := range intervals {
		if v[0] < pre {
			return false
		}
		pre = v[1]
	}
	return false
}
