package algorithm.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BuildTreeII106 {

    /**
     * 105、106这两题类似
     * 要恢复二叉树需要前序|后序遍历+中序遍历的组合（前提二叉树的值不允许重复）
     * 前序|后续遍历是确定每次递归时的根节点值
     * （前序遍历的根节点对应是这次递归的preLeft，后序遍历的根节点是这次递归的postRight）
     * 中序遍历根据上一层的根节点值定位出根节点的下标，继而确定了根节点左右子树拥有的节点数
     */

    /**
     * dfs解法
     */
    Map<Integer,Integer> map;
    int[] postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.map=new HashMap<>();
        //哈希表存储中序遍历数值-->下标
        for(int i=0;i<inorder.length;++i){
            map.put(inorder[i],i);
        }
        this.postorder=postorder;
        return buildTree(0,inorder.length-1,0,postorder.length-1);
    }

    /**
     * @param inLeft    中序最左节点
     * @param inRight   中序最右节点
     * @param postLeft  后序最左节点
     * @param postRight 后序最右节点
     */
    public TreeNode buildTree(int inLeft, int inRight, int postLeft, int postRight){
        if(inLeft>inRight) return null;
        int rootVal=postorder[postRight];
        int pivot=map.get(rootVal);
        TreeNode root=new TreeNode(rootVal);
        //[[左子树的前序遍历结果], [右子树的前序遍历结果], 根节点 ]  --后序遍历的结果数组
        //[[左子树的中序遍历结果], 根节点, [右子树的中序遍历结果]]   --中序遍历的结果数组
        //根据上述关系确定递归边界--建议在草稿纸上标示出点的关系加之举上一个小例子确定递归边界
        root.left=buildTree(inLeft,pivot-1,postLeft,pivot-inLeft+postLeft-1);
        root.right=buildTree(pivot+1,inRight,pivot-inLeft+postLeft,postRight-1);
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
