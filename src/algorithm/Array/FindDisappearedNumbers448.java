package algorithm.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 */
public class FindDisappearedNumbers448 {

    /**
     * 原地hash
     * 哈希函数的规则特别简单，那就是数值为i的数映射到下标为i-1的位置。
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res=new ArrayList<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            int x=nums[i];
            while(nums[x-1]!=x){
                nums[i]=nums[x-1];
                nums[x-1]=x;
                x=nums[i];
            }
        }
        for(int i=0;i<n;++i){
            if(i+1!=nums[i]){
                res.add(i+1);
            }
        }
        return res;
    }

    /**
     * 原地修改，道理和原地哈希一致，遍历到某个数将其对应索引下标值标识为不在给定范围内的数
     * 再一次遍历所有数时根据数值来进行判断
     * 相比原地哈希好处是不需要交换数据
     * 将出现了的数字对应索引值置为负数
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> res=new ArrayList<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            int x=Math.abs(nums[i]);
            //如果nums[x-1]已经为负数就没有必要将其置为负数了
            // （说明先前有重复的数字先将这个索引对应值置为负数）
            if(nums[x-1]>0){
                nums[x-1]*=-1;
            }
        }
        for(int i=0;i<n;++i){
            if(nums[i]>0){
                res.add(i+1);
            }
        }
        return res;
    }
}
