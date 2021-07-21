package algorithm.SlidingWindow;

import java.util.HashMap;

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
}
