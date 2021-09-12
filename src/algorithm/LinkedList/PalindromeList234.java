package algorithm.LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 * 提示：
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeList234 {

    /**
     * 与143重排链表很像
     * 1.先使用快慢指针找到中间节点（876找中间节点）
     * 2.反转链表后半部分节点
     * 3.两头比较是否为回文链表
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast=head,slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode newHead=reverse(slow.next);
        while(newHead!=null){
            if(newHead.val!=head.val){
                return false;
            }
            newHead=newHead.next;
            head=head.next;
        }
        return true;
    }

    /**
     * 使用数组存储链表
     * 双指针比较是否为回文链表
     */
    public boolean isPalindrome1(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();
        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }
        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    public ListNode reverse(ListNode curr){
        ListNode pre=null;
        ListNode next;
        while(curr!=null){
            next=curr.next;
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
