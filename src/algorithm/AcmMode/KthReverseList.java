package algorithm.AcmMode;

import algorithm.LinkedList.ReverseKGroup25;

import java.util.Scanner;

/**
 * 描述
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * 说明：
 * 1. 你需要自行定义链表结构，将输入的数据保存到你的链表中；
 * 2. 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换；
 * 3. 你的算法只能使用常数的额外空间。
 *
 *
 *
 * 输入描述：
 * 第一行输入是链表的值
 * 第二行输入是K的值，K是大于或等于1的整数
 *
 * 输入形式为：
 * 1 2 3 4 5
 * 2
 *
 * 输出描述：
 * 当 k = 2 时，应当输出:
 * 2 1 4 3 5
 *
 * 当 k = 3 时，应当输出:
 * 3 2 1 4 5
 *
 * 当k=6时，应当输出：
 * 1 2 3 4 5
 *
 * 示例1
 * 输入：
 * 1 2 3 4 5
 * 2
 * 输出：
 * 2 1 4 3 5
 */
public class KthReverseList {
    //定义Node节点
    static class ListNode {
        public int val;
        public ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        //1.获取输入信息
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int k = scanner.nextInt();
        String[] strings = string.split(" ");
        //2.创建头结点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        //3.将输入的字符串变为链表节点
        for (String str : strings) {
            tail.next = new ListNode(Integer.parseInt(str));
            tail = tail.next;
        }
        head = head.next;
        //每k个反转链表
        ListNode node = reverseGroup(head, k);
        //输出按照要求形式
        while(node!=null){
            System.out.print(node.val+" ");
            node = node.next;
        }
    }


    //k个反转具体实现
    public static ListNode reverseGroup(ListNode head, int k) {
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode curr=dummy,nextNode=dummy;
        int count=0;
        while(nextNode.next!=null){
            //换成for循环
            //for(int i=0;i<k&&nextNode!=null;i++) nextNode=nextNode.next;
            while(nextNode!=null&&count<k){
                nextNode=nextNode.next;
                count++;
            }
            if(nextNode==null){
                return dummy.next;
            }
            ListNode next=nextNode.next;
            ListNode pre=reverseLinkedList(curr.next,next);
            ListNode front=curr.next;
            front.next=next;
            curr.next=pre;
            curr=front;
            nextNode=front;
            count=0;
        }
        return dummy.next;
    }

    public static ListNode reverseLinkedList(ListNode head, ListNode end){
        ListNode pre=null;
        ListNode next;
        while(head!=end){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }
}
