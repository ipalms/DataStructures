package algorithm.BinarySearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class SearchRange34 {
    @Test
    public void test(){
        int []nums={5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange3(nums, 8)));
    }


    /**
     * 通过二分分别找到左右边界
     * left<right情况的二分
     */
    public int[] searchRange3(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};
        int left=0,left1=0,right=nums.length-1,right1=nums.length-1;
        //排除target极大极小情况
        if(target<nums[0]||target>nums[right]) return new int[]{-1,-1};
        //找左边界
        while(left<right){
            //根据后面的if else 的条件语句来决定是向mid值上取舍还是向下取舍（为了防止死循环的产生）
            int mid=left+(right-left)/2;
            if(nums[mid]<target){
                //nums[mid]<target，mid索引及左侧一定不包含目标值 下一轮搜索的区间是 [mid+1..right]
                left=mid+1;
            }else{
                //nums[mid]>=target，mid对应的索引可能为要输出的索引
                //因为当target不存在于数组之中，即使是大于target的对应索引也可能是要输出的左边界位置
                //当nums[mid]==target时，mid对应的索引可能恰为左边界，所以也要包含mid
                //故下一轮搜索的区间是 [left..mid]
                right=mid;
            }
        }
        //排除target不存在数组之中，这里判断了那么寻找右边界过程接不需要判断了
        if(nums[left]!=target){
            return new int[]{-1,-1};
        }
        //找右边界
        while(left1<right1){
            //left1=mid 情况下要向上取整避免死循环
            int mid=left1+(right1-left1+1)/2;
            if(nums[mid]>target){
                //nums[mid]>target，mid索引及右侧一定不包含目标值 下一轮搜索的区间是 [left..mid-1]
                right1=mid-1;
            }else{
                //nums[mid]<=target，mid对应的索引可能为要输出的索引
                //因为当target不存在于数组之中，即使是小于target的对应索引也可能是要输出右边界的位置
                //当nums[mid]==target时，mid对应的索引可能恰为右边界，所以也要包含mid
                //故下一轮搜索的区间是 [left..mid]
                left1=mid;
            }
        }
        return new int[]{left,left1};
    }

    /**
     * 通过二分分别找到左右边界
     * left<=right情况的二分
     */
    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        //找左边界
        int firstPosition = findFirstPosition(nums, target);
        //处于三种情况，target过大、过小、或者处于数组最大最小值之间但是不是数组内出现的元素会返回-1
        if (firstPosition == -1) {
            return new int[]{-1, -1};
        }
        //不需要再判断三种无值情况
        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }


    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // ① 不可以直接返回，应该继续向左边找，即[left, mid-1]区间里找
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                //此时nums[mid]>target，应该继续向左边找，即[left, mid-1]区间里找
                right = mid - 1;
            }
        }
        //此时left和right的位置关系是 [right, left]
        //当target过于大时，right位于nums.length-1，而left会移动到nums.length（right+1的位置）
        //当target过于小时，left位于0，而right会移动到-1（left+1的位置），那么left位置的数不等于target
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 只有这里不一样：不可以直接返回，应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                // 此时nums[mid]>target，应该继续向左边找，即 [left, mid-1] 区间里找
                right = mid - 1;
            }
        }
        //由于findFirstPosition 方法可以返回是否找到，这里无需单独再做判断
        return right;
    }

    /**
     * 时间复杂度没有满足O(log n)
     */
    public int[] searchRange(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                int i=mid,j=mid;
                while(i>0&&nums[i]==nums[i-1]){
                    i--;
                }
                while(j<nums.length-1&&nums[j]==nums[j+1]){
                    j++;
                }
                return new int[]{i,j};
            }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return new int[]{-1,-1};
    }
}
