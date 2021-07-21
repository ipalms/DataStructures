package linkedlist.CircleSingleLinkedList;

public class Node {
    private int no;// 编号
    private Node next; // 指向下一个节点,默认null

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
