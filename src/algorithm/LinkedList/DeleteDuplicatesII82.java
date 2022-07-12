package algorithm.LinkedList;

/**
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，
 * 只保留原始链表中 没有重复出现的数字。
 * 返回同样按升序排列的结果链表。
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class DeleteDuplicatesII82 {


    public ListNode deleteDuplicatesMy(ListNode head) {
        if(head==null) return null;
        //构造一个哑节点指向头结点，防止当前头结点也是要被删除节点无法删除
        ListNode pre=new ListNode(200,head);
        //slow fast节点之间差一个位置 curr用来连贯删除元素后的节点
        ListNode slow=pre,fast=head,curr=pre;
        while(fast.next!=null){
            //说明fast节点处的元素为不重复元素
            if(slow.val!=fast.val&&fast.val!=fast.next.val){
                curr.next=fast;
                curr=curr.next;
            }
            //fast slow节点后移
            fast=fast.next;
            slow=slow.next;
        }
        if(slow.val!=fast.val)
            curr.next=fast;
        else
            curr.next=null;
        return pre.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        //维护一个不变的结点guard
        ListNode dummy = new ListNode(200,head);
        //pre cur任然可看作快慢节点
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            //如果出现重复的情况如下，相等的结点则跳过，走到相同值元素的最后一步
            while(cur.next!=null&&cur.next.val==cur.val)
                cur=cur.next;
            //如果pre和cur之间没有重复节点，pre后移
            if(pre.next==cur)
                pre=pre.next;
            //将cur的前指针向后移动一位
            else
                pre.next=cur.next;
            cur=cur.next;
        }
        return dummy.next;
    }


    /**
     * 递归版本
     */
    public ListNode deleteDuplicates2(ListNode head) {
        //递归终止情况
        if(head==null||head.next==null) return head;
        //如果不等值则删除，如果等值则要进行判断
        if(head.val==head.next.val){
            //此时本身要进行删除，但是下一个结点也要进行判断，来判断是否进行删除
            ListNode temp=head.next;
            //判断之后是否有相等的结点，没有则ok,有的话一直跳过这些节点
            while(temp!=null&&head.val==temp.val)
                temp=temp.next;
            //删除所有相同的结点
            //之后再对temp之后的结点进行预测
            return deleteDuplicates(temp);
        }
        head.next=deleteDuplicates(head.next);
        return head;
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
