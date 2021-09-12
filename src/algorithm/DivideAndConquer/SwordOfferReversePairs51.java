package algorithm.DivideAndConquer;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 * 解释：
 *  7、5    7、6    7、4    5、4    6、4 五队
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class SwordOfferReversePairs51 {

    /**
     * 可以使用分治的思想（或者归并排序的套路--与归并排序的代码几乎一模一样）
     * 在合并的过程中可以算出子数组的贡献值
     * 假设我们有两个已排序的序列等待合并，分别是 L={8,12,16,22,100}和R={9,26,55,64,91}。
     * L是前面的那个数组，R是后面的那个数组，那么当L所指的元素大于R所指元素时
     * L指针后面的所有元素都会大于R当中的元素，只要我们在遍历的过程中把这些贡献值相加就行
     */
    public int reversePairs(int[] nums) {
        //这样修改了原数组，当然我们可以拷贝一个nums数组
        int []tmp=new int[nums.length];
        return mergeCount(nums,0,nums.length-1,tmp);
    }

    public int mergeCount(int[] nums, int left, int right, int []tmp){
        if(right<=left){
            return 0;
        }
        //与归并排序一致的操作
        int count=0;
        int mid=left+(right-left)/2;
        count+=mergeCount(nums,left,mid,tmp);
        count+=mergeCount(nums,mid+1,right,tmp);
        if(nums[mid]<=nums[mid+1]){
            return count;
        }
        return count + mergeAndCount(nums, left, mid, right, tmp);
    }

    public int mergeAndCount(int[] nums, int left, int mid, int right, int []tmp){
        int i=left,j=mid+1,t=left,count=0;
        while(i<=mid&&j<=right){
            if(nums[i]<=nums[j]){
                tmp[t++]=nums[i++];
            }else{
                //在此处统计贡献量
                count+=mid+1-i;
                tmp[t++]=nums[j++];
            }
        }
        while(i<=mid){
            tmp[t++]=nums[i++];
        }
        while(j<=right){
            tmp[t++]=nums[j++];
        }
        for(int k=left;k<=right;++k){
            nums[k]=tmp[k];
        }
        return count;
    }

    /**
     * 先拷贝到tmp数组在排序比较的版本
     */
    private int mergeAndCount1(int[] nums, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = tmp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = tmp[i];
                i++;
            } else if (tmp[i] <= tmp[j]) {
                nums[k] = tmp[i];
                i++;
            } else {
                nums[k] = tmp[j];
                j++;
                //在此处统计贡献量
                count += (mid - i + 1);
            }
        }
        return count;
    }

    /**
     * 还有使用线段树的题解--不懂
     */
}
