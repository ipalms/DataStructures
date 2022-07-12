package main

func maxArea(height []int) int {
	l, r, res := 0, len(height)-1, 0
	for l < r {
		res = max(res, (r-l)*min(height[l], height[r]))
		if height[l] < height[r] {
			l++
		} else {
			r--
		}
	}
	return res
}

func max(i, j int) int {
	if i < j {
		return j
	}
	return i
}

func min(i, j int) int {
	if i > j {
		return j
	}
	return i
}
