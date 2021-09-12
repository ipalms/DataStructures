package algorithm.DoublePointer;

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
     * 维护双指针
     */
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

    /**
     * 优化寻找0位置过程
     */
    public void moveZeroes(int[] nums) {
        int i=0,j=0;
        while(j<nums.length){
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
}
