package algorithm.DynamicProgramming;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaxProductSubarray152 {



    /**
     * 动态规划
     * 与第53题最大子序和有相似点
     * 遍历数组时计算当前最大值，不断更新
     * nums[i]为非负数时，令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。
     * 因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     *
     * 与53题类似的
     * 由于第i个状态只和第 i−1个状态相关，根据「滚动数组」思想，
     * 我们可以只用两个变量来维护i−1时刻的状态，一个维护imax、一个维护imin
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            //出现负数，最大值最小值进行交换
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            //不断维护乘积的最大值和最小值
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);
            //从最大值中选出子数组中所有的最大乘积
            max = Math.max(max, imax);
        }
        return max;
    }

    /**
     * 不交换max min的dp转移方程写法
     * maxDP[i + 1] = max(maxDP[i] * A[i + 1], A[i + 1],minDP[i] * A[i + 1])
     * minDP[i + 1] = min(minDP[i] * A[i + 1], A[i + 1],maxDP[i] * A[i + 1])
     * dp[i + 1] = max(dp[i], maxDP[i + 1])
     */
    public int maxProduct2(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

}
