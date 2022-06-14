package algorithm.Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253.会议室II
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间
 * intervals[i] = [starti, endi] ，为避免会议冲突，同时要考虑充分利用会议室资源，
 * 请你计算至少需要多少间会议室，才能满足这些会议安排。
 * 示例 1：
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 示例 2：
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 * */
public class CanAttendMeetingsII253 {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}};
        int[][] intervals3 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(canAttendMeetings(intervals));
        System.out.println(canAttendMeetings(intervals2));
        System.out.println(canAttendMeetings(intervals3));
    }


    public static int canAttendMeetings(int[][] intervals){
        if(intervals.length==0){
            return 0;
        }
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        PriorityQueue<Integer>minHeap=new PriorityQueue<>((a,b)->a-b);
        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }
        return minHeap.size();
    }
}
