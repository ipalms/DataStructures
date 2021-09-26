package algorithm.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeInterval56 {

    /**
     * 变形体57插入区间
     */

    /**
     * 我的思路--排序+二分查找
     * 排序start 和 end数组，按照end索引处值查找start对应索引
     */
    public int[][] mergeMy(int[][] intervals) {
        int n=intervals.length;
        int []start=new int[n];
        int []end=new int[n];
        for(int i=0;i<n;++i){
            start[i]=intervals[i][0];
            end[i]=intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        List<int[]> res=new ArrayList<int[]>();
        int i=0,j=0;
        while(i<n-1){
            //输出情况
            if(end[i]<start[i+1]){
                res.add(new int[]{start[j],end[i]});
                ++i;
                j=i;
            }
            i=binarySearch(start,j,n-1,end[i]);
        }
        res.add(new int[]{start[j],end[i]});
        // int [][]result=new int[res.size()][2];
        // for(int k=0;k<result.length;++k){
        //     result[k][0]=res.get(k)[0];
        //     result[k][1]=res.get(k)[1];
        // }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 二分查找start数组中最后一个小于target（end[i]）的数
     */
    private int binarySearch(int []start, int left, int right, int target){
        while(left<right){
            int mid=left+(right-left+1)/2;
            if(start[mid]>target){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return left;
    }

    /**
     * 官解思路  排序
     * 只用到了start序列排序的结果
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        //Arrays.sort(intervals, (v1, v2) -> Integer.compare(v1[0],v2[0]));
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        //结果数组的尾元素索引
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        //拷贝新的结果
        return Arrays.copyOf(res, idx + 1);
    }
}
