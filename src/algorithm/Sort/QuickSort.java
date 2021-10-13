package algorithm.Sort;

import java.util.Random;

/**
 * 912排序题
 */
public class QuickSort {

    /**
     * 快排版本--使用random函数增加了标杆节点选定的随机性
     * 降低了快排最坏时间复杂度O（N^2）的出现
     * 快排递归操作是先找pivot标杆处，然后再向两边递归处理
     */
    Random r=new Random();
    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public void quickSort(int[] nums, int start, int end){
        if(start>=end){
            return;
        }
        int pivot=findPivot(nums,start,end);
        quickSort(nums,start,pivot-1);
        quickSort(nums,pivot+1,end);
    }

    /**
     * nextInt（n）：该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
     * 标杆随机后放置在头部版本
     * 升序版本
     */
    public int findPivot(int[] nums, int start, int end){
        //随机一个标杆值放到尾索引处
        int randomIndex=r.nextInt(end-start+1)+start;
        int pivotNum=nums[randomIndex];
        nums[randomIndex]=nums[start];
        nums[start]=pivotNum;
        //counter维护小于标杆值所在的索引值，在counter索引前的数均小于标杆值
        int counter=start+1;
        for(int i=start+1;i<=end;++i){
            //降序只需要将< 改为 >
            if(nums[i]<pivotNum){
                //如果counter==i没必要交换
                if(counter<i){
                    int tmp=nums[counter];
                    nums[counter]=nums[i];
                    nums[i]=tmp;
                }
                ++counter;
            }
        }
        nums[start]=nums[counter-1];
        nums[counter-1]=pivotNum;
        return counter-1;
    }

    /**
     * 标杆随机后放置在尾部版本
     * 升序版本
     */
    public int findPivot1(int[] nums, int start, int end){
        //随机一个标杆值放到尾索引处
        int randomIndex=r.nextInt(end-start+1)+start;
        int pivotNum=nums[randomIndex];
        nums[randomIndex]=nums[end];
        nums[end]=pivotNum;
        //counter维护小于标杆值所在的索引值，在counter索引前的数均小于标杆值
        int counter=start;
        for(int i=start;i<end;++i){
            if(nums[i]<pivotNum){
                //如果counter==i没必要交换
                if(counter<i){
                    int tmp=nums[counter];
                    nums[counter]=nums[i];
                    nums[i]=tmp;
                }
                ++counter;
            }
        }
        nums[start]=nums[counter];
        nums[counter]=pivotNum;
        return counter;
    }
}
