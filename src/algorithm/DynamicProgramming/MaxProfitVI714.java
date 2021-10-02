package algorithm.DynamicProgramming;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * 示例 1：
 *
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 *
 * 提示：
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 */
public class MaxProfitVI714 {

    /**
     * 买卖股票的最佳时机系列题--此题特点是可以买卖无限次，并且附带每笔交易的交易费
     * 此题和122. 买卖股票的最佳时机 II几乎一致
     * 1.贪心算法
     * 2.动态规划
     */

    /**
     * dp--只要每次交易算上交易费用就行
     */
    public int maxProfit(int[] prices, int fee) {
        int []dp=new int[2];
        dp[0]=0;
        dp[1]=-prices[0]-fee;
        for(int i=1;i<prices.length;++i){
            dp[0]=Math.max(dp[0],dp[1]+prices[i]);
            dp[1]=Math.max(dp[1],dp[0]-prices[i]-fee);
        }
        return dp[0];
    }


    /**
     * 贪心--不重点了解
     */
    public int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
}
