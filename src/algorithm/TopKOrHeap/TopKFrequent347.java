package algorithm.TopKOrHeap;


import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 提示：
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class TopKFrequent347 {
    /**
     * 哈希+小顶堆
     * 时间复杂度 o(n*logk)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //定义优先队列的插入元素规则
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>(k,(o1, o2) -> map.get(o1)-map.get(o2));
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(pq.size()<k){
                pq.offer(entry.getKey());
            }else if(entry.getValue()>map.get(pq.peek())){
                pq.poll();
                pq.offer(entry.getKey());
            }
        }
        //pq.toArray() 返回的是Object[]数组
        int []res=new int[k];
        int i=0;
        for(int num:pq){
            res[i++]=num;
        }
        return res;
    }

    /**
     * 优先队列插入维护规则较第一种有变化
     */
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((m, n) -> m[1] - n[1]);
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    /**
     * 基于桶排序求解   O(N)时间复杂度
     */
    public int[] topKFrequent3(int[] nums, int k) {
        List<Integer> res = new ArrayList();
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new List[nums.length+1];
        for(int key : map.keySet()){
            // 获取出现的次数作为下标
            int i = map.get(key);
            if(list[i] == null){
                list[i] = new ArrayList();
            }
            //有记录就顺序加入
            list[i].add(key);
        }
        // 倒序遍历数组获取出现顺序从大到小的排列
        for(int i = list.length - 1;i >= 0 && res.size() < k;i--){
            if(list[i] == null) continue;
            //加入整个链表的所有元素
            //因为leetcode的测试用力保证答案唯一所以不会出现添加链表所有元素超出k个情况
            res.addAll(list[i]);
        }
        int i=0;
        int []res1=new int[k];
        for(int num:res){
            res1[i++]=num;
        }
        return res1;
    }
}
