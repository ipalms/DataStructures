package main

func reorderList(head *ListNode){
	if head==nil||head.Next==nil{
		return
	}
	slow,fast:=head,head
	for fast!=nil&&fast.Next!=nil{
		slow=slow.Next
		fast=fast.Next.Next
	}
	l2:=reverse(slow.Next)
	slow.Next=nil
	l1:=head
	for l2!=nil{
		tmp2:=l2.Next
		l2.Next=l1.Next
		l1.Next=l2
		l1=l2.Next
		l2=tmp2
	}
}

func reverse(curr *ListNode)*ListNode{
	var pre *ListNode
	for curr!=nil{
		n:=curr.Next
		curr.Next=pre
		pre=curr
		curr=n
	}
	return pre
}
