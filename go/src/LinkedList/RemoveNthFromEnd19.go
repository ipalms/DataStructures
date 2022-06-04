package main

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	dummy:=&ListNode{-1,head}
	slow,fast:=dummy,head
	for n>0{
		fast=fast.Next
		n--
	}
	for fast!=nil{
		fast=fast.Next
		slow=slow.Next
	}
	slow.Next=slow.Next.Next
	return dummy.Next
}
