package main

func longestConsecutive(nums []int) int {
	max := 0
	have := make(map[int]bool)
	for _, v := range nums {
		have[v] = true
	}
	for _, v := range nums {
		if !have[v-1] {
			ans := 1
			for have[v+1] {
				ans++
				v++
			}
			if ans > max {
				max = ans
			}
		}
	}
	return max
}
