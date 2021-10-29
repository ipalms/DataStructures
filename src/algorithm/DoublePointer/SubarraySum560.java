package algorithm.DoublePointer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class SubarraySum560{
    @Test
    public void test(){
        int []nums={0,1,2,2,1,2,3};
        System.out.println(subarraySum2(nums,6));
    }


    /**
     * 前缀和 + 哈希表   类似leetcode第一题的哈希解法
     * 由于只关心次数，不关心具体的解，我们可以使用哈希表加速运算
     * 对于一开始的情况，下标 0 之前没有元素，可以认为前缀和为 0，个数为 1 个
     * 时间复杂度：O（N）
     * 空间：O（N）
     */
    public int subarraySum3(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;
            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            // preSum[i]-preSum[j]==k  --> preSum[i]-k==preSum[j]
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            //然后维护preSumFreq的定义,前缀和可能出现多次
            //这行要写在if判断行之后，不然在k为0会误导结果值
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    /**
     * 注意题目的意思是连续的数组元素和为目标k值的情况--所以可以使用前缀和
     * 左右指针枚举--双指针解法
     * 时间复杂度：O（N^2）
     * 空间：O（1）
     */
    public int subarraySum(int[] nums, int k) {
        int count=0;
        int n=nums.length;
        //固定左边界进行枚举
        for(int left=0;left<n;left++){
            int sum=0;
            for(int right=left;right<n;right++){
                sum+=nums[right];
                //即使找到了一个也要继续进行枚举，因为后面可能有一负一正等情况抵消为0
                if(sum==k){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 数组前缀和解法
     * 时间复杂度：O（N^2）
     * 空间：O（N）
     */
    public int subarraySum2(int[] nums, int k) {
        int count=0;
        int n=nums.length;
        int []arrayPre=new int[n+1];
        //前0个元素和为0--这一点很重要
        arrayPre[0]=0;
        //计算数组的前缀和
        for(int i=0;i<n;i++){
            arrayPre[i+1]=nums[i]+arrayPre[i];
        }
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                //利用前缀和进i行比较
                if(arrayPre[j+1]-arrayPre[i]==k){
                    count++;
                }
            }
        }
        return count;
    }

}
