package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {

	obj := Constructor()
	param_1 := obj.Insert(1)
	param_2 := obj.Remove(0)
	param_3 := obj.GetRandom()
	fmt.Print(param_1, param_2, param_3)
}

type RandomizedSet struct {
	m map[int]int
	v []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{m: make(map[int]int), v: []int{}}
}

func (this *RandomizedSet) Insert(val int) bool {
	if _, ok := this.m[val]; ok {
		return false
	}
	this.v = append(this.v, val)
	this.m[val] = len(this.v)
	return true
}

func (this *RandomizedSet) Remove(val int) bool {
	res, ok := this.m[val]
	if !ok {
		return false
	}
	last := len(this.v) - 1
	this.v[res] = this.v[last]
	this.m[res] = last
	this.v = this.v[:last]
	delete(this.m, val)
	return true
}

func (this *RandomizedSet) GetRandom() int {
	rand.Seed(time.Now().UnixNano())
	return this.v[rand.Intn(len(this.v))]
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */
