package algorithm.Math;

import java.util.concurrent.locks.Lock;

/**
 * 400. 第 N 位数字
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]
 * 中找出并返回第 n 位数字
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class FindNthDigit400 {

    /**
     * 找规律题
     * 1-9之间有9==1*9*1个数（一位数）
     * 10-99之间有90*2==10*9*2个数（两位数）
     * 100-999之间有900*3==100*9*3个数（三位数）
     * 注意数据溢出的情况
     */
    public int findNthDigit(int n) {
        //数位（个位/十位/百位）
        int digit = 1;
        //属于该数位的所有数的起始点数（个位是1，十位是10，百位是100）
        long start = 1;
        //该数位的数一共的索引个数（不是数字个数）
        long count = digit * 9 * start;
        while (n > count) {
            // 找出 n 属于那个数位里的索引
            n -= count;
            ++digit;
            start *= 10;
            count = digit * 9 * start;
        }
        // 上面的循环结束后：
        // digit 等于原始的 n 所属的数位；start 等于原始的 n 所属数位的数的起始点
        // count 等于原始的 n 所属数位的索引总个数（不重要了，下面不用）
        // n 等于在当前数位里的第 n - 1 个索引（索引从 0 开始算起）

        // 算出原始的 n 到底对应那个数字
        long num = start + (n - 1) / digit;
        // 余数就是原始的 n 是这个数字中的第几位
        int remainder = (n - 1) % digit;
        //将该数字转为 string 类型，找到remainder 位，再转成 int
        return String.valueOf(num).charAt(remainder) - '0';
    }

}
