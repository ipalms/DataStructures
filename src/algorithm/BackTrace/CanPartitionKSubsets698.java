package algorithm.BackTrace;

import java.util.Arrays;

/**
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 * 提示：
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 */
public class CanPartitionKSubsets698 {


    /**
     * 与416. 分割等和子集原理上都可以使用回溯解
     * 但是416的测试数据范围太大会超时，416应该使用动态规划（背包类型的衍生）
     * 注意题目的问法，问的是能否划分，所以状态压缩dp、回溯都能做。
     * 但如果要求返回具体的分组组合，那就只能用回溯暴力做了。
     */


    /**
     * 本题应该使用的回溯不断进行试探
     * 使用桶来装取集合的和
     */
    int[] bucket;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) return true; //如果k是1，直接返回true。
        int len = nums.length;
        int sum = 0;
        for (int num : nums) sum += num; // 算出nums的总和。
        if (sum % k != 0) return false; //子集分不出k份，直接false。
        sum /= k;// sum变为每个子集的和。
        /*
            为什么要排序呢，其实不排序这道题也能做对，但是由于时间的关系就t了。
            排序就是为了优化时间，怎么优化呢？
            我们从nums中最大的数开始找，如果最大的数比子集和都要大，或者装下它后没到子集和的大小但是装不下nums中最小的值了，
            那么这个nums绝对是false，因为有一个这么大的数在nums里，你把它放在哪个子集里都不合适。
        */
        Arrays.sort(nums);
        //如果子集的和小于数组最大的直接返回false
        if(nums[len-1]>sum) return false;
        bucket = new int[k];//这个数组里放的是子集和，总共有k个。相当于k个桶，元素一个一个往里面放。
        Arrays.fill(bucket, sum);
        return dfs(k, nums, len - 1);
    }

    public boolean dfs(int k, int[] nums, int cur) {//cur为当前的位置，从最后开始往前走。
        if (cur < 0) return true;// cur走到-1时，说明所有的数全部都放进桶里了。这时一定是true
        for (int i = 0; i < k; i++) {
            //两种可能，这个数正好是桶当前的容量，或者将这个数放进桶后这个桶还能再放别的数。
            if (bucket[i] == nums[cur] || bucket[i] - nums[cur] >= nums[0]) {
                //将cur放进第一个桶里，如果不行，拿出来再放进第二个桶里。
                //区别就是如果cur放进第一个桶，那么下一个数如果符合也会放在第一个桶，可是最后发现是false，那么可能我应该把这两个数分开来放。
                bucket[i] -= nums[cur];
                if (dfs(k, nums, cur - 1)) return true;
                bucket[i] += nums[cur];
            }
        }
        return false;
    }
}
