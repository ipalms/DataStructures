package algorithm.Tree;

import java.util.*;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
 * 找出所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * 提示：
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSumII113 {

    /**
     * 相似题：  112路径总和、113路径总和 II
     * 回溯解法
     */
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root==null) return res;
        backTrace(root,targetSum,new ArrayDeque<Integer>());
        return res;
    }

    /**
     * 更精简的回溯版本
     */
    public void backTrace1(TreeNode root, int targetSum, Deque<Integer> path){
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            res.add(new LinkedList<Integer>(path));
        }
        backTrace1(root.left, targetSum, path);
        backTrace1(root.right, targetSum, path);
        path.pollLast();
    }

    public void backTrace(TreeNode root, int targetSum, Deque<Integer> path){
        if(root.left==null&&root.right==null){
            path.add(root.val);
            if(targetSum==root.val){
                res.add(new ArrayList(path));
            }
            path.removeLast();
            return;
        }
        if(root.left!=null){
            path.add(root.val);
            backTrace(root.left,targetSum-root.val,path);
            path.removeLast();
        }
        if(root.right!=null){
            path.add(root.val);
            backTrace(root.right,targetSum-root.val,path);
            path.removeLast();
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
