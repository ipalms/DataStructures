package algorithm.BackTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回所有可能得到的字符串集合。
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 * 输入：S = "12345"
 * 输出：["12345"]
 * 提示：
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */
public class LetterCasePermutation784 {

    /**
     * 此题很类似78题（子集）---同样的也有非回溯的写法
     */
    int n;
    List<String> res=new ArrayList<>();
    public List<String> letterCasePermutation(String s) {
        n=s.length();
        backtrace(s,0,new StringBuilder());
        return res;
    }

    public void backtrace(String s, int begin, StringBuilder sb){
        if(begin==n){
            res.add(sb.toString());
            return;
        }
        char c=s.charAt(begin);
        if(Character.isDigit(c)){
            sb.append(c);
        }else{
            //如果是字母的话，遍历其中两种形态（遍历完一种形态记得回溯）
            char r=Character.toUpperCase(c);
            sb.append(r);
            backtrace(s,begin+1,sb);
            sb.deleteCharAt(begin);
            char small=(char) (r+32);
            sb.append(small);
        }
        backtrace(s,begin+1,sb);
        sb.deleteCharAt(begin);
    }
}
