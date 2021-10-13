package algorithm.Math;

import java.util.Arrays;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class MajorityElement169 {

    /**
     * 摩尔投票--核心就是对拼消耗。
     * 从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1
     * 减到0就重新换个数开始计数，由于题目给出给定的数组总是存在多数元素（存在大于 ⌊ n/2 ⌋ 的元素），所以总能找到最多的那个
     */
    public int majorityElement(int[] nums) {
        int num=nums[0],count=1;
        for(int i=1;i<nums.length;++i){
            if(count==0){
                num=nums[i];
                count=1;
            }else if(nums[i]!=num){
                --count;
            }else if(nums[i]==num){
                ++count;
            }
        }
        return num;
    }

    //一思路不同写法
    public int majorityElement1(int[] nums) {
        int num=nums[0],count=1;
        for(int i=1;i<nums.length;++i){
            if(nums[i]==num){
                ++count;
            }else if(--count==0){
                num=nums[i+1];
            }
        }
        return num;
    }

    /**
     * 排序
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }
}
