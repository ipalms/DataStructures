package algorithm.LinkedList;

import org.junit.Test;

/**
 * 补充题：
 * 给定一个奇数位升序，偶数位降序的链表，将其重新排序。
 * 输入: 1->8->3->6->5->4->7->2->NULL
 * 输出: 1->2->3->4->5->6->7->8->NULL
 * 以第一位数为奇数，第二位数为偶数依次计算
 */
public class SortOddEvenList {

    @Test
    public void main() {
        ListNode node3=new ListNode(4);
        ListNode node8=new ListNode(4,node3);
        ListNode node7=new ListNode(3,node8);
        ListNode node9=new ListNode(6,node7);
        ListNode node2=new ListNode(2,node9);
        ListNode node4=new ListNode(7,node2);
        ListNode head2=new ListNode(1,node4);
        sortOddEvenList(head2);
        while(head2!=null){
            System.out.println(head2.val);
            head2=head2.next;
        }
    }

    /**
     * 可以看作一道缝合题：拆分--反转--合并
     * step1:leetcode328奇偶链表，去掉两链表合并的步骤； 
     * step2：leetcode206反转链表；
     * step3:leetcode21合并两个有序链表；
     * 1. 按奇偶位置拆分链表，得1->3->5->7->NULL和8->6->4->2->NULL
     * 2. 反转偶链表，得1->3->5->7->NULL和2->4->6->8->NULL
     * 3. 合并两个有序链表，得1->2->3->4->5->6->7->8->NULL
     */
    public ListNode sortOddEvenList(ListNode head){
        if(head==null||head.next==null) return head;
        //要保存好偶数链表链头，后面链表反转时会用到
        ListNode next=head.next;
        ListNode even=head,odd=next;
        while(odd!=null&&odd.next!=null){
            ListNode tmp=odd.next;
            odd.next=tmp.next;
            even.next=tmp;
            odd=odd.next;
            even=even.next;
        }
        even.next=null;
        ListNode newOdd = reverse(next);
        ListNode dummy=new ListNode(0);
        ListNode curr=dummy;
        while(head!=null&&newOdd!=null){
            if(head.val<newOdd.val){
                curr.next=head;
                head=head.next;
            }else{
                curr.next=newOdd;
                newOdd=newOdd.next;
            }
            curr=curr.next;
        }
        curr.next=head==null?newOdd:head;
        return dummy.next;
    }

    private ListNode reverse(ListNode head){
        if(head==null) return null;
        ListNode pre=null,next;
        while(head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
