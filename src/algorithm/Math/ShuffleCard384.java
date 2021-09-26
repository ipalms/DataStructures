package algorithm.Math;

import java.util.Random;

/**
 * 384. 打乱数组
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * 实现 Solution class:
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 * 示例：
 * 输入
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * 输出
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 *
 * 提示：
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 5 * 104 次 reset 和 shuffle
 */
public class ShuffleCard384 {

    /**
     * 该题目的核心是每一种排列的出现可能性均为1/n!(全排列分之一)
     * 这样的含义可以理解为某个数出现在某个位置的概率为1/n
     * 经典的洗牌算法，思路是在后n-i-1张牌洗好的情况下，第n张牌随机与前n-1张牌的其中一张牌交换
     * 条件概率下：
     * 第一次：随机数到第n-1位置概率 ：1/5
     * 第二次 随机数到第n-2位置概率 ：4/5*1/4=1/5
     * 第三次等等依次类推
     */
    //由于需要返回原数组，所以需要两个数组
    int []nums;
    int []copy;
    public ShuffleCard384(int[] nums) {
        this.nums=nums;
        copy=new int[nums.length];
        System.arraycopy(nums,0,copy,0,nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return copy;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random ran=new Random();
        for(int i=nums.length-1;i>0;--i){
            //随机出一个符合范围的数,交换两个位置的元素,再不断的缩小范围
            int j=ran.nextInt(i+1);
            if(i!=j){
                int tmp=nums[i];
                nums[i]=nums[j];
                nums[j]=tmp;
            }
        }
        return nums;
    }
}
