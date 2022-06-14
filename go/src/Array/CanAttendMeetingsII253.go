package main

import (
	"container/heap"
	"sort"
)

func main() {
	intervals := [][]int{{1, 3}, {2, 6}, {8, 10}, {15, 18}}
	println(canAttendMeetingsII(intervals))
}

type Heap struct {
	sort.IntSlice
}

func (p *Heap) Less(i, j int) bool {
	return p.IntSlice[i] < p.IntSlice[j]
}

func (p *Heap) Pop() interface{} {
	last := p.IntSlice[len(p.IntSlice)-1]
	p.IntSlice = p.IntSlice[:len(p.IntSlice)-1]
	return last
}

func (p *Heap) Push(val interface{}) {
	p.IntSlice = append(p.IntSlice, val.(int))
}

func canAttendMeetingsII(intervals [][]int) int {
	min := &Heap{sort.IntSlice{}}
	for _, v := range intervals {
		if min.Len() > 0 && min.IntSlice[0] <= v[0] {
			heap.Pop(min)
		}
		heap.Push(min, v[1])
	}
	return min.Len()
}
