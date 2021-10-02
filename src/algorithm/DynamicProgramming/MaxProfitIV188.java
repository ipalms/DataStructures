package algorithm.DynamicProgramming;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 提示：
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class MaxProfitIV188 {


    /**
     * 买卖股票的最佳时机系列题--此题特点是可以买卖k次，此题和123、买卖股票的最佳时机 III类似
     * 1.动态规划---此题不可以贪心
     *
     * 空间优化版
     */
    public int maxProfit(int k, int[] prices) {
        if(prices.length==0) return 0;
        //除了0以外，偶数就是卖出，奇数就是买入股票。
        int []dp=new int[2*k+1];
        //可以推出dp[0][j]当j为奇数的时候都初始化为 -prices[0]，相当于前面j-1个状态在买入并卖出第一天的股票
        for(int i=1;i<=2*k;i+=2){
            dp[i]=-prices[0];
        }
        for(int i=1;i<prices.length;++i){
           /* for(int j=1;j<=2*k;++j){
                if(j%2==1){
                    dp[j]=Math.max(dp[j],dp[j-1]-prices[i]);
                }else{
                    dp[j]=Math.max(dp[j],dp[j-1]+prices[i]);
                }
            }*/
            for (int j = 1; j <2*k ; j+=2) {
                dp[j]=Math.max(dp[j],dp[j-1]-prices[i]);
                dp[j+1]=Math.max(dp[j+1],dp[j]+prices[i]);
            }
        }
        return dp[2*k];
    }



}
