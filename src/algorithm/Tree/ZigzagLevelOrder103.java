package algorithm.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class ZigzagLevelOrder103 {

    /**
     * 这题是102--层序遍历的变形（bfs遍历二叉树）
     * 用到层序遍历解题的：102层序遍历、103二叉树的锯齿形层序遍历、637二叉树的层平均值
     * 104二叉树的最大深度也可以使用层遍历、111二叉树的最小深度
     */


    /**
     * 这题不应该考虑如何将节点按照奇偶层顺序插入到队列当中，这样会导致代码繁琐
     * 而是遍历任为层序遍历但是将根据奇偶层将结果（节点值）头插或尾插如tmp链表当中
     */


    /**
     * 更为高效的思路是结点仍然按照中序遍历的顺序加入到结点队列当中
     * 然后按照当前结点属于奇数层还是偶数层来头插或尾插进结果集合
     * 具体的：
     * 如果当前处于奇数层，我们每次将被遍历到的元素插入至双端队列的末尾。
     * 如果当前处于偶数层，我们每次将被遍历到的元素插入至双端队列的头部。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        //奇数层栈
        LinkedList<TreeNode> list=new LinkedList<>();
        list.add(root);
        boolean isOdd=true;
        while(!list.isEmpty()){
            List<Integer> tmp=new LinkedList<>();
            int n=list.size();
            for(int i=0;i<n;++i) {
                TreeNode node = list.poll();
                if (isOdd) {
                    //奇数层，数据尾插
                    tmp.add(node.val);
                } else {
                    //偶数层，数据头插
                    tmp.add(0,node.val);
                }
                if(node.left!=null){
                    list.add(node.left);
                }
                if(node.right!=null){
                    list.add(node.right);
                }
            }
            res.add(tmp);
            isOdd=!isOdd;
        }
        return res;
    }

    //dfs版，虽然是深度优先遍历，但是依旧按照先左子节点，后右子节顺序（加之level代表层数）
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, res, 0);
        return res;
    }

    //level对应的就是结果集合的索引
    private void traversal(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(new ArrayList<Integer>());
        }
        //相较层序遍历dfs版只有这里的不同
        if ((level & 1) == 1){
            //奇数层头插
            res.get(level).add(0, root.val);
        } else {
            res.get(level).add(root.val);
        }
        traversal(root.left, res, level + 1);
        traversal(root.right, res, level + 1);
    }


    /**
     * 但队列---但是是按奇偶层模拟了节点如何插入到队列中
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null) return res;
        Deque<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        boolean isEven=true;
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> tmp=new ArrayList<>();
            for(int i=0;i<size;++i){
                TreeNode curr;
                if(isEven){
                    curr=queue.pollLast();
                    if(curr.left!=null){
                        queue.addFirst(curr.left);
                    }
                    if(curr.right!=null){
                        queue.addFirst(curr.right);
                    }
                }else{
                    curr=queue.pollFirst();
                    if(curr.right!=null){
                        queue.addLast(curr.right);
                    }
                    if(curr.left!=null){
                        queue.addLast(curr.left);
                    }
                }
                tmp.add(curr.val);
            }
            res.add(tmp);
            isEven=!isEven;
        }
        return res;
    }

    /**
     * 自己写的双栈实现
     * 这个的实现是改变结点插入到双向链表的顺序（并非直接的层序遍历顺序）,不推荐该思路
     */
    public List<List<Integer>> zigzagLevelOrderMy(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        //奇数层栈
        LinkedList<TreeNode> odd=new LinkedList<>();
        //偶数层栈
        LinkedList<TreeNode> even=new LinkedList<>();
        odd.add(root);
        boolean isOdd=true;
        while(!odd.isEmpty()||!even.isEmpty()){
            List<Integer> tmp=new ArrayList<>();
            //根据是奇数层还是偶数层来改变加入结点栈的元素顺序
            if(isOdd){
                int size=odd.size();
                for(int i=0;i<size;++i) {
                    TreeNode node=odd.pollFirst();
                    tmp.add(node.val);
                    if(node.left!=null){
                        even.addFirst(node.left);
                    }
                    if(node.right!=null){
                        even.addFirst(node.right);
                    }
                }
            }else {
                int size=even.size();
                for(int i=0;i<size;++i) {
                    TreeNode node=even.pollFirst();
                    tmp.add(node.val);
                    if(node.right!=null){
                        odd.addFirst(node.right);
                    }
                    if(node.left!=null){
                        odd.addFirst(node.left);
                    }
                }
            }
            res.add(tmp);
            isOdd=!isOdd;
        }
        return res;
    }

    public class TreeNode {
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
