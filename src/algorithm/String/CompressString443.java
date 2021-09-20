package algorithm.String;

/**
 *443. 压缩字符串
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 * 示例 1：
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * 解释：
 * "aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * 示例 2：
 * 输入：chars = ["a"]
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * 解释：
 * 没有任何字符串被替代。
 * 示例 3：
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * 解释：
 * 由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 * 注意每个数字在数组中都有它自己的位置。
 * 提示：
 * 1 <= chars.length <= 2000
 * chars[i] 可以是小写英文字母、大写英文字母、数字或符号
 */
public class CompressString443 {

    /**
     * 我的思路：
     * 维护上一个不同于当前字符的长度preLen，字符preChar
     * 以及维护当前处理到的位置的位置pre
     * 也算双指针
     */
    public int compressMy(char[] chars) {
        int len=chars.length,pre=0,preLen=1;
        //tmp是用来反转preLen的
        char []tmp=new char[4];
        char preChar=chars[0];
        //等于号是为了兼容处理最后一个字符等于上一个字符而不能进行统一处理的情况
        for(int i=1;i<=len;++i){
            if(i<len&&chars[i]==preChar){
                ++preLen;
            }else{
                chars[pre++]=preChar;
                //处理将长度压缩进目标字符串，可以使用tmp数组反转（也可以反转结果char的某段区域）
                if(preLen>1){
                    int j=0;
                    while(preLen>0){
                        tmp[j++]=(char)('0'+preLen%10);
                        preLen/=10;
                    }
                    --j;
                    while(j>=0){
                        chars[pre++]=tmp[j--];
                    }
                }
                if(i<len){
                    preChar=chars[i];
                    preLen=1;
                }
            }
        }
        return pre;
    }

    /**
     * 双指针
     * 令输入数组cs长度为n。
     * 使用两个指针i和j分别指向「当前处理到的位置」和[答案待插入的位置」:
     * 1. i指针-直往后处理,每次找到字符相同的连续一 段[i, idx)，令长度为cnt;
     * 2.将当前字符插入到答案,并让j指针后移: cs[j++] = cs[i]
     * 3.检查长度cnt否大于1,如果大于1,需要将数字拆分存储。由于简单的实现中,我们只能从个位开始处理cnt,
     *  因此需要使用start 和end记录下存储数字的部分，再处理鯇cnt后，将[start, end)部分进行翻转,并更新j指针;
     * 4.更新i为idx,代表循环处理下一字符。
     */
    public int compress(char[] cs) {
        int n = cs.length;
        int i = 0, j = 0;
        while (i < n) {
            int idx = i;
            while (idx < n && cs[idx] == cs[i]) idx++;
            int cnt = idx - i;
            cs[j++] = cs[i];
            if (cnt > 1) {
                int start = j, end = start;
                while (cnt != 0) {
                    cs[end++] = (char)((cnt % 10) + '0');
                    cnt /= 10;
                }
                //反转字符数组这个区间
                reverse(cs, start, end - 1);
                j = end;
            }
            i = idx;
        }
        return j;
    }

    private void reverse(char[] cs, int start, int end) {
        while (start < end) {
            char t = cs[start];
            cs[start] = cs[end];
            cs[end] = t;
            start++; end--;
        }
    }
}
