package algorithm.BinarySearch;

import org.junit.Test;

/**
 * 33. 搜索旋转排序数组
 * 整数数组nums按升序排列，数组中的值互不相同 。
 * 在传递给函数之前，nums在预先未知的某个下标 maxIndex（0 <= maxIndex < nums.length）上进行了旋转，
 * 使数组变为[nums[maxIndex],nums[maxIndex+1],...,nums[n-1],nums[0],nums[1],...,nums[maxIndex-1]]（下标从0开始计数）。
 * 例如，[0,1,2,4,5,6,7]在下标3处经旋转后可能变为[4,5,6,7,0,1,2] 。
 * 给你旋转后的数组nums和一个整数target如果nums中存在这个目标值target，则返回它的下标，否则返回 -1 。
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 */
public class SearchRotatedArray33 {

    /**
     * 无论旋转几次，数组中有序的序列始终只有两个（正好旋转到一周可以看作一个长度为0和旋转后到数组）
     *
     * 直接在旋转数组中搜索目标值
     * 对于该数组，可以观察到对半分成两个子数组的情况下，必有一个子数组是呈现有序的
     * 那么就可以通过通过不断的划分子数组来找到目标target值
     * 如果target处于有序的部分就可以直接找到，处于无序的部分就等待下一次递归查询
     * 实质就是无限分割永远都在有序的序列里面找。
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            //中值就是
            if (nums[mid] == target) {
                return mid;
            }
            //前半部分有序---这里要取等号nums[start] <= nums[mid]
            //取等号即start==mid(因为题目要求不能有重复元素，那么一定在 [mid+1,end]范围内找下一个数)
            if (nums[start] <= nums[mid]) {
                //target在前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {//target在后半无须部分（这时移动start就行），等待下次循环再次分成有序无序两部分
                    start = mid + 1;
                }
            } else {//后半部分有序
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search3(int[] nums, int target) {
        int n=nums.length,left=0,right=n-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]>=nums[left]){
                if(nums[mid]>target&&target>=nums[left]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(nums[mid]<target&&target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test(){
        int []r={3,1,2};
        System.out.println(search(r,5));
    }
    /**
     * 题33、81、153、154一个类型
     * 将「旋转数组查找目标值」 转化成 「有序数组查找目标值」
     * 找到数组最值就行了--使用的是while(left<=right)
     */
    public int search(int[] nums, int target) {
        int left=0,right=nums.length-1;
        //定义数组中最大数所在的索引（默认为数组最后一个位置，即数组从k=0位置进行旋转了）
        int maxIndex=right;
        //判断数组是否从k=0位置进行旋转了[相当于判断数组是否旋转后还是顺序递增的]
        if(nums[left]>nums[right]){
            //二分找到旋转了的数组最大数所在索引
            while(left<=right){
                int mid=left+(right-left)/2;
                //当前数大于前一个数，找到数组最大数所在索引
                //（在排除掉顺序递增的数组情况下，mid+1永远不可能超过nums.length-1,因为mid是向下取舍的）
                if(nums[mid]>nums[mid+1]){
                    maxIndex=mid;
                    break;
                }else if(nums[mid]>=nums[0]){
                    left=mid+1;
                }else if(nums[mid]<nums[0]){
                    right=mid-1;
                }
            }
        }
        int l=0,r=nums.length-1;
        int res=-1;
        //按target的大小进行一次二分查找
        if(maxIndex==r){
            res=binarySearch(nums,l,r,target);
        }else if(target>=nums[maxIndex+1]&&target<=nums[r]){
            res=binarySearch(nums,maxIndex+1,r,target);
        }else if(target>=nums[l]&&target<=nums[maxIndex]){
            res=binarySearch(nums,l,maxIndex,target);
        }
        return res;
    }

    //修改了使用while(left<right) 去寻找旋转数组最大的数
    public int search1(int[] nums, int target) {
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
        int l=0,r=nums.length-1;
        int res=-1;
        //按target的大小进行一次二分查找
        if(left==r){
            res=binarySearch(nums,l,r,target);
        }else if(target>=nums[left+1]&&target<=nums[r]){
            res=binarySearch(nums,left+1,r,target);
        }else if(target>=nums[l]&&target<=nums[left]){
            res=binarySearch(nums,l,left,target);
        }
        return res;
    }

    public int binarySearch(int[] nums,int left,int right, int target){
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return -1;
    }

}
