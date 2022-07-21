package main

/**
DP
*/
func wordBreak(s string, wordDict []string) bool {
	n := len(s)
	dp := make([]bool, n+1)
	dp[0] = true
	set := make(map[string]bool)
	for _, v := range wordDict {
		set[v] = true
	}
	for i := 1; i <= n; i++ {
		for _, v := range wordDict {
			pre := i - len(v)
			if pre >= 0 && dp[pre] && set[s[pre:i]] {
				dp[i] = true
				break
			}
		}
	}
	return dp[n]
}

/*
 DFS+记忆化搜索
*/
func wordBreak2(s string, wordDict []string) bool {
	wordMap := map[string]bool{}
	for _, v := range wordDict {
		wordMap[v] = true
	}
	memo := make(map[int]bool)
	return canBreak(0, s, wordMap, memo)
}

func canBreak(start int, s string, wordMap map[string]bool, memo map[int]bool) bool {
	if start == len(s) {
		return true
	}
	if res, ok := memo[start]; ok {
		return res
	}
	for i := start + 1; i <= len(s); i++ {
		prefix := s[start:i]
		if wordMap[prefix] && canBreak(i, s, wordMap, memo) {
			memo[start] = true
			return true
		}
	}
	memo[start] = false
	return false
}
