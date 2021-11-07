package algorithm.DoublePointer;

import java.util.Arrays;

/**
 * 556. 下一个更大元素 III
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。
 * 如果不存在这样的正整数，则返回 -1 。
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * 示例 1：
 * 输入：n = 12
 * 输出：21
 * 示例 2：
 * 输入：n = 21
 * 输出：-1
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class NextGreaterElementIII556 {

    /**
     * 本题是需要处理数字到字符数组的转换的下一个排列（31题变形）
     */
    public int nextGreaterElement(int n) {
        //数字到字符数组
        char []nums=String.valueOf(n).toCharArray();
        int first=nums.length-2;
        //找到从后往前第一个非升序的元素的下标
        while(first>=0&&nums[first]>=nums[first+1]){
            --first;
        }
        //排除元素全部为降序的情况（从前往后看）
        if(first==-1) return -1;
        int second=nums.length-1;
        //找到第一个元素大于第一个索引所指元素的元素的下标
        while(second>0&&nums[second]<=nums[first]){
            --second;
        }
        //交换
        char tmp1=nums[first];
        nums[first]=nums[second];
        nums[second]=tmp1;
        //升序排序后面的字符数组
        //api调用写法  Arrays.sort(nums,first+1,nums.length);
        int left=first+1,right=nums.length-1;
        while(left<right){
            char tmp=nums[left];
            nums[left]=nums[right];
            nums[right]=tmp;
            ++left;
            --right;
        }
        //获得结果，注意对于数据溢出情况的判断（第7题，整数反转相似）
        int res=0,pre=0;
        for (char c : nums) {
            res = res * 10 + c - '0';
            if (res / 10 != pre) {
                return -1;
            }
            pre = res;
        }
        return res;
    }
}
