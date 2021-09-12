package algorithm.String;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 示例 1：
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 * 输入："leetcode"
 * 输出："leotcede"
 * 提示：
 * 元音字母不包含字母 "y"  大写字母也是元音 。
 */
public class ReverseVowels345 {

    /**
     * 双指针
     */
    public String reverseVowels(String s) {
        char []res=s.toCharArray();
        int i=0,j=s.length()-1;
        while(i<j){
            while(i<j&&!isVowels(res[i])){
                ++i;
            }
            while(i<j&&!isVowels(res[j])){
                --j;
            }
            char tmp=res[j];
            res[j--]=res[i];
            res[i++]=tmp;
        }
        return new String(res);
    }

    public boolean isVowels(char c){
        return c=='a'||c=='e'||c=='o'||c=='u'||c=='i'||c=='A'||c=='E'||c=='O'||c=='U'||c=='I';
    }
}
