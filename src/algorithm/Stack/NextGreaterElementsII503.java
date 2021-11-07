package algorithm.Stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数
 * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 */
public class NextGreaterElementsII503 {

    /**
     * 由于数组是循环的，所以找到一个数的下一个更大的元素理论上最多只需要遍历两遍
     * 两边循环维护单调栈即可
     */
    public int[] nextGreaterElementsMy(int[] nums) {
        int []res=new int[nums.length];
        Deque<Integer> stack=new LinkedList<>();
        for(int i=0;i<nums.length;++i){
            while(!stack.isEmpty()&&nums[i]>nums[stack.peekLast()]){
                res[stack.pollLast()]=nums[i];
            }
            stack.add(i);
        }
        //第二遍遍历不再向stack中加入元素
        for(int i=0;i<nums.length;++i){
            while(!stack.isEmpty()&&nums[i]>nums[stack.peekLast()]){
                res[stack.pollLast()]=nums[i];
            }
            if(stack.isEmpty()){
                return res;
            }
        }
        while(!stack.isEmpty()){
            res[stack.pollLast()]=-1;
        }
        return res;
    }

    /**
     * 同上面两边遍历的思想，但是可以利用取模的形式缩减重复代码
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        //首先对所有下标对应的值赋值为-1
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        //2*n-1  控制遍历两遍该循环数组
        for (int i = 0; i < n * 2 - 1; i++) {
            //取数组中的数时需要取模
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            //放入单调栈的下标要取模
            stack.push(i % n);
        }
        return ret;
    }
}
