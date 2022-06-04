package main

func rotateRight(head *ListNode, k int) *ListNode {
	if head==nil||head.Next==nil{
		return head
	}
	count,curr:=0,head
	//这一步其实可以获得tail节点
	for curr!=nil{
		curr=curr.Next
		count++
	}
	mod:=k%count
	if mod==0{
		return head
	}
	slow,fast:=head,head
	for mod>0{
		fast=fast.Next
		mod--
	}
	for fast.Next!=nil{
		fast=fast.Next
		slow=slow.Next
	}
	newHead:=slow.Next
	slow.Next=nil
	fast.Next=head
	return newHead
}
