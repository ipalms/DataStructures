package algorithm.UnionFind;

import java.util.HashMap;
import java.util.Map;

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
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestContinuousSubArray128 {

    /**
     * 并查集做法
     * 时间复杂度：迫近O(n)
     * 空间复杂度：O(n)
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        // 首次遍历，与邻居结盟
        UnionFind uf = new UnionFind(nums);
        for (int v : nums)
            uf.union(v, v + 1); // uf.union() 结盟

        // 二次遍历，记录领队距离
        int max = 1;
        for (int v : nums)
            max = Math.max(max, uf.find(v) - v + 1); // uf.find() 查找领队
        return max;
    }

    /**
     * 并查集的一些内容：
     * 并查集（Union-find Data Structure）是一种树型的数据结构。
     * 它的特点是由子结点找到父亲结点，用于处理一些不交集（Disjoint Sets）的合并及查询问题。
     * 即合并不相交的集合、并查询合并后集合内容
     *
     * 初始化（Initialize）：把每个点所在集合初始化为其自身。
     * 通常来说，这个步骤在每次使用该数据结构时只需要执行一次，无论何种实现方式，时间复杂度均为O(N)。
     *
     * 查找（Find）：确定元素属于哪一个子集。它可以被用来确定两个元素是否属于同一子集。
     *
     * 合并（Union）：将两个子集合并成同一个集合
     * 在将两个子集合并之前应该尝试使用Find方法判断两个元素是否属于同一集合。
     *
     * 路径压缩：为了加快查找速度，查找时将x到根节点路径上的所有点的parent设为根节点
     * 该优化方法称为压缩路径。
     * 由于路径压缩只在查询时进行，也只压缩一条路径，所以并查集最终的结构仍然可能是比较复杂的
     * 例如对于【2,5,6,7,8,9】数组，较大值为父节点情况下，在第一遍遍历时是无法做到路径压缩的
     *
     * 按秩合并：两个集合合并，应该优先于向树高较高的一方进行合并，这样合并后的树高高度不变
     * 使用这两种优化后，平均复杂度可视为其是一个常数（比O（logN）还快）。
     */
    static class UnionFind {
        //count记录的不同集合数，这题里没什么用
        private int count;
        private Map<Integer, Integer> parent; // (curr, leader)

        UnionFind(int[] arr) {
            parent = new HashMap<>();
            for (int v : arr)
                parent.put(v, v); // 初始时，各自为战，自己是自己的领队

            count = parent.size(); // 而非 arr.length，因可能存在同key的情况
        }

        // 结盟
        void union(int p, int q) {
            // 不只是 p 与 q 结盟，而是整个 p 所在队伍 与 q 所在队伍结盟
            // 结盟需各领队出面，而不是小弟出面
            Integer rootP = find(p), rootQ = find(q);
            if (rootP == rootQ) return;
            if (rootP == null || rootQ == null) return;
            // 结盟，子集合并
            parent.put(rootP, rootQ); // 谁大听谁
            // 应取 max，而本题已明确 p < q 才可这么写
            // 当前写法有损封装性，算法题可不纠结
            count--;
        }

        // 查找领队
        Integer find(int p) {
            if (!parent.containsKey(p))
                return null;
            // 递归向上找领队
            int root = p;
            while (root != parent.get(root))
                root = parent.get(root);
            //路径压缩：扁平化管理，避免日后找领队层级过深
            while (p != parent.get(p)) {
                int curr = p;
                p = parent.get(p);
                parent.put(curr, root);
            }
            return root;
        }
    }
}
