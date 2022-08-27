package algorithm.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * 提示：
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 */
public class IsValidBST98 {


    /**
     * 二叉搜索树一条通用思路：
     * 中序遍历二叉搜索树等于遍历有序数组，等于对有序数组进行操作
     * tips:中序遍历在题目需要用到上一个节点时可以用全局变量保存上一个节点的val值或者上一个节点
     * 98验证二叉搜索树、99恢复二叉搜索树、230二叉搜索树中第K小的元素、501二叉搜索树中的众数都是该思路
     * 958二叉树的完全性检验
     */

    /**
     * 在递归的过程中判断合法性
     * 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
     * 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
     * 在向左子树递归的过程中改变max值为当前结点val值
     * 在向右子树递归的过程中改变min值为当前结点val值
     */
    public boolean isValidBST(TreeNode root) {
        return judge(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    //long值接收（测试用例有int类型最大值）
    public boolean judge(TreeNode node,long min,long max){
        if(node==null) return true;
        if(node.val<=min||node.val>=max){
            return false;
        }
        return judge(node.left,min,node.val)&&judge(node.right,node.val,max);
    }

    /**
     * 思路二
     * 对于二叉搜索树的中序遍历结果是递增的--非递归版
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        long inorder = Long.MIN_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            //更改inorder值
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    long pre = Long.MIN_VALUE; // 记录上一个节点的值，初始值为Long的最小值

    /**
     * 中序遍历 dfs版本
     */
    public boolean isValidBST3(TreeNode root) {
        return inorder(root);
    }

    // 中序遍历
    private boolean inorder(TreeNode node) {
        if(node == null) return true;
        boolean l = inorder(node.left);
        if(node.val <= pre) return false;
        pre = node.val;
        boolean r = inorder(node.right);
        return l && r;
    }


    /**
     * 自己实现的
     * 大体逻辑也和第一种逻辑差不多，第一种逻辑是在递归的过程判断二叉树结点值的合法性
     * 我的实现逻辑是在递归返回结果时来判断二叉树结点值的合法性
     */
    public boolean isValidBSTMy(TreeNode root) {
        return judge(root).valid;
    }

    public Node judge(TreeNode node){
        //叶子节点递归终点
        if(node.left==null&&node.right==null) return new Node(node.val,node.val,true);
        int min=node.val,max=node.val;
        if(node.left!=null){
            //先判断合法性
            if(node.val<=node.left.val){
                return new Node(0,0,false);
            }
            //递归向左子树判断
            Node tmp=judge(node.left);
            //判断合法性
            if(!tmp.valid||tmp.max>=node.val){
                return new Node(0,0,false);
            }
            //更新左半子树的最小值
            min=tmp.min;
        }
        //同理
        if(node.right!=null){
            if(node.val>=node.right.val){
                return new Node(0,0,false);
            }
            Node tmp=judge(node.right);
            if(!tmp.valid||tmp.min<=node.val){
                return new Node(0,0,false);
            }
            max=tmp.max;
        }
        return new Node(min,max,true);
    }

    /**
     * 封装的返回结果集
     */
    class Node{
        int min;
        int max;
        boolean valid;
        public Node(int min,int max,boolean valid){
            this.min=min;
            this.max=max;
            this.valid=valid;
        }
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
