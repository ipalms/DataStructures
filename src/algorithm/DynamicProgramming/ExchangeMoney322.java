package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2 31 - 1
 * 0 <= amount <= 10 4
 */
public class ExchangeMoney322 {


    @Test
    public void test() {
        int []coins={15,9,12,3};
        int amount=39;
        System.out.println(coinChange(coins,amount));
    }


    /**
     * 完全背包类型：
     * 兑换钱币不能使用纯贪心，因为贪心局部的最优结果并非全局最优结果
     * dp含义：dp[j]：凑足总额为j所需钱币的最少个数为dp[j]
     * 递推公式：dp[j] = min(dp[j - coins[i]] + 1, dp[j]);
     * 初始化：考虑到递推公式的特性，dp[j]必须初始化为一个最大的数
     *   否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。
     *   所以下标非0的元素都是应该是最大值。
     * 遍历顺序：本题求钱币最小个数，那么钱币有顺序和没有顺序都可以，都不影响钱币的最小个数。
     */
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        //初始化dp数组为最大值，当然这个最大值只要大于amount就行（所以也可以是amount+1）
        for (int j = 1; j < dp.length; j++) {
            dp[j] = max;
        }
        //当金额为0时需要的硬币数目为0
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            //正序遍历：完全背包每个硬币可以选择多次
            for (int j = coins[i]; j <= amount; j++) {
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j - coins[i]] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    /**
     * 更简洁的版本
     * 初始化数组不同
     */
    public int coinChange1(int[] coins, int amount) {
        int []dp=new int[amount+1];
        for(int i=1;i<=amount;++i){
            //除了dp[0]其余均初始化为amount+1(因为coins数组均为整数，所以最坏情况也只可能需要amount张币)
            dp[i]=amount+1;
        }
        dp[0]=0;
        for(int i=0;i<coins.length;++i){
            for(int j=coins[i];j<=amount;++j){
                dp[j]=Math.min(dp[j],dp[j-coins[i]]+1);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }


}
