package algorithm.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum，
 * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum。
 * 叶子节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 * 提示：
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSum112 {

    /**
     * 与路径有关题的bfs版几乎都是层序遍历的思路
     * 而且一般都需要两个队列（一个队列存储节点+一个队列存储路径和）
     */

    /**
     * 与二叉树路径有关的相似题：
     * 自顶向下的路径题：
     * 112路径总和、113路径总和 II、437路径总和 III
     * 129求根节点到叶节点数字之和、257二叉树的所有路径
     * 非自顶向下：就是从任意节点到任意节点的路径，不需要自顶向下
     * 124二叉树中的最大路径和、543二叉树的直径、687最长同值路径
     * 更为标准的解  dfs
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        if(root.left==null&&root.right==null){
            return root.val==targetSum;
        }
        return hasPathSum(root.left,targetSum-root.val)||hasPathSum(root.right,targetSum-root.val);
    }

    /**
     * bfs(层序遍历模板)
     * 更改了原来树的val值---如果不想改变的话要维护一个辅助队列，添加动态的val值
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if(root==null) return false;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left==null&&node.right==null){
                if(node.val==targetSum){
                    return true;
                }
                continue;
            }
            if(node.left!=null){
                node.left.val+=node.val;
                queue.add(node.left);
            }
            if(node.right!=null){
                node.right.val+=node.val;
                queue.add(node.right);
            }
        }
        return false;
    }

    /**
     * 把targetSum提出来变为类变量还要在递归回撤时反向加回root.val
     * 不如直接传入参数
     */
    int targetSum;
    public boolean hasPathSumMy(TreeNode root, int targetSum) {
        if(root==null) return false;
        this.targetSum=targetSum;
        return check(root);
    }

    public boolean check(TreeNode root) {
        targetSum-=root.val;
        if(root.left==null&&root.right==null){
            if(targetSum==0){
                return true;
            }else{
                targetSum+=root.val;
                return false;
            }
        }
        if(root.left!=null&&check(root.left)){
            return true;
        }
        if(root.right!=null&&check(root.right)){
            return true;
        }
        targetSum+=root.val;
        return false;
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
