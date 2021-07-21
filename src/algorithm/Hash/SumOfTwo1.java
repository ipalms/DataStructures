package algorithm.Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SumOfTwo1 {
    public static void main(String[] args) {
        int nums []= {7,9,2,11};
        new ArrayList<Integer>(Arrays.asList(5,8,10));
        System.out.println(Arrays.toString(twoSum(nums,9)));
    }
    /**
    * 注：这题返回的是原数组的下标，所以不能对原数组排序。
    */

    /**
     * 哈希表解题
     * HashSet 不能记录找到目标值的索引（所以应该用HashMap--记录索引）
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        HashMap<Integer,Integer> maps=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            if(maps.containsKey(target-nums[i])){
                return new int[]{maps.get(target - nums[i]), i};
           /*   result[0]=maps.get(target - nums[i]);
                result[1]=i;
                return result;*/
            }
            maps.put(nums[i],i);
        }
        return result;
    }

    /**
     * 这里对数组排序了，只能返回凑好的两个数(不符合题目要求)
     * 排序+首尾交并推进
     */
    public static int[] twoSum2(int[] copy, int target) {
        int[] result=new int[2];
        Arrays.sort(copy);
        int end=copy.length-1;
        for (int i = 0; i <copy.length ; i++) {
            if(i>=end){
                return null;
            }
            while (copy[i]+copy[end]>target){
                end--;
            }
            if(copy[i]+copy[end]==target){
                result[0]=i;
                result[1]=end;
                return result;
            }
        }
        return null;
    }

    /**
     * 暴力解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        int[] result=new int[2];
        for (int i = 0; i <nums.length ; i++) {
            for (int j =i+1 ; j <nums.length ; j++) {
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    return result;
                }
            }
        }
        return null;
    }
}
