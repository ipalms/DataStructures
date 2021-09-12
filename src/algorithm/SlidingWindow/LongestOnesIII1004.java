package algorithm.SlidingWindow;

/**
 * 1004. 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * 示例 1：
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * 提示：
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1
 */
public class LongestOnesIII1004 {


    /**
     * 滑动窗口解法---思路和第424题一样
     * 只记录数字1能形成的最大窗口长度
     * 时间复杂度： O（N）
     * 空间复杂度： O（1）
     * 本题还可以使用前缀和 + 二分解决  但时间空间复杂度都变高
     */
    public int longestOnesMy(int[] nums, int k) {
        int left=0,right=0,maxAns=0,count=0;
        while(right<nums.length){
            if(nums[right]==1) count++;
            maxAns=Math.max(maxAns,count);
            if(right-left+1>maxAns+k){
                if(nums[left]==1) count--;
                left++;
            }
            right++;
        }
        return right-left;
    }

    /**
     * 滑动窗口二
     */
    public int longestOnes2(int[] A, int k) {
        int N = A.length;
        int res = 0;
        int left = 0, right = 0;
        //计算窗口区内0的数量
        int zeros = 0;
        while (right < N) {
            if (A[right] == 0)
                zeros ++;
            //窗口区内0的数量超过了k个，左指针缩小到窗口内0的数量为k
            while (zeros > k) {
                //更新最长连续为一字串长度
                res = Math.max(res,right-left);
                if (A[left++] == 0)
                    zeros --;
            }
            right++;
        }
        //更新最长连续为一字串长度
        return Math.max(res,right-left);
    }

    /**
     * 对于数组A的区间[left,right]而言，只要它包含不超过k个0
     * 所以可以计算含有零的个数的前缀和数组
     * 区间应该满足P[right]−P[left−1]≤k
     * 取前缀和时对取0个数组前缀和置为0
     */
    public int longestOnes3(int[] nums, int k) {
        int n = nums.length;
        int[] P = new int[n + 1];
        //计算含有零的个数的前缀和数组
        for (int i = 1; i <= n; ++i) {
            P[i] = P[i-1]+(1-nums[i-1]);
        }
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            int left = binarySearch(P, P[right + 1] - k);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    //找到前缀和数组中第一个但与等于target的数
    public int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int longestOnes4(int[] nums, int k) {
        int n = nums.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            rsum += 1 - nums[right];
            while (lsum < rsum - k) {
                lsum += 1 - nums[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
