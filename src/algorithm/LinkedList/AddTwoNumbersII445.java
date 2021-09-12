package algorithm.LinkedList;

import java.util.Stack;

/**
 *445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 * 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。
 */
public class AddTwoNumbersII445 {

    /**
     * 此题是第2题的进阶版，传入的链表没有提前反转
     * 按照第2题的思路可以对链表进行反转，然后在实施和第二题一样的操作
     * 因为进阶思路要求不对链表进行一些反转操作，所以可以使用栈存储这两个链表的值，后续操作逻辑和第2题一致
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode head = null;
        //采用并结构循环
        //while分支使用并语句---对于已经遍历到结尾的数据进行三元运算符判断后（或特判）当作0一并处理
        //类似两个字符串的处理或者两个链表处理会用到，如2、43、67、165（双指针分区）、455
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            sum += stack1.isEmpty()? 0: stack1.pop();
            sum += stack2.isEmpty()? 0: stack2.pop();
            ListNode node = new ListNode(sum % 10);
            //节点头插
            node.next = head;
            head = node;
            carry = sum / 10;
        }
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
    }
}
