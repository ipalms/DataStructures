package main

func isPalindrome(head *ListNode) bool {
	if head==nil||head.Next==nil{
		return true
	}
	reverse:=func(curr *ListNode)*ListNode{
		var pre *ListNode=nil
		for curr!=nil{
			n:=curr.Next
			curr.Next=pre
			pre=curr
			curr=n
		}
		return pre
	}
	slow,fast:=head,head
	for fast.Next!=nil&&fast.Next.Next!=nil{
		fast=fast.Next.Next
		slow=slow.Next
	}
	l2:=reverse(slow.Next)
	slow.Next=nil
	for l2!=nil{
		if head.Val!=l2.Val{
			return false
		}
		head=head.Next
		l2=l2.Next
	}
	return true
}
