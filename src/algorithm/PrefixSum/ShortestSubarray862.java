package algorithm.PrefixSum;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 862. 和至少为 k 的最短子数组
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，
 * 并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 * 子数组 是数组中 连续 的一部分。
 * 示例 1：
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 * 提示：
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 */
public class ShortestSubarray862 {

    /**
     * 单调双端队列加前缀和---209. 长度最小的子数组扩展版
     * 两个内部循环为啥弹出值且不影响结果参考如下题解解释
     * 参考题解：
     * https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/solution/by-elliefeng-ip3d/
     * https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/solution/tu-jie-by-wo-shi-a-miao-jiang-mpc7/
     * */

    public int shortestSubarray(int[] A, int k) {
        int N = A.length;
        //前缀和，p[0]=0
        long[] p = new long[N+1];
        for (int i = 0; i < N; ++i)
            p[i+1] = p[i] + (long) A[i];
        int ans = N+1;
        //维护一个单调递增队列，队列存储的是前缀和的下标
        Deque<Integer> q = new LinkedList<>();
        for (int y = 0; y < p.length; ++y) {
            //在遍历前缀和数组时先看有没有满足差值大于k的
            //如果当前值和队列首位的差值大于k，队首就可以弹出队列并更新ans结果值
            //弹出队首的值不影响最终结果
            while (!q.isEmpty() && p[y]-p[q.getFirst()]>=k)
                //注意，这里的区间长度不需要加一
                ans = Math.min(ans, y - q.removeFirst());
            //对于队尾大于当前值的也可以弹出单调队列（维护单调队列递增的性质）
            //且弹出的值对后续寻找最优值无影响
            while (!q.isEmpty() && p[y] <= p[q.getLast()])
                q.removeLast();
            q.addLast(y);
        }
        return ans < N+1 ? ans : -1;
    }
}
