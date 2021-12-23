package algorithm.DynamicProgramming;

import java.util.*;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 */
public class CombinationSumIV377 {

    /**
     * 这题数据范围较大--不能采取回溯做
     * 完全背包-求排列数
     * 如果存在负权值，答案可能会有无穷个。因为本身数值能够被选无限次，
     * 一旦存在负权，我们可以构造多个总和为 0 的方案，然后在此方案上构造出target。
     * 因此，如果允许出现负权，需要增加选择数量的限制。当我们期望从状态f[0][0]到达f[m][n]，
     * 但是中间存在总权值为0的环，那么我们可以通过进入无数次这样的环，来构成无限种方案。
     * 因此直接限制进环次数，或者增加总步数限制，就能从无限集合中解脱出来
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        //外层循环先枚举容量
        for (int i = 1; i <= target; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}

