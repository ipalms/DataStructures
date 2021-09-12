package algorithm.LinkedList;

import org.junit.Test;

import java.util.List;

public class ReverseBetween92 {

    @Test
    public void test() {
        ListNode e=new ListNode(5);
        ListNode d=new ListNode(4,e);
        ListNode c=new ListNode(3,d);
        ListNode b=new ListNode(2,c);
        ListNode a=new ListNode(1,b);
        ListNode head=reverseBetween1(a,1,4);
        System.out.println(head);
    }

    /**
     * 这个实现逻辑是找到链表要交换的前一个节点（m-1）位置，对后面的m-n个节点采取和反转链表一样的操作
     * 最后连接好反转的首尾部分--穿针引线
     */
    public ListNode reverseBetweenMy(ListNode head, int left, int right) {
        if(left==right) return head;
        ListNode dummy=new ListNode(0,head);
        ListNode pre=dummy;
        int start=1;
        while(start<left){
            pre=pre.next;
            start++;
        }
        ListNode before=pre.next;
        ListNode curr=pre.next.next;
        while(left<right){
            ListNode next=curr.next;
            curr.next=before;
            before=curr;
            curr=next;
            left++;
        }
        //将两端连上
        pre.next.next=curr;
        pre.next=before;
        return dummy.next;
    }

    /**
     * 头插法：
     * 第二种思路，遍历到要反转的区域节点将这个节点直接插入到待反转区域最前面
     * 使用三个指针变量 pre、curr、next 来记录反转的过程中需要的变量，它们的意义如下：
     * curr：指向待反转区域的第一个节点 left；
     * next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
     * pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。
     * 操作步骤：
     * 先将 curr 的下一个节点记录为 next；
     * 执行操作 ①：把 curr 的下一个节点指向 next 的下一个节点；
     * 执行操作 ②：把 next 的下一个节点指向 pre 的下一个节点；
     * 执行操作 ③：把 pre 的下一个节点指向 next。
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //if (left == right) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        int start = 1;
        //while循环可以换成for循环
        while (start < left) {
            pre = pre.next;
            start++;
        }
        ListNode curr=pre.next;
        ListNode next;
        while(left<right){
            next=curr.next;
            curr.next=next.next;
            next.next=pre.next;
            pre.next=next;
            left++;
        }
        return dummy.next;
    }

    //同第一中思路
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if(m==n){ return head; }
        int count=1;
        ListNode hair=new ListNode(0,head);
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
