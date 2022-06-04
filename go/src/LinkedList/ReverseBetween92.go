package main

func reverseBetween(head *ListNode, left int, right int) *ListNode {
	dummy:=&ListNode{Val:0,Next:head}
	pre:=dummy
	i:=0
	for ;i<left-1;i++{
		pre=pre.Next
	}
	curr:=pre.Next
	var before *ListNode=nil
	for ;i<right;i++{
		next:=curr.Next
		curr.Next=before
		before=curr
		curr=next
	}
	pre.Next.Next=curr
	pre.Next=before
	return dummy.Next
}
