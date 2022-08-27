package main

func zigzagLevelOrder(root *TreeNode) [][]int {
	res := make([][]int, 0)
	if root == nil {
		return res
	}
	q := []*TreeNode{root}
	level := 1
	for len(q) > 0 {
		tmp, size := []int{}, len(q)
		for i := 0; i < size; i++ {
			p := q[0]
			q = q[1:]
			tmp = append(tmp, p.Val)
			if p.Left != nil {
				q = append(q, p.Left)
			}
			if p.Right != nil {
				q = append(q, p.Right)
			}
		}
		if (level & 1) == 0 {
			for i, j := 0, len(tmp)-1; i < j; {
				tmp[i], tmp[j] = tmp[j], tmp[i]
				i++
				j--
			}
		}
		res = append(res, tmp)
		level++
	}
	return res
}

// 另外一种写法（更偏GO习惯）
func zigzagLevelOrderII(root *TreeNode) (ans [][]int) {
	// 第一点不同就是返回值直接在函数中定义好名字可以直接return
	if root == nil {
		return
	}
	queue := []*TreeNode{root}
	for level := 0; len(queue) > 0; level++ {
		vals := []int{}
		//将切片赋值，可以直接对赋值后的切片进行range操作
		q := queue
		//原队列赋空值
		queue = nil
		for _, node := range q {
			vals = append(vals, node.Val)
			//但是添加元素的操作还是要对原队列进行的
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
		}
		// 本质上和层序遍历一样，我们只需要把奇数层的元素翻转即可
		if level%2 == 1 {
			for i, n := 0, len(vals); i < n/2; i++ {
				vals[i], vals[n-1-i] = vals[n-1-i], vals[i]
			}
		}
		ans = append(ans, vals)
	}
	return
}

// 迭代的第三种实现，直接add元素。内存效率比前两个低，同理dfs也可以同样套路
func zigzagLevelOrder3(root *TreeNode) (res [][]int) {
	if root == nil {
		return
	}
	queue := []*TreeNode{root}
	left2Right := true
	for ; len(queue) != 0; left2Right = !left2Right {
		item := []int{}
		for size := len(queue); size > 0; size-- {
			pop := queue[0]
			queue = queue[1:]
			if left2Right {
				item = append(item, pop.Val)
			} else {
				//不同点，切片头插的不同实现
				//这里是直接重建一个[]int切片，内存效率低
				item = append([]int{pop.Val}, item...)
			}
			if pop.Left != nil {
				queue = append(queue, pop.Left)
			}
			if pop.Right != nil {
				queue = append(queue, pop.Right)
			}
		}
		res = append(res, item)
	}
	return
}

// dfs实现
func zigzagLevelOrder4(root *TreeNode) (res [][]int) {
	if root == nil {
		return
	}
	//先申明了这个方法的函数类型再对方法句柄赋值,才能进行方法自递归调用
	var dfs func(root *TreeNode, depth int)
	dfs = func(root *TreeNode, depth int) {
		if root == nil {
			return
		}
		if depth+1 > len(res) {
			res = append(res, []int{})
		}
		if depth&1 == 0 {
			res[depth] = append(res[depth], root.Val)
		} else {
			res[depth] = append([]int{root.Val}, res[depth]...)
		}
		dfs(root.Left, depth+1)
		dfs(root.Right, depth+1)
	}

	dfs(root, 0)
	return
}
