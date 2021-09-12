package algorithm.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 示例 2：
 * 输入：root = [1]
 * 输出：["1"]
 * 提示：
 * 树中节点的数目在范围 [1, 100] 内
 * -100 <= Node.val <= 100
 */
public class BinaryTreePaths257 {

    /**
     * 这题实际上也考察的是对字符串的操作
     */

    /**
     * 优化版本
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        helper(root, "", res);
        return res;
    }

    public void helper(TreeNode root, String path, List<String> res) {
        if (root == null) {return;}
        // 由原始解法二可以知道，root的值肯定会下面某一个条件加入到path中，那么干脆直接在这一步加入即可
        StringBuilder sb = new StringBuilder(path);
        sb.append(root.val);
        if (root.left == null && root.right ==null) {
            res.add(sb.toString());
        }else{
            // 如果是非叶子结点则还需要跟上一个 “->”
            sb.append("->");
            helper(root.left,sb.toString(),res);
            helper(root.right,sb.toString(),res);
        }
    }

    /**
     * 非优化版本--直接操作String
     */

    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if(root==null) return ret;
        solve(root, "", ret);
        return ret;
    }
    public void solve(TreeNode root, String cur, List<String> ret) {
        if (root == null) return;
        cur += root.val;
        if (root.left == null && root.right == null) {
            ret.add(cur);
        } else {
            solve(root.left, cur + "->", ret);
            solve(root.right, cur + "->", ret);
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
