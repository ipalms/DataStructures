package algorithm.DynamicProgramming;

/**
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 提示：
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */
public class MaxProfitII122 {

    /**
     * 买卖股票的最佳时机系列题--此题特点是可以买卖无限次
     * 1.贪心算法，维护min(阶段最小股值)、res（类和阶段买卖所赚）
     * 2.动态规划
     */

    /**
     * 贪心1--这个贪心要不断更新min
     * 这个贪心是模拟了买卖股票的过程
     */
    public int maxProfit(int[] prices) {
        int min=prices[0],res=0,n=prices.length;
        for(int i=1;i<n;++i){
            if(prices[i]<min){
                min=prices[i];
            }else if(i==n-1||prices[i]>prices[i+1]){
                res+=prices[i]-min;
                min=prices[i];
            }
        }
        return res;
    }

    /**
     * 贪心2--由于股票的购买没有限制，因此整个问题等价于寻找x个不相交的区间(l,r] 累和（nums[r]-nums[l]）最大化
     * 因此问题可以简化为找x个长度为1的区间累加nums[i+1]-nums[i]
     * 这里的贪心算法只能用于计算最大利润，计算的过程并不是实际的交易过程。
     */
    public int maxProfit4(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }


    /**
     * dp1
     * dp的思路和121题几乎一致（除了推导dp[i][1]的状态转移方程要考虑到前面多次交易的赚取的金额）
     * 这里的状态转移方程说明第i天的持有或不持有股票都与前面i-1天持有或不持有股票的现金额有关
     * 保证了结果的正确性
     */
    public int maxProfit1(int[] prices) {
        int n=prices.length;
        int [][]dp=new int[n][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for(int i=1;i<n;++i){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[n-1][0];
    }

    /**
     * dp2--空间优化1
     */
    public int maxProfit2(int[] prices) {
        int n=prices.length;
        int [][]dp=new int[2][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for(int i=1;i<n;++i){
            dp[i%2][0]=Math.max(dp[(i-1)%2][0],dp[(i-1)%2][1]+prices[i]);
            dp[i%2][1]=Math.max(dp[(i-1)%2][1],dp[(i-1)%2][0]-prices[i]);
        }
        return dp[(n-1)&1][0];
    }

    /**
     * dp3--空间优化2
     */
    public int maxProfit3(int[] prices) {
        int n=prices.length;
        int []dp=new int[2];
        dp[0]=0;
        dp[1]=-prices[0];
        //int tmp;
        for(int i=1;i<n;++i){
            //由于可以进行多次买卖，dp[1]可以直接使用dp[0]变化后的结果
            //（如果dp[0]不等于原来的dp[0]就相当于在第i天卖出股票，但是任然可以在第i天买入股票，就相当于第i天没有进行操作）
            //tmp=dp[0];
            dp[0]=Math.max(dp[0],dp[1]+prices[i]);
            dp[1]=Math.max(dp[1],dp[0]-prices[i]);
        }
        return dp[0];
    }
}
