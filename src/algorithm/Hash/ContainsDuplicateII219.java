package algorithm.Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引i和 j，使得 nums [i] = nums [j]，
 * 并且 i 和 j 的差的绝对值至多为 k。
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class ContainsDuplicateII219 {

    /**
     * 类似窗口的思想--维护K个长度的非重复的set集合
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            //这个k大小范围内有重复元素
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            //移除超过k个大小范围的元素
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }


    /**
     * 返回索引不能排序，但同样可以使用哈希表
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])&&i-map.get(nums[i])<=k){
                return true;
            }
            map.put(nums[i],i);
        }
        return false;
    }


}
