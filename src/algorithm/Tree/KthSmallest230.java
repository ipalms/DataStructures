package algorithm.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素】
 * （从 1 开始计数）。
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * 提示：
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
public class KthSmallest230 {



    int res=-1;
    int k;

    /**
     * 注意这里的k值采用的是自减到0判断的方式
     * 因为java语言方法是按值传递
     * （java所有的参数传递都是传递的副本--普通参数直接拷贝一份，引用参数拷贝一份地址传入方法）
     * 所以普通参数不能作为参数传入遍历方法中，要作为全局变量进行更改
     * 当然还有一种就是存储遍历到的数值然后直接返回索引为k-1的那个值
     */

    /**
     * DFS版本
     */
    public int kthSmallest(TreeNode root, int k) {
        this.k=k;
        reverse(root);
        return res;
    }

    public void reverse(TreeNode root){
        if(root==null) return;
        //剪枝，已经遍历到了第k个大小
        if(res>=0) return;
        reverse(root.left);
        if(--k==0){
            res=root.val;
            return;
        }
        reverse(root.right);
    }

    /**
     * 迭代版本（辅助栈）
     */
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack=new LinkedList<>();
        while(root!=null||!stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            TreeNode node = stack.pop();
            if(--k==0){
                return node.val;
            }
            root=node.right;
        }
        return -1;
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
