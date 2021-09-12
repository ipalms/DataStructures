package algorithm.SlidingWindow;

import org.junit.Test;

import java.util.*;

/**
 * 题目描述
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LongestSubstringNoRepeated3 {
    public static void main(String[] args) {
        String s="abcabcbb";
        System.out.println(lengthOfLongestSubstring2(s));
    }

    /**
     * 滑动窗口思想：
     * ①）窗口由两个指针构成，一个左指针left，一个右指针right，然后[left,right]表示的索引范围是一个窗口了。
     * ②）右指针right的功能是用来扩展窗口：当窗口内的条件没有达到题目要求时，我们需要不断移动右指针right直到窗口内的条件第一次满足题目要求为止。
     * ③）左指针left的功能是用来缩小窗口的：当窗口内的条件已满足题目条件或多于题目条件时（窗口溢出），我们缩小窗口，也就是左指针left需要右移直到窗口条件不满足为止。
     *    这时，我们需要记录当前窗口的大小，并更新目前为止满足条件的最小窗口记录。之后，再次扩展右指针right，使得窗口满足题目的条件。
     * 注：滑动窗口用来处理连续满足一定条件的连续区间的性质（长度等）问题的，两个指针都起始于原点，并一前一后向终点前进。
     * 3、30、76、220、424、438、567、718、1004、1284、1438都可以使用滑动窗口解决
     */
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        int left=0,right=0,maxLength=0;
        while (right<s.length()){
           map.put(s.charAt(right),map.getOrDefault(s.charAt(right),0)+1);
           while (map.get(s.charAt(right))>1){
               map.put(s.charAt(left),map.get(s.charAt(left))-1);
               left++;
           }
           maxLength=Math.max(maxLength,right-left+1);
           right++;
        }
        return maxLength;
    }

    //使用数组解答更快
    public static int lengthOfLongestSubstring2(String s) {
        int []map=new int[130];
        int left=0,right=0,maxLength=0;
        while (right<s.length()){
            map[s.charAt(right)]++;
            while (map[s.charAt(right)]>1){
                map[s.charAt(left)]--;
                left++;
            }
            maxLength=Math.max(maxLength,right-left+1);
            right++;
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringMy(String s) {
        int []map=new int[128];
        int left=0,right=0,max=0;
        while(right<s.length()){
            if(map[s.charAt(right)]==0){
                map[s.charAt(right)]=1;
                right++;
                continue;
            }
            max=Math.max(max,right-left);
            while(s.charAt(left)!=s.charAt(right)){
                map[s.charAt(left)]=0;
                left++;
            }
            map[s.charAt(left)]=0;
            left++;
        }
        return Math.max(max,right-left);
    }
}
