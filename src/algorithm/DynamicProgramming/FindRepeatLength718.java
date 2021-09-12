package algorithm.DynamicProgramming;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例：
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * 提示：
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class FindRepeatLength718 {

    /**
     * 动态规划：
     * 令 dp[i][j]表示 A[i:]和 B[j:]的最长公共前缀，那么答案即为所有dp[i][j]中的最大值。
     * 如果 A[i] == B[j]，那么dp[i][j] = dp[i + 1][j + 1] + 1，否则 dp[i][j] = 0。
     * 这里借用了 Python 表示数组的方法，A[i:] 表示数组 A 中索引 i 到数组末尾的范围对应的子数组。
     * 时间复杂度：O(N×M)。
     * 空间复杂度：O(N×M)。
     */
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    /**
     * 动态规划二
     * 从前往后的状态转移方程：dp[i][j] = dp[i - 1][j - 1] + 1
     */
    public int findLength3(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[n + 1][m + 1]; // dp[i][j]表示A的前i项与B的前j项的最长重复子数组长度
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans,dp[i][j]);
                }
            }
        }
        return ans;
    }


    /**
     * 滑动窗口版本：这里的窗口可以视作对齐后相较部分的长度
     * 可看作第一个数组不动，第二个数组并行驶过，并比较错开之后比较相同的部分
     */
    public int findLength2(int[] A, int[] B) {
        //较长的数组视为第二个数组
        return A.length < B.length ? findMax(A, B):findMax(B, A);
    }


    /**
     * 窗口滑动的重叠过程可以分为三个过程：
     *   1、下面子串进入，开始重叠的过程
     *   2、上下两子串完全重叠，中间过程
     *   3、下面的子串开始离开，重叠区域减少，离开过程
     * 时间复杂度：O((N+M)×min(N,M))。
     *
     * 空间复杂度：O(1)。
     */
    public int findMax(int[] A, int[] B) {
        int max = 0;
        int an = A.length, bn = B.length;
        //进入时候的处理--窗口扩大
        for(int len=1; len <= an; len++) {
            max = Math.max(max, maxLen(A, 0, B, bn - len, len));
        }
        //中间过程的处理--窗口不变
        for(int j=bn-an; j >= 0;j--) {
            max = Math.max(max, maxLen(A, 0, B, j, an));
        }
        //出去时的处理--窗口减小
        for(int i=1;i<an;i++) {
            max = Math.max(max, maxLen(A, i, B, 0, an - i));
        }
        return max;
    }

    public int maxLen(int[] a, int i, int[] b, int j, int len) {
        int count = 0, max = 0;
        for(int k = 0; k < len; k++) {
            if(a[i+k] == b[j+k]) {
                count++;
            } else if(count > 0) {
                max = Math.max(max, count);
                count = 0;
            }
        }
        return count > 0 ? Math.max(max, count) : max;
    }

}
