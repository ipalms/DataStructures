package main

import (
	"container/list"
	"fmt"
	"testing"
)

func Test_List(t *testing.T){
	s(t)
}

//测试Go 自带的双向链表的API
func s(t *testing.T) {
	l:=list.New()
	l.PushBack("1")
	l.PushFront("2")
	//从前往后遍历链表
	for f:=l.Front();f!=nil;f=f.Next(){
		fmt.Println(f.Value)
	}
	fmt.Println()
	//从后往前遍历链表
	for e:=l.Back();e!=nil;e=e.Prev(){
		fmt.Println(e.Value)
	}
	fmt.Println()
	//重置链表
	l.Init()
	l.PushFront("3")
	p2:=l.PushFront("4")
	l.PushFront("5")
	//在标杆节点后插入
	l.InsertAfter("6",p2)
	l.InsertBefore("7",p2)
	//移除某个节点
	l.Remove(p2)
	for f:=l.Front();f!=nil;f=f.Next(){
		fmt.Println(f.Value)
	}
	fmt.Println(l.Len())
}



