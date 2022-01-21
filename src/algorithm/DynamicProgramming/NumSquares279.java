package algorithm.DynamicProgramming;

import java.util.Arrays;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；
 * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * 提示：
 * 1 <= n <= 104
 */
public class NumSquares279 {

    //与322.零钱兑换类似，都是取最少次数，只是这一题我们需要把完全平方数想象为一个物品。把n想象为背包的容量。
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        int max = Integer.MAX_VALUE;
        Arrays.fill(dp, max);
        dp[0] = 0;
        // 遍历物品， 物品就是每一个平方数(i * i) 1,4,9,16...
        for(int i = 1; i * i <= n; i++){
            // 遍历背包，背包容量为n，存放的物品是i * i
            for(int j = i * i; j <= n; j++){
                // 不为max才被采用
                if(dp[j - i * i] != max){
                    // 取最少次数
                    dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
                }
            }
        }
        return dp[n];
    }

    public int numSquares2(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        //先容量
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            //再物品
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }

    /**
     * 我是先计算出来了平方数组
     */
    public int numSquaresMy(int n) {
        int []muti=new int[101];
        for(int i=1;i<=100;++i){
            muti[i]=i*i;
        }
        int []dp=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=100;++i){
            for(int j=muti[i];j<=n;++j){
                dp[j]=Math.min(dp[j],dp[j-muti[i]]+1);
            }
        }
        return dp[n];
    }
}
