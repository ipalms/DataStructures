package algorithm.LinkedList;


import java.util.HashSet;
import java.util.Set;

/**
 * 141.环形链表
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * 提示：
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 */
public class JudgmentCycleLinkList141 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(2, node2);
        node1.next=node3;
        System.out.println( hasCycle(node3));
    }

     /**
     * 进阶题 142  同类题：202
     */

    /**
     *快慢指针 时间O（n）  空间O（1）
     */
    public static boolean hasCycle(ListNode head) {
        boolean flag = false;
        ListNode fast = head, slow = head;
        while (slow != null&&fast !=null) {
            slow = slow.next;
            if(fast.next==null){
                break;
            }
            fast=fast.next.next;
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 快慢指针2
     */
    public boolean hasCycle3(ListNode head) {
        if(head==null||head.next==null) return false;
        ListNode slow=head,fast=head.next;
        while(fast!=null&&fast.next!=null&&fast!=slow){
            fast=fast.next.next;
            slow=slow.next;
        }
        return fast==slow;
    }

    /**
     *使用哈希表来存储所有已经访问过的节点
     *每次我们到达一个节点，如果该节点已经存在于哈希表中，则说明该链表是环形链表，否则就将该节点加入哈希表中
     *时间O（n）  空间O（n）
     */
    public static boolean hasCycle1(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            //add操作存在返回值，为boolean型的
            //因为Set分支的特点是无序且不可重复，因此通过add方法添加相同的值时，第一次返回为true
            //后面再加相同元素的话就会返回false，因为元素重复
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val,ListNode next) {
            this.val = val;
            this.next=next;
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
