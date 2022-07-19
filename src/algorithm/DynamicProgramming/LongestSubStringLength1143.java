package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class LongestSubStringLength1143 {
    @Test
    public void test() {
        //最长子序列长度例子
        System.out.println(longestCommonSubsequencePath("android", "random"));
    }

    /**
     * 与718题很像--最长重复子数组
     * 最长公共子序列（Longest Common Subsequence，简称 LCS）是一道非常经典的面试题目
     * 从二维表格可只每个点的最大自序长度都由该点左上角的区域的来的（转移方程）
     *
     * 1. 状态定义
     * 定义dp[i][j] 表示t1[0:i-1] 和t2[0:j-1]的最长公共子序
     * (注: text1[0:i-1] 表示的是text1 的第0个元索到第i- 1个元素,两端都包含)
     * 之所以dp[i][j] 的定义不是t1[0:i-1] 和t2[0:j-1],是为了方便当i=0或者j=0的时候，
     * dp[i][j]表示的为空字符串和另外一个字符串的匹配，这样dp[i][j]可以初始化为0.
     * 2. 状态转移方程
     * dp[i][i]=dp[i- 1][j- 1]+1,当text1[i- 1]== text2[j- 1];
     * dp[i][i] = max(dp[i - 1][j], dp[i][j- 1]), 当text1[i - 1]!= text2[j - 1]
     * 3.状态的初始化
     * 初始化就是要看当i=0与j=0时，dp[i][j] 应该取值为多少。
     * ●当i=0时，dp[0][j] 示的是text1 中取空字符串跟text2的最长公共子列，结果肯定为0.
     * ●当j=0时，dp[i][0] 表示的是text2中取空字符串跟text1的最长公共子序列,结果肯定为0.
     * 综上，当i=0或者j=0时，dp[i][j] 初始化为0.
     * 4.返回值，如果有可能是过程中取到最大值就要维护一个变量记录最大值
     * 如果是遍历结束可获得最大值可从dp数组获得最大值
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n=text1.length(),m=text2.length();
        int[][] dp =new int[n+1][m+1];
        for(int i=1;i<=n;++i){
            char c1=text1.charAt(i-1);
            for(int j=1;j<=m;++j){
                if(c1==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 找到这个具体的子序列
     * */
    public String longestCommonSubsequencePath(String text1, String text2) {
        int n=text1.length(),m=text2.length();
        int[][] dp =new int[n+1][m+1];
        for(int i=1;i<=n;++i){
            char c1=text1.charAt(i-1);
            for(int j=1;j<=m;++j){
                if(c1==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        int i=n,j=m;
        StringBuilder sb=new StringBuilder();
        while(i!=0&&j!=0){
            //这个条件也可以替换成（dp[i][j] > dp[i-1][j] && dp[i][j] > dp[i][j-1])
            if(text1.charAt(i-1)==text2.charAt(j-1)){
                sb.insert(0,text1.charAt(i-1));
                i--;
                j--;
            }else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }else{
                j--;
            }
        }
        return sb.toString();
    }

    /**
     * 空间优化版本--要保存左上角的dp(dp[i-1][j-1])的值
     * 因为在滚动数组过程中该值会被下一行（遍历到第i行第j-1列时覆盖），所以要提前保存下来
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        int len1=text1.length(),len2=text2.length();
        int []dp=new int[len2+1];
        for(int i=1;i<=len1;++i){
            int northWest=0;
            for(int j=1;j<=len2;++j){
                int curr=dp[j];
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[j]=northWest+1;
                }else{
                    dp[j]=Math.max(dp[j],dp[j-1]);
                }
                northWest=curr;
            }
        }
        return dp[len2];
    }
}
