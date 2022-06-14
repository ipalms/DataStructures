package main

import (
	"fmt"
	"sort"
)

func main() {
	s1 := "anagram"
	s1 += "a"
	s2 := "anagram"
	s2 += "a"
	fmt.Println(s1 == s2)
}

func isAnagram(s, t string) bool {
	s1, s2 := []byte(s), []byte(t)
	sort.Slice(s1, func(i, j int) bool { return s1[i] < s1[j] })
	sort.Slice(s2, func(i, j int) bool { return s2[i] < s2[j] })
	return string(s1) == string(s2)
}

// Go == 能直接比较两个数组是否相等
func isAnagram3(s string, t string) bool {
	var c1, c2 [26]int
	for _, ch := range s {
		c1[ch-'a']++
	}
	for _, ch := range t {
		c2[ch-'a']++
	}
	return c1 == c2
}

func isAnagram2(s string, t string) bool {
	b1 := []byte(s)
	b2 := []byte(t)
	len1, len2 := len(b1), len(b2)
	if len1 != len2 {
		return false
	}
	arr := [26]int{}
	for i := 0; i < len1; i++ {
		arr[b1[i]-'a']++
		arr[b2[i]-'a']--
	}
	for _, v := range arr {
		if v != 0 {
			return false
		}
	}
	return true
}
