package algorithm.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 */
public class BuildTree105 {

    /**
     * 105、106这两题类似
     * 108、109是根据中序遍历结果（递增的序列）还原一颗二叉搜索树
     * 要恢复二叉树需要前序|后序遍历+中序遍历的组合（前提二叉树的值不允许重复）
     * 前序|后续遍历是确定每次递归时的根节点值
     * （前序遍历的根节点对应是这次递归的preLeft，后序遍历的根节点是这次递归的postRight）
     * 中序遍历根据上一层的根节点值定位出根节点的下标，继而确定了根节点左右子树拥有的节点数
     */

    /**
     * dfs解法
     */
    private int[] preorder;
    private Map<Integer, Integer> hash;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        this.preorder = preorder;
        this.hash = new HashMap<>();
        //哈希表存储中序遍历数值-->下标
        for (int i = 0; i < inLen; i++) {
            hash.put(inorder[i], i);
        }
        return buildTree(0, preLen - 1, 0, inLen - 1);
    }


    /**
     * @param preLeft  前序最左节点
     * @param preRight 前序最右节点
     * @param inLeft  中序最左节点
     * @param inRight  中序最右节点
     */
    private TreeNode buildTree(int preLeft, int preRight, int inLeft, int inRight) {
        // 因为是递归调用的方法，先写递归终止条件
        if (preLeft > preRight){
            return null;
        }
        //前序遍历中的第一个节点就是根节点
        int pivot = preorder[preLeft];
        //构造此次递归调用的根节点
        TreeNode root = new TreeNode(pivot);
        //获得中序根节点数值对应下标
        int pivotIndex = hash.get(pivot);
        //[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ] --前序遍历的结果数组
        //[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ] --中序遍历的结果数组
        //根据上述关系确定递归边界--建议在草稿纸上标示出点的关系加之举上一个小例子确定递归边界
        root.left = buildTree(preLeft + 1, pivotIndex - inLeft + preLeft,
                inLeft, pivotIndex - 1);
        root.right = buildTree(pivotIndex - inLeft + preLeft + 1, preRight,
                pivotIndex + 1, inRight);
        return root;
    }

    /**
     * 迭代解法  --略
     */


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
