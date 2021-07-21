package algorithm.LinkedList;

import java.util.List;

public class ReverseBetween92 {
    public static void main(String[] args) {
        ListNode e=new ListNode(5);
        ListNode d=new ListNode(4,e);
        ListNode c=new ListNode(3,d);
        ListNode b=new ListNode(2,c);
        ListNode a=new ListNode(1,b);
        ListNode head=reverseBetween(a,1,4);
        System.out.println(head);
    }
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n){ return head; }
        int count=1;
        ListNode hair=new ListNode(0);
        hair.next=head;
        ListNode pre = hair;
        while (m>count){
            pre=head;
            head=head.next;
            count++;
        }
        ListNode before=head;
        ListNode curr=head.next;
        //if(curr==null) return hair.next;
        while (n>count){
            ListNode nex=curr.next;
            curr.next=before;
            before=curr;
            curr=nex;
            count++;
        }
        //若n=1则pre此时和hair结点指向相同
        //因为pre为引用变量，所以修改pre指向等同于修改hair指向，那么此时hair.next仍为反转后的第一个结点
        pre.next=before;
        head.next=curr;
        return hair.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        ListNode(int x,ListNode next) {
            val = x;
            this.next=next;
        }
        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                  /*  ", next=" + next +*/
                    '}';
        }
    }
}
