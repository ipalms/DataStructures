package algorithm.DoublePointer;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，
 * 这样它就有足够的空间保存来自 nums2 的元素。
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 */
public class MergeOrderArray88 {

    /**
     * 双指针从后往前合并数组
     * 从前往后合并即麻烦还要使用临时变量，因为如果直接合并到数组nums1中，nums1中的元素可能会在取出之前被覆盖
     * 因此可以指针设置为从后向前遍历，每次取两者之中的较大者放进nums1的最后面（即从后往前合并）
     * 一个思考点就是即使从后往前合并，对于nums1位置指针所指元素也永远不会被从nums2合并来的元素覆盖掉
     * 即p1后面的位置永远足够容纳被插入的元素，不会产生p1的元素被覆盖的情况。
     * 因为nums1数组起初就预留了n个长度的值为0的部分，p1指针一直小于等于i指针
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1=m-1;
        int p2=n-1;
        int i=n+m-1;
        while(p1>=0&&p2>=0){
            if(nums1[p1]>nums2[p2]){
                nums1[i--]=nums1[p1--];
            }else{
                nums1[i--]=nums2[p2--];
            }
            //一行版代码
            //nums1[i--]=nums1[p1]>nums2[p2]?nums1[p1--]:nums2[p2--];
        }
        //说明nums2数组的前半部分小，直接拷贝到nums1数组上
        if(p2>=0){
            System.arraycopy(nums2,0,nums1,0,p2+1);
        }
//         不调用库函数的拷贝
//        while(p2>=0){
//            nums1[i--]=nums2[p2--];
//        }
    }

    /**
     * 变形题--合并n个升序数组的值
     * 思路和合并n个升序链表一样----使用优先队列（归并排序思路应该也可以，但是不如优先队列实现简单）
     */
}
