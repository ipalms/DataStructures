package main

func checkInclusion(s1 string, s2 string) bool {
	have, need := [128]int{}, [128]int{}
	for _, v := range s1 {
		need[v]++
	}
	l, r, all := 0, 0, len(s1)
	for r < len(s2) {
		v := s2[r]
		have[v]++
		for have[v] > need[v] {
			have[s2[l]]--
			l++
		}
		if r-l+1 == all {
			return true
		}
		r++
	}
	return false
}
