package algorithm.TopKOrHeap;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 480. 滑动窗口中位数
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；
 * 此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * 提示：
 * 你可以假设 k 始终有效，即：k 始终小于等于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class MedianSlidingWindow480 {

    @Test
    public void test() {
        int[] num = {-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648};
        System.out.println(Arrays.toString(medianSlidingWindow(num, 2)));
    }


    /**
     * 此题可以看作是295题的延申题
     * 对顶堆解决问题（维护一个大顶堆和一个小顶堆）
     * 相比与295不同的是，窗口在右移的时候要考虑删去（或者平衡）窗口左侧的值带来的影响
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        //小顶堆存后半截数
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //大顶堆存前半截数（或者比小顶堆多存入一个元素）
        //比较器直接返回o1-o2可能会遇到问题，如果T类型为Integer，那么意味着只有四个字节大小
        //那么如果o1一个为正数，一个为负数相差大于4字节就会造成数据溢出，那么就会排序不准。
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        //哈希表存储堆中本应要删除的的数
        HashMap<Integer, Integer> debt = new HashMap<>();
        //balance记录了小值堆和大值堆之间数字的差值 一轮操作中balance的值0、-2、2
        int i = 0, j = k - 1, index = 0, balance = 0;
        //初始化大顶堆|小顶堆
        for (int q = 0; q < k; q++) {
            maxHeap.offer(nums[q]);
        }
        for (int q = 0; q < k / 2; q++) {
            minHeap.offer(maxHeap.peek());
            maxHeap.poll();
        }
        res[index++] = insertRst(maxHeap, minHeap, k);
        while (++j < nums.length) {
            //清除窗口左侧元素操作
            balance += deleteElement(debt, nums, i++, maxHeap, minHeap);
            //添加窗口右侧元素操作
            balance += insertElement(nums, j, maxHeap, minHeap);
            //平衡操作
            makeBalance(debt, maxHeap, minHeap, balance);
            //提取本轮窗口的中位数
            res[index++] = insertRst(maxHeap, minHeap, k);
            balance = 0;
        }
        return res;
    }

    private int deleteElement(HashMap<Integer, Integer> debt, int[] nums, int i, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        int cur = nums[i];
        //并不实际删除元素，而是将要删除的元素记录在hash表中
        debt.put(cur, debt.getOrDefault(cur, 0) + 1);
        //判断这个元素在那个堆中
        return !maxHeap.isEmpty() && nums[i] <= maxHeap.peek() ? -1 : 1;
    }

    private int insertElement(int[] nums, int j, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (!maxHeap.isEmpty() && nums[j] <= maxHeap.peek()) {
            maxHeap.add(nums[j]);
            return 1;
        }
        minHeap.add(nums[j]);
        return -1;
    }

    //balance记录了此时两个堆不平等的情况，需要将其平衡到初始水平，
    //此时如果是正的，就从小堆中删除，加到大堆里，如果是负的，就反过来，
    //平衡完之后，只需要对欠债元素进行删除就可。
    //欠债元素必须先从maxHeap里进行删除，因为添加的时候也是优先添加到maxHeap
    //优先删除minHeap中的元素极有可能导致minHeap为空，从而导致添加中位数时出问题
    private void makeBalance(HashMap<Integer, Integer> debt, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int balance) {
        if (balance > 0)
            minHeap.offer(maxHeap.poll());
        else if (balance < 0)
            maxHeap.offer(minHeap.poll());
        //检测两个堆的堆顶元素是否是应该被删除的元素（体现了堆的被动删除操作）
        while (!maxHeap.isEmpty() && debt.getOrDefault(maxHeap.peek(), 0) > 0) {
            debt.put(maxHeap.peek(), debt.get(maxHeap.peek()) - 1);
            maxHeap.poll();
        }
        while (!minHeap.isEmpty() && debt.getOrDefault(minHeap.peek(), 0) > 0) {
            debt.put(minHeap.peek(), debt.get(minHeap.peek()) - 1);
            minHeap.poll();
        }
    }

    //取出窗口的中位数
    private double insertRst(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int k) {
        return (k & 1) == 1 ? (double) maxHeap.peek() : ((double) maxHeap.peek() + (double) minHeap.peek()) / 2;
    }


    /**
     * 思路二：二分查找+冒泡排序
     * 1.维护一个排过序的滑动窗口数组
     * 2.使用二分查找检索删除的索引
     * 3.将需要删除的值替换为需要插入的值
     * 4.使用局部冒泡排序保证数组顺序
     *
     * 时间复杂度： O（N^2）
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        int[] window = new int[k];
        //添加初始值
        for (int i = 0; i < k; i++) {
            window[i] = nums[i];
        }
        //快排维护初始窗口
        Arrays.sort(window);
        res[0] = getMid(window);
        //窗口滑动
        for (int i = 0; i < nums.length - k; i++) {
            //需要删除的数
            int index = search(window, nums[i]);
            //替换为需要插入的数
            window[index] = nums[i + k];
            //向后冒泡
            while (index < window.length - 1 && window[index] > window[index + 1]) {
                swap(window, index, index + 1);
                index++;
            }
            //向前冒泡
            while (index > 0 && window[index] < window[index - 1]) {
                swap(window, index, index - 1);
                index--;
            }
            res[i + 1] = getMid(window);
        }
        return res;
    }

    //交换
    private void swap(int[] window, int i, int j) {
        int temp = window[i];
        window[i] = window[j];
        window[j] = temp;
    }

    //求数组的中位数
    private double getMid(int[] window) {
        int len = window.length;
        if (window.length % 2 == 0) {
            //避免溢出
            return window[len / 2] / 2.0 + window[len / 2 - 1] / 2.0;
        } else {
            return window[len / 2];
        }
    }

    //最简单的二分查找
    private int search(int[] window, int target) {
        int start = 0;
        int end = window.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (window[mid] > target) {
                end = mid - 1;
            } else if (window[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
