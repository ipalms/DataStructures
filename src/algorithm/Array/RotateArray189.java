package algorithm.Array;

/**
 * 189. 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 进阶：
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 * 通过次数314,869提交次数694,732
 */
public class RotateArray189 {

    /**
     * 使用额外内存
     * 空间O（k）
     */
    public void rotateMy(int[] nums, int k) {
        if(k==0) return;
        int n=nums.length;
        k=k%n;
        int []tmp=new int[k];
        for(int i=n-k,j=0;i<n;++i){
            tmp[j++]=nums[i];
        }
        for(int i=n-k-1;i>=0;--i){
            nums[i+k]=nums[i];
        }
        for(int i=0;i<k;++i){
            nums[i]=tmp[i];
        }
        //库函数直接拷贝做法
        //if (n - k >= 0) System.arraycopy(nums, 0, nums, k, n - k - 1 + 1);
        //System.arraycopy(tmp, 0, nums, 0, k);
    }

    /**
     * 反转数组做法
     * 先整体反转，再对旋转点两侧再次进行反转
     */
    public void rotate(int[] nums, int k) {
        k%=nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public void reverse(int[] nums, int i, int j){
        while(i<j){
            int tmp=nums[j];
            nums[j--]=nums[i];
            nums[i++]=tmp;
        }
    }

    /**
     *  环状替代  ---数学方法
     *  数据a移到k个位置后面时，将要被覆盖的数先保存起来，然后下一轮以这个被零时保存的数开始移动其到k个位置后
     *  对于一个长度为n的数组，整体移动k个位置
     *  当n和k的最大公约数等于1的时候：1次遍历就可以完成交换；比如n=5,k=3
     *  当n和k的最大公约数不等 1的时候：1次遍历是无法完成的所有元素归位，需要m(最大公约数)次
     *  那么如何判断所有的分组都执行归位了呢？ 可以有两种方法来控制
     *  第一种：我们就用最大公约数m来控制外循环，代表总共有m轮循环
     *  第二种：由于nn个元素归位需要n次交换，所以我们定义一个count代表交换次数，当 count = n 时完成
     */
    public void rotate3(int[] nums, int k) {
        int len  = nums.length;
        k = k % len;
        //记录交换位置的次数，n个位置一共需要换n次
        int count = 0;
        for(int start = 0; count < len; start++) {
            int cur = start;       // 从0位置开始换位子
            int pre = nums[cur];
            do{
                int next = (cur + k) % len;
                int temp = nums[next];    //先临时被保存
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            }while(start != cur)  ;     //循环暂停，回到起始位置，角落无人
        }
    }

    /**
     * 求最大公约数函数-- not understand
     */
    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
}
