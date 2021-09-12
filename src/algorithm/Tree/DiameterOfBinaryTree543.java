package algorithm.Tree;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 示例 :
 * 给定二叉树
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree543 {


    /**
     * 与124二叉树中的最大路径和相似都是非自顶向下的二叉树路径问题
     */
    int max=Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        traverse(root);
        return max;
    }

    //后序遍历进行操作
    public int traverse(TreeNode root) {
        if(root==null) return 0;
        int leftNum=traverse(root.left);
        int rightNum=traverse(root.right);
        //更新max值
        max=Math.max(max,leftNum+rightNum);
        //选择一条子树和自身的高度返回给上一层
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
