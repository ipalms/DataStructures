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
        System.out.println(mergeTwoListsNoRepeat(head1,head2));
    }

    public static ListNode mergeTwoListsMy(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode();
        ListNode curr=dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                curr.next=l1;
                l1=l1.next;
            }else{
                curr.next=l2;
                l2=l2.next;
            }
            curr=curr.next;
        }
        curr.next=l1==null?l2:l1;
        return dummy.next;
    }

    /**
     * 合并链表并去重做法
     */
    public static ListNode mergeTwoListsNoRepeat(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode();
        ListNode curr=dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                if(curr==dummy||l1.val!=curr.val){
                    curr.next=l1;
                    curr=curr.next;
                }
                l1=l1.next;
            }else{
                if(curr==dummy||l2.val!=curr.val) {
                    curr.next = l2;
                    curr=curr.next;
                }
                l2=l2.next;
            }
        }
        curr.next=l1==null?l2:l1;
        return dummy.next;
    }

    /**
     * 递归做法
     */
    public ListNode mergeTwoListsRecurse(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoListsRecurse(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecurse(l1, l2.next);
            return l2;
        }
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
