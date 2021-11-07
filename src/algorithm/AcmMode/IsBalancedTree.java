package algorithm.AcmMode;

import java.io.*;
import java.util.*;

/**
 * 题目描述
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 输入描述:
 *   第一行两个数n,root，分别表示二叉树有n个节点，第root个节点时二叉树的根
 *   接下来共n行，第i行三个数val_i,left_i,right_i，
 *   分别表示第i个节点的值val是val_i,左儿子left是第left_i个节点，右儿子right是第right_i个节点。
 *   节点0表示空（null）。
 *   1<=n<=100000,保证是合法的二叉树
 *    输入案例：
 *     5 1
 *     5 2 3
 *     1 0 0
 *     3 4 5
 *     4 0 0
 *     6 0 0
 * 例如：
 * 输入：
 *     5
 *    / \
 *   1   3
 *      / \
 *     4   6
 * 输出: false
 */
public class IsBalancedTree {
    //构造树需要的结点类
    static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Scanner read = new Scanner(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int root = Integer.parseInt(s[1]);
        TreeNode[] tree = new TreeNode[n + 1];
        int[][] leaf = new int[n + 1][2];
        //java要先建立好节点后再将节点左右指针指向好
        for (int i = 1; i <= n; i++) {
            String[] ss = reader.readLine().split(" ");
            int val_i = Integer.parseInt(ss[0]);
            int left_i = Integer.parseInt(ss[1]);
            int right_i = Integer.parseInt(ss[2]);
            TreeNode node = new TreeNode(val_i);
            node.left = tree[left_i];
            node.right = tree[right_i];
            leaf[i][0] = left_i;
            leaf[i][1] = right_i;
            tree[i] = node;
        }
        for (int i = 1; i <= n; i++) {
            int left = leaf[i][0];
            if (left != 0) {
                tree[i].left = tree[left];
            }
            int right = leaf[i][1];
            if (right != 0) {
                tree[i].right = tree[right];
            }
        }
        TreeNode head = tree[root];
        boolean flag = isBinarySearchTree(head);
        System.out.println(flag);
    }


    private static boolean isBinarySearchTree(TreeNode node) {
        if (node == null) {
            return true;
        }
        int pre = Integer.MIN_VALUE;
        Stack<TreeNode> s = new Stack<>();
        while (!s.isEmpty() || node != null) {
            while (node != null) {
                s.push(node);
                node = node.left;
            }
            node = s.pop();
            if (node == null) {
                break;
            }
            if (pre > node.val) {
                return false;
            }
            pre = node.val;
            node = node.right;
        }
        return true;
    }


    static class IsBalanced {
        /**
         * 第二种建树方式
         */
        class TreeNode {
            public int val;             // 本结点value
            public int leftId;      // 左孩子的节点编号
            public int rightId;     // 右孩子的节点编号
            TreeNode left, right;       // 左右孩子节点引用

            public TreeNode(int val, int leftId, int rightId) {
                this.val = val;
                this.leftId = leftId;
                this.rightId = rightId;
                left = null;
                right = null;
            }
        }

        static TreeNode[] nodes;
        static ArrayList<Integer> inorderSeq;

        public void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] params = br.readLine().trim().split(" ");
            int n = Integer.parseInt(params[0]), root = Integer.parseInt(params[1]);
            nodes = new TreeNode[n + 1];
            for (int i = 1; i <= n; i++) {
                params = br.readLine().trim().split(" ");
                int val = Integer.parseInt(params[0]);
                int leftId = Integer.parseInt(params[1]);
                int rightId = Integer.parseInt(params[2]);
                TreeNode node = new TreeNode(val, leftId, rightId);
                nodes[i] = node;
            }
            // 重建二叉树
            TreeNode tree = nodes[root];
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(tree);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.leftId != 0) {
                    node.left = nodes[node.leftId];       // 找到左孩子
                    queue.offer(node.left);
                }
                if (node.rightId != 0) {
                    node.right = nodes[node.rightId];     // 找到右孩子
                    queue.offer(node.right);
                }
            }
            boolean flag = isBinarySearchTree(tree);
            System.out.println(flag);
        }

        private static boolean isBinarySearchTree(IsBalanced.TreeNode node) {
            if (node == null) {
                return true;
            }
            int pre = Integer.MIN_VALUE;
            Stack<IsBalanced.TreeNode> s = new Stack<>();
            while (!s.isEmpty() || node != null) {
                while (node != null) {
                    s.push(node);
                    node = node.left;
                }
                node = s.pop();
                if (node == null) {
                    break;
                }
                if (pre > node.val) {
                    return false;
                }
                pre = node.val;
                node = node.right;
            }
            return true;
        }
    }
}
