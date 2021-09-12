package algorithm.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 1248. 统计「优美子数组」
 * 给你一个整数数组 nums 和一个整数 k。
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中「优美子数组」的数目。
 * 示例 1：
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 */
public class NumberOfSubarray1248 {
    /**
     * 两种解法：
     * 1.滑动窗口
     * 2.前缀统计+哈希表
     */

    /**
     * 前缀统计+哈希表  like 560题
     * 时间空间均为O(N)
     */
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer,Integer> res=new HashMap<Integer,Integer>();
        int count=0;
        int sum=0;
        res.put(0,1);
        for(int num:nums){
            //统计前缀中奇数数字个数
            sum+=num%2;
            if(res.containsKey(sum-k)){
                count+=res.get(sum-k);
            }
            res.put(sum,res.getOrDefault(sum,0)+1);
        }
        return count;
    }

    /**
     * 使用数组代替hash表 数组下标+数组值代替在某些题目里可以起到hash表的作用
     * 而且数组可利用下标进行寻址（直接寻址），速度更快
     */
    public int numberOfSubarrays2(int[] nums, int k) {
        // 数组 prefixCnt 的下标是前缀和（即当前奇数的个数），值是前缀和的个数。
        int[] prefixCnt = new int[nums.length + 1];
        prefixCnt[0] = 1;
        // 遍历原数组，计算当前的前缀和，统计到 prefixCnt 数组中，
        // 并且在 res 中累加上与当前前缀和差值为 k 的前缀和的个数。
        int res = 0, sum = 0;
        for (int num: nums) {
            sum += num & 1;
            //这里由于k>0所以 prefixCnt[sum]++操作可以放在if条件语句前面也可以放在后面
            prefixCnt[sum]++;
            if (sum >= k) {
                res += prefixCnt[sum - k];
            }
        }
        return res;
    }

    /**
     * 滑动窗口解法
     * 时间O（N） 空间O（1）
     */
    public int numberOfSubarrays3(int[] nums, int k) {
        //oddCnt 记录窗口中奇数数字个数
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            //右指针先走，每遇到一个奇数则oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }
            //若当前滑动窗口[left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);
                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }
        }
        return res;
    }

    /**
     * 滑动窗口 -自己做的
     */
    public int numberOfSubarraysMy(int[] nums, int k) {
        int left=0,right=0,count=0,n=nums.length,total=0;
        while(right<n){
            if((nums[right]&1)==1) count++;
            if(count==k){
                int pivotLeft=left,pivotRight=right;
                while((nums[left]&1)==0){
                    left++;
                }
                while((right+1)<n&&(nums[right+1]&1)==0){
                    right++;
                }
                total+=(right-pivotRight+1)*(left-pivotLeft+1);
                left++;
                count--;
            }
            right++;
        }
        return total;
    }

}
