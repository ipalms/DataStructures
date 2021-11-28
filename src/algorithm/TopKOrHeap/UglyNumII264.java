package algorithm.TopKOrHeap;

import org.junit.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和 5 的正整数。
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * 提示：
 * 1 <= n <= 1690
 */
public class UglyNumII264 {

    @Test
    public void test(){
        nthUglyNumberDynamic(16);
    }

    /**
     * 什么是质因子：
     * 质因子（或质因数）在数论里是指能整除给定正整数的质数
     * 题目给定的质因子集合为2、3、5,所以丑数的质数集合不能来自除了这三个数以外的质因子（如7、13）
     */
    public int nthUglyNumberNew(int n) {
        int []dp=new int[n];
        dp[0]=1;
        int a=0,b=0,c=0;
        for(int i=1;i<n;++i){
            int a1=dp[a]*2,b1=dp[b]*3,c1=dp[c]*5;
            dp[i]=Math.min(a1,Math.min(b1,c1));
            if(dp[i]==a1) ++a;
            if(dp[i]==b1) ++b;
            if(dp[i]==c1) ++c;
        }
        return dp[n-1];
    }

    /**
     * 动态规划+三指针（多指针）
     * 「后面产生的丑数」都是基于「已有丑数」而来（使用「已有丑数」乘上「质因数」2、3、5
     * 因此，如果我们所有丑数的有序序列为 a1,a2,a3,...,an的话，
     * 序列中的每一个数都必然能够被以下三个序列（中的至少一个）覆盖：
     * 由丑数 * 2 所得的有序序列：1∗2、2∗2、3∗2、4∗2、5∗2、6∗2、8∗2 ...
     * 由丑数 * 3 所得的有序序列：1∗3、2∗3、3∗3、4∗3、5∗3、6∗3、8∗3 ...
     * 由丑数 * 5 所得的有序序列：1∗5、2∗5、3∗5、4∗5、5∗5、6∗5、8∗5 ...
     * 使用三个指针i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
     * dp[i]取每一轮中这三个序列值的最小数
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)。
     */
    public int nthUglyNumberDynamic(int n) {
        //dp 用作存储已有丑数（从下标1开始存储，第一个丑数为 1）
        int[] dp = new int[n + 1];
        dp[1] = 1;
        // 由于三个有序序列都是由「已有丑数」*「质因数」而来
        // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
        int i2 = 1, i3 = 1, i5 = 1;
        for (int i = 2; i <= n; i++) {
            // 由 dp[iX] * X 可得当前有序序列指向哪一位
            int a = dp[i2] * 2, b = dp[i3] * 3, c = dp[i5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            int min = Math.min(a, Math.min(b, c));
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过（不能使用 else if ）
            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;
            dp[i] = min;
        }
        return dp[n];
    }

    /**
     * 优先队列+哈希去重表
     * 1.起始先将最小丑数1放入队列
     * 2.每次从队列取出最小值x，然后将x所对应的丑数2x、3x和5x进行入队。
     * 3.对步骤2循环多次，第n次出队的值即是答案。
     * 4.为了防止同一丑数多次进队，我们需要使用数据结构Set来记录入过队列的丑数。
     * 注意使用long类型防止数据溢出
     * 时间复杂度：O（NlogN）
     * 空间复杂度：O（N）
     */
    public int nthUglyNumber(int n) {
        long []nums={2,3,5};
        PriorityQueue<Long> queue=new PriorityQueue<>();
        Set<Long> set=new HashSet<>();
        queue.offer(1L);
        set.add(1L);
        for(int i=1;i<=n;++i){
            long num=queue.poll();
            if(i==n) return (int)num;
            for(long x:nums){
                if(set.add(num*x)){
                    queue.offer(num*x);
                }
            }
        }
        return -1;
    }
}
