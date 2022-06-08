package main

import (
	"math"
)

//标量是t中的不同字符个数 也可以是t中字符的长度
func minWindow(s string, t string) string {
	have, need := [128]int{}, [128]int{}
	l, r, size, max, left := 0, 0, 0, math.MaxInt, 0
	for _, v := range t {
		if need[v] == 0 {
			size++
		}
		need[v]++
	}
	for r < len(s) {
		v := s[r]
		have[v]++
		if have[v] == need[v] {
			size--
			for size == 0 {
				if r-l+1 < max {
					max = r - l + 1
					left = l
				}
				have[s[l]]--
				if have[s[l]] < need[s[l]] {
					size++
				}
				l++
			}
		}
		r++
	}
	if max == math.MaxInt {
		return ""
	}
	return s[left : left+max]
}
