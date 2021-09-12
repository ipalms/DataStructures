package algorithm.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * 示例 1：
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 * 提示：
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 */
public class RecoverTree99 {

    /**
     * 递归版--不符合O（1）时间复杂度
     * 没有错误情况下BST中序遍历结果应该为递增的，所以在递归的过程中找到错误顺序的两个节点
     * 最简单的是将遍历结果存放在list中并记录下下标
     */
    //pre 节点用于记录上一个节点 t1、t2用于记录错误顺序的两个节点
    TreeNode t1, t2, pre;
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = t1.val;
        t1.val = t2.val;
        t2.val = temp;
    }

    public void inorder(TreeNode root){
        if (root == null) return ;
        //递归左节点
        inorder(root.left);
        if (pre != null && pre.val > root.val) {
            if (t1 == null) t1 = pre;
            //t2节点每次都改变（顺序有误时）
            t2 = root;
        }
        pre = root;
        //递归右左节点
        inorder(root.right);
    }

    /**
     * Morris 中序遍历可以实现O（1）空间
     */
    public void recoverTree1(TreeNode root) {
        //记录错误的两个值
        TreeNode x = null, y = null;
        //记录中序遍历当前节点的前驱
        TreeNode pre = null;
        //用来完成Morris连接的寻找前驱的指针
        TreeNode predecessor = null;
        while(root != null) {
            if(root.left != null) {//左子树不为空，1、链接root节点的前驱，他的前驱还没访问，所以root不能现在访问,继续访问root左子树  2、root节点访问,并且断开root节点的前驱连接，然后访问root的右子树
                predecessor = root.left;
                while(predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == root) {//说明了1已经执行过了，我们执行2
                    //访问
                    if(pre != null && pre.val > root.val) {
                        if(x == null) x = pre;
                        y = root;
                    }
                    //更新前驱,为下一个节点做准备
                    pre = root;
                    //断开前驱连接
                    predecessor.right = null;
                    //访问root右子树
                    root = root.right;
                }else {//predecessor.right ！= root,说明了还没执行过1
                    predecessor.right = root;
                    root = root.left;
                }
            }else {//root.left == null，root不需要链接节点的前驱（他的前驱其实就是pre(第一个节点pre为null)，且已经被访问过了），那么直接访问root
                //访问
                if(pre != null && pre.val > root.val) {
                    if(x == null) x = pre;
                    y = root;
                }
                //更新前驱,为下一个节点做准备
                pre = root;
                //访问root的右子树
                root = root.right;
            }
        }
        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
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
}
