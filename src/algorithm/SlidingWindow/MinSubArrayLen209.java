package algorithm.SlidingWindow;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinSubArrayLen209 {

    /**
     * 滑动窗口
     * 时间O（N）
     * 空间O（1）
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left=0,right=0,min=Integer.MAX_VALUE,n=nums.length,sum=0;
        while(right<n){
            sum+=nums[right];
            while(sum>=target){
                min=Math.min(min,right-left+1);
                sum-=nums[left++];
            }
            right++;
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    /**
     * 前缀和+二分---1004题也可以使用前缀和+二分
     * 对于全都是正整数的数组来说，其前缀和一定呈递增形式，所以可以考虑对于前缀和的数组使用二分查找进行
     * 时间O（N*logN）
     * 空间O（N）
     */
    public int minSubArrayLen1(int target, int[] nums) {
        int []prefix=new int[nums.length+1];
        //可以理解没有元素前缀和为0，当解是需要从第一个元素开始累加时会用到
        //如[1,2,3,4,5]  target=15时，前缀和为[0,1,3,6,10,15]，计算边界会用到为0情况
        prefix[0]=0;
        //计算前缀和
        for(int i=1;i<=nums.length;i++){
            prefix[i]=prefix[i-1]+nums[i-1];
        }
        //计算最小窗口长度
        int min=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            //只有前缀和长度已经大于target时才可能出现
            if(prefix[i+1]>=target) {
                int left = binarySearch(prefix, prefix[i+1] - target,i+1);
                //更新最小长度  min 上一个最小长度  i+1（右边界）-left(左边界)
                min = Math.min(min, i+1-left);
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    //这里的二分查找是为了找到前缀和数组中小于等于target的索引
    //即第一个小于等于target的索引
    public int binarySearch(int[] prefix,int target,int right){
        int left=0;
        while(left<right){
            int mid=left+(right-left+1)/2;
            //mid数大于target，说明包含mid的右侧都不可能出现目标索引
            if(prefix[mid]>target){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return left;
    }

    /**
     * 后面再写的二进制版本
     */
    public int minSubArrayLen3(int target, int[] nums) {
        int n=nums.length;
        int []preSum=new int[n+1];
        for(int i=1;i<=n;++i){
            preSum[i]=preSum[i-1]+nums[i-1];
        }
        if(preSum[n]<target) return 0;
        int min=n+1;
        for(int i=1;i<=n;++i){
            if(preSum[i]>=target){
                int left=binarySearch(preSum,preSum[i]-target,i);
                min=Math.min(min,i-left);
            }
        }
        return min==n+1?0:min;
    }
}
