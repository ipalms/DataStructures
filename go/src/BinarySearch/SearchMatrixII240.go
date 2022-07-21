package main

// 非二分方法
func searchMatrix(matrix [][]int, target int) bool {
	n, m := len(matrix), len(matrix[0])
	i, j := n-1, 0
	for i >= 0 && j < m {
		if matrix[i][j] == target {
			return true
		}
		if matrix[i][j] > target {
			i--
		} else {
			j++
		}
	}
	return false
}
