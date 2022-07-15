package algorithm.Tree;


import java.util.*;

/**
 * 补充题： https://www.nowcoder.com/test/question/done?tid=58385557&qid=2589594#summary
 * 牛牛有一棵n个节点的二叉树，该二叉树每个节点的权值为1。牛牛想要删掉该树其中的k层节点，删除序列为。
 * 如有一棵二叉树，删除其中的第3层节点:
 *       1
 *      / \
 *     1   1
 *    / \  /
 *   1   1 1
 *  / \   \
 * 1   1   1
 *  \  /
 *   1 1
 * 其会变为如下四棵二叉树:
 *       1
 *      / \
 *     1   1
 *
 * 1   1   1
 *  \  /
 *   1 1
 * 牛牛现在给你初始二叉树，以及表示删除第几层的删除序列a。
 * 牛牛希望能能将最后剩下的子树，按照根节点层序遍历的顺序返回子树数组。
 * 输入例子1:
 * {1,1,1,1,1,1,#,1,1,#,1,#,#,#,1,1},[3]
 * 输出例子1:
 * [{1,1,1},{1,#,1},{1,1},{1}]
 * 例子说明1:
 * 其为如题意给定的二叉树所得到的子树序列。
 * 输入例子2:
 * {1,#,1,#,1,#,1,#,1},[2,4]
 * 输出例子2:
 * [{1},{1},{1}]
 * 例子说明2:
 * 给定的为一条长度为5的链，删去第2层与4层后剩下三个单节点子树。
 * */
public class DeleteLevel {

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

    // 与层序便利很像，在层序遍历到过程中做文章
    // 注意，这个返回值是TreeNode的List集合，而不是每棵子树的遍历序列集合
    public ArrayList<TreeNode> deleteLevel (TreeNode root, ArrayList<Integer> a) {
        Set<Integer> set = new HashSet<>();
        for (int num : a)
            set.add(num);
        set.add(0);
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> list = new ArrayList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                // 上一层是待删除待，下一层是不被删除的，所以将下一层的节点加入到result list中
                if (set.contains(depth - 1) && !set.contains(depth))
                    list.add(node);
                // 下一层是待删除的，则需要将这一层节点的左右指针断开
                // 但是由于左右子节点在前两部被加入队列，所以不用担心树断开
                if (!set.contains(depth) && set.contains(depth + 1)) {
                    node.left = null;
                    node.right = null;
                }
            }
        }
        return list;
    }


}
