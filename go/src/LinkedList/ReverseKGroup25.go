package main

func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy:=&ListNode{0,head}
	pre:=dummy
	for pre!=nil{
		curr,first:=pre,pre.Next
		for i:=0;i<k;i++{
			if curr.Next==nil{
				return dummy.Next
			}
			curr=curr.Next
		}
		tmp:=curr.Next
		pre.Next=reverseKNode(first2,tmp)
		first.Next=tmp
		pre=first
	}
	return dummy.Next
}

func reverseKNode(curr,tail *ListNode)*ListNode{
	var pre *ListNode=nil
	for curr!=tail{
		n:=curr.Next
		curr.Next=pre
		pre=curr
		curr=n
	}
	return pre
}
