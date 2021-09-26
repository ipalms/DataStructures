package algorithm.DynamicProgramming;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class FindTargetSumWays494 {


    /**
     * 这题非常像39、40题的组合题（回溯区），这题除了使用动态规划也可以使用回溯解
     * 不同的是这一题是有正有负的（nums也可以取-nums，这样剪枝就不好弄）
     */
    int count = 0;
    public int findTargetSumWays1(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }



    /**
     * 思考如何转化到01背包问题：
     * sum(P) 前面符号为+的集合；sum(N) 前面符号为减号的集合
     * 所以题目可以转化为
     * sum(P) - sum(N) = target
     * => sum(nums) + sum(P) - sum(N) = target + sum(nums)
     * => 2 * sum(P) = target + sum(nums)
     * => sum(P) = (target + sum(nums)) / 2
     * 因此题目转化为01背包，也就是能组合成容量为sum(P)的方式有多少种
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || (sum + target) % 2 == 1) {
            return 0;
        }
        int size = (sum + target) / 2;
        if(size<0) size=-size;
        //dp[j] 表示：填满j（包括j）这么大容积的包，有dp[i]种方法
        int[] dp = new int[size + 1];
        //初始化：dp[0]=1，理论上也很好解释，装满容量为0的背包，有1种方法，就是装0件物品。
        dp[0] = 1;
        for (int num : nums) {
            for (int j = size; j >= num; j--) {
                //状态转移方程
                //不考虑nums[i]的情况下，填满容量为j - nums[i]的背包，有dp[j - nums[i]]种方法。
                //那么只要搞到nums[i]的话，凑成dp[j]就有dp[j - nums[i]] 种方法。
                //那么需要把 这些方法累加起来就可以了，dp[j] += dp[j - nums[i]]
                //所以求组合类问题的公式
                dp[j] += dp[j - num];
            }
        }
        return dp[size];
    }
}
