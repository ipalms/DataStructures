package algorithm.TopKOrHeap;

import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */
public class MedianFinder295 {

    /**
     * 对顶堆：
     * 维护两个优先队列--分别是最大堆存储数据流中前一半中较小的数字和最小堆存储数据流中后一半最大数字
     * 在总的流入数据量为奇数时，大顶堆多保存一个数
     *
     * 进阶考量--处理方式
     * 数值在非负整数，并且不是很大的时候，可以用数组记录每一个元素出现的次数、以及总的从数据流里所有元素的个数。
     * 思想来自桶排序，或者称为哈希。
     * 在需要计算中位数的时候，以线性方式去遍历计数数组。
     * 如果99%以上都是0到100的数，大于100的数一定不是中位数，把大于100的数单独记成一个数，例如 101
     * 并按上面的处理方式一样
     */
    int total=0;
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    public MedianFinder295(){
        maxHeap=new PriorityQueue<Integer>((a,b)->b-a);
        minHeap=new PriorityQueue<Integer>((a,b)->a-b);
    }

    public void addNum3(int num) {
        maxHeap.offer(num);
        ++total;
        if(total%2==0){
            minHeap.offer(maxHeap.poll());
        }else if(!minHeap.isEmpty()&&maxHeap.peek()>minHeap.peek()){
            maxHeap.offer(minHeap.poll());
            minHeap.offer(maxHeap.poll());
        }
    }

    //还可以根据两个堆的长度来维系两个堆的长度
    public void addNum(int num) {
        total += 1;
        //让两个优先队列自动维护插入过程
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
        if ((total & 1) != 0) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public void addNum2(int num) {
        total++;
        //不等说明此次应该向小顶堆输入一个数
        if(minHeap.size() != maxHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        } else {  //相等说明此次应该向大顶堆输入一个数
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
    }

    public void addNumMy(int num) {
        total++;
        //主动判断在那个队列插入元素，因为自己判断了所以平均移动次数要小于后两种，更快一点
        if(maxHeap.size()<(total+1)/2){
            if(maxHeap.size()==0||num<=minHeap.peek()){
                maxHeap.offer(num);
            }else{
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }
        }else{
            if(num<maxHeap.peek()){
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }else{
                minHeap.offer(num);
            }
        }
    }


    public double findMedian() {
        if(total%2==0){
            return (double)(minHeap.peek()+maxHeap.peek())/2.0;
        }
        return (double)maxHeap.peek();
    }
}
