package main

import "math"

func main() {
	mySqrt1(8)
}

// 二分，其他way 待定write
func mySqrt(x int) int {
	if x == 0 || x == 1 {
		return x
	}
	l, r := 1, x/2
	for l < r {
		mid := l + (r-l+1)/2
		//Go 在这里与 Java不同点是相乘不会溢出
		//因为题目范围是2^31次方，而go int类型是根据机器是否是64位而确定其int类型大小
		if mid*mid > x {
			r = mid - 1
		} else {
			l = mid
		}
	}
	return l
}

func mySqrt1(x int) int {
	if x == 0 || x == 1 {
		return x
	}
	t, con, diff := float64(x), float64(x), 1e-6
	next := float64((t + 1) / 2)
	for math.Abs(t-next) > diff {
		t = next
		next = (t + con/t) / 2
	}
	return int(t)
}
