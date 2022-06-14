package main

func dailyTemperatures(temperatures []int) []int {
	stack, res := []int{}, make([]int, len(temperatures))
	for i, v := range temperatures {
		for len(stack) > 0 && temperatures[stack[len(stack)-1]] < v {
			p := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			res[p] = i - p
		}
		stack = append(stack, i)
	}
	return res
}
