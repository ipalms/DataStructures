package algorithm.Array;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * 限制：
 * 2 <= n <= 100000
 */
public class SwordOfferFindRepeatNumber3 {
    /**
     * 只需要任意找出一个重复的数
     */
    @Test
    public void test(){
        int []a={2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber3(a));
    }

    /**
     * 修改原数组版本 一
     * 用数组下标作Key映射，正负记录两种状态，为负代表已经出现过
     * 时间O(N) 空间O(1)
     */
    public int findRepeatNumber(int[] nums) {
        for(int i:nums){
            int n=Math.abs(i);
            if(nums[n]<0) return n;
            nums[n]*=-1;
        }
        return 0;
    }

    /**
     * 修改原数组版本 二
     * 原地hash  --重点要掌握方法
     * 因为出现的元素值 < nums.size()，所以我们可以将见到的元素放到索引的位置
     * 如果交换时，发现索引处已存在该元素，则重复
     * 即使内部也有while循环，总的交换次数也不会超过N-1次，时间复杂度为O（N）
     * 时间O(N) 空间O(1)
     */
    public int findRepeatNumber1(int[] nums) {
        for(int i=0;i<nums.length;i++){
            //如果元素的值和下标不匹配，则将其交换至对的位置
            //要使用while循环进行交换，一致交换到nums[i]==i
            while(nums[i]!=i){
                //如果发现待交换的两个元素相同则直接返回
                if(nums[nums[i]]==nums[i])
                    return nums[i];
                //一次交换至少有一个位置的数值==索引值
                int tmp=nums[i];
                nums[i]=nums[tmp];
                nums[tmp]=tmp;
            }
        }
        return -1;
    }

    /**
     * 不改变原数组---使用哈希表
     * 时间O(N) 空间O(N)
     */
    public int findRepeatNumber2(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for (int num : nums) {
            //add操作存在返回值，为boolean型的
            //因为Set分支的特点是无序且不可重复，因此通过add方法添加相同的值时，第一次返回为true
            //后面再加相同元素的话就会返回false，因为元素重复
            //比直接用contains判断快的多
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 不改变原数组---使用数组充当哈希表
     */
    public int findRepeatNumber3(int[] nums) {
        int []ans=new int[nums.length];
        for (int num : nums) {
            ans[num]++;
            if(ans[num]>1) return num;
        }
        return -1;
    }
}
