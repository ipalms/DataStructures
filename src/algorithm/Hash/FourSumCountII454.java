package algorithm.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，
 * 使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * 2
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class FourSumCountII454 {

    /**
     * 哈希表解法
     * 两个数组为一个集体进行数组和统计，将其中一组和放入hash表
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer,Integer> result=new HashMap<>();
        int n=nums1.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                result.put(nums1[i]+nums2[j],result.getOrDefault(nums1[i]+nums2[j],0)+1);
            }
        }
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(result.containsKey(-(nums3[i]+nums4[j]))){
                    count+=result.get(-(nums3[i]+nums4[j]));
                }
            }
        }
        return count;
    }

    /**
     * 暴力解法类似四数之和 要将四个数组分别进行排序
     */
}
