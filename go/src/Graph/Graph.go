package Graph

import (
	"fmt"
)

/*  不了解--仅仅摘抄下来  微软第一次笔试第三题
1.给定A=【1，1，3】，B=【2，2，1】和S=3，函数应返回真。我们可以通过以下方式分配患者:[123]其中数组的第K个元素表示病人K被分配到的槽的编号。另一个正确的作业应该是[213]另一方面，【2,2，1】将是一个错误的分配，因为两个患者将被分配到插槽2。
2. 给定 A =【 3 , 2 , 3 , 1 】， B =【 1 , 3 ， 1 , 2 】和 S = 3 ，函数应该返回 false 。有四个病人想去看医生。因此，不可能在
将病人分配到各个时段，这样每次只能有一个病人去看医生。
3.给定A=【2,5,6,5】，B=【5,4,2,2】和S=8，函数应该返回true。例如，我们可以通过以下方式分配患者：[5, 4,6,2].
4.在给定A=【1,2,1,6,8,7,8】,B=【2,3,4,7，7,7】和S=10的情况下，函数应该返回false。不可能将所有的病人到他们喜欢的时段之一，以便只有一个病人将访问医生在一个时段。
根据上面的例子写一个有效的算法。:

思路
给定数组A和B，表示病人集合N到医生集合S的连线（每个病人i有两个偏好医生A[i]，B[i]），判断是否能满足每个病人的偏好
类似于判断二分图是否有完美匹配（匈牙利算法）
*/
//https://blog.csdn.net/weixin_41896265/article/details/126219829
func Solution(A []int, B []int, S int) bool {
	// write your code in Go (Go 1.4)
	N := len(A)
	if N > S {
		return false
	}

	matching, check := make([]int, N+S), make([]bool, N+S)
	for i := 0; i < N+S; i++ {
		matching[i] = -1
	}

	G := make(map[int][]int)
	for i := 0; i < N; i++ {
		G[i] = append(G[i], N+A[i]-1)
		G[i] = append(G[i], N+B[i]-1)
		G[N+A[i]-1] = append(G[N+A[i]-1], i)
		G[N+B[i]-1] = append(G[N+B[i]-1], i)
	} // 参考匈牙利算法，略复杂
	var dfs func(u int) bool
	dfs = func(u int) bool {
		for i := range G[u] {
			to := G[u][i]
			if check[to] {
				continue
			}
			check[to] = true
			if matching[to] == -1 || dfs(matching[to]) {
				matching[to] = u
				matching[u] = to
				return true
			}
		}
		return false
	}

	ret := 0
	for i := 0; i < N+S; i++ {
		if matching[i] != -1 {
			ret++
			continue
		}
		check = make([]bool, S+N)
		if dfs(i) {
			ret++
		}
	}
	fmt.Println(N, S, ret, 2*N, S+N)
	return ret == 2*N
}
