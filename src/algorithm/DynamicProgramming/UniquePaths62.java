package algorithm.DynamicProgramming;

import java.util.Arrays;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 *
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 */
public class UniquePaths62 {


    /**
     * 动态规划经典思路
     * 我们令dp[i][j] 是到达i，j最多路径
     * 动态方程: dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 注意,对于第一行dp[0][j]，或者第一列dp[i][0]，由于都是在边界，所以只能为1
     */
    public int uniquePaths(int m, int n) {
        int [][]dp=new int[m][n];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(i==0||j==0){
                    dp[i][j]=1;
                }else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 空间优化--滚动数组的思想
     */
    public int uniquePaths1(int m, int n) {
        int []dp=new int[n];
        Arrays.fill(dp,1);
        for(int i=1;i<m;++i){
            for(int j=1;j<n;++j){
                dp[j]+=dp[j-1];
            }
        }
        return dp[n-1];
    }

    /**
     * 数学--组合数
     * 从左上角到右下角的过程中，我们需要移动m+n-2次,其中有m- 1次向下移动, n- 1次向右移动。
     * 因此路径的总数，就等于从m +n - 2次移动中选择m - 1次向下移动的方案数,即组合数C m-1/m+n-2
     */
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
}
