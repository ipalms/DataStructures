package algorithm.TopKOrHeap;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378. 有序矩阵中第 K 小的元素
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * 示例 1：
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * 示例 2：
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 */
public class KthSmallestMatrix378 {

    /**
     * 对于矩阵寻找第k小可以采用23合并两个有序链表中的PriorityQueue的同样的思路
     * 维护每个数组的头结点，移除k-1个节点
     */

    /**
     * 值域二分查找：时间复杂度 O(N*log(right-left))
     * 由题目给出的性质可知，这个矩阵内的元素是从左上到右下递增的
     * 我们知道整个二维数组中 matrix[0][0]为最小值，matrix[n−1][n−1]为最大值，现在我们将其分别记作l和r。
     * 可以发现一个性质：任取一个数 mid满足l≤mid≤r，那么矩阵中不大于mid的数，肯定全部分布在矩阵的左上角。
     * 我们只要沿着这条锯齿线走一遍即可计算出这两个板块的大小，也自然就统计出了这个矩阵中不大于mid的数的个数了。
     * 可以这样描述走法（查找规则）：
     *      初始位置在 matrix[n - 1][0]matrix[n−1][0]（即左下角）；
     *      设当前位置为 matrix[i][j]，若matrix[i][j]≤mid，
     *      则将当前所在列的不大于mid的数的数量（即i+1）累加到答案中并向右移动，否则向上移动；不断移动直到走出格子为止。
     * 恰好可以使用二分查找规则（这里是二分答案而非二分下标）：
     * 不妨假设答案为x，那么可以知道l≤x≤r，这样就确定了二分查找的上下界。
     * 每次对于「猜测」的答案 mid，计算矩阵中有多少数不大于 mid ：
     * 如果数量不少于k，那么说明最终答案x不大于 mid；
     * 如果数量少于k，那么说明最终答案x大于 mid。
     *
     * 为什么最后返回的left（返回right也行，因为最后left==right）值一定存在于值域中？
     * 不必保证每次循环时每一个left或right都在矩阵中，但可以保证当left==right时，left一定在矩阵中，
     * 因为第k小的那个数始终在区间[left,right]中，只需要缩小区间的范围即可，
     * 当范围缩小到只有一个元素时,第k小的元素就等于left了。
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        //值域最大最小值
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            //定义二分中值，中值是向下约等的（mid直接取的值，而非中间索引）
            int mid = left + ((right - left) >> 1);
            //小于mid的数超过了k个，所以右边界值大于等于目标值，
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                //反之改动左边界的值
                left = mid + 1;
            }
        }
        //最终left==right后才能退出while循环，这个值就是目标值
        return left;
    }

    //O(N)时间复杂度
    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                //加上列向小于mid值的数量
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

    /**
     * 使用小顶堆的归并排序（通常是两个数组的归并排序，现在是n个数组的归并排序）
     * 时间复杂度： O(k*logN) 当k=n^2时复杂度为 n^2*logN
     */
    public int kthSmallest3(int[][] matrix, int k) {
        //定义小顶堆
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        //向优先队列中加入第一列值（每个值为每行最小）
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        //循环弹出k-1个数
        for (int i = 0; i < k - 1; i++) {
            //每次弹出最小的数
            int[] now = pq.poll();
            //如果这个数所在列不是矩阵最后一列，则可以继续向优先队列压入下一个数
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        //优先队列顶就是第k小的数
        return pq.poll()[0];
    }


    /**
     * 自己写的：使用优先队列--类似题373的写法
     * 复杂度较高--但由于剪枝了可能比用归并的好
     */
    public int kthSmallestMy(int[][] matrix, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<Integer>(k,(a, b)->b-a);
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                //剪枝
                if(queue.size()==k&&matrix[i][j]>queue.peek()){
                    break;
                }
                if(queue.size()==k){
                    queue.poll();
                }
                queue.offer(matrix[i][j]);
            }
        }
        return queue.peek();
    }
}
