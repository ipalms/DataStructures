package algorithm.Hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class ContainsDuplicate217 {
    /**
     * 规定数据范围的可以使用数组代替hash表结构，比如都是字母--或者一定范围内的数字
     * hash表  优化一下只用add()方法而不用contains()方法
     * 也可以使用排序算法判断，但时间复杂度O(NlogN)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++)
            //不存在返回true 存在返回false
            if (!set.add(nums[i]))
                return true;
        return false;
    }

    public boolean containsDuplicate1(int[] nums) {
        Set<Integer>a=new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(a.contains(nums[i])){
                return true;
            }
            a.add(nums[i]);
        }
        return false;
    }
}
