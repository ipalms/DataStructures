package main

import (
	"container/heap"
)

func main() {
	//nums := []int{1, 1, 1, 2, 2, 3, 4, 5, 6, 7}
	//k := 2
	//res := topKFrequent(nums, k)
	//for _, v := range res {
	//	println(v)
	//}
	s1 := make([][]int, 2+1)
	for i := 0; i <= 2; i++ {
		println(s1[i] == nil)
		//s1[i] = []int{}
		s1[i] = append(s1[i], 1)
		print(len(s1[i]), "\t")
		println(s1[i] == nil)
	}
}

// 实现堆接口的结构体可以不需要len字段，因为len(arr)就是元素个数
type MinHeap struct {
	arr [][2]int
	len int
}

func (p *MinHeap) Len() int {
	return p.len
}

func (p *MinHeap) Less(i, j int) bool {
	return p.arr[i][1] < p.arr[j][1]
}

func (p *MinHeap) Swap(i, j int) {
	p.arr[i], p.arr[j] = p.arr[j], p.arr[i]
}

func (p *MinHeap) Push(v interface{}) {
	p.len++
	p.arr = append(p.arr, v.([2]int))
}

func (p *MinHeap) Pop() interface{} {
	val := p.arr[p.len-1]
	p.len--
	p.arr = p.arr[:p.len]
	return val
}

func topKFrequent(nums []int, k int) []int {
	cache := make(map[int]int)
	for _, v := range nums {
		cache[v]++
	}
	h := &MinHeap{}
	for i, v := range cache {
		heap.Push(h, [2]int{i, v})
		if h.Len() > k {
			heap.Pop(h)
		}
	}
	res := []int{}
	for h.Len() > 0 {
		res = append(res, heap.Pop(h).([2]int)[0])
	}
	return res
}

func topKFrequent2(nums []int, k int) []int {
	size := len(nums)
	s1 := make([][]int, size+1)
	cache := make(map[int]int)
	for _, v := range nums {
		cache[v]++
	}
	for i, v := range cache {
		// 即使s1[i]是空的，但是任然可以执行append操作
		//if s1[v] == nil {
		//	s1[v] = []int{}
		//}
		s1[v] = append(s1[v], i)
	}
	res := []int{}
	for i := size; i >= 0 && k > 0; i-- {
		// append操作不仅可以添加一个元素，还可以添加多个元素（添加一个切片进去）
		res = append(res, s1[i]...)
		k -= len(s1[i])
	}
	return res
}
