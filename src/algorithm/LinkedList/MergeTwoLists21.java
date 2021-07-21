package algorithm.LinkedList;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoLists21 {
    public static void main(String[] args) {
        ListNode node4=new ListNode(7);
        ListNode node3=new ListNode(4,node4);
        ListNode node2=new ListNode(2,node3);
        ListNode head1=new ListNode(1,node2);

        ListNode node9=new ListNode(6);
        ListNode node8=new ListNode(4,node9);
        ListNode node7=new ListNode(3,node8);
        ListNode head2=new ListNode(1,node7);
        System.out.println(mergeTwoLists(head1,head2));
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead=new ListNode(0);
        ListNode pre=prehead;
        while (l1!=null&&l2!=null){
            if(l1.val<l2.val){
                pre.next=l1;
                l1=l1.next;
            }else {
                pre.next=l2;
                l2=l2.next;
            }
            pre=pre.next;
        }
        pre.next=(l1==null?l2:l1);
        return prehead.next;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
