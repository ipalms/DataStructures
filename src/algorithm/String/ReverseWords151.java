package algorithm.String;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151. 翻转字符串里的单词
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * 说明：
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
 * 示例 4：
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * 进阶：
 * 请尝试使用 O(1) 额外空间复杂度的原地解法
 * (因为JAVA语言字符串是不可变的，所以无法仅使用O（1）空间复杂度完成)。
 */
public class ReverseWords151 {

    @Test
    public void test(){
        System.out.println(' '==31);
    }

    /**
     * 双指针--倒叙遍历
     * 使用了StringBuilder(空间复杂度为O（N）)
     *  (因为JAVA语言字符串是不可变的，即原地修改复杂度O（1）不成立
     *  所以无法仅使用O（1）空间复杂度完成)。
     */
    public String reverseWordsMy(String s) {
        StringBuilder sb=new StringBuilder();
        int i=s.length()-1;
        while(i>=0){
            if(s.charAt(i)!=' '){
                if(sb.length()>0)
                    sb.append(" ");
                int left=i;
                i--;
                while(i>=0&&s.charAt(i)!=' '){
                    i--;
                }
                sb.append(s,i+1,left+1);
            }
            i--;
        }
        return sb.toString();
    }

    //空格的ascll码为32--所以可以使用 r!=' ' 或 r!=32
    public boolean isValid(char r){
        return (r>='a'&&r<='z')||(r>='A'&&r<='Z')||(r>='0'&&r<='9');
    }


    /**
     * 官方手动写API
     * 对于字符串不可变的语言，首先得把字符串转化成其他可变的数据结构
     */
    public String reverseWords(String s) {
        //去除多余空格
        StringBuilder sb = trimSpaces(s);
        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);
        // 翻转每个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }
        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            ++left;
        }
        return sb;
    }

    //StringBuilder中字符反转
    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    /**
     * 使用String的API
     * 使用 trim 将除去字符串开头和末尾的空白字符
     * 使用 split 将字符串按空格分割成字符串数组；
     * 使用 reverse 将字符串数组进行反转；
     * 使用 join 方法将字符串数组拼成一个字符串。
     */
    public String reverseWordsApi(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        //以空格为分隔符连接这些单词
        return String.join(" ", wordList);
    }

}
