package algorithm.BinarySearch;

/**
 * 367. 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 * 提示：
 * 1 <= num <= 2^31 - 1
 */
public class FindIsPerfectSquare367 {

    //二分--与69题一样，同时也可以使用牛顿迭代法
    public boolean isPerfectSquare(int num) {
        if(num==1) return true;
        int left=0,right=num/2;
        while(left<right){
            int mid=left+(right-left+1)/2;
            if(num/mid<mid){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return left*left==num;
    }

}
