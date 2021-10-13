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
     * 哈希表判断是否加入了重复元素的技巧(只用添加方法而不用contains判断是否存在方法，时间复杂度会低许多)
     * HashSet:
     *  add操作存在返回值，为boolean型的
     *  因为Set分支的特点是无序且不可重复，因此通过add方法添加相同的值时，第一次返回为true
     *  后面再加相同元素的话就会返回false。可以通过判断返回值的布尔值来判断是否第一次添加
     * HashMap:
     *   在Map中添加元素使用put方法，在返回值问题上.
     *   因为Map中是以键值对存在，因此当一个键值是第一次被添加时返回值为null
     *   否则返回为上一次添加的value。可以通过判断返回值是否为null来判断是否第一次添加
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
