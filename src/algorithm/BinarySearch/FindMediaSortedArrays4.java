package algorithm.BinarySearch;

import org.testng.annotations.Test;

/**
 *4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 */
public class FindMediaSortedArrays4 {
    //补充题17. 两个有序数组第k小的数
    //不能直接将k设为need，会存在k过大导致数据索引溢出情况
    //第二种二分方法可解
    public double findMedianSortedArraysMy(int[] nums1, int[] nums2) {
        int n1= nums1.length,n2= nums2.length;
        //将较短的数组放在第一个参数
        if(n1>n2){
            return findMedianSortedArraysMy(nums2,nums1);
        }
        int need=(n1+n2+1)/2;
        int l=0,r=n1;
        //只需要一个数组的 l,r来圈定二分实现
        while (l<r){
            //p1,p2代表分割线两数组右侧的第一个数
            //这一步的向上｜或者向下取舍主要看后面的比较中l,r指针移动的情况
            //这一题向上取舍，向下也行
            int p1=l+(r-l)/2;
            int p2=need-p1;
            //这里注意，如果向下取舍p1是可能为0的，所以不能使用p1-1
            //向下取舍p2是可能取到n2的
            //反过来向上取舍，这里就可以相反操作一波
            if(nums1[p1]<nums2[p2-1]){
                l=p1+1;
            }else{
                r=p1;
            }
        }
        int i=l,j=need-i;
        //这里要注意指针越界的情况，0和len(nums)两侧都有可能不能直接取值
        int nums1L=i==0?Integer.MIN_VALUE:nums1[i-1];
        int nums2L=j==0?Integer.MIN_VALUE:nums2[j-1];
        int nums1R=i==n1?Integer.MAX_VALUE:nums1[i];
        int nums2R=j==n2?Integer.MAX_VALUE:nums2[j];
        if((n1+n2)%2==1){
            return Math.max(nums1L,nums2L);
        }else{
            return (Math.max(nums1L,nums2L)+Math.min(nums1R,nums2R))/2.0;
        }
    }

    //这种可以解决 两个有序数组第k小的数
    public double findMedianSortedArraysMy2(int[] nums1, int[] nums2) {
        int len1=nums1.length,len2=nums2.length;
        int sum=len1+len2;
        if((sum&1)==1){
            return findKth(nums1,nums2,(sum+1)/2);
        }
        return (findKth(nums1,nums2,sum/2)+findKth(nums1,nums2,sum/2+1))/2.0;
    }

    private double findKth(int[] nums1, int[] nums2, int k) {
        int len1=nums1.length,len2=nums2.length;
        int i=0,j=0;
        while(true){
            if(i==len1){
                return nums2[j+k-1];
            }
            if(j==len2){
                return nums1[i+k-1];
            }
            if(k==1){
                return Math.min(nums1[i],nums2[j]);
            }
            int n1=Math.min(len1,i+k/2)-1,n2=Math.min(len2,j+k/2)-1;
            if(nums1[n1]<nums2[n2]){
                k-=n1-i+1;
                i=n1+1;
            }else{
                k-=n2-j+1;
                j=n2+1;
            }
        }
    }

    @Test
    public void test(){
        int []n1=new int[]{1,3,5,7,8,9,10,22};
        int []n2=new int[]{2,3,4,8,11,14,18,22};
        System.out.println(findMedianSortedArrays4(n1,n2));
    }


    /**
     * 本题用到的：
     * 如果我们要找的元素的性质比较复杂：例如需要满足「条件 1」并且「条件 2」。
     * 我们在编写 if 语句的时候，就可以把其中一个条件取反，就可以达到缩减搜索区间的目的。
     *
     * 这一点也不难想明白，「条件 1」并且「条件 2」的反面就是「取反条件 1」或者「取反条件 2」。
     * 再举一个具体的例子：例如我们要找一个数x，需要满足4≤x≤9，即x≥4并且x≤9。
     * 如果我们看到一个数小于4，我们就知道此时需要在当前位置的右边继续查找，可以得到缩减问题区间的目的。
     */
    /**
     * 官方题解  时间复杂度：O(log min(m,n)))
     * 主要就是找到一条分割线，使得这个分割线任意左边的元素小于等于分割线右边的元素，且分割线两端元素个数相等或多一（奇数情况）
     * 对于一个数组而言分割线就可看作中位数前面的自然划线
     * 而对于两个数组而言就需要依照二分的形式去逼近找到这个分割线（折线）
     * 最后的答案出现在分割线周围的四个数中（特殊情况在分割线左侧或右侧下标溢出时使用int最大最小值代替）
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int leftLength = nums1.length;
        int rightLength = nums2.length;
        //为了保证第一个数组比第二个数组小(或者相等)
        if (leftLength > rightLength) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // 分割线左边的所有元素需要满足的个数 m + (n - m + 1) / 2;
        // 两个数组长度之和为偶数时，当在长度之和上+1时，由于整除是向下取整，所以不会改变结果
        // 两个数组长度之和为奇数时，按照分割线的左边比右边多一个元素的要求，此时在长度之和上+1，就会被2整除，会在原来的数
        //的基础上+1，于是多出来的那个1就是左边比右边多出来的一个元素
        int totalLeft = (leftLength + rightLength + 1) / 2;
        // 在nums1的区间[0, leftLength]里查找恰当的分割线
        // 在长度短的区间找分隔线可以防止元素越界，也可以适用于一个数组为空的特殊情况
        // 使得 nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
        int left = 0;
        int right = leftLength;
        // nums1[i - 1] <= nums2[j]
        //  此处要求第一个数组中分割线的左边的值 不大于(小于等于) 第二个数组中分割线的右边的值
        // nums2[j - 1] <= nums1[i]
        //  此处要求第二个数组中分割线的左边的值 不大于(小于等于) 第一个数组中分割线的右边的值
        // 循环条件结束的条件为指针重合，即分割线已找到
        while (left < right) {
            //i 的定义是分割线的右边第一个元素，而它的左边一定有值
            // 二分查找，此处为取第一个数组中左右指针下标的中位数，决定起始位置
            // 此处+1首先是为了不出现死循环，即left永远小于right的情况
            // 其次i的定义是分割线的右边第一个元素，而它的左边一定有值
            // 取i时下标+1还会保证下标不会越界，因为+1之后向上取整，保证了i不会取到0值，即i-1不会小于0
            // 此时i也代表着在一个数组中左边的元素的个数
            int i = left + (right - left + 1) / 2;
            // 第一个数组中左边的元素个数确定后，用左边元素的总和-第一个数组中元素的总和=第二个元素中左边的元素的总和
            // 此时j就是第二个元素中左边的元素的个数
            int j = totalLeft - i;
            // 此处用了nums1[i - 1] <= nums2[j]的取反，当第一个数组中分割线的左边的值大于第二个数组中分割线的右边的值
            // 说明右指针应该左移，即-1
            if (nums1[i - 1] > nums2[j]) {
                // 下一轮搜索的区间 [left, i - 1]
                right = i - 1;
                // 此时说明条件满足，应当将左指针右移到i的位置，至于为什么是右移，请看i的定义
            } else {
                // 下一轮搜索的区间 [i, right]
                left = i;
            }
        }
        // 退出循环时left一定等于right，所以此时等于left和right都可以
        int i = left;
        // 此时j代表分割线在第二个数组中的位置
        // nums2[j]为第一个数组中分割线右边的第一个值
        // nums2[j-1]即第一个数组中分割线左边的第一个值
        int j = totalLeft - i;
        // 当i=0时，说明第一个数组分割线左边没有值，为了不影响
        // nums1[i - 1] <= nums2[j] 和 Math.max(nums1LeftMax, nums2LeftMax)
        // 的判断，所以将它设置为int的最小值
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        // 等i=第一个数组的长度时，说明第一个数组分割线右边没有值，为了不影响
        // nums2[j - 1] <= nums1[i] 和 Math.min(nums1RightMin, nums2RightMin)
        // 的判断，所以将它设置为int的最大值
        int nums1RightMin = i == leftLength ? Integer.MAX_VALUE : nums1[i];
        // 当j=0时，说明第二个数组分割线左边没有值，为了不影响
        // nums2[j - 1] <= nums1[i] 和 Math.max(nums1LeftMax, nums2LeftMax)
        // 的判断，所以将它设置为int的最小值
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        // 等j=第二个数组的长度时，说明第二个数组分割线右边没有值，为了不影响
        // nums1[i - 1] <= nums2[j] 和 Math.min(nums1RightMin, nums2RightMin)
        // 的判断，所以将它设置为int的最大值
        int nums2RightMin = j == rightLength ? Integer.MAX_VALUE : nums2[j];
        // 如果两个数组的长度之和为奇数，直接返回两个数组在分割线左边的最大值即可
        if (((leftLength + rightLength) % 2) == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            // 如果两个数组的长度之和为偶数，返回的是两个数组在左边的最大值和两个数组在右边的最小值的和的二分之一
            // 此处不能被向下取整，所以要强制转换为double类型
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }

    /**
     * 维护双指针起初指向两个数组0索引处，两指针指向的更小数指针加一
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int l1=nums1.length;
        int l2=nums2.length;
        int need=(l1+l2-1)/2;
        int start1=0,start2=0;
        while(start1+start2<need){
            int n1=start1<l1?nums1[start1]:Integer.MAX_VALUE;
            int n2=start2<l2?nums2[start2]:Integer.MAX_VALUE;
            if(n1>=n2){
                start2++;
            }else{
                start1++;
            }
        }
        int nums1Left=start1==l1?Integer.MAX_VALUE:nums1[start1];
        int nums1Right=start1>=l1-1?Integer.MAX_VALUE:nums1[start1+1];
        int nums2Left=start2==l2?Integer.MAX_VALUE:nums2[start2];
        int nums2Right=start2>=l2-1?Integer.MAX_VALUE:nums2[start2+1];
        if((l1+l2)%2==0){
            return (float)(Math.min(nums1Left,nums2Left)+Math.min(Math.max(nums1Left,nums2Left),Math.min(nums1Right,nums2Right)))/2;
        }
        return Math.min(nums1Left,nums2Left);
    }

    /**
     * 维护双指针 二
     */
    public double findMedianSortedArrays3(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    /**
     * 二分2：两个有序数组第k小的数--特殊版两个有序数组的中位数（len1+len2）/2
     * 假设两个有序数组分别是A和B。要找到第k个元素，我们可以比较A[k/2−1]和B[k/2−1]
     * 
     * 假设两个有序数组分别是 A 和 B。要找到第 k 个元素，我们可以比较 A[k/2−1] 和 B[k/2−1]，
     * 其中 / 表示整数除法。由于A[k/2−1] 和B[k/2−1]的前面分别有 A[0..k/2−2] 和 B[0..k/2−2]
     * 即 k/2−1 个元素，对于A[k/2−1] 和B[k/2−1] 中的较小值，
     * 最多只会有 (k/2−1)+(k/2−1)≤k−2 个元素比它小，那么它就不能是第 k 小的数了。
     * 因此我们可以归纳出三种情况：
     *   1.如果A[k/2−1]<B[k/2−1]，则比A[k/2−1] 小的数最多只有 A 的前 k/2−1 个数和 B 的前k/2−1 个数
     *    即比A[k/2−1] 小的数最多只有k−2 个，因此A[k/2−1] 不可能是第 k 个数，A[0] 到A[k/2−1] 也都不可能是第 k 个数，可以全部排除。
     *   2.如果A[k/2−1]>B[k/2−1]，则可以排除 B[0] 到 B[k/2−1]。
     *   3.如果A[k/2−1]=B[k/2−1]，则可以归入第一种情况处理。
     * 链接：https://leetcode.cn/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
     */
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;
        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
