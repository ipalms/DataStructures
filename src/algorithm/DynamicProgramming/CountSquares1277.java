package algorithm.DynamicProgramming;

/**
 * 1277. 统计全为 1 的正方形子矩阵
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，
 * 请你统计并返回其中完全由 1 组成的正方形子矩阵的个数。
 * 示例 1：
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 * 提示：
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class CountSquares1277 {

    /**
     *  221. 最大正方形 与本题本质一样
     */
    public int countSquares(int[][] matrix) {
        int n=matrix.length,m=matrix[0].length;
        int [][]dp=new int[n+1][m+1];
        int count=0;
        for(int i=1;i<=n;++i){
            for(int j=1;j<=m;++j){
                if(matrix[i-1][j-1]==1){
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    count+=dp[i][j];
                }
            }
        }
        return count;
    }
}
