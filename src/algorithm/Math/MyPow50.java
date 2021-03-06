package algorithm.Math;

import org.testng.annotations.Test;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^-2 = 1/2^2 = 1/4 = 0.25
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 */
public class MyPow50 {
    /**
     * 一些可以用到数学思想的题
     * 50（快速幂--数字拆成二进制）、69、367(牛顿迭代--找方程的零点)
     */
    @Test
    public void test(){
        myPow2(2,10);
    }

    /**
     * 快速幂思想
     * 对于任何十进制正整数n，设其二进制为"bm..b3b2b1" (b为进制某位值, i∈[1,m])，则有:
     * 二进制转十进制: n= 1*b1 + 2*b2 + 4*b3 +...+2^m-1*bm (即二进制转t进制公式)
     * 幂的二进制展开: x= x^1*b1 + 2*b2 + 4*b3 +...+2^m-1*bm
     * 根据以上推导，可把计算x^n转化为解决以下两个问题:
     * 计算x,x^2,.... x^2^m-1的值:循环赋值操作x= x^2即呵;
     * 获取二进制各位b1, b2, b3,.... bm的值:循环执行以下操作即可。
     * 1. n&1(与操作) :判断二进制最右一位是否为1
     * 2.n>> 1(移位操作) : n右移一位(可理解为删除最后一位)
     *
     * 时间复杂度 O(logn) ： 二分的时间复杂度为对数级别。
     * 空间复杂度O(1) ：res, b等变量占用常数大小额外空间。
     */


    /**
     * Java代码中int32变量n∈[−2147483648,2147483647]，因此当n=−2147483648时执行n=−n会因越界而赋值出错。
     * 解决方法是先将n存入long变量b，后面用b操作即可。
     */
    public double myPow(double x, int n) {
        if(x == 0.0f) return 0.0d;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            //这里不能使用 b=-n 不然对于−2147483648 还是转换不来
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) //等价于b%2== 1，即二进制下这一位非0，要计算到结果中去
                res *= x;
            //不断平方
            x *= x;
            b >>= 1;  // 等价于b/=2;
        }
        return res;
    }

    /**
     * 递归法
     * 时间复杂度 O(logn) ： 二分的时间复杂度为对数级别。
     * 空间复杂度O(logn) ： 递归调用栈空间
     */
    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

}
