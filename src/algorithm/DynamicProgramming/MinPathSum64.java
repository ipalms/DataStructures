package algorithm.DynamicProgramming;

import java.util.Arrays;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class MinPathSum64 {

    /**
     * dp含义，走到第i列第j行的最小步数
     * dp转移方程：
     * dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j-1]
     */
    public int minPathSum1(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    /**
     * 两版状态知只是dp数组初始化不同而已，dp转移方程仍然一致
     * 状态压缩版1--滚动数组
     */
    public int minPathSum(int[][] grid) {
        int [] dp = new int[grid[0].length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < grid.length; i++){
            dp[0]=dp[0]+grid[i][0];
            for(int j = 1; j < grid[i].length; j++){
                dp[j]=Math.min(dp[j-1],dp[j])+grid[i][j];
            }
        }
        return dp[grid[0].length-1];
    }

    /**
     * 状态压缩版2--滚动数组
     */
    public int minPathSum2(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int []dp=new int[n+1];
        for(int i=0;i<n;++i){
            dp[i+1]=dp[i]+grid[0][i];
        }
        dp[0]=Integer.MAX_VALUE;
        for(int i=1;i<m;++i){
            for(int j=1;j<=n;++j){
                dp[j]=Math.min(dp[j],dp[j-1])+grid[i][j-1];
            }
        }
        return dp[n];
    }
}
