package main

import (
	"fmt"
	"strconv"
)

// 由于Golang中的int的取值是和和操作系统位数相关的，如果是32位操作系统，int类型的大小就是4字节;
// 如果是64位操作系统，int类型的大小就是8个字节。所以我们在这里就不用担心int相除数据溢出的问题了

// 这里的拼接字符串采用的是byte数组  太麻烦了
func fractionToDecimal(numerator int, denominator int) string {
	if numerator%denominator == 0 {
		return strconv.Itoa(numerator / denominator)
	}
	res := []byte{}
	if numerator*denominator < 0 {
		res = append(res, '-')
	}
	a, b := abs(numerator), abs(denominator)
	m1 := map[int]int{}
	res = append(res, []byte(strconv.Itoa(a/b))...)
	res = append(res, '.')
	less := a % b
	for less != 0 {
		m1[less] = len(res)
		less *= 10
		res = append(res, []byte(strconv.Itoa(less/b))...)
		less %= b
		if val, ok := m1[less]; ok {
			return fmt.Sprintf("%s(%s)", string(res[:val]), string(res[val:]))
		}
	}
	return string(res)
}

// 这里的拼接字符串采用的是 + 号
func fractionToDecimal2(numerator int, denominator int) string {
	if numerator%denominator == 0 {
		return strconv.Itoa(numerator / denominator)
	}
	res := ""
	if numerator*denominator < 0 {
		res += "-"
	}
	a, b := abs(numerator), abs(denominator)
	m1 := map[int]int{}
	res += strconv.Itoa(a/b) + "."
	less := a % b
	for less != 0 {
		m1[less] = len(res)
		less *= 10
		res += strconv.Itoa(less / b)
		less %= b
		if val, ok := m1[less]; ok {
			return fmt.Sprintf("%s(%s)", res[:val], res[val:])
		}
	}
	return res
}

func abs(val int) int {
	if val < 0 {
		return -val
	}
	return val
}
