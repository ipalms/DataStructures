package algorithm.PrefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. 连续数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 */
public class FindMaxLength525 {

    /**
     * 相似的题，也是处理原数组的值分为  1，，-1两种，做前缀和：1124. 表现良好的最长时间段
     * */



    /**
     * 计算前缀和： 将1视为1，0视为-1，前缀和使用counter变量维护
     * 遍历数组nums，当遇到元素1时将counter的值加1，当遇到元素0时将counter的值减1
     * 遍历过程中使用哈希表存储每个前缀和第一次出现的下标。
     * 如果counter的值在哈希表中已经存在，则取出counter在哈希表中对应的下标 prevIndex
     * nums从下标 prevIndex+1到下标i的子数组中有相同数量的0和1，该子数组的长度为i−prevIndex
     * 使用该子数组的长度更新最长连续子数组的长度；
     * 如果counter的值在哈希表中不存在，则将当前余数和当前下标i的键值对存入哈希表中。
     */
    public int findMaxLength(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        //初始化
        map.put(counter, -1);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                maxLength = Math.max(maxLength, i - map.get(counter));
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }
}
