package algorithm.DivideAndConquer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * 示例：
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class CountSmaller315 {

    @Test
    public void test(){
        System.out.println(countSmaller(new int[]{5,2,6,1}));
    }
    /**
     * 分治思想：剑指offer51题的变形
     * 只是这里要求出每个位置上得元素后面比它小得个数，所以我们在将结果进行统计得时候
     * 需要求得值在原来数组得位置，但是直接归并排序原来得数组，会将位置打乱，所以我们加一层索引数组来操作。
     * 我们原本是通过nums[i]来获取值进行比较，加一层变为nums[indexNums[i]]来获取
     * 并且在真正在进行排序的是索引数组，原数组不变
     * 即索引数组在并的过程的值是趋向于原数组排序后原索引的变化：
     * 举个例子：对于数组【5,2,6,1】  最终的索引数组的结果是 【3,1,0,2】-->索引数组映射到的元素组就呈递增排序状态
     */
     public List<Integer> countSmaller(int[] nums) {
         //存放结果的数组
         int []res=new int[nums.length];
         //tmp存储索引数组排序的中间状态
         int []tmp=new int[nums.length];
         //构建索引数组
         int []index=new int[nums.length];
         //初始化索引数组
         for(int i=0;i<nums.length;++i){
             index[i]=i;
         }
         mergeCount(nums,0,nums.length-1,tmp,res,index);
         List <Integer> r=new ArrayList<>();
         //为了输出list题目要求的形式
         for(int i=0;i<res.length;++i){
             r.add(res[i]);
         }
         return r;
     }

    public void mergeCount(int[] nums, int left, int right, int[] tmp,int[] res,int []index){
        if(left>=right){
            return;
        }
        int mid=left+(right-left)/2;
        mergeCount(nums,left,mid,tmp,res,index);
        mergeCount(nums,mid+1,right,tmp,res,index);
        //如果mid+1对应值大于等于mid就没必要排序，因为已近呈正序了
        if(nums[index[mid]]>nums[index[mid+1]]){
            mergeAndCount(nums,left,mid,right,tmp,res,index);
        }
    }

    public void mergeAndCount(int[] nums, int left, int mid, int right, int[] tmp,int[] res,int []index){
        int i=left,j=mid+1,t=left;
        while(i<=mid&&j<=right){
            //通过索引数组来进行比较，这里相较51不同的是在<=的分支内计算贡献量
            //因为这个题目计算的是每个数右边小于该数的数目
            if(nums[index[i]]<=nums[index[j]]){
                res[index[i]]+=j-mid-1;
                //每次比较都要向tmp数组输入索引数组的变化情况
                tmp[t++]=index[i++];
            }else{
                tmp[t++]=index[j++];
            }
        }
        while(i<=mid){
            res[index[i]]+=j-mid-1;
            tmp[t++]=index[i++];
        }
        while(j<=right){
            tmp[t++]=index[j++];
        }
        //将tmp数组的变化赋值给索引数组
        for(int k=left;k<=right;++k){
            index[k]=tmp[k];
        }
    }

    /**
     * 还可以使用树状数组解决问题
     */
}
