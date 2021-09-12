package algorithm.SlidingWindow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串
 * 给定一个字符串s和一些长度相同的单词words 。找出 s 中恰好可以由words中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑words中单词串联的顺序。
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 */
public class FindSubstring30 {
    /**
     *  注意题目中的给定单词
     */
    @Test
    public void test(){
        String s="barfoothefootbarman";
        String [] words={"foot","bar"};
        System.out.println(findSubstring(s,words).toString());
    }

    /**
     * 会有重复现象
     */
    public List<Integer> findSubstringMy(String s, String[] words) {
        List<Integer> res=new ArrayList<>();
        int len=words[0].length();
        int wl=words.length;
        if(wl*len>s.length()) return res;
        Map<String,Integer> need=new HashMap<>();
        int []dic=new int[26];
        for(String word:words){
            need.put(word,need.getOrDefault(word,0)+1);
            //保存首字母出现情况
            dic[word.charAt(0)-'a']++;
        }
        Map<String,Integer> have=new HashMap<>();
        int left=0,right=len-1,all=0;
        while(right<s.length()){
            //截取一个单词
            String sub=s.substring(right-len+1,right+1);
            //这个单词为正好缺少的单词
            if(need.containsKey(sub)&&have.getOrDefault(sub,0)<need.get(sub)){
                have.put(sub,have.getOrDefault(sub,0)+1);
                all++;
                //判断当前子序列维护的目标单词的个数【left--right】
                if(all==wl){
                    res.add(left);
                }else{
                    //不足所需的单词个数就只移动有边界
                    right+=len;
                    continue;
                }
            }
            //其他情况--包含找到了一个索引，或者该单词不存在need中，或者出现次数已近等于所需要次数都要移动左边界
            left++;
            //可以跳过的左边界情况
            while(left<s.length()&&dic[s.charAt(left)-'a']==0){
                left++;
            }
            right=left+len-1;
            have.clear();
            all=0;
        }
        return res;
    }


    /**
     * 确定words[0].length() 次数的外循环
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            // hasWords.put(word, value);
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        //清空原来的已有单词
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);
                }
                //优化情景--出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }
            }
        }
        return res;
    }

    /**
     * 时间复杂度：假设 s 的长度是 n，words 里有 m 个单词，那么时间复杂度就是 O（n * m）。
     * 空间复杂度：两个 HashMap，假设 words 里有 m 个单词，就是 O（m）。
     */
    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        int wordLen = words[0].length();
        //HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            //HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res=new ArrayList<>();
        int len=words[0].length();
        int wl=words.length;
        if(wl*len>s.length()) return res;
        Map<String,Integer> need=new HashMap<>();
        Map<String,Integer> use=new HashMap<>();
        int []dic=new int[26];
        for(String word:words){
            need.put(word,need.getOrDefault(word,0)+1);
            use.put(word,0);
            dic[word.charAt(0)-'a']++;
        }
        //map赋值的过程比较耗时，重建一个map更好
        Map<String,Integer> have=new HashMap<>(use);
        int left=0,right=len-1,all=0;
        while(right<s.length()){
            String sub=s.substring(right-len+1,right+1);
            if(need.containsKey(sub)&&have.get(sub)<need.get(sub)){
                have.put(sub,have.get(sub)+1);
                all++;
                if(all==wl){
                    res.add(left);
                }else{
                    right+=len;
                    continue;
                }
            }
            left++;
            while(left<s.length()&&dic[s.charAt(left)-'a']==0){
                left++;
            }
            right=left+len-1;
            have=new HashMap<>(use);
            all=0;
        }
        return res;
    }
}
