package algorithm.Hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class LongestContinuousSubArray128 {
    /**
     * 注意题目是要求O(N)时间复杂度 所以不能使用排序
     * 哈希表  -- 此题还可以使用并查集的做法（详见并查集目录）
     */

    /**
     * 先第一遍循环将元素放入哈希表中（hashset去重）
     * 第二遍循环要判断最长连续字串，根据题意可得连续字串的首元素的前一个元素一定不在哈希表中
     * 而该元素后面的连续元素的前一个元素在哈希表中，可以按照前一个元素在不在哈希表来降低判断流程的时间复杂度
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int longestStreak = 0;
        //第二遍循环使用Set会快很多  可能是Set是去重集合快了些
        for (int num : set) {
            //判断前一个数是否在哈希表中
            //技巧：如果有比自己小一点的，那自己不查，让小的去查（降低了复杂度）
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                //连续序列的第一个数，判断其后续的数是否页在哈希表中
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    public int longestConsecutive1(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int max=0;
        for(int num:nums){
            if(!set.contains(num-1)){
                int currentNext=num+1;
                while(set.contains(currentNext)){
                    currentNext++;
                }
                max=Math.max(max,currentNext-num);
            }
        }
        return max;
    }
}
