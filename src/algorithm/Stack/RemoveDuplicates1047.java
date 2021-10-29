package algorithm.Stack;

/**
 *
 */
public class RemoveDuplicates1047 {

    /**
     * 可以使用双指针或者栈解决该问题
     */

    /**
     * 我们可以发现，当字符串中同时有多组相邻重复项时，我们无论是先删除哪一个，都不会影响最终的结果。
     * 因此我们可以从左向右顺次处理该字符串。
     * 而消除一对相邻重复项可能会导致新的相邻重复项出现，如从字符串abba中删除bb会导致出现新的相邻重复项
     * aa出现。因此我们需要保存当前还未被删除的字符。
     * 使用栈：我们只需要遍历该字符串，如果当前字符和栈顶字符相同，我们就贪心地将其消去，否则就将其入栈即可。
     */
    public String removeDuplicates(String s) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }

    /**
     * 双指针
     */
    public String removeDuplicates1(String s) {
        int first=0,second=1,tail=s.length();
        StringBuilder sb=new StringBuilder(s);
        while(second<tail){
            if(sb.charAt(first)==sb.charAt(second)){
                sb.deleteCharAt(first);
                sb.deleteCharAt(first);
                tail-=2;
                if(first>0){
                    --first;
                    --second;
                }
            }else{
                ++first;
                ++second;
            }
        }
        return sb.toString();
    }


}
