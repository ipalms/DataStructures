package algorithm.String;

import org.junit.Test;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * 提示：
 * 1 <= s.length <= 2 * 10^5
 * 字符串 s 由 ASCII 字符组成
 */
public class VerifyPalindrome125 {

    /**
     * 680验证回文II
     * 判断回文三种思路---双指针、栈、反转后逐个匹配
     * 本题主要考的是双指针
     * 和一些字符和串(Character、String)的API(数据的预处理过程)
     */
    @Test
    public void test() {
        System.out.println(isValid('A'));
        System.out.println(toLower('1'));
        System.out.println(Character.toLowerCase('A'));
        System.out.println(Character.toUpperCase('a'));
        System.out.println(Character.isLetterOrDigit('A'));
    }

    /**
     * 不用内置API的版本 --双指针
     */
    public boolean isPalindrome(String s) {
        int p1 = 0, p2 = s.length() - 1;
        while (p1 < p2) {
            char l = s.charAt(p1);
            if (!isValid(l)) {
                ++p1;
                continue;
            }
            char r = s.charAt(p2);
            if (!isValid(r)) {
                --p2;
                continue;
            }
            //这里可以使用toLowerCase来代理判断
            if (l == r || (r >= 'A' && l >= 'A' && Math.abs(r - l) == 32)) {
                ++p1;
                --p2;
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isValid(char k) {
        return (k>='a'&&k<='z')||(k>='A'&&k<='Z')||(k>='0'&&k<='9');
    }

    public char toLower(char c){
        if(c>='A'&&c<='Z'){
            return (char)(c+32);
        }
        return c;
    }


    /**
     * 使用内置API
     */
    public boolean isPalindrome1(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    /**
     * StringBuilder
     */
    public boolean isPalindrome2(String s) {
        StringBuilder res = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                res.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer reverse = new StringBuffer(res).reverse();
        return res.toString().equals(reverse.toString());
    }
}
