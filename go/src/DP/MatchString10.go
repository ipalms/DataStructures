package main

func isMatch(s string, p string) bool {
	n, m := len(s), len(p)
	judge := func(i, j int) bool {
		return p[j-1] == '.' || s[i-1] == p[j-1]
	}
	f := make([][]bool, n+1)
	for i := 0; i <= n; i++ {
		f[i] = make([]bool, m+1)
	}
	f[0][0] = true
	for i := 2; i <= m; i++ {
		if p[i-1] == '*' {
			f[0][i] = f[0][i-2]
		}
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= m; j++ {
			if judge(i, j) {
				f[i][j] = f[i-1][j-1]
			} else if p[j-1] == '*' {
				f[i][j] = f[i][j-2]
				if judge(i, j-1) {
					f[i][j] = f[i][j] || f[i-1][j]
				}
			}
		}
	}
	return f[n][m]
}
