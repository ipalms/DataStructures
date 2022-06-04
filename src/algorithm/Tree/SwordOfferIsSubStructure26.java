package algorithm.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 * */
public class SwordOfferIsSubStructure26 {

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

    /**
     * 一道非常相似的题，572. 另一棵树的子树
     * 不同点是判断是子树 还是 子结构
     * 这两题实质都是对 树s 做先序遍历同时比较当前遍历到的节点下的树与t的结构是否相符
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A==null||B==null){
            return false;
        }
        return recur(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B);
    }

    private boolean recur(TreeNode a, TreeNode b) {
        if (b==null){
            return true;
        }
        if (a==null||a.val!=b.val){
            return false;
        }
        return recur(a.left,b.left)&&recur(a.right,b.right);
    }



    /**
     * 迭代写法1  -- 将递归写法写成使用辅助栈的前序遍历
     */
    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        if (B==null){
            return false;
        }
        Deque<TreeNode> stack=new LinkedList<>();
        stack.push(A);
        while(!stack.isEmpty()||A!=null){
            while(A!=null){
                if (recur1(A,B)){
                    return true;
                }
                stack.push(A);
                A=A.left;
            }
            A=stack.pop().right;
        }
        return false;
    }

    private boolean recur1(TreeNode a, TreeNode b) {
        Deque<TreeNode> stack1=new LinkedList<>();
        Deque<TreeNode> stack2=new LinkedList<>();
        stack1.push(a);
        stack2.push(b);
        while(!stack1.isEmpty()||a!=null){
            while(a!=null){
                if (b==null){
                    break;
                }
                if (a.val!=b.val){
                    return false;
                }
                stack1.push(a);
                stack2.push(b);
                a=a.left;
                b=b.left;
            }
            if (b!=null) return false;
            a=stack1.pop().right;
            b=stack2.pop().right;
        }
        return true;
    }

    /**
     * 迭代写法2  因为原则上只需要遍历一遍A树的节点就行，所以可以采取层序遍历
     * 内层也可以采取层序遍历判断结构是否相同
     * 这样代码更简单
     */
    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        Deque<TreeNode> deque = new LinkedList<>();
        if (A == null) return false;
        if (B == null) return false;
        deque.push(A);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            if (dfs(node, B)) return true;
            if (node.left != null) deque.push(node.left);
            if (node.right != null) deque.push(node.right);
        }
        return false;
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        //保证根节点相同的情况下，判断b是否是a的子结构
        if (a.val != b.val) return false;
        Deque<TreeNode> deque1 = new LinkedList<>();
        Deque<TreeNode> deque2 = new LinkedList<>();
        deque1.offer(a);
        deque2.offer(b);
        while (!deque2.isEmpty()) {
            TreeNode node1 = deque1.poll();
            TreeNode node2 = deque2.poll();
            if (node1.val != node2.val) return false;
            //按目标树的左右节点进行层序遍历判断
            if (node2.left != null) {
                //按照B的树结构走，也要判断A中有没有呀 如果B有的A没有，那么就不匹配 如果A有的B没有，ok的
                if (node1.left == null) return false;
                deque1.offer(node1.left);
                deque2.offer(node2.left);
            }
            if (node2.right != null) {
                if (node1.right == null) return false;
                deque1.offer(node1.right);
                deque2.offer(node2.right);
            }
        }
        return true;
    }


}
