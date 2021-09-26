package algorithm.DynamicProgramming;

/**
 * 题目 70
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1 输入： 2 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶         2.  2 阶
 */
public class ClimbStairs70 {
    public static void main(String[] args) {
        System.out.println(climb(4));
    }

    /**
     * 思路：
     * 第n个台阶只能从第n-1或者n-2个上来。
     * 到第n-1个台阶的走法 + 第n-2个台阶的走法 = 到第n个台阶的走法
     * 已经知道了第1个和第2个台阶的走法，一路加上去。
     * 爬第n（n>2）阶楼梯的方法 dp[n]=dp[n-1]+dp[n-2];
     * 这个公式也是斐波那契数列的通项公式---剑指 Offer 10- I. 斐波那契数列
     */
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

    /**
     * 优化空间复杂度
     */
    public int climbStairs(int n) {
        if(n<3) return n;
        int i1=1,i2=2;
        for(int i=3;i<=n;++i){
            i2=i1+i2;
            i1=i2-i1;
        }
        return i2;
    }
}
