package algorithm.LinkedList;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *  L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 示例 2:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class ReorderList143 {
    /**
     * 使用了了栈的额外空间
     */
    public void reorderListMy(ListNode head) {
        Deque<ListNode> stack=new LinkedList<>();
        int count=0;
        ListNode curr=head;
        //计算链表长度
        while(curr!=null){
            curr=curr.next;
            count++;
        }
        //将后半部分节点加入栈
        ListNode tmp=head;
        int index=(count+1)/2;
        for(int i=0;i<count;++i){
            if(i>=index)
                stack.add(tmp);
            tmp=tmp.next;
        }
        ListNode p=head;
        //拼接
        for(int i=0;i<count/2;++i){
            ListNode next=p.next;
            ListNode have=stack.pollLast();
            have.next=next;
            p.next=have;
            p=next;
        }
        if(p!=null){
            p.next=null;
        }
    }

    /**
     * 目标链表即为将原链表的左半端和反转后的右半端合并后的结果
     * 具体可划分三步骤：
     * 1.找到原链表的中点（参考「876. 链表的中间结点」）。
     *    我们可以使用快慢指针来O(N)地找到链表的中间节点。
     * 2.将原链表的右半端反转（参考「206. 反转链表」）。
     *    我们可以使用迭代法实现链表的反转。
     * 3.将原链表的两端合并。
     *   因为两链表长度相差不超过1，因此直接合并即可。
     */
    public void reorderList(ListNode head) {
        ListNode fast=head,slow=head;
        //迭代结束后slow指针指向的是目标链表最后一个节点
        //可看作找链表中间节点--其中这个中间节点如果链表长度为偶数就是中间靠前的哪一个
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //反转后半段链表
        ListNode newHead = reverseList(slow.next);
        slow.next=null;
        //链表合并
        while(newHead!=null){
            ListNode next=newHead.next;
            newHead.next=head.next;
            head.next=newHead;
            head=newHead.next;
            newHead=next;
        }
    }

    /**
     * 使用线性表存储节点，这样就支持下标访问节点（O（1）访问目标节点）
     * O（N）额外空间
     */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    private ListNode reverseList(ListNode curr) {
        ListNode pre=null;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=pre;
            pre=curr;
            curr=next;
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
    }
}
