package algorithm.Hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class ArrayIntersection349 {

    /**
     * 使用一个set集合
     * 时间、空间均为 O（N+M）
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer>set=new HashSet<>();
        for(int num:nums1){
            set.add(num);
        }
        int []res=new int[nums1.length];
        int t=0;
        for(int num:nums2){
            if(set.contains(num)){
                res[t++]=num;
                set.remove(num);
            }
        }
        return Arrays.copyOf(res,t);
    }

    /**
     * 排序加双指针
     * 时间O（N*log(N)+M*log(M)）
     * 空间O（1）
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1,length2)];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
