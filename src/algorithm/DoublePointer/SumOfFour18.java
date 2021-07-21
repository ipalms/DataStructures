package algorithm.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目描述     LeetCode  18
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * 输入：nums = [], target = 0
 * 输出：[]
 */
public class SumOfFour18 {
    public static void main(String[] args) {
        int nums[] = {2,2,2,2,2};
        List<List<Integer>> list = fourSum(nums,8);
        System.out.println(list);
    }

    //剪枝后的的输出时间会大幅减少
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        int n=nums.length;
        if(n<4){
            return result;
        }
        Arrays.sort(nums);
        for(int i=0;i<n-3;i++){
            //去重
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<n-2;j++){
                //去重
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                if (nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) // 剪枝
                    break;
                if (nums[i] + nums[j] + nums[n-2] + nums[n-1] < target) // 剪枝
                    continue;
                for(int left=j+1,right=n-1;left<right;){
                    if(nums[i]+nums[j]+nums[left]+nums[right]==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while(left<right&&nums[left]==nums[left+1])
                            left++;
                        while(left<right&&nums[right]==nums[right-1])
                            right--;
                        left++;
                        right--;
                        continue;
                    }
                    if(nums[i]+nums[j]+nums[left]+nums[right]<target){
                        left++;
                    }
                    if(nums[i]+nums[j]+nums[left]+nums[right]>target){
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
