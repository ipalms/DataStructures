package linkedlist.DoubleLinkedList2;

import java.util.Iterator;

public class DoubleLinkedList2<T> implements Iterable<T>{
    //首结点
    private Node head;
    //最后一个结点（涉及到最后一个结点的增删要记得该表last指针的指向）
    private Node last;
    //链表的长度
    private int N;
    public DoubleLinkedList2() {
        last = null;
        head = new Node(null,null,null);
        N=0;
    }
    //清空链表
    public void clear(){
        last=null;
        head.next=last;
        head.pre=null;
        head.item=null;
        N=0;
    }
    //获取链表长度
    public int length(){
        return N;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return N==0;
    }
    //插入元素t（往链表尾端）
    public void insert(T t){
        if (last==null){
            last = new Node(t,head,null);
            head.next = last;
        }else{
            Node oldLast = last;
            Node node = new Node(t, oldLast, null);
            oldLast.next = node;
            //改变last指针指向
            last = node;
        }
        //长度+1
        N++;
    }
    //向指定位置i处插入元素t  如果N=0或i=N,调用上面方法插入尾部
    public void insert(int i,T t){
        //链表为空，或往最后一个节点的后面加节点情况
        if(N==i||N==0){
            insert(t);
            return;
        }
        if (i<0 || i>N){
            throw new RuntimeException("位置不合法");
        }
        //找到位置i的前一个结点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        //当前结点
        Node curr = pre.next;
        //构建新结点
        Node newNode = new Node(t, pre, curr);
        curr.pre= newNode;
        pre.next = newNode;
        //长度+1
        N++;
    }
    //获取指定位置i处的元素
    public T get(int i){
        if (i<0||i>=N){
            throw new RuntimeException("位置不合法");
        }
        //寻找当前结点
        Node curr = head.next;
        for (int index = 0; index <i; index++) {
            curr = curr.next;
        }
        return curr.item;
    }
    //找到元素t在链表中第一次出现的位置
    public int indexOf(T t){
        Node n= head;
        for (int i=0;n.next!=null;i++){
            n = n.next;
            if (n.next.equals(t)){
                return i;
            }
        }
        return -1;
    }
    //删除位置i处的元素，并返回该元素（考虑到可能删除最后一个结点情况）
    public T remove(int i){
        if (i<0 || i>=N){
            throw new RuntimeException("位置不合法");
        }
        //寻找i位置的前一个元素
        Node pre = head;
        for (int index = 0; index <i ; index++) {
            pre = pre.next;
        }
        //i位置的元素
        Node curr = pre.next;
        //i位置的下一个元素
        Node curr_next = curr.next;
        pre.next = curr_next;
        if(curr_next!=null) {
            curr_next.pre = pre;
        }else {
            //被删除的结点是最后一个结点,改变last指针指向
            last=pre;
        }
        //长度-1；
        N--;
        return curr.item;
    }
    //获取第一个元素
    public T getFirst(){
        if (isEmpty()){
            return null;
        }
        return head.next.item;
    }
    //获取最后一个元素
    public T getLast(){
        if (isEmpty()){
            return null;
        }
        return last.item;
    }
    @Override
    public Iterator<T> iterator() {
        return new TIterator();
    }
    private class TIterator implements Iterator{
        private Node n = head;
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }
        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }
    //结点类
    private class Node{
        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
        //存储数据
        public T item;
        //指向上一个结点
        public Node pre;
        //指向下一个结点
        public Node next;
    }
}