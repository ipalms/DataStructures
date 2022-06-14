package main

func firstUniqChar(s string) byte {
	m := make(map[byte]int)
	chs := []byte(s)
	for i, v := range chs {
		if _, ok := m[v]; !ok {
			m[v] = i
		} else {
			m[v] = -1
		}
	}
	for _, v := range chs {
		if m[v] != -1 {
			return v
		}
	}
	return ' '
}
