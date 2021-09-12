package algorithm.BinarySearch;

/**
 * 1095. 山脉数组中查找目标值
 * （这是一个 交互式问题 ）
 * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
 * 如果不存在这样的下标 index，就请返回 -1。
 * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
 * 首先，A.length >= 3
 * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
 * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 * MountainArray.length() - 会返回该数组的长度
 * 注意：
 * 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。
 * 示例 1：
 * 输入：array = [1,2,3,4,5,3,1], target = 3
 * 输出：2
 * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 * 示例 2：
 * 输入：array = [0,1,2,4,2,1], target = 3
 * 输出：-1
 * 解释：3 在数组中没有出现，返回 -1。
 * 提示：
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 */
public class FindInMountainArray1095 {

    class MountainArray{
        public int get(int index) {return -1;}
        public int length() { return -1;}
    }

    /**
     * 题目要求调用get的次数不能超过100次，即不能复杂度较大--二分
     * 思路，先二分找到数组最大值所在索引，分别往数组左右查找
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int left=0,right=mountainArr.length()-1;
        //通过二分找到数组最大值所在索引
        while(left<right){
            int mid=left+(right-left)/2;
            if(mountainArr.get(mid)<mountainArr.get(mid+1)){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        //查找最大数左侧有无目标数字
        int index=findIndex(target,mountainArr,0,left,false);
        if(index==-1){
            //左侧没有查询最大数右侧
            index=findIndex(target,mountainArr,left+1,mountainArr.length()-1,true);
        }
        return index;
    }


    //reverse 判断数组是正序还是逆序的
    public int findIndex(int target, MountainArray arr,int left,int right,boolean reverse){
        while(left<=right){
            int mid=left+(right-left)/2;
            int curr=arr.get(mid);
            if(curr>target){
                if(reverse){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }else if(curr<target){
                if(reverse){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                return mid;
            }
        }
        return -1;
    }
}
