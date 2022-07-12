package algorithm.LinkedList;

/**
 * 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次。
 * 返回同样按升序排列的结果链表。
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class DeleteDuplicates83 {

    //没有使用辅助指针
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        //由于头结点可以确保不会变动，所以可以不使用辅助节点指向头结点
        ListNode curr=head;
        while(curr.next!=null){
            //相等改变当前节点的next指向
            if(curr.val==curr.next.val){
                curr.next=curr.next.next;
            }else{
                //不相等curr节点右移
                curr=curr.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates4(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode curr=head,next=head.next;
        while(next!=null){
            if(next.val!=curr.val){
                curr.next=next;
                curr=next;
            }
            next=next.next;
        }
        curr.next=null;
        return head;
    }

    //使用了辅助指针
    public ListNode deleteDuplicates1(ListNode head) {
        if(head==null) return null;
        ListNode pre=new ListNode(0,head);
        //使用了两个指针完成删除操作
        ListNode slow=pre,fast=head;
        while(fast.next!=null){
            //快指针遇到了新值，让慢指针指向快指针所在
            if(fast.val!=fast.next.val){
                slow.next=fast;
                slow=slow.next;
            }
            fast=fast.next;
        }
        slow.next=fast;
        return pre.next;
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
