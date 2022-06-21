package main

import "math"

func minPathSum(grid [][]int) int {
	n, m := len(grid), len(grid[0])
	dp := make([][]int, n)
	dp[0] = make([]int, m)
	dp[0][0] = grid[0][0]
	for i := 1; i < n; i++ {
		dp[i] = make([]int, m)
		dp[i][0] = dp[i-1][0] + grid[i][0]
	}
	for i := 1; i < m; i++ {
		dp[0][i] = dp[0][i-1] + grid[0][i]
	}
	for i := 1; i < n; i++ {
		for j := 1; j < m; j++ {
			dp[i][j] = Min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
		}
	}
	return dp[n-1][m-1]
}

func minPathSum2(grid [][]int) int {
	n, m := len(grid), len(grid[0])
	dp := make([]int, m+1)
	for i := 1; i <= m; i++ {
		dp[i] = dp[i-1] + grid[0][i-1]
	}
	dp[0] = math.MaxInt
	for i := 1; i < n; i++ {
		for j := 1; j <= m; j++ {
			dp[j] = Min(dp[j], dp[j-1]) + grid[i][j-1]
		}
	}
	return dp[m]
}

func Min(i, j int) int {
	if i <= j {
		return i
	}
	return j
}
