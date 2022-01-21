package algorithm.DynamicProgramming;

/**
 * 887. 鸡蛋掉落
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * 示例 1：
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * 示例 2：
 * 输入：k = 2, n = 6
 * 输出：3
 * 示例 3：
 * 输入：k = 3, n = 14
 * 输出：4
 * 提示：
 * 1 <= k <= 100
 * 1 <= n <= 104
 */
public class NEggDrop887 {

    /**
     * https://leetcode-cn.com/problems/egg-drop-with-2-eggs-and-n-floors/solution/java-dong-tai-gui-hua-de-di-gui-xie-fa-t-6cvy/
     * https://leetcode-cn.com/problems/super-egg-drop/solution/ji-ben-dong-tai-gui-hua-jie-fa-by-labuladong/
     */

    /**
     * 函数定义:
     * dp(n，k)表示现在有n层楼需要验证，此时你手里有k个鸡蛋，返回此时的最小操作次数。
     * base case:
     *    如果没有楼层需要验证了(n == 0),那你不管有几个鸡蛋都不用操作了。
     *    如果你手里只有一-个鸡蛋了(k == 1 ),那你只能从1楼开始试: 1楼、 2楼、... n楼,共需要n次操作。
     * 状态转移:
     * 现在你手里有k个鸡蛋，接下来你要选一层楼扔鸡蛋，假设你选择了第i层楼,有两种情况:
     * 1.鸡蛋碎了。你知道你要确定的f一定在[0, i-1]范围内了，也就是需要验证的楼层数变成了i-1，
     * 除此之外你还损失了一个鸡蛋,只剩下k-1个鸡蛋了。
     * 2.鸡蛋没碎。你知道你要确定的f一定在[i, n]范围内了，接下来需要验证的楼层数变成了n-i，
     * 并且你没有损失鸡蛋，你还有k个鸡蛋。
     *
     * 题目的要求可以解释为:最坏情况下最少需要操作几次。最坏情况就是在这两种情况之间，选择那个需要操
     * 作次数最大的，再加上自己这一次扔鸡蛋的操作，状态转移方程也就是：
     * dp(n，k)=Math. max (dp(i-1, k-1)， dp(n-i, k))+1,
     * 这个值就是你选择从第i层楼扔鸡蛋，最坏情况下需要的操作次数。
     * dp(n，k) 需要的操作次数,是你在所有楼层中选择一个最坏情况下需要的操作次数最小的楼层扔鸡蛋。
     * 所以需要遍历所有楼层，找到Math. min(res, Math. max(dp(i-1，k-1)， dp(n-i, k)) + 1)。
     * 为了避免重复计算,加上备忘录。
     */
    int[][] memo;
    public int superEggDrop(int k, int n) {
        memo = new int[n+1][k+1];
        return dp(n, k);
    }

    /**
     * 本dp超时
     * dp过程可用二分优化能通过
     */
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
