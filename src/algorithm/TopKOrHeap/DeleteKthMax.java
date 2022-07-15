package algorithm.TopKOrHeap;


import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 补充题：https://www.nowcoder.com/question/next?pid=39959332&qid=2589594&tid=58397035
 * 小红拿到了一个数组 a，每次操作小红可以选择数组中的任意一个数减去 x，小红一共能进行 k 次。
 * 小红想在 k 次操作之后，数组的最大值尽可能小。请你返回这个最大值。
 * 输入例子1:
 * [7,2,1],3,2
 * 输出例子1:
 * 2
 * */
public class DeleteKthMax {

    /**
     * 贪心的看，每次对这个数组排序将第一个数减去x再放回是局部最优的
     * 总体来看也是成立的，所以用PriorityQueue维护优先队列
     * */

    /**
     * @param a int整型ArrayList
     * @param k int整型
     * @param x int整型
     * @return int整型
     */
    public int minMax (ArrayList<Integer> a, int k, int x) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (Integer num : a) {
            queue.offer(num);
        }
        for (int i = 0; i < k; i++) {
            Integer num1 = queue.poll();
            queue.offer(num1-x);
        }
        return queue.peek();
    }
}
