package algorithm.BinarySearch;

import org.junit.Test;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * 示例 3：
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class FindRotatedArrayMin153 {
    @Test
    public void test(){
        int []r={3,1,2};
        System.out.println(findMax(r));
    }
    /**
     * 题目不能有重复元素
     * 旋转后的数组左右边界和中值大小关系,找最小值的优题解是与右边界进行比较（如果是找最大值，那么就应该与左边界进行比较）
     * 1.左值 < 中值, 中值 < 右值 ：相当于旋转n次，最小值在最左边，可以收缩右边界
     *         右
     *      中
     *  左
     * 2.左值 > 中值, 中值 < 右值 ：有旋转，最小值在左半边，可以收缩右边界
     *  左
     *          右
     *      中
     * 3.左值 < 中值, 中值 > 右值 ：有旋转，最小值在右半边，可以收缩左边界
     *      中
     *  左
     *          右
     */
    /**
     * 此二分参照33题的第一解--时间复杂度O(logN)
     * 二分是与左边界进行比较（需要考虑特殊情况--进行了n此旋转）
     * 使用的是 while(left<=right)
     */
    public int findMinMy(int[] nums) {
        int left=0,right=nums.length-1;
        if(nums[left]>nums[right]){
            while(left<=right){
                int mid=left+(right-left)/2;
                if(nums[mid]>nums[mid+1]){
                    return nums[mid+1];
                }else if(nums[mid]>=nums[0]){
                    left=mid+1;
                }else if(nums[mid]<nums[0]){
                    right=mid-1;
                }
            }
        }
        return nums[0];
    }

    /**
     * 二分是与右边界进行比较--使用右边界可以不用考虑使用左边界时要考虑的全部旋转特殊情况
     * 使用的是 while(left<right)
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;                /* 左闭右闭区间，如果用右开区间则不方便判断右值 */
        while (left < right) {                      /* 循环不变式，如果left == right，则循环结束 */
            int mid = left + (right - left) / 2;    /* 地板除，mid更靠近left */
            if (nums[mid] > nums[right]) {          /* 中值 > 右值，最小值在右半边，收缩左边界 */
                left = mid + 1;                     /* 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid */
            } else if (nums[mid] < nums[right]) {   /* 明确中值 < 右值，最小值在左半边，收缩右边界（不会出现中值等于边界情况）*/
                right = mid;                        /* 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处 */
            }
        }
        return nums[left];    /* 循环结束，left == right，最小值输出nums[left]或nums[right]均可 */
    }

    //寻找旋转数组最大的数，可以用于第33题的方法一
    public int findMax(int []nums){
        int left=0,right=nums.length-1;
        while(left<right){
            //向上取舍
            int mid=left+(right-left+1)/2;
            //mid>left，那么最大值出现在【mid,right】范围
            if(nums[mid]>nums[left]){
                left=mid;
            }else {
            //mid<left，nums[mid]一定不是最大值，那么最大值出现在【left,mid-1】范围,
                right=mid-1;
            }
        }
        return nums[left];
    }
}
