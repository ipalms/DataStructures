package algorithm.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 962. 最大宽度坡
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
 * 示例 1：
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 * 示例 2：
 *
 * 输入：[9,8,1,0,1,9,4,0,4,1]
 * 输出：7
 * 解释：
 * 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
 * 提示：
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 * */
public class MaxWidthRamp962 {

    /**
     * 962. 最大宽度坡  、1124. 表现良好的最长时间段
     * （这两题的单调栈和其他单调栈做法不同只有压栈的过程，没有弹栈的过程）
     * 其中1124. 表现良好的最长时间段 是搭配前缀和数组实现的
     * */
    public int maxWidthRamp(int[] nums) {
        Deque<Integer> stack=new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            if(stack.isEmpty()||nums[stack.peekLast()]>nums[i]){
                stack.addLast(i);
            }
        }
        int max=0;
        for(int i=nums.length-1;i>=0;i--){
            int n=nums[i];
            while(!stack.isEmpty()&&nums[stack.peekLast()]<=n){
                max=Math.max(max,i-stack.pollLast());
            }
        }
        return max;
    }
}
