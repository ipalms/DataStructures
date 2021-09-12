package algorithm.Array;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 268. 丢失的数字
 * 题目叙述
 *给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 * 示例 1：
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 */
public class MissingNumbers268 {
    @Test
    public void test() {
       int nums[]={0,1,3,4,7,6,5};
        System.out.println(missingNumber3(nums));
    }

    /**
     * 如果值是n，我们不做处理
     * 哈希函数的规则特别简单，那就是数值为i的数映射到下标为i的位置。
     */
    public int missingNumberMy(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;++i){
            int x=nums[i];
            while(x!=n&&x!=nums[x]){
                nums[i]=nums[x];
                nums[x]=x;
                x=nums[i];
            }
        }
        for(int i=0;i<n;++i){
            if(i!=nums[i]){
                return i;
            }
        }
        return n;
    }

    /**
     * 如果值是0，我们不做处理
     * 哈希函数的规则特别简单，那就是数值为i的数映射到下标为i-1的位置。
     */
    public static int missingNumber(int[] nums) {
        for (int i = 0; i <nums.length ; i++) {
            while (nums[i]>0&&nums[nums[i]-1]!=nums[i]){
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i <nums.length ; i++) {
            if((i+1)!=nums[i]){
                return i+1;
            }
        }
        return 0;
    }

    /**
     * 使用集合set
     */
    public static int missingNumber2(int[] nums) {
        Set<Integer> code=new HashSet<>();
        for (int i = 0; i <nums.length ; i++) {
            code.add(nums[i]);
        }
        for (int i = 0; i <nums.length ; i++) {
            if(!code.contains(i)){
                return i;
            }
        }
        return nums.length;
    }

    /**
     *   解法三 位运算符（异或）
     *   这种解法和   136. 只出现一次的数字一致
     */

    /**
     * 异或位运算符：参与运算的两个值，如果两个相应bit位相同，则结果为0，否则为1。
     * 按位异或的3个特点：
     * 　　（1） 0^0=0，0^1=1 0异或任何数＝任何数
     * 　　（2） 1^0=1，1^1=0 1异或任何数结果都不是任何数
     * （如过这个任何数为奇数，则异或结果为奇数-1.如过这个任何数为偶数，则异或结果为偶数+1.）
     * 　　（3） 任何数异或自己＝把自己置0
     * eg:0^0 = 0，　1^0 = 1，　0^1 = 1，　1^1 = 0
     *    0^a=a      a^a=0     a^a^b=b
     *    应用：
     *    （1）实现两个值的交换，而不必使用临时变量。
     * 　　例如交换两个整数a=10100001，b=00000110的值，可通过下列语句实现：
     * 　　a = a^b； 　　//a=10100111
     * 　　b = b^a； 　　//b=10100001
     * 　　a = a^b； 　　//a=00000110
     *    （2） 快速判断两个值是否相等
     * 　　举例1： 判断两个整数a，b是否相等，则可通过下列语句实现：
     * 　　return （（a ^ b） == 0）
     */
    public int missingNumber3(int[] nums) {
         int res = nums.length;
         for(int i = 0; i < nums.length; i++){
             res ^= nums[i] ^ i;
         }
         return res;
    }

    /**
     * 求和公式--可看作等差数列(d=1) 又可称为高斯定理
     * 前n和：n*(n+1)/2
     * 求出数组实际总和sum
     * 缺失数就为前n和减去sum
     * 需要小心数据溢出（int），可以在一个循环内加减防止数据溢出
     */
    public int missingNumber4(int[] nums) {
        int n=nums.length;
        int total=n*(n+1)/2;
        int sum=0;
        for(int i:nums){
            sum+=i;
        }
        return total-sum;
    }
}
