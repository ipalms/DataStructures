package algorithm.DynamicProgramming;

import java.util.Arrays;

/**
 * 1884. 鸡蛋掉落-两枚鸡蛋
 * 给你 2 枚相同 的鸡蛋，和一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都 会碎 ，从 f 楼层或比它低 的楼层落下的鸡蛋都 不会碎 。
 * 每次操作，你可以取一枚 没有碎 的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：我们可以将第一枚鸡蛋从 1 楼扔下，然后将第二枚从 2 楼扔下。
 * 如果第一枚鸡蛋碎了，可知 f = 0；
 * 如果第二枚鸡蛋碎了，但第一枚没碎，可知 f = 1；
 * 否则，当两个鸡蛋都没碎时，可知 f = 2。
 * 示例 2：
 * 输入：n = 100
 * 输出：14
 * 解释：
 * 一种最优的策略是：
 * - 将第一枚鸡蛋从 9 楼扔下。如果碎了，那么 f 在 0 和 8 之间。将第二枚从 1 楼扔下，然后每扔一次上一层楼，在 8 次内找到 f 。总操作次数 = 1 + 8 = 9 。
 * - 如果第一枚鸡蛋没有碎，那么再把第一枚鸡蛋从 22 层扔下。如果碎了，那么 f 在 9 和 21 之间。将第二枚鸡蛋从 10 楼扔下，然后每扔一次上一层楼，在 12 次内找到 f 。总操作次数 = 2 + 12 = 14 。
 * - 如果第一枚鸡蛋没有再次碎掉，则按照类似的方法从 34, 45, 55, 64, 72, 79, 85, 90, 94, 97, 99 和 100 楼分别扔下第一枚鸡蛋。
 * 不管结果如何，最多需要扔 14 次来确定 f 。
 * 提示：
 * 1 <= n <= 1000
 */
public class TwoEggDrop1884 {

    // dp[i]依赖比 i 楼层小的状态转移，从 1 到 n 来转移最小操作次数。
    //在 0 到 i 的楼层抛第一枚鸡蛋，分两种情况：
    //第一枚鸡蛋在 j 层碎了，那么仅剩一枚鸡蛋，这时确定 f 就需要从第 1 层逐层向上扔到 j 层，共计 j 次
    //第一枚鸡蛋在 j 层没碎，那么需要考虑 i-j 层有两枚鸡蛋时的操作次数，再加上当前这次操作。
    //由于两种情况都可能出现，需保留最大值
    //dp[i] = Math.min(dp[i], Math.max(j-1, dp[i - j] )+ 1);
    public int twoEggDrop(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], Math.max(j, dp[i - j]+1));
            }
        }
        return dp[n];
    }

    /**
     * 思路二---通用解法--可以解决N个鸡蛋掉落最小 887
     * 记忆化+dp
     * https://leetcode-cn.com/problems/egg-drop-with-2-eggs-and-n-floors/solution/java-dong-tai-gui-hua-de-di-gui-xie-fa-t-6cvy/
     */
    int[][] memo;
    public int twoEggDrop1(int n) {
        memo = new int[n+1][3];
        return dp(n, 2);
    }

    public int dp(int n, int k){
        // base case
        if(n == 0){
            return 0;
        }
        if(k == 1){
            return n;
        }
        // 查表
        if(memo[n][k] != 0){
            return memo[n][k];
        }
        int res = Integer.MAX_VALUE;
        // 选一层楼扔鸡蛋
        for(int i = 1; i <= n; i++){
            res = Math.min(res, Math.max(dp(i-1, k-1), dp(n-i, k)) + 1);
        }
        memo[n][k] = res;
        return res;
    }
}
