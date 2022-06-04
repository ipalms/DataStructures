package main

type Node struct {
	Val    int
	Next   *Node
	Random *Node
}

func copyRandomList(head *Node) *Node {
	m := make(map[*Node]*Node)
	copy(head, m)
	return m[head]
}

func copy(curr *Node, m map[*Node]*Node) *Node {
	if curr==nil{
		return nil
	}
	if val, ok := m[curr]; ok {
		return val
	}
	n := &Node{Val: curr.Val}
	m[curr] = n
	n.Next = copy(curr.Next, m)
	n.Random = copy(curr.Random, m)
	return n
}