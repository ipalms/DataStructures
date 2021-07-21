package algorithm.SpeedPointer;


import java.util.HashSet;
import java.util.Set;

/**
 * 141.环形链表
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
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
