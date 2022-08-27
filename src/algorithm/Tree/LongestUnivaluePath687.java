package algorithm.Tree;

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * 示例 1:
 * 输入:
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 * 2
 * 示例 2:
 * 输入:
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */
public class LongestUnivaluePath687 {

    /**
     * 与124二叉树中的最大路径和相似都是非自顶向下的二叉树路径问题
     */
    int max=Integer.MIN_VALUE;
    public int longestUnivaluePath(TreeNode root) {
        if(root==null) return 0;
        dfs(root);
        return max;
    }

    // 也可以设计一个dfs函数,入参除了root外还有父节点的val值，但是这样做没有如下后序遍历的形式清晰
    public int dfs(TreeNode root){
        if(root==null) return 0;
        int leftNum=dfs(root.left);
        int rightNum=dfs(root.right);
        //相当于修正leftNum和rightNum值，和124一样
        if(root.left!=null)
            leftNum=root.val==root.left.val?leftNum:0;
        if(root.right!=null)
            rightNum=root.val==root.right.val?rightNum:0;
        max=Math.max(max,leftNum+rightNum);
        return 1+Math.max(leftNum,rightNum);
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
