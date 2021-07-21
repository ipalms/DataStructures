package algorithm.LinkedList;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。*
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class ReverseKGroup25 {
    public static void main(String[] args) {
        //ListNode a=new ListNode(6);
        ListNode b=new ListNode(5);
        ListNode c=new ListNode(4,b);
        ListNode d=new ListNode(3,c);
        ListNode e=new ListNode(2,d);
        ListNode f=new ListNode(1,e);
        ListNode head=reverseKGroup2(f,3);
        System.out.println(head);
    }

    /**
     * 迭代实现  使用递归会使得空间复杂度变高不满足常数级别时间复杂度
     * 链表反转操作考虑到通用性会给头结点设置一个前置结点
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        //为头结点构造一个前结点（hair结点不动）
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while (head != null) {
            //保存上一段结点的尾结点
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    //小于K个结点直接返回首结点，此时链表反转已经完成
                    return hair.next;
                }
            }
            //保存下一段结点的头结点
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;   //改变前一个结点指向
            head = tail.next;  //改变头结点的指向
        }
        //返回反转后整段的头结点
        return hair.next;
    }

    /**
     * 迭代的方式反转当前这K个结点
     * @param head
     * @param tail
     * @return
     */
    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        //将prev初始化为尾结点的后一个结点（让反转后的这一段结点的尾结点也能连接到后面的结点）
        ListNode prev = tail.next;
        ListNode p = head;
        //将这一段的结点
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        //返回反转后的首尾结点（可知head结点反转后为尾结点  tail结点反转后为头结点）
        return new ListNode[]{tail, head};
    }

    /**
     * 方法2 通过计算链表长度（长度可以确定迭代次数，即可以使用for循环）
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(0), prev = dummy, curr = head, next;
        dummy.next = head;
        int length = 0;
        //计算链表长度
        while(head != null) {
            length++;
            head = head.next;
        }
        head = dummy.next;
        for(int i = 0; i < length / k; i++) {
            for(int j = 0; j < k - 1; j++) {
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = curr;
            curr = prev.next;
        }
        return dummy.next;
    }

    /**
     * 递归版本
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup3(ListNode head, int k) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        ListNode check = head;
        int canProceed = 0;
        int count = 0;
        // 检查链表长度是否满足翻转
        while (canProceed < k && check != null) {
            check = check.next;
            canProceed++;
        }
        // 满足条件，进行翻转
        if (canProceed == k) {
            while (count < k && cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                count++;
            }
            if (next != null) {
                // head 为链表翻转后的尾节点
                head.next = reverseKGroup(next, k);
            }
            // prev 为链表翻转后的头结点
            return prev;
        } else {
            // 不满住翻转条件，直接返回 head 即可
            return head;
        }
    }
    private static class ListNode {
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
                  /*  ", next=" + next +*/
                    '}';
        }
    }
}
