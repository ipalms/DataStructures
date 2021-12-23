package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class MaxRobMoney198 {

    @Test
    public void test(){
        System.out.println(rob4(new int[]{1,9,6,4}));
    }


    /**
     * 这道题我觉得和买卖股票等也类似，即你可以在第一时间就卖出股票而不需要买入的过程
     * 但是中间隔了一天的休息日（不能交易股票） 类似309. 最佳买卖股票时机含冷冻期
     */


    /**
     * 如果房屋数量大于两间，应该如何计算能够偷窃到的最高总金额呢？对于第 k(k>2)间房屋，有两个选项：
     * 偷窃第k间房屋，那么就不能偷窃第k−1间房屋，偷窃总金额为前k−2间房屋的最高总金额与第k间房屋的金额之和。
     * 不偷窃第k间房屋，偷窃总金额为前k−1间房屋的最高总金额。
     */

    /**
     * 无优化空间版本
     */
    public int rob1(int[] nums) {
        if(nums.length==1) return  nums[0];
        int []dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;++i){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }


    /**
     * 拓展---输出抢劫路径
     */
    public String rob4(int[] nums) {
        int len=nums.length;
        if(len==1) return  "第一次抢劫第1家获得 "+nums[0]+" 元";
        int []dp=new int[len];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<len;++i){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        String path="";
        for(int i=len-1;i>=0;){
            //需要while 而非if 来排除没偷的
            while(i>0&&dp[i-1]==dp[i]){
                --i;
            }
            path="抢劫第"+ (i + 1) +"家获得 "+nums[i]+" 元"+"\n"+path;
            i-=2;
        }
        return path;
    }

    /**
     * 空间优化版---官方题解给出
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    /**
     * 空间优化版
     */
    public int robMy(int[] nums) {
        int []dp=new int[2];
        dp[0]=0;
        dp[1]=nums[0];
        for(int i=1;i<nums.length;++i){
            int a=dp[1];
            dp[1]=Math.max(dp[0]+nums[i],dp[1]);
            dp[0]=a;
        }
        return dp[1];
    }
}
