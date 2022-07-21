package main

/*
迭代+快速幂
*/
func myPow(x float64, n int) float64 {
	flag := 0
	if n < 0 {
		flag = 1
		n = -n
	}
	cur, res := x, 1.0
	for n > 0 {
		if n&1 == 1 {
			res *= cur
		}
		n >>= 1
		cur *= cur
	}
	if flag == 1 {
		res = 1 / res
	}
	return res
}

/*
递归
*/

func myPow1(x float64, n int) float64 {
	if n < 0 {
		return pow(x, -n)
	}
	return pow(x, n)
}

func pow(x float64, n int) float64 {
	if n == 0 {
		return 1.0
	}
	v := pow(x, n/2)
	if n&1 == 0 {
		return v * v
	}
	return v * v * x
}
