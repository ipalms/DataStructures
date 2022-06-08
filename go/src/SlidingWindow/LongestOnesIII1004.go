package main

func longestOnes(nums []int, k int) int {
	l, use, max := 0, 0, 0
	for r, v := range nums {
		if v == 0 {
			use++
			for use > k {
				if nums[l] == 0 {
					use--
				}
				l++
			}
		}
		if r-l+1 > max {
			max = r - l + 1
		}
	}
	return max
}

func longestOnes1(nums []int, k int) int {
	l, r, maxAns, count := 0, 0, 0, 0
	for _, v := range nums {
		if v == 1 {
			count++
		}
		if count > maxAns {
			maxAns = count
		}
		if r-l+1 > maxAns+k {
			if nums[l] == 1 {
				count--
			}
			l++
		}
		r++
	}
	//不能直接return maxAns+k，因为字串的含有0的个数不一定等于k个
	return r - l
}
