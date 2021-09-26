package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalSquare221 {

    @Test
    public void test(){
        char [][]a={{ '1', '1', '1','1','0' },
                { '1', '1', '1','1','0' },
                { '1', '1', '1','1','1' },
                { '1', '1', '1','1','1' },
                { '0', '0', '1','1','1' }};
        System.out.println(maximalSquare1(a));
    }

    /**
     * 自己做的---修改了原输入省去了dp数组的空间
     * 这一题与85. 最大矩形相似，只是哪一题是求最大矩阵面积（套用的84题的方法）采用的是单调栈解题
     * 1277. 统计全为 1 的正方形子矩阵 与本题本质一样
     *
     * 通过归纳可以发现矩阵右下角的点能对应的最大矩阵面积可由其左上角的点递推而来，故可采取动归解题
     * 状态定义：
     * dp[i][j] 以 matrix[i-1][j-1] 为右下角的正方形的最大边长
     * 状态转移方程（可以这样理解dp[i][j]对应最大矩阵面积由其左上角的三个点对应的最短边长决定）：
     * dp(i, j) = min(dp(i - 1, j), dp(i, j - 1), dp(i - 1, j - 1)) + 1;
     */
    public int maximalSquareMy(char[][] matrix) {
        int n=matrix.length,m=matrix[0].length;
        int max=0;
        //先对第一行第一列进行检验
        for(int i=0;i<m;++i){
            if(matrix[0][i]=='1'){
                max=1;
                break;
            }
        }
        if(max==0){
            for(int i=1;i<n;++i){
                if(matrix[i][0]=='1'){
                    max=1;
                    break;
                }
            }
        }
        //遍历后续的矩阵元素
        for(int i=1;i<n;++i){
            for(int j=1;j<m;++j){
                if(matrix[i][j]=='0') continue;
                matrix[i][j]=(char)(Math.min(matrix[i-1][j-1],Math.min(matrix[i][j-1],matrix[i-1][j]))+1);
                max=Math.max(max,(matrix[i][j]-'0')*(matrix[i][j]-'0'));
            }
        }
        return max;
    }

    /**
     * 使用dp数组的空间解题
     * 这一题与85. 最大矩形相似，只是哪一题是求最大矩阵面积（套用的84题的方法）采用的是单调栈解题
     *
     * 通过归纳可以发现矩阵右下角的点能对应的最大矩阵面积可由其左上角的点递推而来，故可采取动归解题
     * 状态定义：
     * dp[i][j] 以 matrix[i-1][j-1] 为右下角的正方形的最大边长
     * 状态转移方程（可以这样理解dp[i][j]对应最大矩阵面积由其左上角的三个点对应的最短边长决定）：
     * dp(i, j) = min(dp(i - 1, j), dp(i, j - 1), dp(i - 1, j - 1)) + 1;
     */
    public int maximalSquare(char[][] matrix) {
        int n=matrix.length,m=matrix[0].length;
        int max=0;
        //申明长度大1是为了兼容处理matrix第一行和第一列的情况
        int [][]dp=new int[n+1][m+1];
        for(int i=1;i<=n;++i){
            for(int j=1;j<=m;++j){
                if(matrix[i-1][j-1]=='1'){
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    max=Math.max(max,dp[i][j]);
                }
            }
        }
        return max*max;
    }


    /**
     * 状态压缩--最重要的是用变量pre保存了左上角的值
     * 1.每次循环前，dp[0...j-1] 保存的是当前行的最大边长，dp[j...cols] 保存的是上一行的最大边长。
     * 2.把上一行dp[j]暂存到temp里面。
     * 3.然后在dp[j-1] (左侧的的最大边长) 、dp[j] (上侧的的最大边长) 、pre (左 上侧的最大边长)里面取最小的，
     * 再加1就是以matrix[i][j]为右下角正方形的最大边长。
     * 4.把暂存的temp放到pre里，作为下一次循环左上侧的最大边长。
     */
    public int maximalSquare1(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dp = new int[cols + 1];
        int maxSide = 0;
        int pre = 0;
        for (int i = 1; i <= rows; i++) {
            pre = 0;
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '0') {
                    dp[j] = 0;
                } else {
                    dp[j] = Math.min(dp[j - 1], Math.min(dp[j], pre)) + 1;
                    maxSide = Math.max(dp[j], maxSide);
                }
                pre = temp;
            }
        }
        return maxSide * maxSide;
    }
}
