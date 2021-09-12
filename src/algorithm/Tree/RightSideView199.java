package algorithm.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，
 * 返回从右侧所能看到的节点值。
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 * 输入: []
 * 输出: []
 * 提示:
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class RightSideView199 {

    /**
     * dfs版本
     */
    List<Integer> res=new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null) return res;
        traverse(root,0);
        return res;
    }

    public void traverse(TreeNode root,int depth){
        if(root==null) return;
        //如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if(res.size()==depth){
            res.add(root.val);
        }
        traverse(root.right,depth+1);
        traverse(root.left,depth+1);
    }

    /**
     * bfs版本
     */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;++i){
                TreeNode node=queue.poll();
                if(i==0){
                    res.add(node.val);
                }
                //先放入右节点，再放入左节点，那么下一层第一个弹出的节点就是要求的右视图所需节点
                if(node.right!=null){
                    queue.add(node.right);
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
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
