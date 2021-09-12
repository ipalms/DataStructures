package algorithm.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class MaxOrMinDepth {

    /**
     * 递归向左右子树找最大深度  -DFS  104
     */
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    /**
     * 层序遍历的写法  -BFS  104
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * 递归找左右子树找最小深度--根结点到叶子结点最少结点数  111
     */
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        return count(root);
    }

    public int count(TreeNode root) {
        if(root==null) return Integer.MAX_VALUE;
        if(root.left==null&&root.right==null) return 1;
        return Math.min(count(root.left),count(root.right))+1;
    }

    /**
     * 简洁写法
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return left == 0 || right == 0 ? 1 + left + right : 1 + Math.min(left, right);
    }

    /**
     * BFS写法 --测试的效率比DFS高
     */
    public int minDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            while (size > 0) {
                TreeNode node = queue.poll();
                //遇到第一个叶子结点就输出当前层
                if(node.left==null&&node.right==null) return ans;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
        }
        return ans;
    }

    /**
     * 559. N 叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
     * 示例 1：
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：3
     * 示例 2：
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：5
     * 提示：
     * 树的深度不会超过 1000 。
     * 树的节点数目位于 [0, 104] 之间。
     */

    /**
     * 同二叉树的最大深度思路一致 559
     */
    public int maxDepth(Node root) {
        if(root==null) return 0;
        int max=0;
        for(Node n:root.children){
            max=Math.max(max,maxDepth(n));
        }
        return max+1;
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

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
