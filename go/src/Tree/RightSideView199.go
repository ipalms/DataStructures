package main

func rightSideView(root *TreeNode) (arr []int) {
	if root == nil {
		return
	}
	var dfs func(root *TreeNode, level int)
	dfs = func(root *TreeNode, level int) {
		if root == nil {
			return
		}
		if len(arr) == level {
			arr = append(arr, root.Val)
		}
		dfs(root.Right, level+1)
		dfs(root.Left, level+1)
	}
	dfs(root, 0)
	return
}

func rightSideViewII(root *TreeNode) (arr []int) {
	if root == nil {
		return
	}
	q := []*TreeNode{root}
	for level := 1; len(q) > 0; level++ {
		size := len(q)
		for i := 0; i < size; i++ {
			p := q[0]
			q = q[1:]
			if i == 0 {
				arr = append(arr, p.Val)
			}
			if p.Right != nil {
				q = append(q, p.Right)
			}
			if p.Left != nil {
				q = append(q, p.Left)
			}
		}
	}
	return
}
