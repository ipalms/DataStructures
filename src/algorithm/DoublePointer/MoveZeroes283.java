package algorithm.DoublePointer;

import org.junit.Test;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class MoveZeroes283 {

    /**
     * 维护双指针---相似题：剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     */

    @Test
    public void test(){
        int []nums=new int[]{1,7,2,4,4,2,3,1};
        moveZeroesChange(nums);
        for(int n:nums){
            System.out.print(n+"\t");
        }
    }

    /**
     * 优化寻找0位置过程
     */
    public void moveZeroes(int[] nums) {
        int i=0,j=0;
        while(j<nums.length){
            //只有遇上0元素才能让两指针位置不同步
            if(nums[j]!=0){
                //执行替换操作,如果i==j没有必要，因为这证明前面的数均为非0数
                if(i!=j){
                    nums[i]=nums[j];
                    nums[j]=0;
                }
                //nums[j]!=0时，寻0指针可以前移
                i++;
            }
            j++;
        }
    }

    /**
     * 变形题---将1、2、3三种数字移动到数组尾部，且不该变其相应的顺序
     * 原理和移动0到尾部一致，但是需要整体拷贝罢了
     */
    public void moveZeroesChange(int[] nums) {
        int pre=0;
        for(int i=0;i<nums.length;++i){
            if(nums[i]!=1&&nums[i]!=2&&nums[i]!=3){
                //需要将[pre,i-1]拷贝到[pre+1,i]处，因为题目需要保证不改变原始数据的相对顺序
                if(i!=pre){
                    int tmp=nums[i];
                    //if (i - pre >= 0) System.arraycopy(nums, pre, nums, pre + 1, i - pre);
                    System.arraycopy(nums,pre,nums,pre+1,i-pre);
                    nums[pre]=tmp;
                }
                ++pre;
            }
        }
    }


    public void moveZeroesMy(int[] nums) {
        int i=0,j=0;
        while(j<nums.length){
            if(nums[j]!=0){
                swap(nums,i,j);
                ++i;
                //前指针找到下一个为0的索引
                while(i<j&&nums[i]!=0){
                    ++i;
                }
            }
            j++;
        }
    }

    public void swap(int[] nums, int i, int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
