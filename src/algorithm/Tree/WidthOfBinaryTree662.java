package algorithm.Tree;

import java.util.*;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * 示例 1:
 * 输入:
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 * 输入:
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 * 输入:
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 * 输入:
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 */
public class WidthOfBinaryTree662 {

    /**
     * bfs  要维护一个二元组
     * 与958二叉树的完全性检验相似
     */
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        int max=0;
        Queue<Node> queue=new LinkedList<>();
        queue.offer(new Node(root,1));
        while(!queue.isEmpty()){
            int size=queue.size();
            int start=queue.peek().index;
            for(int i=0;i<size;++i){
                Node curr=queue.poll();
                if(curr.node.left!=null){
                    queue.offer(new Node(curr.node.left,curr.index*2));
                }
                if(curr.node.right!=null){
                    queue.offer(new Node(curr.node.right,curr.index*2+1));
                }
                //到每一层最后一个节点更新max
                if(i==size-1){
                    max=Math.max(max,curr.index-start+1);
                }
            }
        }
        return max;
    }

    /**
     * dfs版本--除了要维护index索引还要维护level层数
     */
    private int maxW = 0;
    public int widthOfBinaryTree2(TreeNode root) {
        /**
         假设满二叉树表示成数组序列, 根节点所在的位置为1, 则任意位于i节点的左右子节点的index为2*i, 2*i+1
         用一个List保存每层的左端点, 易知二叉树有多少层List的元素就有多少个. 那么可以在dfs的过程中记录每个
         节点的index及其所在的层level, 如果level > List.size()说明当前节点就是新的一层的最左节点, 将其
         加入List中, 否则判断当前节点的index减去List中对应层的最左节点的index的宽度是否大于最大宽度并更新
         **/
        dfs(root, 1, 1, new ArrayList<>());
        return maxW;
    }

    private void dfs(TreeNode r, int level, int index, List<Integer> left) {
        if(r == null) return;
        //每一层的第一个节点写入数组index下标
        if(level > left.size())
            left.add(index);
        maxW = Math.max(maxW, index - left.get(level-1) + 1);
        dfs(r.left, level+1, index*2, left);
        dfs(r.right, level+1, index*2+1, left);
    }

    class Node{
        int index;
        TreeNode node;
        public Node(TreeNode node,int index){
            this.index=index;
            this.node=node;
        }
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
