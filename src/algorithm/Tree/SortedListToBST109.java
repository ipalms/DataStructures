package algorithm.Tree;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class SortedListToBST109 {

    /**
     * 这题可看作108题的变形题---加入了链表的元素
     */
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head);
    }

    /**
     * 找链表的中点是利用快慢指针
     * 这里是截断了链表，所以要找到中点的左右节点
     * 时间O（N*logN）
     */
    public TreeNode buildTree(ListNode head){
        if(head==null) return null;
        if(head.next==null) return new TreeNode(head.val);
        ListNode fast=head.next,slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode tmp=slow.next;
        slow.next=null;
        TreeNode root=new TreeNode(tmp.val);
        //递归生成树
        root.left=buildTree(head);
        root.right=buildTree(tmp.next);
        return root;
    }

    /**
     * 不用截断链表的双参dfs
     */
    public TreeNode sortedListToBST1(ListNode head) {
        return helper(head,null);
    }

    /**
     * 时间O（N*logN）
     */
    private TreeNode helper(ListNode head,ListNode tail){
        if(head == tail){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != tail && fast.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head,slow);
        root.right = helper(slow.next,tail);
        return root;
    }

    /**
     * 优化找到根节点的过程
     */
    ListNode globalHead;
    public TreeNode sortedListToBST2(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

    /**
     * globalHead会按照中序遍历的顺序推进
     */
    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
