package algorithm.Tree;

/**
 * 572. 另一棵树的子树
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。
 * 如果存在，返回 true ；否则，返回 false 。
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。
 * tree 也可以看做它自身的一棵子树。
 * 示例 1：
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 示例 2：
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 */
public class IsSameTreeII572 {

    /**
     * 普通解法就是套用100题相同的树 的函数
     * 复杂度较高O（s*t） s、t分别为树s和t上的节点总数
     *
     * 一道非常相似的题，剑指Offer26.树的子结构
     * 不同点是判断是子树 还是 子结构
     * 这两题实质都是对 树s 做先序遍历同时比较当前遍历到的节点下的树与t的结构是否相符
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // 我s都遍历完了。你居然还没匹配上。那就返回false
        if (s == null) {
            return false;
        }
        // 短路运算符，有一个为真，返回真
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isSameTree(TreeNode s, TreeNode t) {
        // 同时为空 说明树一起到底，两树相同
        if (s == null && t == null) {
            return true;
        }
        // 如果上面没有返回值，说明必有一个没有为空（有可能两个都不为空）
        if (s == null || t == null) {
            return false;
        }
        // 如果判断到了这一步，说明两个都不为空
        // 先序遍历 自己--左 -- 右
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    /**
     * 更优解法---KMP算法，树哈希等 略~
     */



    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
