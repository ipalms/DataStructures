package algorithm.Tree;

import java.util.*;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class IsSymmetric101 {

    /**
     * 这题不能使用中序遍历保存遍历结果后比较结果数组是否回文
     * 因为中序遍历的结果的并不能确定树形（即使加上了null节点输出）--如下面这种树形
     * 输出结果是 N 2 N 2 N 1 N 2 N 2 N     (N代表null)
     *     1
     *    / \
     *   2   2
     *  /   /
     * 2    2
     */


    /**
     * 此题和101题很相似
     * 递归写法-dfs
     * 这个递归是同时递归两个结点
     * 「同步移动」两个指针的方法来遍历这棵树，p指针和q指针一开始都指向这棵树的根
     * 随后p右移时，q左移。p左移时，q右移。每次检查当前p和q节点的值是否相等，如果相等再判断左右子树是否对称。
     */
    public boolean isSymmetric(TreeNode root) {
        return dfs(root,root);
    }

    public boolean dfs(TreeNode l, TreeNode r){
        if(l==null&&r==null){
            return true;
        }
        if(l==null||r==null||l.val!=r.val){
            return false;
        }
        return dfs(l.left,r.right)&&dfs(l.right,r.left);
    }

    /**
     * bfs写法--类似层序遍历
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        //遍历前先加入root左右节点
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            //不用特别的按照层序遍历似的一层一层的遍历
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            //对node1 和 node2为null的情况做特判
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            //按顺序将两队节点加入到队列
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    /**
     * 双队列--层序遍历
     */
    public boolean isSymmetricMy(TreeNode root) {
        if((root.left==null&&root.right!=null)||(root.left!=null&&root.right==null)){
            return false;
        }else if(root.left == null){
            return true;
        }
        return check(root);
    }

    public boolean check(TreeNode node){
        Deque<TreeNode> leftQueue=new LinkedList<>();
        Deque<TreeNode> rightQueue=new LinkedList<>();
        leftQueue.offer(node.left);
        rightQueue.offer(node.right);
        while(!leftQueue.isEmpty()&&!rightQueue.isEmpty()){
            int n1=leftQueue.size();
            int n2=rightQueue.size();
            if(n1!=n2){
                return false;
            }
            for(int i=0;i<n1;++i){
                TreeNode l1=leftQueue.poll();
                TreeNode r1=rightQueue.poll();
                if(l1.val!=r1.val) return false;
                if(l1.left!=null&&r1.right!=null){
                    leftQueue.offer(l1.left);
                    rightQueue.offer(r1.right);
                }else if(l1.left != null || r1.right != null){
                    return false;
                }
                if(l1.right!=null&&r1.left!=null){
                    leftQueue.offer(l1.right);
                    rightQueue.offer(r1.left);
                }else if(l1.right != null || r1.left != null){
                    return false;
                }
            }
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
