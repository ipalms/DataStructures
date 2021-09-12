package algorithm.Sort;

public class MergeSort {
    /**
     * 归并排序
     * 归并排序是先求区间索引中位数，然后对两侧递归（注意这个递归因为求的中位数是向下取舍的所以左侧不能写成mid-1）
     * 或者说递归的操作只是切分数组的操作，mid处的数据任然是无序的，不能略过mid
     * （快排能向pivot两侧递归是因为取pivot的函数保证了pivot两侧的元素严格小于等于、大于等于pivot）
     * 切分数组后再对数组元素进行合并操作（递归完成以后，合并两个有序数组的这一步骤-->递归函数执行完成以后，我们还可以做点事情）
     * 「归并排序」比「快速排序」好的一点是，它借助了额外空间，可以实现「稳定排序」
     */
    public int[] sortArray(int[] nums) {
        //tmp数组用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
        int []tmp=new int[nums.length];
        mergeSort(nums,0,nums.length-1,tmp);
        return nums;
    }

    public void mergeSort(int[] nums, int start, int end, int[] tmp){
        if(start<end){
            int mid=(start+end)/2;
            mergeSort(nums,start,mid,tmp);
            mergeSort(nums,mid+1,end,tmp);
            // 如果数组的这个子区间本身有序，无需合并
            if(nums[mid]<=nums[mid + 1]){
                return;
            }
            merge(nums,start,mid,end,tmp);
        }
    }

    public void merge(int[] nums, int start, int mid, int end, int[] tmp){
        int i=start,j=mid+1,t=start;
        while(i<=mid&&j<=end){
            //注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
            if(nums[i]<=nums[j]){
                tmp[t++]=nums[i++];
            }else{
                tmp[t++]=nums[j++];
            }
        }
        while(i<=mid){
            tmp[t++]=nums[i++];
        }
        while(j<=end){
            tmp[t++]=nums[j++];
        }
        //把临时数组元素拷贝回arr
        for(int k=start;k<=end;++k){
            nums[k]=tmp[k];
        }
    }


    /**
     * 归并版本二
     * 列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
     */
    private static final int INSERTION_SORT_THRESHOLD = 7;

    public int[] sortArray1(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort1(nums, 0, len - 1, temp);
        return nums;
    }

    /**
     * 对数组 nums 的子区间 [left, right] 进行归并排序
     * @param temp  用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
     */
    private void mergeSort1(int[] nums, int left, int right, int[] temp) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }
        int mid = left + (right - left) / 2;
        // Java 里有更优的写法，在 left 和 right 都是大整数时，即使溢出，结论依然正确
        // int mid = (left + right) >>> 1;
        mergeSort1(nums, left, mid, temp);
        mergeSort1(nums, mid + 1, right, temp);
        // 如果数组的这个子区间本身有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        mergeOfTwoSortedArray(nums, left, mid, right, temp);
    }

    /**
     * 对数组 arr 的子区间 [left, right] 使用插入排序
     * @param arr   给定数组
     * @param left  左边界，能取到
     * @param right 右边界，能取到
     */
    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    /**
     * 合并两个有序数组：先把值复制到临时数组，再合并回去
     * @param mid   [left, mid] 有序，[mid + 1, right] 有序
     * @param temp  全局使用的临时数组
     */
    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right + 1 - left);
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            } else {
                // temp[i] > temp[j]
                nums[k] = temp[j];
                j++;
            }
        }
    }
}
