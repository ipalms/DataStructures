package algorithm.SlidingWindow;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 *  给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *  说明：
 *  字母异位词指字母相同，但排列不同的字符串。
 *  不考虑答案输出的顺序。
 *  示例 1:
 *  输入: s = "cbaebabacd", p = "abc"
 *  输出: [0,6]
 *  解释:
 *  起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 *  起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *  输入: s = "abab", p = "ab"
 *  输出: [0,1,2]
 *  解释:
 *  起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 *  起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 *  起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *  提示:
 *  1 <= s.length, p.length <= 3 * 10^4
 *  s 和 p 仅包含小写字母
 */
public class LetterVariants438 {
    public static void main(String[] args) {
        String s="badabadc";
        String p="abcd";
        List<Integer> list = findAnagrams2(s, p);
        System.out.println(list);
    }


    /**
     * 滑动窗口
     * 衍生题567
     */
    public List<Integer> findAnagramsMy(String s, String p) {
        List<Integer> res=new  ArrayList<>();
        int n1=s.length(),n2=p.length();
        if(n1<n2) return res;
        int []need=new int[26];
        int []have=new int[26];
        for(int i=0;i<n2;i++){
            need[p.charAt(i)-'a']++;
        }
        //因为期望的子序列所在窗口不含有其他字符，所以可以不用count变量维护元素个数（用窗口长度维护窗口中元素个数）
        int left=0,right=0;
        while(right<n1){
            char r=s.charAt(right);
            //可以将p中不存在的字符的判断操作合并下个while循环一起判断
            /* if(need[r-'a']==0){
                 while(left<right){
                     have[s.charAt(left)-'a']--;
                     left++;
                 }
                 right++;
                 left++;
                 count=0;
                 continue;
             }*/
            have[r-'a']++;
            while(have[r-'a']>need[r-'a']){
                have[s.charAt(left)-'a']--;
                left++;
            }
            if(right-left+1==n2){
                res.add(left);
                have[s.charAt(left)-'a']--;
                left++;
            }
            right++;
        }
        return res;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> results=new ArrayList<>();
        if(s.length()==0||p.length()==0||s.length()<p.length()){
            return results;
        }
        int[] need =new int[26];
        int[] have =new int[26];
        for (int i = 0; i <p.length() ; i++) {
            need[p.charAt(i)-'a']++;
        }
        int left=0,right=0,count=0;
        while (right<s.length()){
           if(need[s.charAt(right)-'a']==0){
                right++;
                left=right;
                count=0;
                Arrays.fill(have,0);
                continue;
            }
           if(have[s.charAt(right)-'a']==need[s.charAt(right)-'a']){
               while (s.charAt(left)!=s.charAt(right)){
                   have[s.charAt(left)-'a']--;
                   count--;
                   left++;
               }
               left++;
           }
           if(have[s.charAt(right)-'a']<need[s.charAt(right)-'a']){
                have[s.charAt(right)-'a']++;
                count++;
           }
           right++;
           while (count==p.length()){
                results.add(left);
                have[s.charAt(left)-'a']--;
                count--;
                left++;
           }
        }
        return results;
    }

    //使用哈希表 way2
    public static List<Integer> findAnagrams2(String s, String p) {
        Map<Character,Integer> smap = new HashMap<>(); //记录s的每个字符和出现的次数
        Map<Character,Integer> pmap = new HashMap<>(); //记录p的每个字符和出现的次数
        for(char ch : p.toCharArray()){
            pmap.put(ch,pmap.getOrDefault(ch,0)+1);
        }
        List<Integer> res = new ArrayList<>();
        int count = 0; //候选字符的个数
        int len = p.length();
        int left = 0;
        int right = 0;
        while(right < s.length()){
            char ch = s.charAt(right);
            smap.put(ch,smap.getOrDefault(ch,0) + 1);
            //如果 p 中包含当前字符，且s的窗口中该字符出现次数不足，则该字符可以作为候选字符，count加一
            if(pmap.containsKey(ch) && smap.get(ch) <= pmap.get(ch)){
                count++;
            }
            //当候选字符个数等于p长度，此时left为起始索引
            if(count == len){
                res.add(left);
            }
            //当窗口大小等于p长度时，窗口左边需要收缩一个字符
            if(right - left + 1 >= len){
                char leftChar = s.charAt(left);
                //判断收缩的这个字符是否是候选字符，是则count减一
                if(pmap.containsKey(leftChar) && smap.get(leftChar) <= pmap.get(leftChar)){
                    count--;
                }
                //窗口收缩一个字符
                smap.put(leftChar,smap.getOrDefault(leftChar,1) - 1);
                left++;
            }
            right++;
        }
        return res;
    }
}
