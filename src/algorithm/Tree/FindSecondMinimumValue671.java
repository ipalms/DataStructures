package algorithm.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * 示例 1：
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * 示例 2：
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 * 提示：
 * 树中节点数目在范围 [1, 25] 内
 * 1 <= Node.val <= 231 - 1
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 */
public class FindSecondMinimumValue671 {

    /**
     * 层序遍历类似思路解题 bfs--缺点是不能进行剪枝
     */
    public int findSecondMinimumValue(TreeNode root) {
        Deque<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        long pre=Long.MAX_VALUE;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            if(node.left!=null){
                //将子节点进行比较
                if(node.left.val>node.val&&node.left.val<pre){
                    pre=node.left.val;
                }
                queue.offer(node.left);
            }
            if(node.right!=null){
                //将子节点进行比较
                if(node.right.val>node.val&&node.right.val<pre){
                    pre=node.right.val;
                }
                queue.offer(node.right);
            }
        }
        return pre==Long.MAX_VALUE?-1:(int)pre;
    }

    public int findSecondMinimumValue1(TreeNode root) {
        Deque<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        long pre=Long.MAX_VALUE;
        int min=root.val;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            //先比较父结点
            if(node.val>min&&node.val<pre){
                pre=node.val;
            }
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return pre==Long.MAX_VALUE?-1:(int)pre;
    }

    /**
     * dfs  有剪枝逻辑
     * https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/solution/yi-ti-san-jie-shen-du-jian-zhi-bfsdfsyu-yu41d/
     */
    public int findSecondMinimumValue2(TreeNode root) {
        if(root == null || root.left == null) return -1;
        int left = root.val == root.left.val ?
                findSecondMinimumValue2(root.left) :
                root.left.val;
        int right = root.val == root.right.val ?
                findSecondMinimumValue2(root.right) :
                root.right.val;
        int min = Math.min(left,right);
        return  min > 0 ? min : Math.max(left,right);
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
