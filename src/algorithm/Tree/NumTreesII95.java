package algorithm.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树。
 * 可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * 提示：
 * 1 <= n <= 8
 */
public class NumTreesII95 {

    /**
     * 本题是要求列举出节点的取值（需要回溯||递归生成树）【树区】
     * 96题不能使用回溯做，因为n的最大取值为19，使用回溯会超时【动态规划区】
     * 实际上这题和108建立将有序数组转换为二叉搜索树为同一类型题目
     * 都是如何使用递归去建树（区别108是构建一颗平衡的二叉搜索树）
     * 这一题是枚举出所有合法的二叉搜索树
     */

    /**
     * 本题
     * 枚举根节点
     * 递归构建左子树，并拿到左子树所有可能的根结点列表left
     * 递归构建右子树，并拿到右子树所有可能的根结点列表right
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            //这里要加上null，因为如果一颗树的左子树为空，右子树不为空，
            //要正确构建所有树，依赖于对左右子树列表的遍历
            //也就是下面代码两层for循环的地方，如果其中一个列表为空，那么循环都将无法进行。
            allTrees.add(null);
            return allTrees;
        }
        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    //root节点需要每次遍历生成一个
                    //不然会导致num = left.size() * right.size() > 1时
                    //num棵子树会共用这个root结点
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    allTrees.add(root);
                }
            }
        }
        return allTrees;
    }

    /**
     * 108题
     */
    public TreeNode createBinaryTree(int n){
        return helper(1, n);
    }

    public TreeNode helper(int start, int end){
        if(start > end)
            return null;
        // 这里可以选择从start到end的任何一个值做为根结点，
        // 这里选择它们的中点，实际上，这样构建出来的是一颗平衡二叉搜索树
        int val = (start + end) / 2;
        TreeNode root = new TreeNode(val);
        root.left = helper(start, val - 1);
        root.right = helper(val + 1, end);
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
}
