package algorithm.String;

/**
 * 696. 计数二进制子串
 * 给定一个字符串 s，统计并返回具有相同数量 0 和 1 的非空（连续）子字符串的数量，
 * 并且这些子字符串中的所有 0 和所有 1 都是成组连续的。
 * 重复出现（不同位置）的子串也要统计它们出现的次数。
 * 示例 1：
 * 输入：s = "00110011"
 * 输出：6
 * 解释：6 个子串满足具有相同数量的连续 1 和 0 ："0011"、"01"、"1100"、"10"、"0011" 和 "01" 。
 * 注意，一些重复出现的子串（不同位置）要统计它们出现的次数。
 * 另外，"00110011" 不是有效的子串，因为所有的 0（还有 1 ）没有组合在一起。
 * 示例 2：
 * 输入：s = "10101"
 * 输出：4
 * 解释：有 4 个子串："10"、"01"、"10"、"01" ，具有相同数量的连续 1 和 0 。
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 为 '0' 或 '1'
 */
public class CountBinarySubstrings696 {

    /**
     * 注意零  一 是要连续的
     * 滑动窗口可能可以做但是很麻烦
     */

    /**
     * 双变量记录
     * 1.定义 curCount 用来累加当前正在遍历的相等的值的数量；
     * 2.定义 preCount 用来保存上一次curCount的值；
     * 3.当遍历遇到的值跟前一个值相等时，curCount++；
     * 4.当遍历遇到的值跟前一个值不相等时，用 ans 累加 preCount 和 curCount 之间的最小值；把curCount的值赋给preCount，并把curCount置为1，重新开始累加值；
     * 5.遍历结束之后，再进行一次 ans 累加最小值的操作。
     */
    public int countBinarySubstrings(String s) {
        int preCount = 0;
        int curCount = 1;
        int ans = 0;
        char[] array = s.toCharArray();
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                curCount += 1;
            } else {
                ans += Math.min(preCount, curCount);
                preCount = curCount;
                curCount = 1;
            }
        }
        //补加一次
        ans += Math.min(preCount, curCount);
        return ans;
    }

    //同上面双变量的思路，但是对结果累加的判断进行改变
    public int countBinarySubstrings1(String s) {
        int preCount = 0;
        int curCount = 1;
        int ans = 0;
        char[] array = s.toCharArray();
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                curCount += 1;
            } else {
                preCount = curCount;
                curCount = 1;
            }
            //改变点--前一个数字出现的次数 >= 后一个数字出现的次数，则一定包含满足条件的子串
            if(preCount>=curCount){
                ++ans;
            }
        }
        return ans;
    }

}
