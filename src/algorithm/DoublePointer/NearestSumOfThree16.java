package algorithm.DoublePointer;

import java.util.Arrays;

/**
 * 题目描述
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)
 */
public class NearestSumOfThree16 {
    public static void main(String[] args) {
        int nums[] = {1,1,1,0};
        System.out.println(threeSumClosest(nums, -100));
    }

    /**
     * 排序+双指针
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);  //对原数组进行了改动
        int n = nums.length;
        int result = Integer.MAX_VALUE;
        int diffMin = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    if (target - sum < diffMin) {
                        diffMin = target - sum;
                        result = sum;
                    }
                    j++;
                } else {
                    if (sum - target < diffMin) {
                        diffMin = sum -target;
                        result = sum;
                    }
                    k--;
                }
            }
        }
        return result;
    }
    /**
     * 优化解法
     */
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    /**
     * 暴力解法
     */
    public int threeSumClosest3(int[] nums, int target) {
        Arrays.sort(nums);
        //假定第一组三数之和最接近target
        int ans = nums[0] + nums[1] + nums[2];
        if(target <= ans){
            return ans;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                for(int k = j + 1; k < nums.length; k++){
                    int sum = nums[i] + nums[j] + nums[k];
                    if(Math.abs(target - ans) >= Math.abs(target - sum)){
                        ans = sum;
                    }
                }
            }
        }
        return ans;
    }
}

