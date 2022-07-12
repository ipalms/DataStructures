package main

import (
	"fmt"
	"math"
)

func main() {
	arr := []int{84, -37, 32, 40, 95}
	fmt.Print(shortestSubarray(arr, 167))
}

func shortestSubarray(nums []int, k int) int {
	size := len(nums)
	p, q := make([]int, size+1), []int{}
	for i, v := range nums {
		p[i+1] = p[i] + v
	}
	ans := math.MaxInt
	for i, v := range p {
		for len(q) > 0 && v-p[q[0]] >= k {
			//注意，这里的区间长度不需要加一
			ans = min(ans, i-q[0])
			q = q[1:]
		}
		for len(q) > 0 && p[q[len(q)-1]] >= v {
			q = q[:len(q)-1]
		}
		q = append(q, i)
	}
	if ans == math.MaxInt {
		return -1
	}
	return ans
}

func min(i, j int) int {
	if i < j {
		return i
	}
	return j
}
