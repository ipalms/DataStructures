package algorithm.LinkedList;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * 提示：
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 */
public class SortList148 {

    /**
     * 这题与其他几道链表题有些联系
     * 首先147题就是链表的插入排序，对于此题同样可以通过（但是时间复杂度为O(N^2)）
     * 其次对于链表排序O（n*logn）的好的选择应该是归并排序，因为在链表在并的过程中并不需要额外空间（而数组版需要额外空间）
     * 对于具体实现而言主要分为三个部分：
     * 1.找到链表中间节点（快慢指针）：可以参考876找链表中间节点
     * 2.对中间节点next指针置null（从中点将链表断开）,递归分割链表，当head为null或仅有一个节点递归结束（分治中的分）
     * 3.合并这两条链表，这一点可以参考21题合并两条链表元素
     * 时间复杂度：O(nlogn)，其中n是链表的长度。
     * 空间复杂度：O(logn)，其中n是链表的长度。空间复杂度主要取决于递归调用的栈空间。
     */
    public ListNode sortList(ListNode head) {
        //递归终止条件
        if(head==null||head.next==null){
            return head;
        }
        ListNode fast=head,slow=head;
        //快慢指针找中间节点
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode next=slow.next;
        //从中点将链表断开
        slow.next=null;
        //递归分割链表然后再合并这两条链表---理解分治合并操作是在递归操作后进行的操作
        return merge(sortList(head),sortList(next));
    }

    /**
     *  自底向上归并排序---23题我自己写的也有非递归的分治
     *  总结就是用非递归的形式（一般嵌套for循环来实现分治的分过程）--递乘步长，从1开始步长逐渐乘2
     *  使用非递归就需要处理很多链表断开连接的逻辑
     *  时间复杂度：O(nlogn)，其中n是链表的长度。
     *  空间复杂度：O(1)
     */
    public ListNode sortList1(ListNode head) {
        if(head == null){
            return null;
        }
        //1.首先从头向后遍历,统计链表长度
        int length = 0;
        ListNode node = head;
        while(node != null){
            length++;
            node = node.next;
        }
        //2.初始化引入dummynode
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        //3.每次将链表拆分成若干个长度为subLen的子链表,并按照每两个子链表一组进行合并
        for(int subLen = 1;subLen < length;subLen <<= 1){ // subLen每次左移一位（即sublen = sublen*2）
            ListNode prev = dummyHead;
            //curr用于记录拆分链表的位置
            ListNode curr = dummyHead.next;
            //如果链表没有被拆完
            while(curr != null){               
                //3.1拆分subLen长度的链表1
                ListNode h1 = curr;        // 第一个链表的头 即curr初始的位置
                for(int i = 1; i < subLen && curr != null && curr.next != null; i++){    //拆分出长度为subLen的链表1
                    curr = curr.next;
                }
                //3.2 拆分subLen长度的链表2
                ListNode h2 = curr.next;  //第二个链表的头  即链表1尾部的下一个位置
                curr.next = null;             //断开第一个链表和第二个链表的链接
                curr = h2;                // 第二个链表头重新赋值给curr
                for(int i = 1;i < subLen && curr != null && curr.next != null;i++){      //再拆分出长度为subLen的链表2
                    curr = curr.next;
                }
                //3.3再次断开第二个链表最后的next的链接
                ListNode next = null;
                if(curr != null){
                    next = curr.next;   // next用于记录 拆分完两个链表的结束位置
                    curr.next = null;   // 断开连接
                }
                // 3.4 合并两个subLen长度的有序链表
                prev.next = merge(h1,h2);        // prev.next 指向排好序链表的头
                while(prev.next != null){  // while循环将prev移动到 subLen*2 的位置后去
                    prev = prev.next;
                }
                curr = next;              // next用于记录拆分完两个链表的结束位置
            }
        }
        //返回新排好序的链表
        return dummyHead.next;
    }


    /**
     * 合并两条有序链表
     */
    public ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy=new ListNode();
        ListNode curr=dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                curr.next=l1;
                l1=l1.next;
            }else{
                curr.next=l2;
                l2=l2.next;
            }
            curr=curr.next;
        }
        curr.next=l1==null?l2:l1;
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
