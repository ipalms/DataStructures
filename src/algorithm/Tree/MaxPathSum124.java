package algorithm.Tree;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 */
public class MaxPathSum124 {

    /**
     * 不能使用中序遍历的结果找最大值，因为中序遍历的结果可能并不是连线的结果
     */

    /**
     * dfs版本
     * 有点类似动态规划第53题求连续数组最大和（本题求得是二叉树的最大路径和）
     * 非自顶向下：就是从任意节点到任意节点的路径，不需要自顶向下
     * 类似的题543二叉树的直径
     */

    //max值初始化为最小值
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //不需要接收返回值
        dfs(root);
        return max;
    }

    /**
     * 返回经过root的单边分支最大和， 即Math.max(root, root+left, root+right)
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //计算左边分支最大值，左边分支如果为负数还不如不选择
        int leftMax = Math.max(0, dfs(root.left));
        //计算右边分支最大值，右边分支如果为负数还不如不选择
        int rightMax = Math.max(0, dfs(root.right));
        //以当前节点作为根节点的最大路径和
        //节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        max = Math.max(max, root.val + leftMax + rightMax);
        //返回经过root的单边最大分支给当前root的父节点计算使用
        //（root.val一定要纳入结果值的计算，然后再挑选出左右分支中最大的那个分支路径和）
        return root.val + Math.max(leftMax, rightMax);
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
