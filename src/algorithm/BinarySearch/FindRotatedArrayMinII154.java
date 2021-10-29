package algorithm.BinarySearch;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
 * 请你找出并返回数组中的 最小元素 。
 * 示例 1：
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * 进阶：
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 */
public class FindRotatedArrayMinII154 {

    /**
     * 该题解是在153题解基础上得到的，只需要多加分支处理nums[mid]==nums[right]的情况
     * 如果递归中数组中间部分出现重复值，那么完全可以使用153题解，因为无论如何都会找到最小值
     * 当右边界值和mid值相等时需要将右边界缩小 right--
     * 此操作不会使数组越界：因为迭代条件保证了 right > left >= 0；
     * 此操作不会使最小值丢失：假设nums[right]是最小值，有两种情况：
     * 若 nums[right]是唯一最小值：那就不可能满足判断条件nums[mid] == nums[right]，因为 mid < right（left != right 且 mid = (left + right)/2 向下取整）；
     * 若 nums[right]不是唯一最小值，由于 mid<right 而 nums[mid] == nums[right]，即还有最小值存在于 [left, right - 1]区间，因此不会丢失最小值。
     * 算法时间复杂度：平均时间复杂度 O(logN)，在特例情况下会退化到 O(N)（例如[1,1,1,1]）。
     */
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            // nums[mid]一定在左排序数组，则旋转点（最小数字）一定在[mid + 1, right]
            if(nums[mid]>nums[right]){
                left=mid+1;
                // nums[mid]一定在右排序数组，则旋转点（最小数字）一定在[left, mid]
            }else if(nums[mid]<nums[right]){
                right=mid;
            }else{
                //10111和11101这种。此种情况下nums[mid]==nums[right]
                //无法判断nums[mid]在哪个排序数组，无法判断旋转点（最小数字）在哪个区间
                //分不清到底是前面有序还是后面有序，此时right--即可。相当于去掉一个重复的干扰项。
                right--;
            }
        }
        return nums[right];
    }
}
