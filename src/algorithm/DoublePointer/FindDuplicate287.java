package algorithm.DoublePointer;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3：
 * 输入：nums = [1,1]
 * 输出：1
 * 示例 4：
 * 输入：nums = [1,1,2]
 * 输出：1
 * 提示：
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 */
public class FindDuplicate287 {

    /**
     * 142环形链表II的变形--即在数组回环时还要找到那个回环点（重复的数）
     * 十分相似的一题   剑指offer03(使用数组hash、原地哈希)
     */

    /**
     * Floyd 判圈算法
     * 快慢指针
     * 注意题目数据范围为1~n  数组长度为N+1  不能修改原数组  空间要求O(1)
     * 相当于数组的下标作元素映射的自变量，因为存在重复数，所以必然有几个下标指向同一个数，自然形成了环
     * 即：数组下标 n 和数 nums[n] 建立一个映射关系 f(n)，
     * 一个重要点：【从一个确定的环外数出发】
     * 由于存在不包含重复数字的环，用快慢指针也没法没法从这种情况跳出去？【4，1，2】，【1，2】就是不包含重复数字的环
     * 由于数字从1开始计数，0这个位置不可能被其它位置的数字指向，所以0位置不可能在环内，所以从0开始遍历一定会指向某个包含重复数字的环，
     * 这个题还可以再改改，比如数字从0计数到n-1，那就得从n开始遍历了，少了哪个数就从哪开始遍历
     */
    public int findDuplicate(int[] nums) {
        int slow = 0,fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        //因为数组中一定有重复数，所以一定会跳出循环
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }

    public int findDuplicate1(int[] nums) {
        int slow=0,fast=0;
        //先让数据循环一遍再判断  使用do -- while结构
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (slow != fast);
        int pre=0;
        while(pre!=fast){
            fast=nums[fast];
            pre=nums[pre];
        }
        return fast;
    }

    /**
     * 二分查找：
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * 注意题目数据范围为1~n  数组长度为N+1  测试数据数组最小长度为2
     * 我们定义cnt[i]表示nums数组中小于等于i的数有多少个，假设我们重复的数是target
     * 那么[1,target−1]里的所有数满足cnt[i]≤i，[target,n]里的所有数满足cnt[i]>i，具有单调性。
     * 时间复杂度：O(nlogn)，其中n为nums数组的长度。二分查找最多需要二分O(logn)次
     * 每次判断的时候需要O(n)遍历nums数组求解小于等于mid 的数的个数，因此总时间复杂度为O(nlogn)。
     * 空间复杂度：O(1)O(1)。我们只需要常数空间存放若干变量。
     */
    public int findDuplicate2(int[] nums) {
        int n = nums.length;
        //左边界从1开始
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            //计算数组中元素小于mid的个数
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            //依据这个个数进行二分查找
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                //因为cnt[i]>i范围内每个解都有可能为重复数所以要保存
                ans = mid;
            }
        }
        return ans;
    }

}
