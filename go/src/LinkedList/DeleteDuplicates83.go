package main

func deleteDuplicates1(head *ListNode) *ListNode {
	if head==nil||head.Next==nil{
		return head
	}
	pre,curr:=head,head.Next
	for curr!=nil{
		if pre.Val!=curr.Val{
			pre.Next=curr
			pre=pre.Next
		}
		curr=curr.Next
	}
	pre.Next=nil
	return head
}

func deleteDuplicates2(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	cur := head
	for cur.Next != nil {
		if cur.Val == cur.Next.Val {
			cur.Next = cur.Next.Next
		} else {
			cur = cur.Next
		}
	}

	return head
}
