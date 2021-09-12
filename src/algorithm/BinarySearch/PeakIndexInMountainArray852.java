package algorithm.BinarySearch;

/**
 * 852. 山脉数组的峰顶索引
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足
 * arr[0]< arr[1]<...arr[i-1]<arr[i]>arr[i + 1]> ... >arr[arr.length-1]的下标 i 。
 * 示例 1：
 * 输入：arr = [0,1,0]
 * 输出：1
 * 示例 2：
 * 输入：arr = [0,2,1,0]
 * 输出：1
 * 示例 3：
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * 示例 4：
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * 示例 5：
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 * 提示：
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * 题目数据保证 arr 是一个山脉数组
 * 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
 */
public class PeakIndexInMountainArray852 {
    /**
     * 二分---while(left<=right)
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left=0,right=arr.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(arr[mid]>arr[mid+1]){
                if(arr[mid]>arr[mid-1]){
                    //循环体中就可以直接得到结果退出循环
                    return mid;
                }
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return -1;
    }

    /**
     * 二分---while(left<right)
     */
    public int peakIndexInMountainArray2(int[] arr) {
        int left=0,right=arr.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(arr[mid]>arr[mid+1]){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

    /**
     * 「三分」就是使用两个端点将区间分成三份，然后通过每次否决三分之一的区间来逼近目标值。
     * 具体的，由于峰顶元素为全局最大值，因此我们可以每次将当前区间分为[l,m1]、[m1,m2]和[m2,r] 三段，
     * 如果满足arr[m1]>arr[m2]，说明峰顶元素不可能存在与[m2,r] 中，让r=m2−1即可。另外一个区间分析同理。
     * 二分与三分区别：
     * 二分通常用来解决单调函数的找target问题，但进一步深入我们发现只需要满足「二段性」就能使用「二分」来找分割点；
     * 三分则是解决单峰函数极值问题。
     */
    public int peakIndexInMountainArray3(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int m1 = l + (r - l) / 3;
            int m2 = r - (r - l) / 3;
            if (arr[m1] > arr[m2]) {
                r = m2 - 1;
            } else {
                l = m1 + 1;
            }
        }
        return r;
    }
}
