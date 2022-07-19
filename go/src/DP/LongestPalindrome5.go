package main

/**
  中心扩散法  dp看 java
*/
func longestPalindrome(s string) string {
	if len(s) < 2 {
		return s
	}
	res := [2]int{}
	for i := 0; i < len(s)-1; i++ {
		r1 := spreadFind(s, i, i)
		r2 := spreadFind(s, i, i+1)
		var t [2]int
		if r1[1] > r2[1] {
			t = r1
		} else {
			t = r2
		}
		if t[1] > res[1] {
			res = t
		}
	}
	return s[res[0] : res[0]+res[1]]
}

func spreadFind(s string, l, r int) [2]int {
	length := len(s)
	for l >= 0 && r < length && s[l] == s[r] {
		l--
		r++
	}
	return [2]int{l + 1, r - l - 1}
}
