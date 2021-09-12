package algorithm.SlidingWindow;

import org.junit.Test;

/**
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过 10^4。
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class CharacterReplacement424 {


    @Test
    public void test(){
        String s="AABDACCDCCAC";
        System.out.println(characterReplacement(s,2));
    }

    /**
     * 滑动窗口
     * 这题滑动窗口的特点是窗口大小只会变大或者保持不变(保持不变的情况是左右指针会同时+1)
     * 此题和 1004 最大连续1的个数 III为同一类型的
     * debug看一下更清晰
     */
    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        //left:左边界，用于滑动时减去头部或者计算长度
        //right:右边界，用于加上划窗尾巴或者计算长度
        int left = 0, right = 0;
        while (right < n) {
            int indexR = s.charAt(right) - 'A';
            num[indexR]++;
            //求窗口中曾出现某字母的最大次数
            //计算某字母出现在某窗口中的最大次数，窗口长度只能增大或者不变（注意后面left指针只移动了0-1次）
            //这样做的意义：我们求的是最长，如果找不到更长的维持长度不变返回结果不受影响
            //唯一能使滑动的窗口长度继续增长的就是出现一个比当前维护的maxCount次数还大的字符
            //即在这个窗口长度中出现的最多的字符的次数要大于上一次形成这个窗口是多数元素的出现次数
            maxn = Math.max(maxn, num[indexR]);
            //说明此时k不够用
            //把其它不是最多出现的字符替换以后，都不能填满这个滑动的窗口，这个时候须要考虑左边界向右移动
            if (right - left + 1 - maxn > k) {
                //移出滑动窗口的时候，频数数组须要相应地做减法
                num[s.charAt(left) - 'A']--;
                left++;
            }
            //走完这里的时候，其实right会多走一步
            right++;
        }
        //因为right多走一步，结果为(right-1)-left+1==right-left
        return right - left;
    }
}
