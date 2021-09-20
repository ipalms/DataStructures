package algorithm.KMP;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出needle
 * 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * 说明：
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
 * 这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * 示例 1：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 * 输入：haystack = "", needle = ""
 * 输出：0
 * 提示：
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class IndexOf28 {

    /**
     * 字符串匹配--另一道字符串匹配可以做的题459.重复的子字符串（没做）
     * KMP算法实现
     * 链接：https://leetcode-cn.com/problems/implement-strstr/solution/shua-chuan-lc-shuang-bai-po-su-jie-fa-km-tb86/
     */
    public int strStr(String haystack, String needle) {
        // KMP算法：如果已经匹配的字符串包含相同的前缀和后缀，遇到下一个不匹配的位置时，指向needle的指针跳转到前缀的后一个位置，还是不匹配的话，再往前跳转后继续比较；先构造一个next数组来记录needle指针跳转的位置
        int n=haystack.length(), m=needle.length();
        if(m==0) return 0;
        // 先构造next数组，next数组中的元素表示当前两个元素不匹配时，needle指针要跳转的位置
        // haystack: [a, b, e, a, b, a, b, e, a, b, f]
        // needle:   [a, b, e, a, b, f]
        // next:     [0, 0, 0, 1, 2, 0]
        int[] next = new int[m];
        for(int i=1,j=0; i<m; i++){
            while(j>0 && needle.charAt(i)!=needle.charAt(j))
                j = next[j-1]; // 一直和前一位置的值比较，直到遇到相等的字符或者j=0；j通过next[j-1]来回退
            if(needle.charAt(i)==needle.charAt(j)) j++;
            next[i] = j;
        }
        // 利用next数组进行跳转匹配，不再需要回退haystack的指针i
        for(int i=0,j=0; i<n; i++){
            // 匹配不成功，needle指针j回退并继续比较
            while(j>0 && haystack.charAt(i)!=needle.charAt(j))
                j = next[j-1];
            if(haystack.charAt(i)==needle.charAt(j)) j++;
            if(j==m) return i - m + 1;
        }
        return -1;
    }

    /**
     * 简易版字符串匹配暴力算法
     * 时间复杂度：n为原串的长度，m为匹配串的长度。
     * 其中枚举的复杂度为O(n−m)，构造和比较字符串的复杂度为O(m)。整体复杂度为O((n−m)∗m)。
     * 空间复杂度：O(1)。
     */
    public int strStrViolence(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        char[] s = ss.toCharArray(), p = pp.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i;
        }
        return -1;
    }

    /**
     * 暴力的实现（本以为可以使用滑动窗口）
     */
    public int strStrMy(String haystack, String needle) {
        int n=haystack.length(),m=needle.length();
        if(m==0) return 0;
        if(n==0||n<m) return -1;
        int left=0,right=0,count=0;
        char first=needle.charAt(0);
        while(right<n){
            if(haystack.charAt(right)==first){
                left=right;
                while(count<m&&right<n){
                    char r1=haystack.charAt(right);
                    if(r1==needle.charAt(count)){
                        count++;
                    }else{
                        right=left;
                        count=0;
                        break;
                    }
                    right++;
                }
                if(count==m){
                    return left;
                }
            }
            right++;
        }
        return -1;
    }
}
