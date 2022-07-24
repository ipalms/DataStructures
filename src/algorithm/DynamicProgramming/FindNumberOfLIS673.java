package algorithm.DynamicProgramming;


import java.util.Arrays;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 * 注意 这个数列必须是 严格 递增的。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 提示:
 * 1 <= nums.length <= 2000
 * -106 <= nums[i] <= 106
 * */
public class FindNumberOfLIS673 {

    /**
     * DP解法（二分法就不看了）
     * 在朴素的LIS(300.最长递增子序列)问题中，我们定义f[1]为考虑以nums [i]为结尾的最长上升子序列的长度。
     * 最终答案为所有f[0.….(n - 1)]中的最大值。
     * 不失一般性地考虑f[i]该如何转移:
     *   a.由于每个数都能独自一个成为子序列，因此起始必然有f[i] =1;
     *   b.枚举区间(0, i)的所有数nums[j]，如果满足nums[j]<nums[i]，
     *   说明nums[i]可以接在nums[j]后面形成上升子序列，此时使用f[j]更新f[i]，即有f[i]= f [j] +1。
     *
     *
     * 回到本题，由于我们需要求解的是最长上升子序列的个数，因此需要额外定义g[i]为考虑以nums[i]结尾的最长上升子序列的个数。
     * 结合f[i]的转移过程，不失一般性地考虑g[i]该如何转移:
     *   a.同理，由于每个数都能独自一个成为子序列，因此起始必然有g[i]=1;
     *   b.枚举区间[0,i)的所有数nums[i]，如果满足numsli]<nums|i]，
     *   说明nums[i]可以接在nums[i]后面形成上升子序列，这时候对f[i]和f[j]＋1的大小关系进行分情况讨论:
     *   I:满足f[i]<f[j]+1:说明f[i]会被flj]+1直接更新，此时同步直接更新g[i]=g[j]即可;
     *   II:满足f[i]=f[j]+1:说明找到了一个新的符合条件的前驱，此时将值继续累加到方案数当中即有g[i]+=g[j]。
     * 在转移过程，我们可以同时记录全局最长上升子序列的最大长度max，最终答案为所有满足f[i]= max的g[i]的累加值。
     * */
    public int findNumberOfLIS(int[] nums) {
        int n=nums.length;
        int []dp=new int[n];
        int []cnt=new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(cnt,1);
        int max=1;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(nums[j]>nums[i]){
                    if(dp[j]<dp[i]+1){
                        dp[j]=dp[i]+1;
                        cnt[j]=cnt[i];
                    }else if(dp[j]==dp[i]+1){
                        cnt[j]+=cnt[i];
                    }
                }
            }
            max=Math.max(max,dp[i]);
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(dp[i]==max){
                count+=cnt[i];
            }
        }
        return count;
    }
}
