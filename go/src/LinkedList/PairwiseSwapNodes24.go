package main


func swapPairs(head *ListNode) *ListNode {
	if head==nil||head.Next==nil{
		return head
	}
	dummy:=&ListNode{0,head}
	pre,curr:=dummy,head
	for curr!=nil&&curr.Next!=nil{
		l1:=curr.Next
		l2:=curr.Next.Next
		curr.Next=l2
		l1.Next=curr
		pre.Next=l1
		pre=curr
		curr=l2
	}
	return dummy.Next
}


//官方版本
func swapPairs2(head *ListNode) *ListNode {
	dummyHead := &ListNode{0, head}
	temp := dummyHead
	for temp.Next != nil && temp.Next.Next != nil {
		node1 := temp.Next
		node2 := temp.Next.Next
		temp.Next = node2
		node1.Next = node2.Next
		node2.Next = node1
		temp = node1
	}
	return dummyHead.Next
}
