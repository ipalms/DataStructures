package algorithm.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [2,3]
 */
public class FindDuplicates442 {

    /**
     * 原地修改  ---该题特点是在一遍循环之中就可以判断输出符合的数据
     * 同剑指offer 3
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res=new ArrayList<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            int x=Math.abs(nums[i]);
            if(nums[x-1]<0){
                res.add(x);
            }else{
                nums[x-1]*=-1;
            }
        }
        // for(int i=0;i<n;++i){
        //     if(nums[Math.abs(nums[i])-1]>0){
        //         res.add(Math.abs(nums[i]));
        //         nums[Math.abs(nums[i])-1]*=-1;
        //     }
        // }
        return res;
    }
}
