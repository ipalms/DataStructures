package main

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	dummy:=&ListNode{}
	curr:=dummy
	out:=0
	for l1!=nil||l2!=nil||out>0{
		sum:=out
		if l1!=nil{
			sum+=l1.Val
			l1=l1.Next
		}
		if l2!=nil{
			sum+=l2.Val
			l2=l2.Next
		}
		out=sum/10
		curr.Next=&ListNode{Val:sum%10}
		curr=curr.Next
	}
	return dummy.Next
}
