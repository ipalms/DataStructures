package algorithm.DynamicProgramming;

import java.util.Arrays;

/**
 * 1235. 规划兼职工作
 * 你打算利用空闲时间来做兼职工作赚些零花钱。
 * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
 * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，
 * 请你计算并返回可以获得的最大报酬。
 * 注意，时间上出现重叠的 2 份工作不能同时进行。
 * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 * 示例 1：
 * 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * 输出：120
 * 解释：
 * 我们选出第 1 份和第 4 份工作，
 * 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
 * 示例 2：
 * 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * 输出：150
 * 解释：
 * 我们选择第 1，4，5 份工作。
 * 共获得报酬 150 = 20 + 70 + 60。
 * 示例 3：
 * 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * 输出：6
 * 提示：
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 * */
public class JobScheduling1235 {
    public static void main(String[] args) {
        int []a=new int[]{1,2,3,3};
        int []b=new int[]{3,4,5,6};
        int []c=new int[]{50,10,40,70};
        System.out.println(jobScheduling(a,b,c));
    }

    /**
     * 数据排序+dp+二分查找
     * 这个题第一眼看上去和 1353.参加最多的会议 这种题比较像。
     * 这类有开始结束时间，最大化某个东西的一般是排序加贪心加堆。
     * 不过分析一下这个题目会发现，这里多了一个profit，相当于每个工作的权重就不一样了，
     * 导致贪心策略不适用了，因为后面可能有profit非常大的工作要优先选择。
     * 因此这里就需要用dp来解决这种前后状态有关联的问题。
     * 首先还是按照结束时间排序。
     * 然后定义dp[i]表示前i个工作可以得到的最大收益，分为以下两种情况转移：
     * 1、不参加第i份工作，那么dp[i]=dp[i-1]
     * 2、参加第i份工作，那么startTime[i] ~ endTime[i]这段时间就被占用了，因此前i-1份工作我们只能参加结束时间<=startTime[i]的那些，由于dp状态的单调递增特性(可选的工作越多，能获得的最大利益肯定越大)，我们只要从后往前找到第一个endTime[k]<=startTime[i]的k即可，即dp[i]=dp[k]+profit[i]。由于整个数组已经按照endTime排序了，这一过程可以用二分求上界来加速。
     * 综合的状态转移方程即dp[i] = Math.max(dp[i - 1], dp[k] + profit[i])
     * 时间复杂度：O(NlogN)
     * 链接：https://leetcode.cn/problems/maximum-profit-in-job-scheduling/solution/1235gui-hua-jian-zhi-gong-zuo-dper-fen-b-cgle/
     * */
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len=startTime.length;
        int [][]job=new int[len][3];
        for(int i=0;i<len;i++){
            job[i][0]=startTime[i];
            job[i][1]=endTime[i];
            job[i][2]=profit[i];
        }
        Arrays.sort(job,(a, b)->a[1]-b[1]);
        //细节：
        //dp数组定为n+1或n都行
        //定为n在检查二分查找到的任务的index，
        //还要再检查一下其job[index][1]<job[i][0]是否成立，成立再加上dp[index]（存在这种请况）
        //而定为n+1时，由于dp[0]=0。所以在job[index][1]<job[i][0]时index为0，dp[index]+job[i-1][2]不影响
        //但是定为n+1时，dp数组和job数组长度不一致，比较的条件和正常比较的条件有差异（要考虑job数组索引值别越界）
        int []dp=new int[len+1];
        for(int i=1;i<=len;i++){
            int left=0,right=i-1;
            while(left<right){
                int mid=left+(right-left+1)/2;
                // 这里mid和i都要-1然后做比较
                if(job[mid-1][1]>job[i-1][0]){
                    right=mid-1;
                }else{
                    left=mid;
                }
            }
            dp[i]=Math.max(dp[i-1],dp[left]+job[i-1][2]);
        }
        return dp[len];
    }

    /**
     * dp数组和job数组长度一致
     * */
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int[] dp = new int[startTime.length];
        int[][] job = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            job[i][0] = startTime[i];
            job[i][1] = endTime[i];
            job[i][2] = profit[i];
        }
        Arrays.sort(job, (a, b) -> (a[1] - b[1]));
        dp[0] = job[0][2];
        for (int i = 1; i < startTime.length; i++) {
            dp[i] = job[i][2];
            int left = 0;
            int right = i - 1;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                //这里就不需要-1的操作
                if (job[mid][1] <= job[i][0]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            //但是这里要判断一下
            if (job[left][1] <= job[i][0]) {
                dp[i] += dp[left];
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    /**
     * 除了二分外，内层寻找小于当前job[i][0]的任务还可以暴力的循环遍历
     * */
    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {
        int[] dp = new int[startTime.length];
        int[][] job = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            job[i][0] = startTime[i];
            job[i][1] = endTime[i];
            job[i][2] = profit[i];
        }
        Arrays.sort(job, (a, b) -> (a[1] - b[1]));
        for (int i = 0; i < startTime.length; i++) {
            int prev = 0;
            for (int j=i-1; j>=0; --j) {
                if (job[j][1] <= job[i][0]) {
                    prev = dp[j];
                    break;
                }
            }
            dp[i] = Math.max(i > 0 ? dp[i-1] : 0, prev + job[i][2]);
        }
        return dp[dp.length - 1];
    }
}
