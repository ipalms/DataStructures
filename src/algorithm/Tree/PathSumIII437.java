package algorithm.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点root，和一个整数targetSum，
 * 求该二叉树里节点值之和等于targetSum的路径的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * 提示:
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class PathSumIII437 {


    /**
     * 有两种思路，一是前缀和+回溯
     * sum参数记录前面分支的路径总和，判断哈希表中是否存在路径和为sum-target的路径和，存在就将结果集中添加这样的路径数目
     * 在回退这一层时候要记得把到这层的路径和从哈希表中移除
     *
     * 二暴力解法，双重递归，以遍历到的每个节点作为递归的root节点计算路径和为target的数目，将结果累计返回
     */

    /**
     * 回溯+前缀和
     */
    //key是前缀和, value是大小为key的前缀和出现的次数
    Map<Integer,Integer> map;
    int targetSum;
    int res=0;
    public int pathSum(TreeNode root, int targetSum) {
        map=new HashMap<>();
        //前缀和为0的一条路径--计算前缀和一般都会进行初始化操作
        map.put(0,1);
        this.targetSum=targetSum;
        travel(root,0);
        return  res;
    }

    public void travel(TreeNode root,int sum){
        //递归终止条件
        if(root==null) return;
        //当前路径上的和
        sum+=root.val;
        //通过哈希表来反推root到该节点路径上是否存在路径和为target的路径
        //currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res+=map.getOrDefault(sum-targetSum,0);
        //更新路径上当前节点前缀和的个数
        map.put(sum,map.getOrDefault(sum,0)+1);
        //进入下一层
        travel(root.left,sum);
        travel(root.right,sum);
        //回到本层，恢复状态，去除当前节点的前缀和数量（状态回溯）
        map.put(sum,map.get(sum)-1);
    }

    /**
     * 双层递归
     * 首先先序递归遍历每个节点（注意先序递归），再以每个节点作为根节点递归寻找满足条件的路径。
     * 时间O（N^2）
     */
    int pathNumber;
    public int pathSum1(TreeNode root, int sum) {
        if(root == null) return 0;
        count(root,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
        return pathNumber;
    }


    public void count(TreeNode root, int sum){
        if(root == null) return;
        sum-=root.val;
        //不是子节点路径和为target也行
        if(sum == 0){
            ++pathNumber;
        }
        count(root.left,sum);
        count(root.right,sum);
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
