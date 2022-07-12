package main

func findContinuousSequence(target int) [][]int {
	res := make([][]int, 0)
	// prefix:=make([]int{},target+1)
	// for i:=1;i<=target;i++{
	//     prefix[i]=prefix[i-1]+i
	// }
	prefix, sum := make(map[int]int), 0
	prefix[0] = 0
	for i := 1; i < target; i++ {
		sum += i
		if v, ok := prefix[sum-target]; ok {
			tmp := make([]int, i-v)
			for k := v + 1; k <= i; k++ {
				tmp[k-v-1] = k
			}
			res = append(res, tmp)
		}
		prefix[sum] = i
	}
	return res
}

func findContinuousSequence2(target int) [][]int {
	res := make([][]int, 0)
	l, r, sum := 1, 1, 0
	for l <= target/2 {
		if sum < target {
			sum += r
			r++
		} else if sum > target {
			sum -= l
			l++
		} else {
			//sum==target情况，此时右边界是不符合的，左边界l是在结果区间内的
			tmp := make([]int, r-l)
			for i := l; i < r; i++ {
				tmp[i-l] = i
			}
			res = append(res, tmp)
			sum -= l
			l++
		}
	}
	return res
}
