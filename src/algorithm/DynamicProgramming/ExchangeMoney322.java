package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 题目描述
 * 有数组penny，penny中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim(小于等于1000)代表要找的钱数，求换钱有多少种方法。
 * 给定数组penny及它的大小(小于等于50)，同时给定一个整数aim，请返回有多少种方法可以凑成aim。
 * 测试样例：
 * penny=[1,2,4]
 * penny_size=3
 * aim = 3
 * 返回：2
 * 即：方案为{1，1，1}和{1，2}两种
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
