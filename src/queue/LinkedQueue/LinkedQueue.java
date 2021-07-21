package queue.LinkedQueue;

import java.util.Iterator;
import java.util.Queue;

public class LinkedQueue<T> implements Iterable<T> {
    //记录首结点
    private Node head;
    //记录最后一个结点
    private Node last;
    //记录队列中元素的个数
    private int N;
    public LinkedQueue() {
        head = new Node(null, null);
        last = null;
        N = 0;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    //返回队列中元素的个数
    public int size() {
        return N;
    }
    //向队列中插入元素t
    public void enqueue(T t) {
        if (last == null) {
            //将新插入的元素设为last节点
            last = new Node(t, null);
            head.next = last;
        } else {
            Node oldLast = last;
            last = new Node(t, null);
            oldLast.next = last;
        }
     //个数+1
        N++;
    }
    //从队列中拿出一个元素   从head.next取出队首的值
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = head.next;
        head.next = oldFirst.next;
        N--;
        if (isEmpty()) {
            last = null;
        }
        return oldFirst.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new QIterator();
    }
    private class QIterator implements Iterator<T> {
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