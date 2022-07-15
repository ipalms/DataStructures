package algorithm.DynamicProgramming;


/**
 * 补充题：https://www.nowcoder.com/question/next?pid=39959332&qid=2589591&tid=58397841
 * 小红最近在研究 k-size 字符串。
 * 一个字符串为 k-size 指，字符串的连续段共有 k 个。所谓连续段指尽可能多的相同连续字母组成的子串。
 * 例如：aabbbccc为3-size，因为（'aa' 'bb' 'ccc'）
 * ababaab为6-size，因为 （'a' 'b' 'a' 'b' 'aa' 'b'）。
 * 小红认为当且仅当每个连续段长度 至少为2 时，该字符串是合法的。
 * 例如aabbbccc是合法的，但ababaab不是合法的。
 * 小红想知道，给定 n 和 k，长度为n的，仅由小写字母组成的 合法的 k-size 字符串共有多少个？
 * 由于答案可能过大，请对 1e6 取模。
 *
 * 输入例子1:
 * 2,1
 * 输出例子1:
 * 26
 * */
public class KSizeString {


    public static void main(String[] args) {
        System.out.println(numsOfStrings(7,2));
    }

    /**
     * @param n int整型
     * @param k int整型
     * @return int整型
     */

    /**
     * 动态规划，dp[i][j]表示长度为i，可以分为j段的字符串个数。
     * 首先考虑动态转移方程，假设向一个字符串s添加字符c，如果c与s的最后一个字符相同,
     * 转移方程dp[i][j] += dp[i-1][j]：由i-1长度, 段数为j的字符串转移而来；
     * 否则,dp[i][j] += 25*dp[i-2][j-1]：
     * 由长度i-2（为什么是i-2是因为题目要求了连续段的长度需要大于2），段数为j-1的字符串转移而来，且当前选择的字符c有25中选择。
     * 所以dp[i][j] = dp[i - 1][j] + 25*dp[i - 2][j - 1]。
     * */
    public static int numsOfStrings (int n, int k) {
        int mod=1000000;
        int[][] dp=new int[n+1][k+1];
        // 初始化
        for(int i=2;i<=n;i++){
            dp[i][1]=26;
        }
        // 新一轮的初始长度从3  或  4开始都可以（3会直接跳过，因为连续段的长度需要大于2）
        for(int i=4;i<=n;i++){
            for(int j=2;j<=k;j++){
                dp[i][j]=dp[i-1][j]+25*dp[i-2][j-1];
                //注意每一次转移方程后都要取余一下
                dp[i][j]%=mod;
            }
        }
        return dp[n][k];
    }
}
