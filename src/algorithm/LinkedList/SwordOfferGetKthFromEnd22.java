package algorithm.LinkedList;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，
 * 即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。
 * 这个链表的倒数第 3 个节点是值为 4 的节点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 */
public class SwordOfferGetKthFromEnd22 {

    //快慢指针，与leetcode19题几乎一摸一样
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null) return null;
        ListNode curr=head,pre=head;
        while(k>0){
            curr=curr.next;
            k--;
        }
        while(curr!=null){
            curr=curr.next;
            pre=pre.next;
        }
        return pre;
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
