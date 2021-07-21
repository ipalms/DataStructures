package algorithm.TopKOrHeap;

public class QuickSelect {

    /**
     * Quick Select [1]脱胎于快排（Quick Sort），两个算法的思想也非常接近：
     * 选取一个基准元素pivot，将数组切分（partition）为两个子数组
     * 比pivot大的扔左子数组，比pivot小的扔右子数组，然后递推地切分子数组。
     * Quick Select不同于Quick Sort的是其没有对每个子数组做切分，
     * 而是对目标子数组做切分。其次，Quick Select与Quick Sort一样，是一个不稳定的算法；
     * pivot选取直接影响了快排时间复杂度的好坏，最坏情况下的时间复杂度达到了O(n^2)。
     */
    /**
     * 快排的实现
     */
    public static void quickSort(int[] array, int begin, int end) {
        //递归截至条件
        if (end <= begin) {
            return;
        }
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length - 1);
    }

    /**
     * 快速选择代码--可以在O(n)时间复杂度下寻找数组第k大数据
     * Quick Select的目标是找出第k大元素，所以
     * 若切分后的左子数组的长度 > k，则第k大元素必出现在左子数组中（递归查询左子数组），
     * 若切分后的左子数组的长度 = k-1，则第k大元素为pivot，
     * 若上述两个条件均不满足，则第k大元素必出现在右子数组中（递归查询右子数组）。
     * 时间复杂度分析：该方法的时间复杂度是 O(n) ，简单分析就是第一次划分时遍历数组需要花费 n，
     * 而往后每一次都折半（当然不是准确地折半），粗略地计算就是 n+n/2+n/4+..< 2n，因此显然时间复杂度是 O(n)
     */
    public int quickSelect(int[] arr, int k, int left, int right) {
        if (left == right) return arr[right];
        int index = partition(arr, left, right);
        if (index - left + 1 > k)
            return quickSelect(arr, k, left, index - 1);
        else if (index - left + 1 == k)
            return arr[index];
        else
            return quickSelect(arr, k - index + left - 1, index + 1, right);
    }

    //以基准分两边大小即可用于快排也可以用于快速选择
    //第二种快排（使用尾部元素作为基准） pivot标杆选择尾部元素--且是以大于标杆的在左边小于标杆在右边
    private static int partition(int[] array, int begin, int end) {
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] > array[pivot]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        //将基准数放到中间的位置（基准数归位）
        //counter位置之前的数皆小于基准数，counter位置之后的数皆大于等于基准数
        int temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;
        //返回此时基准数的位置
        return counter;
    }
}
