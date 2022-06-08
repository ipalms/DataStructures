package main

import (
	"container/heap"
	"sort"
)

//在go语言的标准库里，有container/list库可以提供链表相关的功能。

//Go 可以用切片模拟队列（或栈）
//因为队列只涉及队头和队尾的增删
//所以可以用切片截取的形式起到删除作用 append起到添加元素的作用
func maxSlidingWindow(nums []int, k int) []int {
	q, res := []int{}, []int{}
	for i, v := range nums {
		n := len(q)
		for len(q) > 0 && nums[q[n-1]] <= v {
			q = q[:n-1]
			n--
		}
		q = append(q, i)
		if i-q[0]+1 > k {
			q = q[1:]
		}
		if i-k+1 >= 0 {
			res = append(res, nums[q[0]])
		}
	}
	return res
}

//两种优先队列  及 解题的写法
//IntSlice自己实现了sort.Interface接口的方法（len()、less()、swap()）
//对于实现堆排序或者优先队列可以只需要再覆写less()、pop()、push()方法即可

//IntSlice也是可以直接调用排序方法的切片，生成时赋予初始值，可以调用sort.IntSlice.sort()方法对Int类型切皮纳排序
//同样的，还有sort.Float64Slice.sort()方法对Float64类型切片排序以及sort.StringSlice.sort()方法对String类型切片排序

var a []int

type hp struct {
	sort.IntSlice
}

// 大根堆
// Go中的比较函数的两个参数在堆中代表的位置与Java正好相反
// 所以比较的内容正好与Java相反
func (h hp) Less(i, j int) bool {
	return a[h.IntSlice[i]] > a[h.IntSlice[j]]
}

func (h *hp) Push(v interface{}) {
	h.IntSlice = append(h.IntSlice, v.(int))
}

func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

func maxSlidingWindow3(nums []int, k int) []int {
	a = nums
	q := &hp{make([]int, k)}
	for i := 0; i < k; i++ {
		q.IntSlice[i] = i
	}
	heap.Init(q)

	n := len(nums)
	ans := make([]int, 1, n-k+1)
	ans[0] = nums[q.IntSlice[0]]
	for i := k; i < n; i++ {
		heap.Push(q, i)
		for q.IntSlice[0] <= i-k {
			heap.Pop(q)
		}
		ans = append(ans, nums[q.IntSlice[0]])
	}
	return ans
}

//写法二
type window struct {
	val   int
	index int
}

type Heap struct {
	*_heap
}

func (h *Heap) Push(v interface{}) {
	heap.Push(h._heap, v)
}

func (h *Heap) Pop() interface{} {
	return heap.Pop(h._heap)
}

func (h *Heap) Fix(i int) {
	heap.Fix(h._heap, i)
}

type _heap struct {
	slice []window
	len   int
}

func CreateHeap() Heap {
	h := &_heap{}
	heap.Init(h)
	return Heap{h}
}

func (h *_heap) Len() int {
	return h.len
}

func (h *_heap) Less(i, j int) bool {
	// 最大堆
	return (h.slice)[i].val > (h.slice)[j].val
}

func (h *_heap) Swap(i, j int) {
	(h.slice)[i], (h.slice)[j] = (h.slice)[j], (h.slice)[i]
}

func (h *_heap) Push(x interface{}) {
	h.len++
	h.slice = append(h.slice, x.(window))
}

func (h *_heap) Pop() (ret interface{}) {
	if h.len != 0 {
		ret = (h.slice)[h.Len()-1]
		h.slice = (h.slice)[:h.Len()-1]
		h.len--
		return
	}
	return
}

func (h *_heap) Top() (ret interface{}) {
	if h.len != 0 {
		ret = (h.slice)[0]
		return
	}
	return
}

func maxSlidingWindow2(nums []int, k int) []int {
	// 创建堆
	h := CreateHeap()
	var ans []int
	for i := 0; i < len(nums); i++ {
		// 先将当前元素推入堆中
		h.Push(window{
			val:   nums[i],
			index: i,
		})
		// 如果窗口已经达到,则取堆顶,判断是否有效,无效进行删除
		if i >= k-1 {
			// 判断栈顶是否符合规范
			for h.Len() != 0 && h.Top().(window).index <= i-k {
				h.Pop()
			}
			if h.Len() != 0 {
				ans = append(ans, h.Top().(window).val)
			}
		}
	}
	return ans
}
