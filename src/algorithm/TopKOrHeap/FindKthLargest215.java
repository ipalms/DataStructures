package algorithm.TopKOrHeap;

import java.util.*;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 提示：
 * 1 <= k <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 */
public class FindKthLargest215 {
    /**
     * 解题角度
     * 最坏是使用排序（快排）
     * 其次就是使用快速选择 或者 使用小顶堆/优先队列解题
     * 小顶堆就是堆数组的升序排序，大顶堆就是堆数组的降序排序
     * compareTo(int a,int b)  a-b：升序排序  b-a：降序排序
     */
    public static void main(String[] args) {
        int []nums={3,1,2,4};
        System.out.println(findKthLargest(nums,2));
    }


    /**
     * 后面尝试用快速选择写该题
     */
    class MyQuickSelect{
        Random r=new Random();
        int len;
        public int findKthLargest(int[] nums, int k) {
            len=nums.length;
            return findKth(nums,0,nums.length-1,k);
        }

        /**
         * 一次只进入一支分支
         * 遍历次数为 n+n/2+n/4...+1-->最坏情况只需大概2*n循环次数，时间复杂度为O（N）
         */
        public int findKth(int[] nums,int left,int right,int k){
            //如果left==right就说明left下标的该数正好是第k大的数
            if (left == right) return nums[right];
            int pivot=findPivot(nums,left,right);
            //如果尾节点标杆到标杆之间的元素大于k个说明第k大元素在后半部分
            if(len-pivot>k){
                return findKth(nums,pivot+1,right,k);
                //如果尾节点标杆到标杆之间的元素等于k个说明第k大元素正好在标杆处
            }else if(len-pivot==k){
                return nums[pivot];
            }else{//反之第k大元素在前半部分
                return findKth(nums,left,pivot-1,k);
            }
        }

        /**
         * 快排找pivot点一样的代码+随机化标杆处理
         */
        public int findPivot(int[] nums,int left,int right){
            int index=left+r.nextInt(right-left);
            swap(nums,index,right);
            int p=nums[right],curr=left;
            for(int i=left;i<right;++i){
                if(nums[i]<p){
                    swap(nums,curr,i);
                    ++curr;
                }
            }
            swap(nums,curr,right);
            return curr;
        }

        public void swap(int[] nums,int i,int j){
            int tmp=nums[i];
            nums[i]=nums[j];
            nums[j]=tmp;
        }
    }

    /**
     * 这个版本是将快排改成降序的
     */
    class MyQuickSelect2{
        public int findKthLargest(int[] nums, int k) {
            int left=0,right=nums.length-1;
            return find(nums,left,right,k);
        }

        //这里就是从前往后找第k个了
        private int find(int[] nums,int left,int right,int k){
            if(left==right) return nums[left];
            int pivot=findPivot(nums,left,right);
            if(pivot+1<k){
                return find(nums,pivot+1,right,k);
            }else if(pivot+1==k){
                return nums[pivot];
            }else{
                return find(nums,left,pivot-1,k);
            }
        }

        //降序找pivot
        Random r=new Random();
        private int findPivot(int[] nums,int left,int right){
            int ran=r.nextInt(right-left+1)+left;
            swap(nums,right,ran);
            int target=nums[right],curr=left;
            for(int i=left;i<right;++i){
                if(nums[i]>target){
                    if(i!=curr){
                        swap(nums,i,curr);
                    }
                    ++curr;
                }
            }
            nums[right]=nums[curr];
            nums[curr]=target;
            return curr;
        }

        private void swap(int[] nums,int i,int j){
            int tmp=nums[i];
            nums[i]=nums[j];
            nums[j]=tmp;
        }
    }

    // 基于堆排序的选择方法
    // 因为只需要维护K个长度的最小堆
    // 所以这种解法只需要编写 堆化 和 下沉两个操作就行
    class HeapSort{
        public int findKthLargest(int[] nums, int k) {
            buildMinHeap(nums, k);
            for (int i = k; i < nums.length ; ++i) {
                if (nums[0]>=nums[i]){
                    continue;
                }
                swap(nums, 0, i);
                minHeapify(nums, 0, k);
            }
            return nums[0];
        }

        public void buildMinHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                minHeapify(a, i, heapSize);
            }
        }

        // 下沉操作直接发现符合条件直接交换数值更简单
        public void minHeapify1(int[] a, int i, int heapSize) {
            for(int k=2*i+1;k<heapSize;k=2*k+1){
                if ((k+1)<heapSize&&a[k]>a[k+1]){
                    ++k;
                }
                if (a[k]>=a[i]){
                    break;
                }else{
                    swap(a,i,k);
                    i=k;
                }
            }
        }

        public void minHeapify(int[] a, int i, int heapSize) {
            int tmp=a[i];
            for(int k=2*i+1;k<heapSize;k=2*k+1){
                if ((k+1)<heapSize&&a[k]>a[k+1]){
                    ++k;
                }
                if (a[k]>=tmp){
                    break;
                }else{
                    a[i]=a[k];
                    i=k;
                }
            }
            a[i]=tmp;
        }


        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


    /**
     * 一次加载所有元素进队列
     * java的优先队列  优先队列不传排序器则默认使用小顶堆--从小到大排序
     * 注意PriorityQueue的声明的前后都要打上范型的方括号，不然内部使用Comparator比较的数据类型需要自己去指定
     */
    public static int findKthLargest(int[] nums, int k) {
        /**
         * 优先队列排序的另一种写法
         * PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
         *      @Override
         *      public int compare(Integer o1, Integer o2) {
         *         return o2-o1;
         *      }
         * });
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b-a);  //队列顶部从大到小排列  a-b代表从小到大
        for(int num : nums) {
            queue.add(num);
        }
        for(int i=0; i<k-1; i++) {
            queue.poll();
        }
        return queue.peek();
    }

    /**
     * 优先队列 使用小顶堆+维护k个长度的队列
     * 时间复杂度O(N*logK)
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(k,(a, b) -> a-b);
        for (int num : nums) {
            if (minQueue.size() < k )
                minQueue.offer(num);
            //如果队列中已近有k个长度的元素
            else if (num> minQueue.peek()) {
                minQueue.poll();
                minQueue.offer(num);
            }
        }
        return minQueue.peek();
    }

    /**
     *  完全由优先队列自己维护
     *  优先队列 使用小顶堆+维护k+1个长度的队列
     *  对于比较规则较为复杂的情况（在if语句中比较难写下，应该维护k+1个长度的优先队列让优先队列自己来比较）
     */
    public int findKthLargest5(int[] nums, int k) {
        int len = nums.length;
        // 使用一个含有 k+1 个元素的最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k+1, (a, b) -> a - b);
        for (int num : nums) {
            minHeap.offer(num);
            if(minHeap.size()>k){
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * 快速选择1
     */
    public int findKthLargest3(int[] nums, int k) {
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
    //第二种快排（使用尾部元素作为基准） pivot标杆选择尾部元素  这种是只在一端比较交换的快排
    private int partition(int[] array, int begin, int end) {
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            //从大到小排序
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

    private static Random random = new Random(System.currentTimeMillis());
    /**
     * 快速选择2--快排（快速选择）极端情况解决
     * 说明：最极端的是顺序数组与倒序数组，此时递归树画出来是链表，时间复杂度是 O(N^2)根本达不到减治的效果。
     * 本题必须随机初始化 pivot 元素，否则通过时间会很慢，因为测试用例中有极端测试用例。
     * 为了应对极端测试用例，使得递归树加深，可以在循环一开始的时候，随机交换第1个元素与它后面的任意1个元素的位置；
     */
    public int findKthLargest4(int[] nums, int k) {
        int len = nums.length;
        int target = len - k;
        int left = 0;
        int right = len - 1;
        while (true) {
            int index = partition(nums, left, right);
            if (index < target) {
                left = index + 1;
            } else if (index > target) {
                right = index - 1;
            } else {
                return nums[index];
            }
        }
    }

    // 在区间 [left, right] 这个区间执行 partition 操作
    private int partition1(int[] nums, int left, int right) {
        //在区间随机选择一个元素作为标定点
        if (right > left) {
            int randomIndex = left + 1 + random.nextInt(right - left);
            swap(nums, left, randomIndex);
        }
        //这样标点就变成了一个随机数，遇到顺序数组或逆序数组（较为整齐的数组）不用递归这么多次
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            //从小到达排序
            if (nums[i] < pivot) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
