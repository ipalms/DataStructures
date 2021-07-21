package algorithm.TopKOrHeap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. 查找和最小的K对数字
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *
 * 提示:
 * 1 <= nums1.length, nums2.length <= 104
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 */
public class KSmallestPairs373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 大顶堆，比较器使用lambda表达式，更简洁
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(k, (o1, o2)->{
            return (o2.get(0) + o2.get(1)) - (o1.get(0) + o1.get(1));
        });
        // 遍历所有可能的集合
        for(int i = 0; i < Math.min(nums1.length, k); i++){
            for(int j = 0; j < Math.min(nums2.length, k); j++){
                // 剪枝，如果当前的两个数之和超过了堆顶元素，由于数组已经排序，后面的元素只会更大，因此无需继续遍历
                if(queue.size() == k && nums1[i]+nums2[j] > queue.peek().get(0) + queue.peek().get(1)){
                    break;
                }
                // 若比堆顶小，则弹出堆顶元素，把当前数对加入
                if(queue.size() == k){
                    queue.poll();
                }
                List<Integer> pair = new ArrayList<>();
                pair.add(nums1[i]);
                pair.add(nums2[j]);
                queue.add(pair);
            }
        }
        // 最后将元素弹出，倒序插入数组即可
        List<List<Integer>> res = new LinkedList<>();
        for(int i =0; i < k && !queue.isEmpty(); i++){
            res.add(0, queue.poll());
        }
        return res;
    }

    /**
     * 自己写的 原来优先队列存储的是数组（更快了一点） PriorityQueue<int[]>
     */
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> queue=new PriorityQueue<>(k+1,(a, b)->nums1[b.get(0)]+nums2[b.get(1)]-nums1[a.get(0)]-nums2[a.get(1)]);
        int min1=Math.min(nums1.length,k);
        int min2=Math.min(nums2.length,k);
        for(int i=0;i<min1;i++){
            for(int j=0;j<min2;j++){
                if(queue.size() == k && nums1[i]+nums2[j] > queue.peek().get(0) + queue.peek().get(1)){
                    break;
                }
                // 若比堆顶小，则弹出堆顶元素，把当前数对加入
                if(queue.size() == k){
                    queue.poll();
                }
                List<Integer> pair = new ArrayList<>();
                pair.add(nums1[i]);
                pair.add(nums2[j]);
                queue.add(pair);
            }
        }
        List<List<Integer>> res=new LinkedList<List<Integer>>();
        while(!queue.isEmpty()){
            res.add(0,queue.poll());
        }
        return res;
    }
}
