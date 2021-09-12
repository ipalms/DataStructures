package algorithm.String;

/**
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * 示例 1:
 * 输入: s = "aba"
 * 输出: true
 * 示例 2:
 * 输入: s = "abca"
 * 输出: true
 * 解释: 你可以删除c字符。
 * 示例 3:
 * 输入: s = "abc"
 * 输出: false
 * 提示:
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 */
public class VerifyPalindromeII680 {

    /**
     * 验证回文串I 125
     * 双指针  找到第一对不相等的字符后向这个字符的两边继续查找
     */
    public boolean validPalindrome(String s) {
        int i=0,j=s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return find(s,i,j-1)||find(s,i+1,j);
            }
            ++i;
            --j;
        }
        return true;
    }

    public boolean find(String s, int i, int j){
        while(i<j){
            if(s.charAt(i)!=s.charAt(j))
                return false;
            ++i;
            --j;
        }
        return true;
    }
}
