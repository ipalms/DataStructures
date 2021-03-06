package algorithm.Sort;

public class HeapSort {

    /**
     * my堆排
     */
    public int[] heapSort(int[] nums) {
        int len=nums.length;
        //第一遍成堆一定要从底向上
        for(int i=len/2-1;i>=0;--i){
            adjustHeap(nums,i,len);
        }
        //第二遍是从上到下的调整
        for(int i=len-1;i>=1;--i){
            swap(nums,0,i);
            adjustHeap(nums,0,i);
        }
        return nums;
    }

    private void adjustHeap(int []nums,int j,int end){
        int tmp=nums[j];
        //这里是2*j+1作为起始
        for(int k=2*j+1;k<end;k=2*k+1){
            if(k+1<end&&nums[k]<nums[k+1]){
                ++k;
            }
            if(nums[k]>tmp){
                nums[j]=nums[k];
                j=k;
            }else{
                break;
            }
        }
        nums[j]=tmp;
    }


    public int[] sortArray(int[] nums) {
        int len = nums.length;
        // 将数组整理成堆
        heapify(nums);
        // 循环不变量：区间 [0, i] 堆有序
        for (int i = len - 1; i >= 1; ) {
            // 把堆顶元素（当前最大）交换到数组末尾
            swap(nums, 0, i);
            // 逐步减少堆有序的部分
            i--;
            // 下标 0 位置下沉操作，使得区间 [0, i] 堆有序
            siftDown(nums, 0, i);
        }
        return nums;
    }

    /**
     * 将数组整理成堆（堆有序）
     */
    private void heapify(int[] nums) {
        int len = nums.length;
        // 只需要从 i = (len - 1) / 2 这个位置开始逐层下移
        for (int i = (len - 1) / 2; i >= 0; i--) {
            siftDown(nums, i, len - 1);
        }
    }

    /**
     * @param k    当前下沉元素的下标
     * @param end  [0, end] 是 nums 的有效部分
     */
    private void siftDown(int[] nums, int k, int end) {
        while (2 * k + 1 <= end) {
            int j = 2 * k + 1;
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] > nums[k]) {
                swap(nums, j, k);
            } else {
                break;
            }
            k = j;
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
