package algorithm.LinkedList;

/**
 * 876. 链表的中间结点
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点
 * 提示：
 * 给定链表的结点数介于 1 和 100 之间。
 */
public class MiddleNode876 {

    /**
     * 快慢指针---返回链表长度如果为偶数的后面那个
     * 关于返回前面或后面应该举两个例子结合代码检验一下
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast=head,slow=head;
        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
            //在while循环内做事
            if(fast.next==null){
                break;
            }
            fast=fast.next;
        }
        return slow;
    }


    /**
     * 快慢指针---返回链表长度如果为偶数的后面那个
     * 关于返回前面或后面应该举两个例子结合代码检验一下
     */
    public ListNode middleNode2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /**
     * 快慢指针---返回链表长度如果为偶数的前面那个
     */
    public ListNode middleNode1(ListNode head) {
        ListNode fast=head,slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
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
