package algorithm.Stack;

import java.util.*;

/**
 * 496. 下一个更大元素 I
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。
 * 如果不存在，对应位置输出 -1 。
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 *     对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 *     对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 提示：
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 */
public class NextGreaterElement496 {

    /**
     * 503. 下一个更大元素 II 也是单调栈的问题
     * 556. 下一个更大元素 III是双指针问题，
     * 暴力法是逐个计算nums1中的每个元素值nums1[i]在nums2中对应位置的右边的第一个比nums1[i]大的元素值。
     * 这样的时间复杂度是O（n*m）
     */


    /**
     * 单调栈（单调递减栈）+哈希表解题
     * 解题思路：
     * 我们可以先预处理nums2,使查询nums1中的每个元素在nums2中对应位置的右边的第一个更大的元素值时不需要再遍历nums2。
     * 于是，我们将题目分解为两个子问题:
     * ●第1个子问题:如何更高效地计算nums2中每个元素右边的第-个更大的值--单调栈
     * ●第2个子问题:如何存储第1个子问题的结果--哈希表
     *
     * 特别的是这题可以不去维护索引，而直接在哈希表中维护 nums2[i]-->右边更大的值 的这样关系（自己做的就是维护的索引，需要嵌套数组查询一次）
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        HashMap<Integer, Integer> hasMap = new HashMap<>();
        int[] result = new int[nums1.length];
        for(int num : nums2) {
            //维护单调递减栈---栈中存放的是值而非索引（题目要求数组值独一无二）
            while(!stack.isEmpty() && stack.peekLast()<num){
                hasMap.put(stack.pollLast(), num);
            }
            stack.add(num);
        }
        for(int i = 0; i < nums1.length; i++)
            result[i] = hasMap.getOrDefault(nums1[i], -1);
        return result;
    }

    /**
     * 栈中和哈希表中维护的索引的做法（更麻烦）
     * 特别的是这题可以不去维护索引，而直接在哈希表中维护 nums2[i]-->右边更大的值 的这样关系（自己做的就是维护的索引，需要嵌套数组查询一次）
     */
    public int[] nextGreaterElementMy(int[] nums1, int[] nums2) {
        Deque<Integer> stack=new LinkedList<>();
        Map<Integer,Integer> map=new HashMap<>();
        int[]index=new int[nums2.length];
        for(int i=0;i<nums2.length;++i){
            map.put(nums2[i],i);
            while(!stack.isEmpty()&&nums2[i]>nums2[stack.peekLast()]){
                index[stack.peekLast()]=nums2[i];
                stack.pollLast();
            }
            stack.add(i);
        }
        while(!stack.isEmpty()){
            index[stack.peekLast()]=-1;
            stack.pollLast();
        }
        int []res=new int[nums1.length];
        for(int i=0;i<nums1.length;++i){
            res[i]=index[map.get(nums1[i])];
        }
        return res;
    }
}
