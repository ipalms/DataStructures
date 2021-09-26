package algorithm.DynamicProgramming;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class CanPartition416 {

    /**
     * 01背包的衍生题--状态压缩版
     * 对于nums数组可以转化01背包的概念--物品i的重量是nums[i]，其价值也是 nums[i]
     */
    public boolean canPartition(int[] nums) {
        int all=0,n=nums.length;
        for(int i=0;i<n;++i){
            all+=nums[i];
        }
        if(all%2!=0) return false;
        int target=all/2;
        //dp[i]含义，给定i空间大小容量到底能装多大的数（从nums挑选数字类和到dp[i]）
        int []dp=new int[target+1];
        for(int num : nums) {
            for (int j = target; j >= num; --j) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return dp[target]==target;
    }
}
