package algorithm.Tree;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 * 输入：root = []
 * 输出：true
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 */
public class IsBalancedTree110 {

    /**
     * 此题与计算二叉树高度等问题有相似之处
     * 具体就是递归过程时判断每个结点是否平衡
     * 时间空间均为O（N）
     */
    public boolean isBalanced(TreeNode root) {
        return judge(root) != -1;
    }

    public int judge(TreeNode node){
        if(node==null) return 0;
        //计算当前结点左右结点深度，当然可以先不计算右子树深度，如果左子树平衡才计算右子树（相当于剪枝）
        int leftDepth=judge(node.left);
        int rightDepth=judge(node.right);
        if(leftDepth==-1||rightDepth==-1||Math.abs(leftDepth-rightDepth)>1){
            return -1;
        }
        return Math.max(leftDepth,rightDepth)+1;
    }

    public class TreeNode {
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
