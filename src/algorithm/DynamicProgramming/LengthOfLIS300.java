package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 10^4
 * 进阶：
 * 你可以设计时间复杂度为 O(n^2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LengthOfLIS300 {
    /**
     * 动态规划：
     * 定义dp[i]为考虑前i个元素，以第i个数字结尾的最长上升子序列的长度，注意nums[i]必须被选取。
     * 我们从小到大计算dp数组的值，在计算dp[i]之前，我们已经计算出dp[0…i−1]的值，则状态转移方程为：
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     * 即考虑往dp[0…i−1] 中最长的上升子序列后面再加一个nums[i]
     * 。由于dp[j]代表nums[0…j]中以nums[j]结尾的最长上升子序列，所以如果能从dp[j]这个状态转移过来，
     * 那么nums[i]必然要大于nums[j]，才能将nums[i]放在nums[j]后面以形成更长的上升子序列。
     * 最后，整个数组的最长上升子序列即所有dp[i]中的最大值。
     * LIS-length=max(dp[i]),其中0≤i<n
     * 为什么最长序列为dp中的最大值，而非dp最后一位？
     * 因为对于[1，2，5，4，6，4，5]这种序列来说，dp序列是从小到大再到小的，最大值取再外层循环遍历到6元素时
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)，需要额外使用长度为n的dp数组。
     */
     public int lengthOfLIS(int[] nums) {
        int max=0;
        int []dp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            //初始化dp数组，最小情况都为1
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    //状态转移关系，dp取当前dp[i]和已存在的dp[j]+1的较大值
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            //一轮循环完得到最终dp[i]后维护这一段中的最长递增子序列
            max=Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 贪心+二分
     * 时间复杂度：O(NlogN)，遍历数组使用了O(N)，二分查找法使用了O(logN)。
     * 空间复杂度：O(N)，开辟有序数组 tail 的空间至多和原始数组一样。
     * 题解链接：
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        //tail数组的定义：长度为i+1的上升子序列的末尾最小是几
        //数组tail也是一个严格上升数组
        int[] tail = new int[len];
        // 遍历第 1 个数，直接放在有序数组 tail 的开头
        tail[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;
        for (int i = 1; i < len; i++) {
            //遍历到的数比tail数组实际有效的末尾的那个元素还大，最长序列可以直接加一
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                //使用二分查找法，在有序数组 tail 中
                //找到第1个大于等于 nums[i] 的元素，尝试让那个元素更小
                //假设找到的位置为k，那么肯定有k个数是严格小于num[i]
                //所以将num[i]插入k索引处操作是合理的（符合tail数组的定义）
                int left = 0;
                int right = end;
                while (left < right) {
                    // 选左中位数不是偶然，取决与后面的
                    int mid = left + ((right - left) >>> 1);
                    //因为找的是第一个大于或者等于nums[i]的元素，
                    if (tail[mid] < nums[i]) {
                        //中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第1个大于等于nums[i]的元素
                tail[left] = nums[i];
            }
            // 调试方法
            printArray(nums[i], tail);
        }
        //此时end 是有序数组tail最后一个元素的索引，题目要求返回的是长度，因此+1后返回
        end++;
        return end;
    }

    // 调试方法，以观察是否运行正确
    private void printArray(int num, int[] tail) {
        System.out.print("当前数字：" + num);
        System.out.print("\t当前 tail 数组：");
        int len = tail.length;
        for (int i = 0; i < len; i++) {
            if (tail[i] == 0) {
                break;
            }
            System.out.print(tail[i] + ", ");
        }
        System.out.println();
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        int lengthOfLIS =lengthOfLIS2(nums);
        System.out.println("最长上升子序列的长度：" + lengthOfLIS);
    }
}
