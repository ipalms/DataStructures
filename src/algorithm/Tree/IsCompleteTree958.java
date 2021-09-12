package algorithm.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 958. 二叉树的完全性检验
 * 给定一个二叉树，确定它是否是一个完全二叉树。
 * 百度百科中对完全二叉树的定义如下：
 * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，
 * 第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）
 * 示例 1：
 * 输入：[1,2,3,4,5,6]
 * 输出：true
 * 解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），
 * 且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
 * 示例 2：
 * 输入：[1,2,3,4,5,null,7]
 * 输出：false
 * 解释：值为 7 的结点没有尽可能靠向左侧。
 * 提示：
 * 树中将会有 1 到 100 个结点。
 */
public class IsCompleteTree958 {

    /**
     * dfs版本
     * 核心关键点：树的size是否等于最后一个节点的indexCode
     * 树的size在便利到每个不为null的节点的时候不断维护，indexCode是在递归时作为参数传入的
     * （对于完全二叉树层序遍历展开为数组而言，假如root索引为x,则root的左节点是2*x,右节点为2*x+1）
     */
    int size = 0;
    int maxCode = 0;
    public boolean isCompleteTree(TreeNode root) {
        if(root == null){
            return true;
        }
        //假设根节点索引为1开始递归
        recursive(root,1);
        return size == maxCode;
    }

    public void recursive(TreeNode root,int index){
        //在没有遇到root==null时不能提前剪枝
        if(root == null){
            return;
        }
        //维护size和maxCode
        size ++;
        maxCode = Math.max(maxCode,index);
        //分别向左右子树递归
        recursive(root.left,index * 2);
        recursive(root.right,index * 2 + 1);
    }

    /**
     * bfs(层序遍历)思路：可以使用编号思路也可以采取第二种思路
     * 对于一个完全二叉树，层序遍历的过程中遇到第一个空节点之后不应该再出现非空节点
     * 这里不需要去纠结具体的层数
     */
    public boolean isCompleteTree1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        //用于表示遍历到当前节点的前一个节点
        TreeNode prev = root;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            //遇到prev为空节点但是node不是空节点说明不为完全二叉树
            if (prev == null && node != null)
                return false;
            if (node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
            prev = node;
        }
        return true;
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
