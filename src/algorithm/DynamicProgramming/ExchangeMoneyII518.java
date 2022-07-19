package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class ExchangeMoneyII518 {

    public int coinChangeMy(int[] coins, int amount) {
        int []dp=new int[amount+1];
        dp[0]=1;
        for(int c:coins){
            for(int j=c;j<=amount;j++){
                dp[j]+=dp[j-c];
            }
        }
        return dp[amount];
    }

    @Test
    public void test() {
        int []coins={1,2,5};
        int amount=5;
        System.out.println(change1(amount,coins));
    }

    /**
     * dp会涉及到遍历顺序的问题
     * 在背包问题求排列数或组合数衍生题中：大多的状态转移方程(一维dp数组)dp[i]+=dp[i-nums[i]]
     * (组合不强调元素之间的顺序，排列强调元素之间的顺序，排列数的结果要大于组合数。（回溯中也有排列和组合概念）
     * 对于target=3 nums[1,2]来说，组合方案是1，2  排列方案可以是1,2 | 2,1即与顺序相关)：
     *
     * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。定义的子问题是，必须选择第k个硬币时，凑成金额i的方案
     * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。子问题就变了，那就是对于金额 i, 我们选择硬币的方案
     */


    /**
     * 完全背包--求组合数（方案数）
     * 和494. 目标和相似也是求方案数目，只不过那题是01背包类型（数只可选一次），不过其dp转移方程一致
     */
    public int change(int amount, int[] coins) {
        int []dp=new int[amount+1];
        dp[0]=1;
        for(int i=0;i<coins.length;++i){
            for(int j=coins[i];j<=amount;++j){
                dp[j]+=dp[j-coins[i]];
            }
        }
        return dp[amount];
    }


    /**
     * 求排列数版本，即与使用钱币的顺序也有关系（不同的兑换顺序也视为不同的方案）
     */
    public int change1(int amount, int[] coins) {
        int []dp=new int[amount+1];
        dp[0]=1;
        for(int i=1;i<=amount;++i){
            for(int j=0;j<coins.length;++j){
                if(i>=coins[j]){
                    dp[i]+=dp[i-coins[j]];
                }
            }
        }
        return dp[amount];
    }
}
