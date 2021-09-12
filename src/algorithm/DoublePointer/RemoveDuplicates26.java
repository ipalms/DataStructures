package algorithm.DoublePointer;

/**
 * 26. 删除有序数组中的重复项
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 * 提示：
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 */
public class RemoveDuplicates26 {

    /**
     * 快慢指针题目有：19、26、80、141、142
     * 其中80题是该题的直接变形
     * 使用快慢指针
     * 让快指针去遍历到整个数组，由于数组是升序的
     * 在快指针遍历到每一个不同于当前数的上个数是就将值赋给慢指针
     * 最终返回慢指针的位置即数组中的不重复数个数
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            //遇到了新的数字，赋值给慢指针并将慢指针指向加一
            if (nums[fast] != nums[slow - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    /**
     * 拓展到最多保留数组中保留k位相同数
     * 所以可以变形到第80题
     */
    public int removeKDuplicatesMy(int[] nums,int k) {
        int slow = 0;
        for (int fast=0;fast<nums.length;fast++) {
            //slow指针还没大于等于k 或者fast指针所指数字在slow指针往前k个所指没有出现过
            if (slow < k || nums[slow - k] != nums[fast])
                nums[slow++] = nums[fast];
        }
        return slow;
    }

    //自己写的冗余的判断
    public int removeDuplicatesMy(int[] nums) {
        int n=nums.length;
        if(n<2) return n;
        int left=1,right=1;
        while(right<n){
            //快指针所指值相等，且前面无该值，说明此值第一次出现
            if(nums[left]==nums[right]&&nums[left]!=nums[left-1]){
                left++;
            //快指针所指值不相等，且前面无该值，要给慢指针赋值
            }else if(nums[right]!=nums[left]&&nums[right]!=nums[left-1]){
                nums[left]=nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

}
