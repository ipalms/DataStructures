package algorithm.DynamicProgramming;

/**
 * 题目
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1 输入： 2 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶         2.  2 阶
 */
public class ClimbStairs {
    public static void main(String[] args) {
        /**
         * 思路：
         * 爬第n（n>2）阶楼梯的方法 dp[n]=dp[n-1]+dp[n-2];
         */
        System.out.println(climb(4));
    }
    public static int climb(int n){
        if(n<=2){
            return n;
        }
        int[] dp=new int[n];
        dp[0]=1;
        dp[1]=2;
        for (int i = 2; i <n ; i++) {
          dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }
}
