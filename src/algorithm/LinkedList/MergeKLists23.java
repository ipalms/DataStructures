package algorithm.LinkedList;

import java.util.PriorityQueue;

public class MergeKLists23 {
    /**
     * 方法1.分治合并
     * 因为链表已经分层了一个一个的链表，所以直接递归两两合并。
     * 第一轮合并以后， k 个链表被合并成了k/2个链表，
     * 合并后每个链表平均长度为2n/k,第二轮合并就只剩下k/4个链表，合并到最后一个链表就返回表头即可。
     * @param lists
     * @return
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
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }
    //合并两个链表的方法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        //构造链表最前端结点，用于返回合并后的第一个结点
        ListNode prehead = new ListNode(0);
        ListNode pre = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = (l1 == null ? l2 : l1);
        return prehead.next;
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
    /**
     * 优先队列
     * 这个方法和前两种方法的思路有所不同，我们需要维护当前每个链表没有被合并的元素的最前面一个
     * k个链表就最多有 k个满足这样条件的元素，每次在这些元素里面选取 val 属性最小的元素合并到答案中。
     * 在选取最小元素的时候，我们可以用优先队列来优化这个过程。
     */
    //定义覆写了Comparable接口的类
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;
        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }
    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    public ListNode mergeKLists2(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                //将list的所有元素加入到优先队列中
                queue.offer(new Status(node.val, node));
            }
        }
        //用于访问最终链表的第一个结点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            //弹出第一个元素（最小的元素）
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                //将弹出的元素的下一个结点添加到优先队列中
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
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
    //合并两个链表的方法
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        //构造链表最前端结点，用于返回合并后的第一个结点
        ListNode prehead=new ListNode(0);
        ListNode pre=prehead;
        while (l1!=null&&l2!=null){
            if(l1.val<l2.val){
                pre.next=l1;
                l1=l1.next;
            }else {
                pre.next=l2;
                l2=l2.next;
            }
            pre=pre.next;
        }
        pre.next=(l1==null?l2:l1);
        return prehead.next;
    }
}

