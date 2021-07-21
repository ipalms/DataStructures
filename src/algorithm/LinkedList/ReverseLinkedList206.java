package algorithm.LinkedList;

/**
 * 反转一个单链表 206
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题
 */
public class ReverseLinkedList206 {
    public static void main(String[] args) {
        ListNode c=new ListNode(3);
        ListNode b=new ListNode(2,c);
        ListNode a=new ListNode(1,b);

        ListNode head=reverseList2(a);
        System.out.println(head);
    }
    
    /**
     * 使用递归 时间复杂度和空间复杂度都为O（n）
     * @param head 当前遍历的结点
     */
    public static ListNode reverseList(ListNode head) {
        //已经到了最后一个元素  或者刚开始就只有一个结点 递归结束
        if (head.next==null||head==null){
            return head;
        }
        //获得递归的最后一个元素（即反转后队列的第一个元素）
        ListNode pre = reverseList(head.next);
        //将当前元素的后一个元素的next域指向自己
        head.next.next = head;
        //当前结点的下一个结点设为null
        head.next=null;
        //返回最后一个结点（pre变量指向一直未做改动）
        return pre;
    }

    /**
     * 迭代版本
     * 思路：在遍历列表时，将当前节点的 next 指针改为指向前一个元素。
     * 由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
     * 在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 结点类
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        ListNode(int x,ListNode next) {
            val = x;
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
