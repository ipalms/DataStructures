package algorithm.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 */
public class DecodeString394 {

    /**
     * 栈---维护两个栈，数字栈和字符串栈
     * 题目保证给定测试数据中括号合法且数字仅出现在倍数中（编码的内容中不出现数字）
     * 数字栈维护遇到的压缩倍数（注意这个倍数可能会大于10，要进行数字的累加操作），入数字栈时机是遇到'['字符
     * 字符串栈是在遇到每一个'['字符时将当前StringBuilder拼接的内容加入到字符串栈当中
     * 当遇到']'字符时就弹栈数字栈和字符串栈的内容，拼接一个新的字符串（已拼接内容+压缩倍数*这个中括号内的字串）
     * 遇到普通的字母就将其追加到当前的StringBuilder内就行
     */
    public String decodeString(String s) {
        Deque<Integer> numStack=new LinkedList<>();
        Deque<String>strStack=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        int len=s.length(),i=0,num=0;
        while(i<len){
            //遇数字累加
            if(Character.isDigit(s.charAt(i))){
                num=num*10+s.charAt(i)-'0';
            }else if(s.charAt(i)=='['){
                numStack.add(num);
                strStack.add(sb.toString());
                num=0;
                sb=new StringBuilder();
            }else if(s.charAt(i)==']'){
                int times=numStack.pollLast();
                StringBuilder tmp=new StringBuilder(strStack.pollLast());
                for(int j=0;j<times;++j){
                    tmp.append(sb.toString());
                }
                sb=tmp;
            }else{
                sb.append(s.charAt(i));
            }
            ++i;
        }
        return sb.toString();
    }


    /**
     * 递归版
     */
    public String decodeString1(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while(i < s.length()) {
            if(Character.isDigit(s.charAt(i)))
                multi = multi * 10 + s.charAt(i)-'0';
            else if(s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while(multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            }
            else if(s.charAt(i) == ']')
                return new String[] { String.valueOf(i), res.toString() };
            else
                res.append(s.charAt(i));
            i++;
        }
        return new String[] { res.toString() };
    }
}
