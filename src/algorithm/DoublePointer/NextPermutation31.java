package algorithm.DoublePointer;

import org.junit.Test;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列
 * （即，组合出下一个更大的整数）。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class NextPermutation31 {

    @Test
    public void test(){
        int []arr=new int[]{1,2,3,4,3,6};
        prePermutation(arr);
        for(int num:arr){
            System.out.print(num+"  ");
        }
    }

    /**
     * 一样的题--556. 下一个更大元素 III（需要处理数字到字符数组的转换的下一个排列）
     * 这题我觉得也不太算双指针，在两遍遍历的途中使用了两个标记指针
     * 还有如果想法中一遍遍历写不出题解就不妨取思考两遍遍历或者嵌套遍历等时间复杂度更高的算法
     * 有可能题解就是没有很低的时间复杂度的解法
     */

    /**
     * 第一个指针记录从后往前第一个非升序的元素的下标
     * 第二个指针记录从后往前第一个元素小于第一个索引所指元素的元素的下标
     * 然后交换这两个索引对应元素，并对第一个索引之后的元素做升序交换操作
     * （因为题目要求重新排列成字典序中下一个更大的排列）
     */

    /**
     * 削减重复代码,抽取出反转数组方法
     */
    public void nextPermutation1(int[] nums) {
        int n=nums.length;
        int first=n-2;
        //找到从后往前第一个非升序的元素的下标
        while(first>=0&&nums[first]>=nums[first+1]){
            first--;
        }
        //排除元素全部为降序的情况（从前往后看）
        if(first!=-1){
            int second=n-1;
            //找到第一个元素大于第一个索引所指元素的元素的下标
            while(nums[second]<=nums[first]){
                second--;
            }
            swap(nums,first,second);
        }
        reverse(nums,first+1);
    }

    /**
     * 扩展题--上一个排列(找比这个小一点的，如果没有就全部反转一遍（即全部降序）)
     * 对比下一个排列的代码就是改变寻找first、second位置数时的符号
     */
    public void prePermutation(int[] nums) {
        int n=nums.length;
        int first=n-2;
        //找到从后往前第一个非降的元素的下标
        while(first>=0&&nums[first]<=nums[first+1]){
            first--;
        }
        //排除元素全部为升序的情况（从前往后看）
        if(first!=-1){
            int second=n-1;
            //找到第一个元素小于第一个索引所指元素的元素的下标
            while(nums[second]>=nums[first]){
                second--;
            }
            swap(nums,first,second);
        }
        reverse(nums,first+1);
    }

    public void nextPermutation(int[] nums) {
        int n=nums.length;
        int first=n-2;
        //找到从后往前第一个非升序的元素的下标
        while(first>=0&&nums[first]>=nums[first+1]){
            first--;
        }
        //排除元素全部为降序的情况
        if(first!=-1){
            int second=n-1;
            //找到第一个元素大于第一个索引所指元素的元素的下标
            while(nums[second]<=nums[first]){
                second--;
            }
            swap(nums,first,second);
            //交换后，[i+1,n-1]为降序序列，要对[i+1,n-1]剩余部分进行进行升序操作
            for(int k=0;k<(n-first-1)/2;k++){
                swap(nums,first+1+k,n-1-k);
            }
        }else{
            //从头进行升序操作
            for(int k=0;k<n/2;k++){
                swap(nums,k,n-1-k);
            }
        }
    }

    public void swap(int[] nums, int i, int j){
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }



    public void reverse(int[] nums,int start){
        int n=nums.length;
        for(int k=0;k<(n-start)/2;k++){
            swap(nums,start+k,n-1-k);
        }
    }

}
