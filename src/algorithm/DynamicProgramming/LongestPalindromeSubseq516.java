package algorithm.DynamicProgramming;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class LongestPalindromeSubseq516 {

    /**
     * 区间dp的一些套路：
     * 区间dp在给定一个回文串的基础上，如果在回文串的边缘分别添加两个新的字符，可以通过判断两字符是否相等来得知新串是否回文
     * 通常区间 DP 问题都是，常见的基本流程为：
     * 从小到大枚举区间大小len
     * 枚举区间左端点l，同时根据区间大小len和左端点计算出区间右端点r=l+len−1
     * 通过状态转移方程求 dp[l][r]的值
     */

    /**
     * 代码逻辑可以参考第5题
     * 与第5题类似的:不同的是此题求的是子序列的最长长度，即可以剔除某些字符
     * 用dp[i][j]表示字符串s的下标范围[i, j]内的最长回文子序列的长度。假设字符串s的长度为n,则只有当
     * 0≤i≤j<n时，才会有dp[i][j]> 0,否则dp[i][j]= 0。
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        //由于左边界小于等于右边界所以dp数组最终赋值是呈现左下三角形的
        int[][] dp = new int[n][n];
        for(int i=0;i<n;++i){
            dp[i][i]=1;
        }
        //注意这里是等于号 len <= n
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                if(cs[l]!=cs[r]){
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }else{
                    //else其实可以直接  dp[l][r] = dp[l + 1][r - 1]+2
                    //因为当len==2是，dp[l + 1][r - 1]==dp[r][l]==0（dp数组右上角为0）,结果任然一致
                    if(len==2){
                        dp[l][r] = 2;
                    }else {
                        dp[l][r] = dp[l + 1][r - 1]+2;
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
