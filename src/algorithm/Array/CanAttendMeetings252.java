package algorithm.Array;


import java.util.Arrays;

/**
 * 252. 会议室I
 * 题目描述：给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间
 * [[s1,e1],[s2,e2],…] (si < ei)，请你判断一个人是否能够参加这里面的全部会议
 *
 * 示例：
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 */
public class CanAttendMeetings252 {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}};
        int[][] intervals3 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(canAttendMeetings(intervals));
        System.out.println(canAttendMeetings(intervals2));
        System.out.println(canAttendMeetings(intervals3));
    }


    //这题实质就是判断二维数组区间是否有交集
    //将二位数组按每行第一列元素排序
    //比较每行第一列元素是否大于上一行第二列元素
    public static boolean canAttendMeetings(int[][] intervals){
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int last=-1;
        for(int []a:intervals){
            if(a[0]<=last){
                return false;
            }
            last=a[1];
        }
        return true;
    }
}
