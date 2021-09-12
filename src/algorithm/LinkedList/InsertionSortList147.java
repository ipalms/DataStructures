package algorithm.LinkedList;

/**
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class InsertionSortList147 {

    /**
     * 需要一个指针指向当前已排序的最后一个位置，这里用的是last指针
     * 需要另外一个指针pre,每次从表头循环，这里用的是dummy表头指针。
     * 每次拿出未排序的节点，先和前驱比较，如果大于或者等于前驱，就不用排序了，直接进入下一次循环
     * 如果前驱小，则进入内层循环，依次和pre指针比较，插入对应位置即可。
     * 时间O（N^2)  空间O（1）
     */
    public ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0,head);
        //起初不从dummy节点出发
        ListNode last=head,curr=head.next,pre;
        while(curr!=null){
            if(last.val<=curr.val){
                last=last.next;
            }else{
                //pre一定要从dummy节点触发，这样可以不用特别的判断链表头不在指定顺序而花费额外操作
                pre=dummy;
                //pre移动到curr应该被插入的节点前面一位
                while(pre.next.val<=curr.val){
                    pre=pre.next;
                }
                //插入逻辑
                last.next=curr.next;
                curr.next=pre.next;
                pre.next=curr;
            }
            //cur节点移动
            curr=last.next;
        }
        return dummy.next;
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
