package algorithm.DoublePointer;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 * 提示：
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 */
public class SwordOfferExchange21 {

    /**
     * 使用双指针可以不借助额外空间完成，借助额外空间完成该题可以使用分治合并的思路
     */

    /**
     * 快慢指针---思路和283题移动零一致
     */
    public int[] exchange(int[] nums) {
        int n=nums.length;
        if(n<2) return nums;
        int before=0;
        for(int i=0;i<nums.length;++i){
            if((nums[i]&1)==1){
                if(i!=before){
                    int tmp=nums[before];
                    nums[before]=nums[i];
                    nums[i]=tmp;
                }
                ++before;
            }
        }
        return nums;
    }

    /**
     * 首尾指针
     * 尾部找偶数，头部找奇数
     */
    public int[] exchange1(int[] nums) {
        int i = 0, j = nums.length - 1, tmp;
        while(i < j) {
            while(i < j && (nums[i] & 1) == 1) i++;
            while(i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

}
