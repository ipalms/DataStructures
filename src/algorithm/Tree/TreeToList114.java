package algorithm.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，
 * 而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */
public class TreeToList114 {

    /**
     * 题目进阶要求原地解法，所以不能使用前序遍历递归展开存储值（没有原地的移动TreeNode）
     * 可以采用94题中序遍历的Morris算法类似的步骤，原地的拉伸二叉树为链表
     * 三个步骤：
     * 1.将原来根节点的右子树接到根节点左子树的最右边节点
     * 2.将根节点左子树插入到根节点右子树的地方
     * 3.考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     *
     * 不和题意的解法，前序遍历存储结果然后遍历这个结果连成链表就行
     */
    public void flatten(TreeNode root) {
        TreeNode pre = null;
        while(root!=null){
            if(root.left!=null) {
                //找左子树最右边的节点
                pre = root.left;
                while(pre.right!=null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                //将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
            }
            root = root.right;
        }
    }

    /**
     * 原中序遍历的Morris算法
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root!=null) {
            //如果左节点不为空，就将当前节点连带右子树全部挂到
            //左节点的最右子树下面
            if(root.left!=null) {
                //找左子树最右边的节点
                pre = root.left;
                while(pre.right!=null) {
                    pre = pre.right;
                }
                //将左子树的最右边节点的right置为root节点
                pre.right = root;
                //将root指向root的left
                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
                //左子树为空，则打印这个节点，并向右边遍历
            } else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
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
