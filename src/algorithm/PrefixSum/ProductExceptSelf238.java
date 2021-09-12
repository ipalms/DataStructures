package algorithm.PrefixSum;

import org.junit.Test;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为n的整数数组nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i]等于nums中除nums[i]之外其余各元素的乘积。
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class ProductExceptSelf238 {
    @Test
    public void test(){
        int []nums={1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }


    /**
     * 统计后缀和乘积  空间O（1）的做法
     * 题目要求不能使用除法
     */
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        //prefix：从左边累乘，suffix：从右边累乘
        int prefix=1,suffix=1;
        int []res=new int[n];
        //结果数组填充1（调用库函数填充用时更长一些）
        Arrays.fill(res,1);
        //因为遍历总是线性的，所以对结果数组两边同时进行赋值
        for(int i=0;i<n;i++){
            res[i]*=prefix; //乘以其左边的乘积
            prefix*=nums[i];
            res[n-1-i]*=suffix; //乘以其右边的乘积
            suffix*=nums[n-1-i];
        }
        return res;
    }

    //同O（1）版本的
    public int[] productExceptSelf3(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for(int i = 0; i < res.length; i++){
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for(int i = res.length - 1; i >= 0; i--){
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }


    /**
     * 统计后缀和乘积  一个额外数组的空间

     */
    public int[] productExceptSelf1(int[] nums) {
        int n=nums.length;
        int []suffix=new int[n];
        suffix[0]=1;
        int count1=1,count2=1;
        //后缀乘积不统计全部元素乘在一起
        for(int i=0;i<n-1;i++){
            count1*=nums[n-1-i];
            suffix[i+1]=count1;
        }
        int []res=new int[n];
        for(int i=0;i<n;i++){
            res[i]=count2*suffix[n-1-i];
            count2*=nums[i];
        }
        return res;
    }

    /**
     * 统计前缀和乘积和后缀和乘积  两个额外数组的空间
     * 题目要求不能使用除法
     */
    public int[] productExceptSelf2(int[] nums) {
        int n=nums.length;
        int []prefix=new int[n];
        int []suffix=new int[n];
        prefix[0]=1;
        suffix[0]=1;
        int count1=1,count2=1;
        //前缀乘积、后缀乘积都不统计全部元素乘在一起
        for(int i=0;i<n-1;i++){
            count1*=nums[i];
            count2*=nums[n-1-i];
            prefix[i+1]=count1;
            suffix[i+1]=count2;
        }
        int []res=new int[n];
        for(int i=0;i<n;i++){
            res[i]=prefix[i]*suffix[n-1-i];
        }
        return res;
    }
}
