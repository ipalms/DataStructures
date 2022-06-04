package main

func mergeTwoLists(list1 *ListNode, list2 *ListNode) *ListNode {
	pre:=&ListNode{}
	cur:=pre
	for list1!=nil&&list2!=nil{
		if list1.Val<=list2.Val{
			cur.Next=list1
			list1=list1.Next
		}else{
			cur.Next=list2
			list2=list2.Next
		}
		cur=cur.Next
	}
	if list1==nil{
		cur.Next=list2
	}else{
		cur.Next=list1
	}
	return pre.Next
}
