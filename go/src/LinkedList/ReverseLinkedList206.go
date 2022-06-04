package main

type ListNode struct {
	Val int
	Next *ListNode
}

func reverseList(head *ListNode) *ListNode {
	if head==nil || head.Next==nil {
		return head
	}
	tmp:=reverseList(head.Next)
	head.Next.Next=head
	head.Next=nil
	return tmp
}

func reverseList1(head *ListNode) *ListNode {
	var pre,cur *ListNode=nil,head
	for ;cur!=nil;{
		next:=cur.Next
		cur.Next=pre
		pre=cur
		cur=next
	}
	return pre
}
