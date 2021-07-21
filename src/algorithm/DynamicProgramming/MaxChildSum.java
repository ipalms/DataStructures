package algorithm.DynamicProgramming;

/**
 * 题目叙述：
 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 示例:
 输入: [-2,1,-3,4,-1,2,1,-5,4]
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxChildSum {
    public static void main(String[] args) {
        /**
         使用动态规划：
         用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」
         向当于如果前面的和大于零，则f(i)加上当前的a[i],否则f[i]就取a[i]
         f(i)=max{f(i−1)+a[i],a[i]}
         遍历f(i)，取其最大的数即可
         */
        int nums[]={100,-100,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        //定义dp数组，dp数组中的每个值dp[i]代表着以nums[i]为结尾的最大子序和
        int[] dp = new int[n];
        //以nums[0]结尾的最大子序和就是nums[0]
        dp[0] = nums[0];
        //遍历，通过状态转移方程求得dp[i]的最大子序和
        for(int i = 1; i < n; ++i){
            //dp[i]的最大子序和要么是自成一派最大，要么就是当前值加上前面i - 1个数的最大子序和
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        //遍历dp数组，求得dp数组中的最大值，就是该题的答案
        //int res = Integer.MIN_VALUE;  //取int类型的最小值
        int res = dp[0];  //取int类型的最小值
        for(int j = 0; j < dp.length; ++j){
            res = Math.max(res, dp[j]);
        }
        return res;
    }

    /**
     * 贪心算法完成    此题目还可以用分治算法做
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            }
            else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
