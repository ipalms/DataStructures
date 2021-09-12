package algorithm.DoublePointer;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，
 * 而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 */
public class RemoveElement27 {


    /**
     * 双指针：
     * 我们可以将数组分成「前后」两段：
     * 前半段是有效部分，存储的是不等于 val 的元素。
     * 后半段是无效部分，存储的是等于 val 的元素。
     */
    public int removeElementMy(int[] nums, int val) {
        int n=nums.length;
        int end=n-1;
        //start<=end 不能少等于号
        for(int start=0;start<=end;start++){
            if(nums[start]==val){
                //这里的逻辑是防止start和end都是val，交换后一个val跑到了前半部分
                while(end>=0&&nums[end]==val)
                    end--;
                if(end==-1) return 0;
                if(end<start) return end+1;
                //这里反正只需要前面的数是非val的就行，就只需要将end的值赋给start
                nums[start]=nums[end];
                end--;
            }
        }
        return end+1;
    }

    /**
     * 同第一种思路
     */
    public int removeElement1(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                nums[i] = nums[j];
                //i--是防止i和j索引对应值都是val，交换后一个val跑到了前半部分
                i--;
                j--;
            }
        }
        return j + 1;
    }

    /**
     * 将不是val的值向前拷贝
     */
    public int removeElement2(int[] nums, int val) {
        int ans = 0;
        for(int num: nums) {
            if(num != val) {
                nums[ans] = num;
                ans++;
            }
        }
        return ans;
    }
}
