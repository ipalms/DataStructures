package algorithm.BitOperation;

/**
 * 231  2的幂
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 * 示例 3:
 * 输入: 218
 * 输出: false
 */
public class IsPowerOfTwo231 {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo2(128));
    }
    /**
     * 解释
     * 2的幂次方在二进制下，只有1位是1，其余全是0。         例如:8---00001000。
     * 负数的在计算机中二进制表示为补码(原码->正常二进制表示，原码按位取反(0-1,1-0)，最后再+1。
     * 然后两者进行与操作，得到的肯定是原码中最后一个二进制的1。
     * 例如8&(-8)->00001000 & 11111000 得 00001000，即8
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
       return (n > 0) && (n & -n) == n;
    }

    public static boolean isPowerOfTwo2(int n) {
        return (n>0)&&((n&n-1)==0);
    }
}
