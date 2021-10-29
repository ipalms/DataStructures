package algorithm.DynamicProgramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class WordBreak139 {

    /**
     * dp[i]表示字符串s的前i-1个字符能否拆分成
     * 两层循环
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // 状态规划：完全背包问题 + 哈希表保存字典
    public boolean wordBreak1(String s, List<String> wordDict) {
        // 将词典保存到哈希表中
        int strLen = s.length();
        Set<String> dict = new HashSet<>(wordDict);
        // dp[i] 表示以i - 1为末尾的前缀字符串是否可以由字典中的单词组成
        boolean[] dp = new boolean[strLen + 1];
        dp[0] = true;
        // 外层遍历字符串，内层遍历字典
        for (int i = 1; i <= strLen; i++) {
            for (String word : dict) {
                int pre = i - word.length();
                if (pre >= 0 && dp[pre] && dict.contains(s.substring(pre, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[strLen];
    }
}
