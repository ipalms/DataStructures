package main

func trap(height []int) int {
	all, l, r := 0, 0, len(height)-1
	lMax, rMax := 0, 0
	for l <= r {
		lMax = Max(height[l], lMax)
		rMax = Max(height[r], rMax)
		if lMax < rMax {
			all += lMax - height[l]
			l++
		} else {
			all += rMax - height[r]
			r--
		}
	}
	return all
}

func Max(i, j int) int {
	if i >= j {
		return i
	}
	return j
}

func trap2(height []int) int {
	stack := []int{}
	res := 0
	for i, v := range height {
		for len(stack) > 0 && height[stack[len(stack)-1]] <= v {
			length := len(stack)
			val := height[stack[length-1]]
			stack = stack[:length-1]
			if length == 1 {
				break
			}
			res += (i - stack[length-2] - 1) * (Min(height[stack[length-2]], v) - val)
		}
		stack = append(stack, i)
	}
	return res
}

func Min(i, j int) int {
	if i <= j {
		return i
	}
	return j
}
