package algorithm.SlidingWindow;

import java.util.*;

import org.junit.Test;
/**
 * 1438. 绝对差不超过限制的最长连续子数组  like 220
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 * 示例 1：
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 */
public class LongestSubarray1438 {
    @Test
    public void test(){
        int[]a={1,5,6,7,8,10,6,5,6};
        System.out.println(longestSubarray(a,4));
    }

    /**
     * 滑动窗口 + 者两个单向队列 目的是分别维护窗口内最大值最小值
     * 时间复杂度：O(n)，其中n是数组长度。我们最多遍历该数组两次，两个单调队列入队出队次数也均为 O(n)。
     * 空间复杂度：O(n)，其中n是数组长度。最坏情况下单调队列将和原数组等大。
     */
    public int longestSubarray4(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<Integer>();
        Deque<Integer> queMin = new LinkedList<Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            //维护递增队列（从左到右）
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            //维护递减队列（从左到右）
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            //比较这个窗口内的最大值与最小值之差
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                //大于给定limit，左边界要前移，需要删除两个队列中的可能保存了的左边界的值
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                //窗口左移
                left++;
            }
            //不断计算结果值
            ret = Math.max(ret, right - left + 1);
            //窗口右移
            right++;
        }
        return ret;
    }

    /**
     * 滑动窗口 + 有序集合
     * 或者 滑动窗口 + 两个优先队列（或者两个单调队列--239也是单调队列） 目的是分别维护窗口内最大值最小值
     */
    public int longestSubarrayMy(int[] nums, int limit) {
        TreeMap<Integer,Integer> tm=new TreeMap<>();
        int len=0;
        for(int left=0,right=0;right<nums.length&&left<=right;){
            if(tm.size()==0||(Math.abs(nums[right]-tm.firstKey())<=limit&&Math.abs(nums[right]-tm.lastKey())<=limit)){
                //先把元素加入就不需要两端判断了
                tm.put(nums[right],tm.getOrDefault(nums[right],0)+1);
                len=(right-left+1>len)?right-left+1:len;
                right++;
            }else{
                if (tm.get(nums[left]) == 1) {
                    tm.remove(nums[left]);
                } else {
                    tm.put(nums[left], tm.get(nums[left]) - 1);
                }
                left++;
            }
        }
        return len;
    }

    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            //先加入元素
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }


    /**
     * 滑动窗口 + 两个优先队列 目的是分别维护窗口内最大值最小值
     * 优先队列的坏处是一次插入平衡需要o(logn)时间复杂度，而单向链表仅维护链表头的话只需要o(1)时间复杂度
     */
    public int longestSubarray3(int[] nums, int limit) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < nums.length && left < nums.length) {
            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);
            //比较窗口内最大最小差值
            if (maxQueue.peek() - minQueue.peek() <= limit) {
                ans = Math.max(ans, right - left + 1);
                right++;
                continue;
            }
            //删除优先队列中的指定元素（删除操作为O（N）复杂度）
            maxQueue.remove((Integer) nums[left]);
            minQueue.remove((Integer) nums[left]);
            left++;
            right++;
        }
        return ans;
    }

}
