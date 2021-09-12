package algorithm.LinkedList;

import java.util.LinkedList;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
 * 使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionLinkList86 {

    /**
     * 维护快慢指针--328题也可快慢指针
     * 快指针若其next指向下一个小于x的数
     * 而慢指针的next指针指向的元素大于等于x就将快指针的next指针插入慢指针之后
     */
    public ListNode partitionMy(ListNode head, int x) {
        ListNode dummy=new ListNode(0,head);
        ListNode slow=dummy,fast=dummy;
        while(fast.next!=null){
            if(fast.next.val<x){
                if(slow.next.val>=x){
                    ListNode tmp=fast.next;
                    fast.next=tmp.next;
                    tmp.next=slow.next;
                    slow.next=tmp;
                    slow=slow.next;
                    //防止fast.next的值小于x而被遗漏了
                    continue;
                }else{
                    slow=slow.next;
                }
            }
            fast=fast.next;
        }
        return dummy.next;
    }

    /**
     * 拆分链表--维护两个指针拼接大小两个链表(两个链表存放的是小于或大于x的节点)
     * 328也可以同样思路
     */
    public ListNode partition(ListNode head, int x) {
        ListNode bigDummy=new ListNode(0);
        ListNode smallDummy=new ListNode(0);
        ListNode big=bigDummy,small=smallDummy;
        while(head!=null){
            if(head.val<x){
                small.next=head;
                small=small.next;
            }else{
                big.next=head;
                big=big.next;
            }
            head=head.next;
        }
        big.next=null;
        small.next=bigDummy.next;
        return smallDummy.next;
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
