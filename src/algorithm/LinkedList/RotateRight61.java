package algorithm.LinkedList;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class RotateRight61 {

    /**
     * 先计算数组长度
     * 在封接成新链表
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||k==0) return head;
        int len=1;
        ListNode tail=head;
        while(tail.next!=null){
            ++len;
            tail=tail.next;
        }
        int diff=len-k%len;
        if(diff==len) return head;
        ListNode pre=head;
        for(int i=0;i<diff-1;++i){
            pre=pre.next;
        }
        ListNode newHead=pre.next;
        pre.next=null;
        tail.next=head;
        return newHead;
    }

    static class ListNode {
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
                    ", next=" + next +
                    '}';
        }
    }
}
