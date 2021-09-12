package algorithm.LinkedList;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class MergeKLists23 {

    /**
     * 采用for循环的分支思想
     * 设每个链表的平均长度为n 有k个链表
     * 那么时间复杂度为： 大循环为logk次 每层循环内合并这个循环内的链表遍历节点次数为(2n*k/2...4n*k/4-->即nk)
     * 所以时间复杂度为O(nk*logk)
     * 空间复杂度O（1）
     */
    public ListNode mergeKListsMy(ListNode[] lists) {
        int n=lists.length;
        if(n==0) return null;
        while(n>1){
            int tail=(n+1)/2;
            for(int i=0;i<tail;++i){
                if(i+tail>=n){
                    continue;
                }
                lists[i]=mergeTwoLists(lists[i],lists[i+tail]);
            }
            n=tail;
        }
        return lists[0];
    }

    /**
     * 合并两个链表方法
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode preHead=new ListNode();
        ListNode curr=preHead;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                curr.next=l1;
                l1=l1.next;
            }else{
                curr.next=l2;
                l2=l2.next;
            }
            curr=curr.next;
        }
        curr.next=l1==null?l2:l1;
        return preHead.next;
    }

    /**
     * 方法1.分治合并
     * 因为链表已经分层了一个一个的链表，所以直接递归两两合并。
     * 第一轮合并以后， k 个链表被合并成了k/2个链表，
     * 合并后每个链表平均长度为2n/k,第二轮合并就只剩下k/4个链表，合并到最后一个链表就返回表头即可。
     * 所以时间复杂度为O(nk*logk)
     * 空间复杂度O（logk）
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        //递归结束条件
        if (l == r) {
            return lists[l];
        }
        //传递链表数组为空情况
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        //先分再合并（分治）
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }


    /**
     * 优先队列
     * 这个方法和前两种方法的思路有所不同，我们需要维护当前每个链表没有被合并的元素的最前面一个
     * k个链表就最多有 k个满足这样条件的元素，每次在这些元素里面选取 val 属性最小的元素合并到答案中。
     * 在选取最小元素的时候，我们可以用优先队列来优化这个过程。
     * 优先队列最多存放k个元素  一次插入、删除的时间复杂度为logk,总共有nk个元素
     * 所以时间复杂度为O(nk*logk)
     * 空间复杂度O（k）
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        //维护的是小顶堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a,b)->a.val-b.val);
        for (ListNode node: lists) {
            if (node != null) {
                //将list的所有元素加入到优先队列中
                queue.offer(node);
            }
        }
        //用于访问最终链表的第一个结点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            //弹出第一个元素（最小的元素）
            ListNode f = queue.poll();
            tail.next = f;
            tail = tail.next;
            if (f.next != null) {
                //将弹出的元素的下一个结点添加到优先队列中
                queue.offer(f.next);
            }
        }
        return head.next;
    }

    /**
     * 方法三：暴力一点
     * 用一个变量 ans 来维护以及合并的链表，第 i次循环把第 i 个链表和 ans 合并，将结果保存到 ans 中。
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            //让ans和第i个链表合并
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    public class ListNode {
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

