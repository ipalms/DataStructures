package algorithm.LinkedList;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * 限制：
 * 0 <= 链表长度 <= 10000
 */
public class SwordOfferReversePrint6 {

    @Test
    public void test(){
        ListNode c=new ListNode(2);
        ListNode b=new ListNode(3,c);
        ListNode a=new ListNode(1,b);
        reversePrint(a);
    }
    /**
     * 利用栈
     */
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack=new LinkedList<>();
        while(head!=null){
            stack.addLast(head.val);
            head=head.next;
        }
        int []res=new int[stack.size()];
        for(int i=0;i<res.length;i++){
            res[i]=stack.pollLast();
        }
        return res;
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
