package algorithm.LinkedList;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class RemoveNthFromEnd19 {

    /**
     * leetcode 19、82、83、剑指offer18、剑指offer22
     */

    /**
     * 链表题的两个经验
     * 1.舍得用辅助变量，千万别想着节省变量，否则容易被逻辑绕晕
     * 2.head有可能需要改动时，先增加一个假head（哑结点dummyHead，指向head）
     *   返回的时候直接取假head.next，这样就不需要为修改 head 增加一大堆逻辑了。
     */

    /**
     * 快慢指针--可看作双指针类型
     * 先让快指针移动n个位置
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(-1, head);
        ListNode fast = pre, slow = pre;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
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
    }
}
