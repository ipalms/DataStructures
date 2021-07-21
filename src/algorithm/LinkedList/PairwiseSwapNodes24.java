package algorithm.LinkedList;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 */
public class PairwiseSwapNodes24 {
    public static void main(String[] args) {
        ListNode node4=new ListNode(4);
        ListNode node3=new ListNode(3,node4);
        ListNode node2=new ListNode(2,node3);
        ListNode head=new ListNode(1,node2);
        System.out.println("没交换前");
        nodeForEach(head);
        System.out.println("交换后");
        ListNode listNode = swapPairs(head);
        nodeForEach(listNode);
    }

    /**
     * 使用递归 交换两两相邻的结点
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        //确定递归结束条件
        if(head==null||head.next==null){
            return head;
        }
        ListNode nodeChild=head.next.next;
        ListNode nodePassBy=head.next;
        nodePassBy.next=head;
        ListNode ss=swapPairs(nodeChild);
        head.next=ss;
        //递归的返回值
        return nodePassBy;
    }
    //打印链表的结点
    public static void nodeForEach(ListNode head){
        if(head!=null){
            System.out.println(head);
        }
        ListNode nextNode=head;
        while (nextNode.next!=null){
            System.out.println(nextNode.next);
            nextNode=nextNode.next;
       }
    }
    //结点类
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }
}
/*//结点类
class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}*/
