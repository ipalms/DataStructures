package algorithm.SlidingWindow;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 * 提示：
 * 1 <= nums.length <= 10^5
 * -104 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class MaxSlidingWindow239 {


    /**
     * 这题也可以看作是维护固定窗口长度
     */

    /**
     * 维护一段数据的最大值或者最小值可以采用
     * 单调队列(需要维护 Deque--LinkedList)、堆（优先队列 PriorityQueue）
     * 红黑树（TreeMap）、或者对数据排序
     */

    /**
     * 单调队列--双端队列（这里的队列存储的是元素的下标而非值）
     * 可以使用队列、单调队列的题目： 239、1438
     * 时间复杂度：O（N）
     * 空间复杂度：O（K）：队列中最多不会有超过k+1个元素，在元素成逆序的情况就会存储K+1个元素
     *
     * 由于我们需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标i和j，其中i在j的左侧（i<j）
     * 并且i对应的元素不大于j 对应的元素（nums[i]≤nums[j]），那么当滑动窗口向右移动时，只要i还在窗口中，
     * 那么j一定也还在窗口中，这是i在j的左侧所保证的。因此，由于nums[j]的存在，nums[i]一定不会是滑动窗口中的最大值了
     * 我们可以将nums[i]永久地移除--换句话说，队列中小于当前要加入的元素的数据都可以被移除
     * 因此我们可以使用一个队列存储所有还没有被移除的下标。
     * 在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组nums中对应的值是严格单调递减的。
     * 当滑动窗口向右移动时，我们需要把一个新的元素放入队列中。为了保持队列的性质，我们会不断地将新的元素与队尾的元素相比较，
     * 如果前者大于等于后者，那么队尾的元素就可以被永久地移除，我们将其弹出队列。
     * 我们需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。
     *
     * 由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。
     * 但与方法一中相同的是，此时的最大值可能在滑动窗口左边界的左侧
     * 并且随着窗口向右移动，它永远不可能出现在滑动窗口中了。
     * 因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        Deque<Integer> queue = new LinkedList<>();
        // 结果数组
        int[] result = new int[nums.length-k+1];
        // 遍历nums数组
        for(int i = 0;i < nums.length;i++){
            //保证从大到小如果前面数小则需要依次弹出，直至满足要求
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效---注意这里最多只有一个元素无效用if语句
            if(queue.peek() <= i-k){
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if(i+1 >= k){
                result[i+1-k] = nums[queue.peek()];
            }
        }
        return result;
    }


    /**
     * 时间复杂度：O(nlogn)，元素均为递增的话n个元素都在就n*logn，如果都是递减的话就n*logk(添加删除操作各执行一遍)
     * 空间坏的情况O（N）
     * 使用优先队列（大顶堆），记录数值和数值对映数组的下标索引
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int []res=new int[nums.length-k+1];
        PriorityQueue<int[]> queue=new PriorityQueue<>(k+1,(a, b)->b[0]-a[0]);
        //先维护k个长度的大顶堆
        for(int i=0;i<k;i++){
            queue.offer(new int[]{nums[i],i});
        }
        res[0]=queue.peek()[0];
        for(int i=0;i<nums.length-k;i++){
            queue.offer(new int[]{nums[i+k],i+k});
            //发现堆顶元素的下标不在窗口范围内就剔除掉堆顶
            //要被动删除，不能主动的调用queue.remove(nums[i])删除元素，这个删除操作是O（N）的
            while(queue.peek()[1]<=i){
                queue.poll();
            }
            res[i+1]=queue.peek()[0];
        }
        return res;
    }

    /**
     * 优先队列2
     */
    public int[] maxSlidingWindow4(int[] nums, int k) {
        Queue<int[]> queue=new PriorityQueue<>((a,b)->b[1]-a[1]);
        int[] result = new int[nums.length-k+1];
        int index=0;
        for(int i = 0;i < nums.length;i++){
            queue.add(new int[]{i,nums[i]});
            if(i+1>=k){
                while(i-queue.peek()[0]+1>k){
                    queue.poll();
                }
                result[index++]=queue.peek()[1];
            }
        }
        return result;
    }

    public int[] maxSlidingWindow5(int[] nums, int k) {
        int n=nums.length;
        PriorityQueue<Integer>queue=new PriorityQueue<>((a,b)->nums[b]-nums[a]);
        int []res=new int[n-k+1];
        for (int i=0;i<n;i++){
            queue.offer(i);
            if(i>=k-1){
                while(!queue.isEmpty()&&i-queue.peek()+1>k){
                    queue.poll();
                }
                res[i-k+1]=nums[queue.peek()];
            }
        }
        return res;
    }


    /**
     * TreeMap  用时最长
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int []res=new int[nums.length-k+1];
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int i=0;i<k;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        res[0]=map.lastKey();
        for(int i=0;i<nums.length-k;i++){
            int num=map.get(nums[i]);
            if(num>1){
                map.put(nums[i],num-1);
            }else{
                map.remove(nums[i]);
            }
            map.put(nums[i+k],map.getOrDefault(nums[i+k],0)+1);
            res[i+1]=map.lastKey();
        }
        return res;
    }

}
