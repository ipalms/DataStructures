package algorithm.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class MaxRobMoneyIII337 {

    /**
     * 本题是树状dp
     */

    /**
     * 自顶向下（暴力法）
     * 题目的意思：
     * 1.首先要明确相邻的节点不能偷，也就是爷爷选择偷，儿子就不能偷了，但是孙子可以偷
     * 2.二叉树只有左右两个孩子，一个爷爷最多 2 个儿子，4 个孙子
     * 求得最大值的情况：
     * 4 个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱
     * 哪个组合钱多，就当做当前节点能偷的最大钱数。这就是动态规划里面的最优子结构
     */
    public int rob(TreeNode root) {
        if(root==null){
            return 0;
        }
        int money=root.val;
        if(root.left!=null){
            money+=rob(root.left.left)+rob(root.left.right);
        }
        if(root.right!=null){
            money+=rob(root.right.left)+rob(root.right.right);
        }
        //比较4个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱作为当前root结果的最大值返回给上一层
        return Math.max(money,rob(root.left)+rob(root.right));
    }

    /**
     * 记忆化搜素，将搜索的结果记录下来供以后的搜索使用结果(hashmap记录缓存结果)--但是任然还是自顶而下的找寻结果
     * 相比自下而上的搜索多了很多的方法栈的生成
     * 上面的暴力法多了很多重复的递归
     * 爷爷在计算自己能偷多少钱的时候，同时计算了 4 个孙子能偷多少钱，也计算了 2 个儿子能偷多少钱。
     * 这样在儿子当爷爷时，就会产生重复计算一遍孙子节点。
     */
    Map<TreeNode,Integer> map=new HashMap<>();
    public int rob1(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(map.containsKey(root)) return map.get(root);
        int money=root.val;
        if(root.left!=null){
            money+=rob(root.left.left)+rob(root.left.right);
        }
        if(root.right!=null){
            money+=rob(root.right.left)+rob(root.right.right);
        }
        //比较4个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱作为当前root结果的最大值返回给上一层
        int result=Math.max(money,rob(root.left)+rob(root.right));
        map.put(root,result);
        return result;
    }

    /**
     * 自顶而下但是少了很多方法栈调用
     * 我们使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷
     * 任何一个节点能偷到的最大钱的状态可以定义为
     * 当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
     * 当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
     */
    public int rob2(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];
        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);
        //当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
        result[1] = left[0] + right[0] + root.val;
        return result;
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
