package algorithm.PrefixSum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 1124. 表现良好的最长时间段
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * 请你返回「表现良好时间段」的最大长度。
 * 示例 1：
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 * 示例 2：
 * 输入：hours = [6,6,6]
 * 输出：0
 * 提示：
 * 1 <= hours.length <= 104
 * 0 <= hours[i] <= 16
 * */
public class LongestWPI1124 {

    /**
     * 相似题：525. 连续数组
     * 前缀和，将数据大于8视为1，小于8视为-1
     * */
    public int longestWPI(int[] hours) {
        int maxInterval = 0;
        Map<Integer, Integer> indices = new HashMap<Integer, Integer>();
        int sum = 0;
        int n = hours.length;
        for (int i = 0; i < n; i++) {
            int score = hours[i] > 8 ? 1 : -1;
            //前缀和
            sum += score;
            //大于0，说明前面的数都大于0
            if (sum > 0) {
                maxInterval = Math.max(maxInterval, i + 1);
            }
            //小于0，如果map存在sum-1，那么那个数的下标到当前数的区间和也是大于0的，也要考虑输出
            else if (indices.containsKey(sum - 1)) {
                int interval = i - indices.get(sum - 1);
                maxInterval = Math.max(maxInterval, interval);
            }
            indices.putIfAbsent(sum, i);
        }
        return maxInterval;
    }

    /**
     * 暴力解法思想，即使求前缀和数组中任意i,j-->使得prefix[i]-prefix[j]>0的时i-j最大的长度
     * 那么可以for循环固定前缀和数组左右端点，挨个算prefix[i]-prefix[j]>0时计算max长度
     * */

    /**
     * 前缀和加单调栈--不记忆
     * 这个单调栈的思路和 962. 最大宽度坡 一样
     * */
    public int longestWPI2(int[] hours) {
        int maxInterval = 0;
        int n = hours.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int score = hours[i] > 8 ? 1 : -1;
            sums[i + 1] = sums[i] + score;
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        //单调栈放置的是索引，代表的是前缀和数组递减的索引
        for (int i = 0; i <= n; i++) {
            int sum = sums[i];
            if (stack.isEmpty() || sums[stack.peek()] > sum) {
                stack.push(i);
            }
        }
        for (int j = n; j >= 0; j--) {
            int sum = sums[j];
            while (!stack.isEmpty() && sums[stack.peek()] < sum) {
                int interval = j - stack.pop();
                maxInterval = Math.max(maxInterval, interval);
            }
        }
        return maxInterval;
    }

}
