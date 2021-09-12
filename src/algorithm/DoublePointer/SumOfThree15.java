package algorithm.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目描述     LeetCode  15
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。   (重点不能重复)
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [[-1, 0, 1],[-1, -1, 2]]
 */
public class SumOfThree15 {
    public static void main(String[] args) {
        int nums[] = {3, 0, -2, -1, 1, 2};
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list);
    }

    /**
     * 自己写的  排序+双指针
     * 排序要看题目要求是否能排序，如果要求的是目标输出集合的下标那么就不可以排序
     * 第二层使用的for循环 此类型要点先排序
     * 先判断能输出的情况和后判断输出情况的执行事件有差别
     */
    public List<List<Integer>> threeSumMy(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length<3){
            return result;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            if (nums[i] + nums[i+1] + nums[i+2] > 0) // 剪枝
                break;
            if (nums[i] + nums[nums.length-1]+nums[nums.length-2]<0) // 剪枝
                continue;
            for(int left=i+1,right=nums.length-1;left<right;){
                if(nums[left]+nums[right]+nums[i]==0){
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                    continue;
                }
                if(nums[left]+nums[right]+nums[i]<0){
                    left++;
                }
                if(nums[left]+nums[right]+nums[i]>0){
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * 第二层使用的while循环 此类型要点先排序
     * 先判断能输出的情况和后判断输出情况的执行时间有差别
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            //跟前一个数组值比较就可以不用while结构
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    j++;
                    continue;
                }
                if (j < k && nums[i] + nums[j] + nums[k] > 0) {
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    k--;
                }
                if (j < k && nums[i] + nums[j] + nums[k] < 0) {
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    j++;
                }
                //后判断输出情况
                if (j < k && nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> target = new ArrayList<>();
                    target.add(nums[i]);
                    target.add(nums[j]);
                    target.add(nums[k]);
                    list.add(target);
                    j++;
                    k--;
                }
            }
        }
        return list;
    }

    /**
     * 第二层使用for循环 此类型要点先排序
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    ans.add(new ArrayList<Integer>(Arrays.asList(nums[first],nums[second],nums[third])));
                }
            }
        }
        return ans;
    }

    /**
     * 第二层使用while循环 但是做了一点改良
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum3(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) return ans;

        Arrays.sort(nums); // O(nlogn)

        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去掉重复情况
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++;
                    right--; // 首先无论如何先要进行加减操作
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ans;
    }
}
