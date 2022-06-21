package main

var nodeCache map[int]int

func buildTree(preorder []int, inorder []int) *TreeNode {
	nodeCache = make(map[int]int)
	for i, v := range inorder {
		nodeCache[v] = i
	}
	//由于切片的缘故，所以可以不适用左右index确定范围而是直接截取切片
	return build2(preorder, inorder)
	//return build(preorder, 0, len(preorder)-1, 0, len(preorder)-1)
}

func build(preorder []int, preLeft, preRight, inLeft, inRight int) *TreeNode {
	if inLeft > inRight {
		return nil
	}
	curr := &TreeNode{Val: preorder[preLeft]}
	index, _ := nodeCache[preorder[preLeft]]
	curr.Left = build(preorder, preLeft+1, preLeft+index-inLeft, inLeft, index-1)
	curr.Right = build(preorder, preLeft+index-inLeft+1, preRight, index+1, inRight)
	return curr
}

func build2(preorder, inorder []int) *TreeNode {
	if len(inorder) == 0 {
		return nil
	}
	curr := &TreeNode{Val: preorder[0]}
	//唯一不同的是索引需要重定位
	//需要分别查找preorder[0]和inorder[0]在哈希表中的位置，其差就是代表原先index含义
	i1, _ := nodeCache[preorder[0]]
	i2, _ := nodeCache[inorder[0]]
	index := i1 - i2
	curr.Left = build2(preorder[1:1+len(inorder[:index])], inorder[:index])
	curr.Right = build2(preorder[1+len(inorder[:index]):], inorder[index+1:])
	return curr
}
