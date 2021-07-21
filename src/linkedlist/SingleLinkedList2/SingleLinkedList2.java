package linkedlist.SingleLinkedList2;

import java.util.Iterator;
//  黑马的单向链表案例，索引是人为加入的
public class SingleLinkedList2<T> implements Iterable<T> {
    //记录头结点
    private Node head;
    //记录链表的长度（最后一个结点的后一个位置）
    private int N;
    public SingleLinkedList2(){
    //初始化头结点
        head = new Node(null,null);
        N=0;
    }
    //清空链表
    public void clear(){
        head.next=null;
        head.item=null;
        N=0;
    }

    //获取链表的长度
    public int length(){
        return N;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return N==0;
    }
    //获取指定位置i出的元素
    public T get(int i){
        if (i<0||i>=N){
            throw new RuntimeException("位置不合法！");
        }
        Node n = head.next;
        //使用for循环找待目标结点
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }
    //向链表尾部添加元素t（先添加元素，再将N++）
    public void insert(T t){
        //找到最后一个节点
        Node n = head;
        while(n.next!=null){
            n = n.next;
        }
        Node newNode = new Node(t, null);
        n.next = newNode;
        //链表长度+1
        N++;
    }
    //向指定位置i处，添加元素t   如果N=0或i=N,调用上面方法插入尾部
    public void insert(int i,T t){
        if(N==i||N==0){
            insert(t);
            return;
        }
        if (i<0||i>=N){
            throw new RuntimeException("位置不合法！");
        }
        //寻找位置i之前的结点
        Node pre = head; //辅助指针
        for (int index = 0; index <=i-1; index++) {
            pre = pre.next;
        }
        //位置i的结点
        Node curr = pre.next;
        //构建新的结点，让新结点指向位置i的结点
        Node newNode = new Node(t, curr);
        //让之前的结点指向新结点
        pre.next = newNode;
        //长度+1
        N++;
    }
    //删除指定位置i处的元素，并返回被删除的元素
    public T remove(int i){
        if (i<0 || i>=N){
            throw new RuntimeException("位置不合法");
        }
        //寻找i之前的元素
        Node pre = head;
        for (int index = 0; index <=i-1; index++) {
            pre = pre.next;
        }
        //当前i位置的结点
        Node curr = pre.next;
        //前一个结点指向下一个结点，删除当前结点
        pre.next = curr.next;
        //长度-1
        N--;
        return curr.item;
    }
    //查找元素t在链表中第一次出现的位置
    public int indexOf(T t){
        Node n = head;
        for (int i = 0;n.next!=null;i++){
            n = n.next;
            if (n.item.equals(t)){
                return i;
            }
        }
        return -1;
    }

    //结点类(内部类形式)
    private class Node{
        //存储数据
        T item;
        //下一个结点
        Node next;
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }


    /**
     * 一种链表反转的形式
     */
    public void reverse(){
        if (N==0){
        //当前是空链表，不需要反转
            return;
        }
        reverse(head.next);
    }
    /**
     * @param curr 当前遍历的结点
     * @return 反转后当前结点上一个结点  带头结点的反转
     */
    public Node reverse(Node curr){
        //已经到了最后一个元素（有头结点的情况  递归终止条件）
        if (curr.next==null){
        //反转后，头结点应该指向原链表中的最后一个元素
            head.next=curr;
            return curr;
        }
        //当前结点的上一个结点
        Node pre = reverse(curr.next);
        pre.next = curr;
        //当前结点的下一个结点设为null
        curr.next=null;
        //返回当前结点
        return curr;
    }

    //实现遍历
    @Override
    public Iterator iterator() {
        return new LIterator();
    }
    private class LIterator implements Iterator<T> {
        //辅助变量
        private Node n;
        public LIterator() {
            this.n = head;  //初始化为头结点
        }
        @Override
        public boolean hasNext() {
            return n.next!=null;
        }
        @Override
        public T next() {
            n = n.next;
            return n.item;
        }
    }
}
