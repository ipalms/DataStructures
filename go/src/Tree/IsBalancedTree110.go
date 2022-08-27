package main

import "fmt"

type TreeNode1 struct {
	Val   int
	Left  *TreeNode1
	Right *TreeNode1
}

func main() {
	fmt.Print(isBalanced(&TreeNode1{1, nil, nil}))
}

func isBalanced(root *TreeNode1) bool {
	if root == nil {
		return true
	}
	return check(root) != -1
}

func check(root *TreeNode1) int {
	if root == nil {
		return 0
	}
	l := check(root.Left)
	r := check(root.Right)
	if l == -1 || r == -1 || abs(l-r) > 1 {
		return -1
	}
	return max(l, r) + 1
}

func abs(val int) int {
	if val < 0 {
		return -val
	}
	return val
}
func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}
