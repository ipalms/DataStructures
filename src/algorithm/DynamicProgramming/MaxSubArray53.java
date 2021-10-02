package algorithm.DynamicProgramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * 输入：nums = [-100000]
 * 输出：-100000
 */
public class MaxSubArray53 {
    @Test
    public void test() {
        //int nums[]={-2,1,-3,4,-1,2,1,-5,4};
        int []nums={1,5,9};
        System.out.println(Arrays.toString(maxSubArray4(nums)));
    }

    /**
     * 定义一个长度与nums数组一样的dp[n]数组，用 dp[i]代表以第 i 个数结尾的连续子数组的最大和
     * 通过分析可知对于dp[i](0<i<n)的取值规则：当前一个元素的最大和（即dp[i-1]）大于零，则dp[i]取前一个元素的最大和加上当前的nums[i],否则dp[i]就取nums[i]
     * 即：dp[i]=max{dp[i-1]+nums[i],dp[i]}
     * 遍历dp[n]数组，取其最大的数即可。
     * 但由于dp[i]只和上一个状态（dp[i-1]）有关，这样就可以只维护一个变量 prev 保存上一个状态即可。
     */

    /**
     * 有动态规划 和 分治算法两种思路
     * 动态规划一
     */
    public int maxSubArray1(int[] nums) {
        //maxAns 首先要取一个第一元素（不能取零防止后面元素全是负数而结果错误情况）
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            //只需要一个变量 pre 就能记录前面i-1个数据的最大和
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /**
     * 变种题-求和最大的子序列（这时要记录左右边界）
     */
    public int[] maxSubArray4(int[] nums) {
        //maxAns 首先要取一个第一元素（不能取零防止后面元素全是负数而结果错误情况）
        int sum = 0, maxAns = nums[0],left=0,right=0,start=0;
        for (int i=0;i<nums.length;++i) {
            if(sum>0){
                sum=sum+nums[i];
            }else{
                sum=nums[i];
                start=i;
            }
            if(sum>maxAns){
                maxAns=sum;
                right=i;
                left=start;
            }
        }
        return Arrays.copyOfRange(nums,left,right+1);
    }


    /**
     * 有动态规划 和 分治算法两种思路
     * 动态规划二
     */
    public int maxSubArray2(int[] nums) {
        int res = nums[0];//ans需要取一个数组值，以防所有条件都不满足直接return ans时的情况
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {//只要sum是大于0的，就对当前的和是积极贡献
                sum += num;
            } else {//否则抛弃之前的sum，sum更新为当前的数组值
                sum = num;
            }
            res = Math.max(res, sum);//每遍历一个数，判断一下sum，更新一次res
        }
        return res;
    }

    /**
     * 动态规划没有优化内存的版本
     */
    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        //定义dp数组，dp数组中的每个值dp[i]代表着以nums[i]为结尾的最大子序和
        int[] dp = new int[n];
        //以nums[0]结尾的最大子序和就是nums[0]
        dp[0] = nums[0];
        //遍历，通过状态转移方程求得dp[i]的最大子序和
        for(int i = 1; i < n; ++i){
            //dp[i]的最大子序和要么是自身最大，要么就是当前值加上前面i - 1个数的最大子序和
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        //遍历dp数组，求得dp数组中的最大值，就是该题的答案
        int res = dp[0];
        for(int j = 0; j < dp.length; ++j){
            res = Math.max(res, dp[j]);
        }
        return res;
    }
}
