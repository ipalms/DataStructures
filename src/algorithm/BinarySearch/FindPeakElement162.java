package algorithm.BinarySearch;

/**
 * 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 提示：
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？
 */
public class FindPeakElement162 {

    /**
     * 二分做法 while(left<=right)
     * 首先要注意题目条件，在题目描述中出现了 nums[-1]=nums[n] = -∞，
     * 这就代表着只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
     */
    public int findPeakElementMy(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(mid!=0&&nums[mid]<nums[mid-1]){
                right=mid-1;
            }else if(mid!=nums.length-1&&nums[mid]<nums[mid+1]){
                left=mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分做法 while(left<right)
     */
    public int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            //此题由mid向上还是向下取舍而有两种解法
            if (nums[mid] > nums[mid+1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 顺序遍历 O（N）
     */
    public int findPeakElement3(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
}
