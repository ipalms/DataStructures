package algorithm.DynamicProgramming;

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
public class ExchangeMoney {
    /**
     * 解题思路(动态规划解法)
     * 　设dp[n][m]为使用前n中货币凑成的m的种数，那么就会有两种情况：
     *               使用第n种货币：dp[n-1][m]+dp[n][m-penney[n]]
     *               不用第n种货币：dp[n-1][m]，为什么不使用第n种货币呢，因为penney[n]>m。
     *        这样就可以求出当m>=penney[n]时 dp[n][m] = dp[n-1][m]+dp[n][m-penney[n]]，
     * 　　否则，dp[n][m] = dp[n-1][m]
     * dp[n][m] =dp[n-1][m]+dp[n][m-penney[n]]怎么推导的
     * 对于任意的一个值dp[i][j]表示使用penney[0~i]的元素来拼凑出目标值为j的方案数目，总结规律可以发现：
     * dp[i][j]=dp[i-1][j]+dp[i-1][j-penney[i]]+dp[i-1][j-penney[i]*2]+..
     * 即penney[i](这张货币选)选0,1,2，...个时的情况
     * 又可推：dp[i-1][j-penney[i]]+dp[i-1][j-penney[i]*2]+... 的计算结果就是dp[i][j-penney[i]]的值
     *
     * 此题目还可以使用贪心算法，即从使用最大数目的钱币开始拼凑钱数
     */
    public static void main(String[] args) {
        int penney[]={1,2,4};
        int n=penney.length;
        int aim=10;
        System.out.println(countWays(penney,n,aim));
    }


    public static int countWays(int[] penny, int n, int aim) {
        // write code here
        if(n==0||penny==null||aim<0){
            return 0;
        }
        int[][] pd = new int[n][aim+1];
        for(int i=0;i<n;i++){
            pd[i][0] = 1;
        }
        for(int i=1;penny[0]*i<=aim;i++){
            pd[0][penny[0]*i] = 1;
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=aim;j++){
                if(j>=penny[i]){
                    pd[i][j] = pd[i-1][j]+pd[i][j-penny[i]];
                }else{
                    pd[i][j] = pd[i-1][j];
                }
            }
        }
        return pd[n-1][aim];
    }
}
