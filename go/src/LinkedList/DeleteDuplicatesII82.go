package main

func deleteDuplicates(head *ListNode) *ListNode {
	if head==nil||head.Next==nil {
		return head
	}
	dummy:=&ListNode{-200,head}
	slow,fast,curr:=dummy,head,dummy
	for fast!=nil&&fast.Next!=nil{
		if fast.Val!=fast.Next.Val&&slow.Val!=fast.Val{
			curr.Next=fast
			curr=curr.Next
		}
		fast=fast.Next
		slow=slow.Next
	}
	curr.Next=nil
	if slow.Val!=fast.Val{
		curr.Next=fast
	}
	return dummy.Next
}
