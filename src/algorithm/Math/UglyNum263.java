package algorithm.Math;

/**
 * 263. 丑数
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * 示例 1：
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 * 示例 2：
 * 输入：n = 8
 * 输出：true
 * 解释：8 = 2 × 2 × 2
 * 示例 3：
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 * 示例 4：
 * 输入：n = 1
 * 输出：true
 * 解释：1 通常被视为丑数。
 * 提示：
 * -231 <= n <= 231 - 1
 */
public class UglyNum263 {

    /**
     * 264丑数II 优先队列 + 动态规划思路
     * 依据题意，非正数不可能为丑数
     * 丑数一定符合  num=2^a * 3^b * 5^c
     * a,b,c均为非负数，所以一个丑数一定可以整除2、3、5中任意一数
     */
    public boolean isUgly(int n) {
        if(n<=0) return false;
        while(n>1){
            if(n%5==0){
                n/=5;
            }else if(n%3==0){
                n/=3;
            }else if(n%2==0){
                n/=2;
            }else{
                return false;
            }
        }
        return true;
    }
}
