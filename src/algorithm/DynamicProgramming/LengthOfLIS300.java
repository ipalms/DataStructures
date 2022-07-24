package algorithm.DynamicProgramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * 提示：
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 10^4
 * 进阶：
 * 你可以设计时间复杂度为 O(n^2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LengthOfLIS300 {


    /**
     * 该题有个相似的题  673. 最长递增子序列的个数（本体的延申题），其实也可以运用动态规划思想解题
     * */
    @Test
    public void test2() {
        int[] nums = new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        int[] nums1 = new int[]{10,9,2,5,3,7,103,101,18};
        //int lengthOfLIS =lengthOfLIS2(nums);
        System.out.println(lengthOfLISMy1(nums));
        System.out.println(Arrays.toString(LIS1(nums)));
    }

    public int lengthOfLISMy(int[] nums) {
        int len=nums.length;
        int []dp=new int[len];
        int max=1;
        Arrays.fill(dp,1);
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(nums[j]>nums[i]){
                    dp[j]=Math.max(dp[j],dp[i]+1);
                    max=Math.max(max,dp[j]);
                }
            }
            //在外层比较max也行
            max=Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 找path
     * */
    public String lengthOfLISMy1(int[] nums) {
        int len=nums.length;
        int []dp=new int[len];
        int []path=new int[len];
        int max=1;
        Arrays.fill(dp,1);
        Arrays.fill(path,-1);
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(nums[j]>nums[i]){
                    if(dp[j]<dp[i]+1){
                        dp[j]=dp[i]+1;
                        path[j]=i;
                    }
                    //dp[j]=Math.max(dp[j],dp[i]+1);
                    max=Math.max(max,dp[j]);
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=len-1;i>=0;i--){
            if(dp[i]==max){
                sb.append(nums[i]);
                while (path[i]!=-1){
                    sb.insert(0, nums[path[i]]+"\t");
                    i=path[i];
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 贪心+二分 搜路径
     * */
    public int[] lengthOfLISMy2(int[] nums) {
        int len= nums.length,end=0;
        int []dp=new int[len];
        int []tail=new int[len];
        //dp[0]=1可以视作 一个元素递增数量为1，如果dp[0]初始为1，那么后面的dp赋值都要找到相应index再加一
        dp[0]=1;
        tail[0]=nums[0];
        for(int i=1;i<len;i++){
            if(nums[i]>tail[end]){
                ++end;
                dp[i]=end+1;
                tail[end]=nums[i];
            }else{
                int left=0,right=end-1;
                while(left<right){
                    int mid=left+(right-left)/2;
                    if(tail[mid]<nums[i]){
                        left=mid+1;
                    }else{
                        right=mid;
                    }
                }
                tail[left]=nums[i];
                dp[i]=left+1;
            }
        }
        int []res=new int[end+1];
        for(int i=len-1;i>=0;i--){
            if(dp[i]==end+1){
                res[end]=nums[i];
                end--;
            }
        }
        return res;
    }

    /**
     * 动态规划：
     * 定义dp[i]为考虑前i个元素，以第i个数字结尾的最长上升子序列的长度，注意nums[i]必须被选取。
     * 我们从小到大计算dp数组的值，在计算dp[i]之前，我们已经计算出dp[0…i−1]的值，则状态转移方程为：
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     * 即考虑往dp[0…i−1] 中最长的上升子序列后面再加一个nums[i]
     * 由于dp[j]代表nums[0…j]中以nums[j]结尾的最长上升子序列，所以如果能从dp[j]这个状态转移过来，
     * 那么nums[i]必然要大于nums[j]，才能将nums[i]放在nums[j]后面以形成更长的上升子序列。
     * 最后，整个数组的最长上升子序列即所有dp[i]中的最大值。
     * LIS-length=max(dp[i]),其中0≤i<n
     * 为什么最长序列为dp中的最大值，而非dp最后一位？
     * 因为对于[1，2，5，4，6，4，5]这种序列来说，dp序列是从小到大再到小的，最大值取再外层循环遍历到6元素时
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)，需要额外使用长度为n的dp数组。
     */

     public int lengthOfLIS(int[] nums) {
        int max=0;
        int []dp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            //初始化dp数组，最小情况都为1
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    //状态转移关系，dp取当前dp[i]和已存在的dp[j]+1的较大值
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            //一轮循环完得到最终dp[i]后维护这一段中的最长递增子序列
            max=Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 输出递增的序列
     * 只要在更新dp数组的时候同时记录路径，最后找LIS长度的时候同时找到终点，经过终点逆向就能找到LIS路径了
     */
    public String lengthOfLISPath(int[] nums) {
        //index为最长序列的最后一位数的位置
        int max=0,index=0;
        int []dp=new int[nums.length];
        //记录路径（上一个节点位置）
        int []path=new int[nums.length];
        //初始化dp为1
        Arrays.fill(dp,1);
        //路径初始化为-1，表示这是最长路径的开始
        Arrays.fill(path,-1);
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    //状态转移关系，dp取当前dp[i]和已存在的dp[j]+1的较大值
                    dp[i]=Math.max(dp[i],dp[j]+1);
                    path[i]=j;
                }
            }
            //更新最长序列，以及序列的最后一位
            if(dp[i]>max){
                max=dp[i];
                index=i;
            }
        }
        StringBuilder s= new StringBuilder();
        //退出的条件是index==-1,即这个坐标是最长递增序列的第一个数
        while(index!=-1){
            s.insert(0,nums[index] + "  ");
            index=path[index];
        }
        return s.toString();
    }

    /**
     * 动态规划，遍历的顺序改变一下
     */
    public int lengthOfLIS3(int[] nums) {
        int n=nums.length;
        int max=1;
        int []dp=new int[n];
        Arrays.fill(dp,1);
        for(int i=0;i<n-1;++i){
            for(int j=i+1;j<n;++j){
                if(nums[j]>nums[i]){
                    dp[j]=Math.max(dp[j],dp[i]+1);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 贪心+二分
     * 依然着眼于某个上升子序列的结尾的元素，如果已经得到的上升子序列的结尾的数越小，
     * 那么遍历的时候后面接上一个数，会有更大的可能构成一个长度更长的上升子序列。
     * 既然结尾越小越好，我们可以记录 在长度固定的情况下，结尾最小的那个元素的数值
     * 这样定义以后容易得到「状态转移方程」。
     *
     * 时间复杂度：O(NlogN)，遍历数组使用了O(N)，二分查找法使用了O(logN)。
     * 空间复杂度：O(N)，开辟有序数组 tail 的空间至多和原始数组一样。
     * 题解链接：
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        //tail[i]数组的定义：长度为i+1的上升子序列的末尾最小是几
        //数组tail也是一个严格上升数组
        int[] tail = new int[len];
        // 遍历第 1 个数，直接放在有序数组 tail 的开头
        tail[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;
        for (int i = 1; i < len; i++) {
            //遍历到的数比tail数组实际有效的末尾的那个元素还大，最长序列可以直接加一
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                //使用二分查找法，在有序数组 tail 中
                //找到第1个大于等于 nums[i] 的元素，尝试让那个元素更小
                //假设找到的位置为k，那么肯定有k个数是严格小于num[i]
                //所以将num[i]插入k索引处操作是合理的（符合tail数组的定义）
                int left = 0;
                int right = end;
                while (left < right) {
                    // 选左中位数不是偶然，取决与后面的
                    int mid = left + ((right - left) >>> 1);
                    //因为找的是第一个大于或者等于nums[i]的元素，
                    if (tail[mid] < nums[i]) {
                        //中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第1个大于等于nums[i]的元素
                tail[left] = nums[i];
            }
            // 调试方法
            printArray(nums[i], tail);
        }
        //此时end 是有序数组tail最后一个元素的索引，题目要求返回的是长度，因此+1后返回
        end++;
        return end;
    }

    /**
     * 二分找递增序列
     * 如果有多个答案，请输出其中 按数值(注：区别于按单个字符的ASCII码值)进行比较的 字典序最小的那个
     * 保证是字典序最小的两个因素：
     * 一个是二分查找tail数组找正好大于（等于）遍历当前值的数进行替换，这样标记的就是字典序列较小的
     * 其次在遍历路径的时候倒序进行，从dp数组末尾开始的路径就是字典序最小的
     */
    //dp数组从0开始版本
    public int[] LIS1(int[] arr) {
        //从0开始
        int[] dp = new int[arr.length];
        int[] tail = new int[arr.length];
        dp[0] = 1;
        int end = 0;
        tail[end] = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            int left = 0, right = end;
            if (arr[i] > tail[end]) {
                ++end;
                dp[i] = end + 1;
                tail[end] = arr[i];
            } else {
                // while(left<right)形式更好理解
                while (left<right){
                    int mid=left+(right-left)/2;
                    if(tail[mid]<arr[i]){
                        left=mid+1;
                    }else{
                        right=mid;
                    }
                }
                tail[left] = arr[i];
                dp[i] = left + 1;
            }
        }

        int[] res = new int[end + 1];
        for (int i = dp.length - 1; i >= 0; --i) {
            if (dp[i] == end + 1) {
                res[end] = arr[i];
                --end;
            }
        }
        return res;
    }


    public int[] LIS (int[] arr) {
        int n = arr.length;
        // 列表的最大子序列 下标从1开始   下标从0开始是另一种写法
        int[] end = new int[n + 1];
        // 存储每个元素的最大子序列个数
        int[] dp = new int[n];
        int len = 1;
        //子序列的第一个元素默认为数组第一个元素
        end[1] = arr[0];
        //第一个元素的最大子序列个数肯定是1
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            if (end[len] < arr[i]) {
                //当arr[i] > end[len] 时 arr[i]添加到 end后面
                end[++len] = arr[i];
                dp[i] = len;
            } else {
                // 当前元素小于end中的最后一个元素 利用二分法寻找第一个大于arr[i]的元素
                // end[l] 替换为当前元素 dp[]
                int l = 0;
                int r = len;
                // 注意这里 left <= right 而不是 left < right，我们要替换的是第一个比 arr[i] 大的元素
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (end[mid] >= arr[i]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
                end[l] = arr[i];
                dp[i] = l;
            }
        }
        int[] res = new int[len];
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == len) {
                res[--len] = arr[i];
            }
        }
        return res;
    }

    // 调试方法，以观察是否运行正确
    private void printArray(int num, int[] tail) {
        System.out.print("当前数字：" + num);
        System.out.print("\t当前 tail 数组：");
        int len = tail.length;
        for (int i = 0; i < len; i++) {
            if (tail[i] == 0) {
                break;
            }
            System.out.print(tail[i] + ", ");
        }
        System.out.println();
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        int[] nums1 = new int[]{10,9,2,5,3,7,103,101,18};
        //int lengthOfLIS =lengthOfLIS2(nums);
        System.out.println("最长上升子序列的长度：" + Arrays.toString(LIS(nums)));
    }
}
