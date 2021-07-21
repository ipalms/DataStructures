package linkedlist.CircleSingleLinkedList;

//用单向环形链表解决约瑟夫问题
public class Josepfu {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(35);// 加入5个小孩节点
        circleSingleLinkedList.showNode();

        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countNode(7, 5, 35);// 2->4->1->5->3

    }
}
