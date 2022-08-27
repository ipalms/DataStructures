package algorithm.Greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *1353. 最多可以参加的会议数目
 * 给你一个数组 events，其中 events[i] = [start day_i, end day_i] ，表示会议 i 开始于 start day_i ，结束于 end day_i 。
 * 你可以在满足 start day_i <= d <= end day_i 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 * 请你返回你可以参加的 最大 会议数目。
 * 示例 1：
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 * 示例 2：
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 * 提示：
 * 1 <= events.length <= 105
 * events[i].length == 2
 * 1 <= start day_i <= end day_i <= 105
 * */
public class MaxEvents1353 {

    /**
     *  贪心思想：
     * 我在这补充下我这个为什么要根据结束时间升序排序。
     * 假设已经安排了一些会议，我们现在再安排的会议肯定是不能和之前冲突的对吧。有这么几种选择
     * 1.安排剩下的里面，具有最早开始时间的
     * 2.安排剩下的里面，持续时间最短的
     * 3.安排剩下的里面，具有最早结束时间的。
     * 选择第一种，极端情况是开始很早，持续时间很长。
     * 选择第二种，持续时间很短，开始很晚。
     * 选择第三种最早结束时间，可以理解为最早开始时间+持续时间也最短，所以按照最早结束时间升序排序就是这么来的。
     *
     * 或者这么说，哪个会今天要结束了，我一定得去参加这个，因为别的会，改天能再参加啊。
     * 但是这个会今天没了就没了，所以说这个会我一定要参加才能保证参加的会最多，这就是贪心。
     * */

    /**
     *  数据排序+贪心+优先队列
     *  贪心加优先队列筛选范围
     *  在选择结束时间早的先分配的基础上，也要判断区间的可行性，也就是根据右端点选择区间分配，需要用到优先队列，时间复杂度 O(nlogn)
     *  优先队列，小顶堆会将start相同，end最小的放置堆顶。每次选择堆顶元素就能确定全局最优
     *  本方法和O(N^2)的不同在于使用变量day去维护那天去选择
     *  利用优先队列加入start 大于day的会议 以及筛除end小于 day的会议
     *
     *  而不是确立一个会议后，逐个试探会议时间范围内的 day 是否已经没有被别的会议使用
     * */
    public int maxEvents2(int[][] events) {
        //首先排序：开始时间小的在前。这样是方便我们顺序遍历，把开始时间一样的都放进堆
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
        //小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //结果、开始时间、events下标、有多少组数据
        int res = 0, day = 1, i = 0, n = events.length;
        while (i < n || !pq.isEmpty()) {
            //将start相同的会议都放进堆里
            while (i < n && events[i][0] == day) {
                pq.offer(events[i++][1]);
            }
            //pop掉当前天数之前的
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }
            //顶上的就是要参加的（end最小会议在堆顶--参加）
            if (!pq.isEmpty()) {
                pq.poll();
                res++;
            }
            day++;
        }
        return res;
    }


    /**
     * https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/solution/chun-cui-de-tan-xin-mei-yong-you-xian-dui-lie-dai-/
     * O(N^2)做法  --贪心+set（加范围性的测试可选时间），但是部分用例过不了(当给定的区间范围很大的时候)，如[1,100000]
     * */
    public int maxEvents(int[][] events) {
        Set<Integer> set = new HashSet<>();
        //
        Arrays.sort(events, (first, second) -> first[1]==second[1]?
                first[0]-second[0]:first[1]-second[1]);

        for(int[] event: events) {
            for(int i = event[0]; i<=event[1]; i++)
                if(set.add(i)) break;
        }
        return set.size();
    }

    public int maxEventsMy(int[][] events) {
        Arrays.sort(events,(a,b)->a[0]-b[0]);
        int day=1,count=0,i=0,len=events.length;
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
        while(i<len||!pq.isEmpty()){
            while(i<len&&events[i][0]==day){
                pq.offer(events[i][1]);
                i++;
            }
            while(!pq.isEmpty()&&pq.peek()<day){
                pq.poll();
            }
            if(!pq.isEmpty()){
                pq.poll();
                count++;
            }
            day++;
        }
        return count;
    }
}
