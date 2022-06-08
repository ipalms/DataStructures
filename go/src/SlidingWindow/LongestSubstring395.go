package main

func longestSubstring(s string, k int) int {
	max := 0
	for i := 1; i <= 26; i++ {
		l, r, have, need, len := 0, 0, 0, 0, len(s)
		size := [26]int{}
		for r < len {
			size[s[r]-'a']++
			if size[s[r]-'a'] == 1 {
				have++
			}
			for have > i {
				size[s[l]-'a']--
				if size[s[l]-'a'] == 0 {
					have--
				}
				if size[s[l]-'a'] == k-1 {
					need--
				}
				l++
			}
			if size[s[r]-'a'] == k {
				need++
			}
			if need == i {
				if r-l+1 > max {
					max = r - l + 1
				}
			}
			r++
		}
	}
	return max
}
