package algorithm.DoublePointer;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class SortColors75 {

    public void sortColors4(int[] nums) {
        int len=nums.length;
        int i=0,j=0;
        for(int k=0;k<len;k++){
            if(nums[k]==1){
                swap(nums,j,k);
                j++;
            }else if(nums[k]==0){
                swap(nums,i,k);
                if(nums[k]==1){
                    swap(nums,j,k);
                }
                i++;
                j++;
            }
        }
    }


    /**
     * 75. 颜色分类
     * 十分像剑指 Offer 21. 调整数组顺序使奇数位于偶数前面、283. 移动零的升级版问题
     */

    /**
     * 两遍扫描
     * 先交换0到前面在交换1到前面
     */
    public void sortColors(int[] nums) {
        int n=nums.length;
        int i=0,j=0;
        while(j<n){
            if(nums[j]==0){
                swap(nums,i,j);
                i++;
            }
            j++;
        }
        int k=i,m=i;
        while(m<n){
            if(nums[m]==1){
                swap(nums,m,k);
                k++;
            }
            m++;
        }
    }

    /**
     * 一遍遍历
     * 其实相当于三个指针，指针p0来交换0，p1来交换1，还有一个用于遍历数组的指针（for循环变量i）
     */
    public void sortColors1(int[] nums) {
        int n=nums.length;
        int p0=0,p1=0;
        for(int i=0;i<n;i++){
            //在没找到1的时候p0和p1指向一致，找到1时p0所指位置为1，p1所指位置为2
            //在找到元素为2是p0和p1指针不动
            if(nums[i]==1){
                swap(nums,i,p1);
                p1++;
            }else if(nums[i]==0){
                swap(nums,i,p0);
                //如果p0小于p1，说明刚刚交换出一个1到后面，需要再将这个位置与p1指针交换
                //if(nums[k]==1){  这个条件也行
                if(p0<p1){
                    swap(nums,i,p1);
                }
                //遇到0时，这两个指针要一起加1
                p0++;
                p1++;
            }
        }
    }



    public void swap(int []nums, int i ,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
