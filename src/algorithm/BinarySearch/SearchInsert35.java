package algorithm.BinarySearch;


/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 * 输入: nums = [1], target = 0
 * 输出: 0
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 */
public class SearchInsert35 {


    //二分细节帖子：https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
    /**
     * 二分实质（left<right）：
     * 二分实际把待搜索区间分成两个部分，即根据看到的中间位置的元素的值nums[mid]可以把待搜索区间分为两个部分：
     * 一定不存在目标元素的区间：下一轮搜索的时候，不用考虑它；
     * 可能存在目标元素的区间：下一轮搜索的时候，需要考虑它。
     *
     * 二分判断条件与边界总结：
     * while(left<right)用在要找的数的性质复杂的时候，把区间分成两个部分，在退出循环以后才可以返回(多用于找范围)
     * 如果while判断条件为left<right，则是左边界或右边界保留mid索引要根据具体题意和【nums[mid]与target关系】去判断
     * 如果mid节点左侧一定不会出现目标元素就应该让 left=mid+1 right=mid(求目标元素左边界、第一个大于等于目标元素下标用到)
     * 如果mid节点右侧一定不会出现目标元素就应该让 left=mid right=mid-1(求目标元素右边界、最后一个不大于等于目标元素下标用到)
     * 对于target==nums[mid]的情况，一般随到带包含mid的分支去（即left=mid或right=mid分支）。
     * 还有当是left=mid情况时，取mid索引要向上取整避免死循环。循环退出时left==right
     *
     * while(left<=right)用在要找的数的性质简单的时候，把区间分成三个部分，在循环体内就可以返回（多用于直接找值）
     * 如果while判断条件为left<=right，在mid索引对应值大于target时需要改变右边界的值（right=mid-1）
     * 在mid索引对应值小于target时需要改变左边界的值（left=mid+1）
     * 如果最后没有元素存在于数组当中，退出循环后结果是left=right+1
     * 二分题目：4、33、34、35、69、81、153、154、240、278、367、852、1095  300
     * 其中378（值域二分） 也可使用二分
     * 还有前缀和搭配二分使用的题目，如209、1004
     * 287题二分思路是二分内遍历查找、209|1004是线性遍历然后对每个数二分查找（时间按复杂度均为O(n*logn)）
     */
    public int searchInsert1(int[] nums, int target) {
        int len = nums.length;
        //特殊判断(target过大的情况)
        if (nums[len - 1] < target) {
            return len;
        }
        //程序走到这里一定有nums[len - 1] >= target
        int left = 0;
        int right = len - 1;
        //在区间nums[left..right]里查找第1个大于等于target的元素的下标
        while (left < right) {
            //根据后面的if else 的条件语句来决定是向mid值上取舍还是向下取舍（为了防止死循环的产生）
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                //nums[mid]<target，mid索引及左侧一定不包含目标值 下一轮搜索的区间是 [mid+1..right]
                left = mid + 1;
            } else {
                //nums[mid]>=target，mid对应的索引可能为要输出的索引
                //因为当target不存在于数组之中，即使是大于target的对应索引也可能是要输出的位置
                // 下一轮搜索的区间是 [left..mid]
                right = mid;
            }
        }
        return left;
    }

    public int searchInsert(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=(left+right)>>>1;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return left;
    }
}
