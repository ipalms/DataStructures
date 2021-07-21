package algorithm.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 41. 缺失的第一个正数
 * 题目描述
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 */
public class MissingFirstPositiveNumber41 {
    public static void main(String[] args) {

        int nums[]={-1,-2,-60,40,43};
        System.out.println(firstMissingPositive(nums));
    }

    /**
     * 自己方法
     * 采用了排序+一次遍历   时间复杂度O（N*logN）
     * 还可以使用排序+ 二分查找算法
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
         if(nums.length==0||nums==null){
            return 1;
        }
        Arrays.sort(nums);
        if(nums[0]>1||nums[nums.length-1]<0||(nums.length==1&&nums[0]<1)){
            return 1;
        }
        for (int i = 1; i <nums.length ; i++) {
            if(nums[i]<=1){
                continue;
            }
            if(nums[i-1]<=0){
                return 1;
            }
            if(nums[i]-nums[i-1]>1){
                return nums[i-1]+1;
            }
        }
        return nums[nums.length-1]+1;
    }
    /**
     * 中心思想：
     * 实际上，对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1, N+1]中。
     * 这是因为如果 [1, N]都出现了，那么答案是 N+1，
     * 否则答案是 [1, N]中没有出现的最小正整数。
     */
    /**
     * 将数组视为哈希表（将数组设计成哈希表）
     * 类似做法题型还有  268. 丢失的数字（此题简单版）  442. 数组中重复的数据  448. 找到所有数组中消失的数字
     * 原地哈希就相当于，让数组中每个数字n都回到下标为n-1的家里。
     * 我们还可以把每个元素存放到对应的位置，比如1存放到数组的第一个位置，3存放到数组的第3个位置，
     * 如果是非正数或者大于数组的长度的值，我们不做处理
     * 最后在遍历一遍数组，如果位置不正确，说明这个位置没有这个数
     */
    public int firstMissingPositive2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //如果在指定的位置就不需要修改
            if (i + 1 == nums[i])
                continue;
            int x = nums[i];
            //这里使用的是if结构   x != nums[x - 1]是防止重复数字的再次交换
            if (x >= 1 && x <= nums.length && x != nums[x - 1]) {
                int temp = nums[x - 1];
                nums[x - 1] = nums[i];
                nums[i] = temp;
                //i--与外部循环的i++相消
                i--;
            }
        }
        //最后在执行一遍循环，查看对应位置的元素是否正确，如果不正确直接返回
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i])
                return i + 1;
        }
        return nums.length + 1;
    }

    /**
     * 带有详细注释的版本
     * @param nums
     */
    public int firstMissingPositive3(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            //这里用while是因为交换回来的值不一定是"值配其位"的，那么就可能还需要进行交换
            //比如[4,1,2,3]
            // 1.第一次将4与3交换，形成[3,1,2,4]，明显3是"值不配位"的，也满足条件，进入新的交换
            // 2.第二次将3与2交换，形成[2,1,3,4]，明显2也是"值不配位"的，且满足条件，进入新的交换
            // 3.第三次将2与1交换，形成[1,2,3,4]，值全都配位了，后面的while也就都不会进了
            // (此处也说明while循环不会每一次都把数组里面的所有元素都看一遍。如果有一些元素在这一次的循环中
            // 被交换到了它们应该在的位置，那么在后续的遍历中，由于它们已经在正确的位置上了，代码再执行到它们
            // 的时候，就会被跳过。
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                //将出现的值都交换到相应的位置上，组成一个近似排序的数组
                //其中的负数和大于数组长度的值都可以不用管，因为它是作为一个出口存在的
                //这里值1放在索引0上，即值(i)需要放在索引(i-1)上
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < len; ++i) {
            //第一个不满足 值(nums[i])=索引(i+1) 的值所在索引(i)+1即最小未出现的正整数
            //注意：以下的例子都是经过换位之后
            //例1：[1,2,3,4] 这个例子是完全排序的数组，且每个位置都正确，那么就应该返回数组长度+1，而不是3+1
            //例2：[1,-1,3,4] 其中-1是第一个也是唯一一个"值不配位"的值，那么它所占的位置就是未出现的最小正整数
            //例3：[5,11,7,9] 这个例子是完全没有进入while排序的，因为他们所有的值都"值不配位"，那么同上面的例2，
            // 第一个"值不配位"的值所占的位置就是未出现的最小正整数
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    /**
     * 使用集合set（哈希表结构）   使用了额外的空间
     * 把原数组的值全部存放到集合set中，然后再从1开始循环，判断这个数是否存在集合中，如果不存在直接返回
    */
    public int firstMissingPositive4(int[] nums) {
        int len = nums.length;
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int i = 1; i <= len; i++) {
            if (!hashSet.contains(i))
                return i;
        }
        return len + 1;
    }

    /**
     * 暴力解法 从1开始一个个查找，没找到就直接返回
     * 时间复杂度： O(N`2)
     */
    public int firstMissingPositive5(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            boolean has = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                //没有找到这个数，直接返回
                return i;
            }
        }
        return nums.length + 1;
    }
}
