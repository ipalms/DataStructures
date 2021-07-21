package algorithm.SpeedPointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 */
public class ReturnCycleLinkListPosition142 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5, node1);
        ListNode node3 = new ListNode(2, node2);
        node1.next=node3;
        System.out.println(detectCycle(node3));
    }
    /**
     * 快慢双指针具体思路：
     * 我们使用两个指针，fast 与 slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，
     * 而 fast 指针向后移动两个位置。如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
     * 如下图所示，设链表中环外部分的长度为a。slow 指针进入环后，又走了b的距离与 fast 相遇。
     * 此时，fast 指针已经走完了环的 n 圈，因此它走过的总距离为：
     * a+n(b+c)+b=a+(n+1)b+nc。
     *根据题意，任意时刻fast 指针走过的距离都为slow 指针的 2倍。因此，我们有：
     * a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
     * 有了 a=c+(n−1)(b+c) 的等量关系，我们会发现：从相遇点到入环点的距离加上 n-1 圈的环长，
     * 恰好等于从链表头部到入环点的距离。
     * 因此，当发现slow 与 fast 相遇时，我们再额外使用一个指针 ptr。
     * ptr起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。
     */
    /**
     * 使用快慢双指针--得到等式关系
     * 最终相遇点在慢指针的第一圈：
     * fast指针走的路程一直是slow指针的二倍，所以当slow指针走环形走一半时，
     * fast指针走的路程一定大于等于一圈，所以当slow指针走一半或者一半以前两者必定相遇
     * 创建一个新指针，利用等式关系去找到入环点
     */
    public static ListNode detectCycle1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /**
     *使用额外的内存空间
     */
    public static ListNode detectCycle(ListNode head) {
        Map<Integer,ListNode> trace=new HashMap<>();
        int i=0;
        while (head!=null){
        //在Map中添加元素使用put方法，在返回值问题上.
        // 因为Map中是以键值对存在，因此当一个键值是第一次被添加时返回值为null，否则返回为上一次添加的value
           if(trace.put(head.val,head)!=null){
               return head;
           }
           head=head.next;
        }
        return null;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next=next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val+"}";
        }
    }
}
