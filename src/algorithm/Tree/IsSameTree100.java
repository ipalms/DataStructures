package algorithm.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例 1：
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * 提示：
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 */
public class IsSameTree100 {

    /**
     * 此题和101题很相似，此题的扩展题572另一棵树的子树
     * dfs版本
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null||p.val!=q.val){
            return false;
        }
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

    /**
     * bfs版本
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        Deque<TreeNode> queue=new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()){
            TreeNode l=queue.poll();
            TreeNode r=queue.poll();
            if(l==null&&r==null){
                continue;
            }
            if(l==null||r==null||l.val!=r.val){
                return false;
            }
            queue.offer(l.left);
            queue.offer(r.left);
            queue.offer(l.right);
            queue.offer(r.right);
        }
        return true;
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
