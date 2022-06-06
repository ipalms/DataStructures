package main

import "fmt"

type HeapInterface interface {
	Less(i, j int) bool
	Len() int
	Push(v interface{})
	Pop() interface{}
}

func main() {
	a := []int{10, 9, 8, 16, 15}
	fmt.Println(len(a))
	h := &Heap{a, len(a)}
	h.Heapity()
	for i, v := range a {
		fmt.Printf("index:%v,val:%v", i, v)
	}
	fmt.Println()
	h.Offer(1)
	h.Offer(2)
	h.Offer(156)
	h.Offer(13)
	for i, v := range h.arr {
		fmt.Printf("index:%v,val:%v", i, v)
	}
}

type Heap struct {
	arr   []int
	index int
}

func (h *Heap) Less(i, j int) bool {
	return i > j
}

func (h *Heap) Len() int {
	return h.index
}

func (h *Heap) Heapity() {
	a := h.arr
	for i := h.index / 2; i >= 0; i-- {
		h.shifDown(a, i)
	}
}

func (h *Heap) Offer(val int) {
	l := len(h.arr)
	if h.index == l {
		vals := make([]int, 2*l, 2*l)
		copy(vals, h.arr)
		h.arr = vals
	}
	h.shifUp(h.arr, h.index, val)
	h.index++
}

func (h *Heap) Poll() int {
	val := h.arr[0]
	h.arr[0] = h.arr[h.index-1]
	h.index--
	h.shifDown(h.arr, 0)
	return val
}

func (h *Heap) shifDown(a []int, i int) {
	tmp := a[i]
	for k := 2*i + 1; k < h.index; k = 2*k + 1 {
		if k+1 < h.index && h.Less(a[k+1], a[k]) {
			k++
		}
		if h.Less(tmp, a[k]) {
			break
		} else {
			a[i] = a[k]
			i = k
		}
	}
	a[i] = tmp
}

func (h *Heap) shifUp(arr []int, index int, val int) {
	for i := (index - 1) / 2; i >= 0 && h.Less(val, arr[i]); i = (i - 1) / 2 {
		arr[index] = arr[i]
		index = i
	}
	arr[index] = val
}
