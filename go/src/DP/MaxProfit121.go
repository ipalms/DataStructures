package main

func maxProfit(prices []int) int {
	min, res := prices[0], 0
	for _, v := range prices {
		if v > min {
			if v-min > res {
				res = v - min
			}
		} else {
			min = v
		}
	}
	return res
}

func maxProfit2(prices []int) int {
	var dp [2]int
	dp[0] = -prices[0]
	for _, v := range prices {
		if -v > dp[0] {
			dp[0] = -v
		}
		if v+dp[0] > dp[1] {
			dp[1] = v + dp[0]
		}
	}
	return dp[1]
}
