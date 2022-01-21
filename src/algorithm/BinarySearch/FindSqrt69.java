package algorithm.BinarySearch;

import org.junit.Test;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class FindSqrt69 {

    @Test
    public void test(){
        System.out.println(myCube(100));
    }

    //值二分--起始right位于x/2
    public int mySqrt(int x) {
        // 特殊值判断
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int left = 1;
        int right = x / 2;
        // 在区间 [left..right] 查找目标元素
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            // 注意：这里为了避免乘法溢出，改用除法
            if (mid > x / mid) {
                // 下一轮搜索区间是 [left..mid - 1]
                right = mid - 1;
            } else {
                // 下一轮搜索区间是 [mid..right]
                left = mid;
            }
        }
        return left;
    }


    //二分--起始right不位于x
    public int mySqrt2(int x) {
        int left=0,right=1;
        while(left<right){
            int mid=left+(right-left)/2;
            if((long)mid*mid<=x){
                if((long)(mid+1)*(mid+1)>x){
                    return mid;
                }
                left=mid+1;
                right+=mid+1;
            }else{
                right=mid-1;
            }
        }
        return left;
    }

    /**
     * 牛顿迭代法是求精确到某一位的有效方法（最容易实现的方法）
     * 牛顿迭代法  --时间复杂度 O（logX）
     * 不断取Xi+1=(Xi+C/Xi)2  --Xi+1会无限迫近于根号C
     * 为什么选择 x为什么选择x0=C作为初始值？
     * 因为 y = x^2 - C有两个零点 sqrt{C}和−sqrt{C}
     * 如果我们取的初始值较小，可能会迭代到 -sqrt{C}这个零点，而我们希望找到的是sqrt{C}
     * 因此选择x0=C作为初始值，每次迭代均有 Xi+1<Xi
     * 迭代到何时才算结束？
     * 每一次迭代后，我们都会距离零点更进一步，所以当相邻两次迭代得到的交点非常接近时，我们就可以断定，
     * 此时的结果已经足够我们得到答案了。一般来说，可以判断相邻两次迭代的结果的差值是否小于一个极小的非负数
     * 一般可以取 10^{-6}或 10^{-7}
     */
    public double mySqrt3(int x) {
        if (x == 0) {
            return 0;
        }
        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-3) {
                break;
            }
            x0 = xi;
        }
        return  ((int)(x0*100))/100.0;
    }


    /**
     * 变形题--可以用牛顿迭代法求各种零点
     * 如 y=x^3
     * 得：Xi+1=(2Xi+C/Xi^2)3
     */
    public int myCube(int x) {
        if(x==0) return 0;
        double x0=0;
        double x1=x;
        while(Math.abs(x0-x1)>1e-9){
            x0=x1;
            x1=(2*x0+x/x0/x0)/3;
        }
        return (int)x0;
    }

    /**
     * 使用二分保留两位小数---保留小数最好使用牛顿迭代法
     */
    public float mySqrtx(int x) {
        if(x==0) return 0;
        int l=1,r=x/2;
        while(l<r){
            int mid=l+(r-l+1)/2;
            if(x/mid>=mid){
                l=mid;
            }else{
                r=mid-1;
            }
        }
        double left=l,right=l+1;
        while(left+0.01<right){
            double mid=left+(right-left+0.01)/2.0;
            if(x/mid>=mid){
                left=mid;
            } else{
                right=mid-0.01;
            }
        }
        return (float)((int)(left*100)/100.0);
    }
}
