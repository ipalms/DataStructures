package algorithm.DynamicProgramming;

import java.lang.annotation.Target;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标
 * 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，
 * 那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 * 进阶：
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 */
public class MinTriangleSum120 {

    /**
     * 这题和124. 二叉树中的最大路径和、337. 打家劫舍 III  有相似之处
     */

    /**
     * 注意这里的N是指三角形的总行数
     */

    /**
     * 递归（自顶而下进行）---超时
     * 递归会重复计算一些分支的对应的最大值
     */
    int n;
    public int minimumTotal(List<List<Integer>> triangle) {
        n=triangle.size();
        return findMax(triangle,0,0);
    }

    public int findMax(List<List<Integer>> triangle,int level,int index){
        if(level==n){
            return 0;
        }
        int left=findMax(triangle,level+1,index);
        int right=findMax(triangle,level+1,index+1);
        return Math.min(left,right)+triangle.get(level).get(index);
    }

    /**
     * 递归+记忆化（二维数组存储结果）
     */
    Integer[][] memo;
    public int minimumTotal1(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return  dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }

    /**
     * 自底向上--动态规划
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n=triangle.size();
        int [][]dp=new int[n+1][n+1];
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=i;++j){
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /**
     * dp  优化内存--滚动数组
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n=triangle.size();
        int []dp=new int[n+1];
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=i;++j){
                dp[j]=Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0];
    }


}
