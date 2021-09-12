package algorithm.PrefixSum;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 523. 连续的子数组和
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 *
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * 示例 2：
 *
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * 示例 3：
 *
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 *
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */
public class CheckSubarraySum523 {

    @Test
    public void test(){
        int []nums={1,3,2,3,6};
        System.out.println(sumOfK(nums,3));
    }

    /**
     * 前缀和+哈希表
     * 同余定理：
     * 如果两个整数m、n满足n-m能被k整除，那么n和m对k同余
     * 即 ( pre(j) - pre (i) ) % k == 0 则 pre(j) % k == pre(i) % k
     * 遍历过程：
     * 计算前缀和 pre( j ) % k
     * 当pre(j) % k 在哈希表中已存在，则说明此时存在 i 满足 pre(j) % k == pre(i) % k ( i < j )
     * HashMap里，已知Key，可以取到Value 即i的值， 最后 判断 j - i >= 2 是否成立 即可
     * 当 pre(j) % k 不存在于哈希表，则将 (pre(j) % k, j ) 存入哈希表
     * 时间复杂度：O(m)，其中m是数组nums的长度。需要遍历数组一次。
     * 空间复杂度：O(min(m,k))，其中m是数组nums的长度。
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        //初始化，如果某个前缀和除k后余0，且之前没有除k余0的前缀和放入，那么从map中取到的就是这个（0，-1）
        //例子 [3,6,1], k =3
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    /**
     * 变形题--同样用到了前缀和+哈希表以及同余定理
     * 求数组中一段连续的子数组和为k的序列个数
     * 数组元素均为正整数，要求子数组元素个数至少为1
     */
    public int sumOfK(int []nums, int k) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //初始化，如果某个子序列除k余数为0，且之前没有除k余0的前缀和放入，那么从map中取到的就是这个（0，1）
        map.put(0, 1);
        int sum=0;
        for (int num : nums) {
            sum=(sum+num)% k;
            if (map.containsKey(sum)) ans += map.get(sum);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }



    /**
     * 超时不通过
     */
    public boolean checkSubarraySum1(int[] nums, int k) {
        if(nums.length<2) return false;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int sum=0;
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i-1]==0&&nums[i]==0) return true;
            sum+=nums[i];
            map.put(sum,i);
            for(int j=1;j<=sum/k;j++){
                if(map.containsKey(sum-j*k)&&i-map.get(sum-j*k)>=2){
                    return true;
                }
            }
        }
        return false;
    }
}
