package algorithm.SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III  --前身 219、217
 * 给你一个整数数组nums和两个整数k和t，请你判断是否存在两个不同下标 i 和 j，
 * 使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 */
public class ContainsDuplicateIII220 {

    /**
     * 有滑动窗口和桶排序两种思路
     */
    /**
     *  滑动窗口 + 有序集合
     *  根据题意，对于任意一个位置 i（假设其值为 u），
     *  我们其实是希望在下标范围为 [max(0,i-k),i)内找到值范围在[u−t,u+t]的数。
     *  为什么使用TreeMap
     *  我们希望使用一个「有序集合」去维护长度为 k 的滑动窗口内的数，
     *  该数据结构最好支持高效「查询」与「插入/删除」操作-->所以选择红黑树
     * 查询：能够在「有序集合」中应用「二分查找」，快速找到「小于等于u的最大值」和「大于等于 u 的最小值」
     * （即「有序集合」中的最接近 u 的数）。
     * 插入/删除：在往「有序集合」添加或删除元素时，能够在低于线性的复杂度内完成（维持有序特性）。
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long u = (long) nums[i];
            // 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
            Long l = ts.floor(u);
            // 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
            Long r = ts.ceiling(u);
            if(l != null && u - l <= t) return true;
            if(r != null && r - u <= t) return true;
            // 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
            ts.add(u);
            if (i >= k) ts.remove((long) nums[i - k]);
        }
        return false;
    }

    /**
     * 同一种滑动窗口的做法
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            //找到窗口中可能存在的大于num[i]-t的最大值，但不能大于nums[i]+t，存在则返回true
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 桶排法变形--以x轴上每t个整数映射到一个桶位
     * 可以使用哈希表来存储映射关系--每个槽位最多只会存储一个数据
     */
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            // 目标桶已存在（桶不为空），说明前面已有 [u - t, u + t] 范围的数字
            if (map.containsKey(id)) {
                return true;
            }
            // 检查相邻的桶
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                // 移除下标范围不在 [max(0, i - k), i) 内的桶
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        //处理自然数的桶位映射
        if (x >= 0) {
            return x / w;
        }
        //处理负整数的桶位映射
        return (x + 1) / w - 1;
    }
}
