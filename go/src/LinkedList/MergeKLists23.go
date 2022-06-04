package main


//传统的java写法
func mergeKLists(lists []*ListNode) *ListNode {
	start,end:=0,len(lists)-1
	return mergeK(lists,start,end)
}

//Go可以利用Slice少一次方法调用
func mergeKLists1(lists []*ListNode) *ListNode {
	length:=len(lists)
	if length==0{
		return nil
	}
	if length==1{
		return lists[0]
	}
	return mergeTwo(mergeKLists1(lists[:length/2]),mergeKLists1(lists[length/2:]))
}

func mergeK(lists []*ListNode,start,end int)*ListNode{
	if start>end {
		return nil
	}
	if start==end {
		return lists[start]
	}
	mid:=start+(end-start)/2
	return mergeTwo(mergeK(lists,start,mid),mergeK(lists,mid+1,end))
}

func mergeTwo(l1,l2 *ListNode)*ListNode{
	dummy:=&ListNode{}
	curr:=dummy
	for l1!=nil&&l2!=nil{
		if l1.Val<=l2.Val{
			curr.Next=l1
			l1=l1.Next
		}else{
			curr.Next=l2
			l2=l2.Next
		}
		curr=curr.Next
	}
	if l1==nil{
		curr.Next=l2
	}else{
		curr.Next=l1
	}
	return dummy.Next
}
