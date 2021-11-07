package algorithm.LinkedList;

import org.junit.Test;

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

    @Test
    public void main() {
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
     * 迭代  --25题 一次反转k个指针的简单版
     */
    public ListNode swapPairs2(ListNode head) {
        if(head==null) return null;
        ListNode dummy=new ListNode(0,head);
        ListNode pre=dummy,curr=head;
        while(curr!=null&&curr.next!=null){
            ListNode next=curr.next.next;
            curr.next.next=curr;
            pre.next=curr.next;
            curr.next=next;
            pre=curr;
            curr=next;
        }
        return dummy.next;
    }

    /**
     * 使用递归 交换两两相邻的结点
     */
    public ListNode swapPairs(ListNode head) {
        //确定递归结束条件
        if(head==null||head.next==null) return head;
        ListNode next=head.next;
        head.next=swapPairs(next.next);
        next.next=head;
        //递归的返回值
        return next;
    }

    public ListNode swapPairs1(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
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
