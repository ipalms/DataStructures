package algorithm.DynamicProgramming;

/**
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
public class CountSubstrings647 {

    /**
     * 与 5最长回文子串 几乎一样
     * 同样可以使用区间DP  或者中间扩散做
     */


    /**
     * 中间扩展法--简单易实现
     */
    public int countSubstrings(String s) {
        int count=0;
        for(int i=0;i<s.length();++i){
            count+=findSubstring(s,i,i);
            count+=findSubstring(s,i,i+1);
        }
        return count;
    }

    public int findSubstring(String s,int left,int right){
        int nums=0;
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            ++nums;
            --left;
            ++right;
        }
        return nums;
    }

    /**
     * 中间扩展法2
     */
    public int countSubstrings3(String s) {
        // 中心扩展法
        int ans = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            // 首先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
            // 大致的关系出来了，可以选择带两个特殊例子进去看看是否满足。
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    /**
     * 区间DP
     */
    public int countSubstrings1(String s) {
        int n=s.length(),count=n;
        boolean [][]dp=new boolean[n][n];
        for(int i=0;i<n;++i){
            dp[i][i]=true;
        }
        for(int L=2;L<=s.length();++L){
            for(int left=0;left<s.length()-L+1;++left){
                int right=left+L-1;
                if(s.charAt(left)==s.charAt(right)&&(L==2||dp[left+1][right-1])){
                    dp[left][right]=true;
                    ++count;
                }
            }
        }
        return count;
    }
}
