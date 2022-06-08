package DP

func findLength(nums1 []int, nums2 []int) int {
	n1, n2 := len(nums1), len(nums2)
	dp := make([][]int, n1+1)
	for i := 0; i <= n1; i++ {
		dp[i] = make([]int, n2+1)
	}
	max := 0
	for i := 1; i <= n1; i++ {
		for j := 1; j <= n2; j++ {
			if nums1[i-1] == nums2[j-1] {
				dp[i][j] = dp[i-1][j-1] + 1
				if dp[i][j] > max {
					max = dp[i][j]
				}
			} else {
				dp[i][j] = 0
			}
		}
	}
	return max
}

//空间优化版
func findLength1(nums1 []int, nums2 []int) int {
	n1, n2 := len(nums1), len(nums2)
	dp := make([]int, n2+1)
	max := 0
	for i := 1; i <= n1; i++ {
		for j := n2; j >= 1; j-- {
			if nums1[i-1] == nums2[j-1] {
				dp[j] = dp[j-1] + 1
				if dp[j] > max {
					max = dp[j]
				}
			} else {
				dp[j] = 0
			}
		}
	}
	return max
}
