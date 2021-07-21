package stack.LinkedStack;

import java.util.Iterator;

public class LinkedStack<T> implements Iterable<T> {
    //记录首结点
    private Node head;
    //栈中元素的个数
    private int N;

    public LinkedStack() {
        head = new Node(null, null);
        N = 0;
    }
    //判断当前栈中元素个数是否为0
    public boolean isEmpty() {
        return N == 0;
    }
    //把t元素压入栈   设定head.next节点为栈顶元素
    public void push(T t) {
        Node oldNext = head.next;
        Node node = new Node(t, oldNext);
        head.next = node;
        //个数+1
        N++;
    }
    //弹出栈顶元素
    public T pop() {
        Node oldNext = head.next;
        if (oldNext == null) {
            return null;
        }
        //删除首个元素
        head.next = head.next.next;
        //个数-1
        N--;
        return oldNext.item;
    }
    //获取栈中元素的个数
    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T> {
        private Node n = head;

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            Node node = n.next;
            n = n.next;
            return node.item;
        }
    }

    private class Node {
        public T item;
        public Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}