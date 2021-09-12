package algorithm.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * 示例 1：
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * 提示：
 * 节点值的范围在32位有符号整数范围内。
 */
public class AverageOfLevels637 {

    /**
     * 层序遍历变形
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res=new ArrayList<>();
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            double tmp=0;
            for(int i=0;i<size;++i){
                TreeNode node=queue.poll();
                tmp+=node.val;
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(tmp/size);
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
