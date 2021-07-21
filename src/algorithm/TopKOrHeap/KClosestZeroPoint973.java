package algorithm.TopKOrHeap;
;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 973. 最接近原点的 K 个点
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * 提示：
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KClosestZeroPoint973 {
    /**
     * points二次数组是只有两列 第一列表示横坐标 第二列表示纵坐标
     * points.length 纵向长度
     * points[0].length 横向长度
     * 最大堆
     */
    public int[][] kClosest(int[][] points, int k) {
        int [][]res=new int[k][2];
        PriorityQueue<int[]> queue=new PriorityQueue<>(k,(a,b)->b[0]*b[0]+b[1]*b[1]-a[0]*a[0]-a[1]*a[1]);
        for(int i=0;i<points.length;i++){
            if(queue.size()<k){
                queue.offer(new int[]{points[i][0],points[i][1]});
            }else if(points[i][0]*points[i][0]+points[i][1]*points[i][1]<queue.peek()[0]*queue.peek()[0]+queue.peek()[1]*queue.peek()[1]){
                queue.poll();
                queue.offer(new int[]{points[i][0],points[i][1]});
            }
        }
        int i=0;
        for(int []q:queue) {
            res[i][0]=q[0];
            res[i][1]=q[1];
            i++;
        }
        return res;
    }

    /**
     * 暴力法  --快排
     */
    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparingInt((array) -> array[0] * array[0] + array[1] * array[1]));
        return Arrays.copyOf(points, K);
    }

    /**
     * 基于快速选择 O（N）时间复杂度
     * 这次要根据二元数组的平方和来将二元数组分成两部分（215是根据数组中的数）
     */
    Random rand = new Random();

    public int[][] kClosest3(int[][] points, int k) {
        int n = points.length;
        random_select(points, 0, n - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    public void random_select(int[][] points, int left, int right, int k) {
        //先随机一个标杆节点
        int pivotId = left + rand.nextInt(right - left + 1);
        int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
        swap(points, right, pivotId);
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            //以平方和来移动二元数组
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (dist <= pivot) {
                ++i;
                swap(points, i, j);
            }
        }
        ++i;
        swap(points, i, right);
        // [left, i-1] 都小于等于 pivot, [i+1, right] 都大于 pivot
        if (k < i - left + 1) {
            random_select(points, left, i - 1, k);
        } else if (k > i - left + 1) {
            random_select(points, i + 1, right, k - (i - left + 1));
        }
    }

    public void swap(int[][] points, int index1, int index2) {
        int[] temp = points[index1];
        points[index1] = points[index2];
        points[index2] = temp;
    }
}
