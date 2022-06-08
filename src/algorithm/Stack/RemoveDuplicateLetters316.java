package algorithm.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 316. 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 */
public class RemoveDuplicateLetters316 {

    /**
     * 此题与402的解题思路差不多
     * 该题并非严格的单调栈--因为题目要求需要保留一次出现的字符，所以栈内字符呈几段单调递增
     * 一个重点：如果遍历到当前栈中已经有的字符，可以舍弃当前遍历到的字符
     * 因为题目又要求我们保持选取字母的相对顺序，但是又必须要保留一次已出现的字符--可以举几个例子测试一下结论
     * 维护栈的单调性的同时还要保证栈顶元素不是最后出现的，不然就移除最后出现的元素了
     * 所以要采用Hash表来记录每个字符最后出现的位置，由于只有字母出现所以用索引数组代替HashMap
     * （空间换时间的想法---不必O（N）去遍历后面是否还有栈顶字母）
     */
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        char[] charArray = s.toCharArray();
        //第1步：记录每个字符出现的最后一个位置--这一步也可以换成统计字符出现的次数
        int[] lastIndex = new int[26];
        for (int i = 0; i < len; i++) {
            lastIndex[charArray[i] - 'a'] = i;
        }
        // 第 2 步：使用栈得到题目要求字典序最小的字符串
        Deque<Character> stack = new ArrayDeque<>(len);
        // 栈中有的字符记录在这里
        boolean[] visited = new boolean[26];
        for (int i = 0; i < len; i++) {
            char currentChar = charArray[i];
            //栈中存在该字符，跳过该字符
            if (visited[currentChar - 'a']) {
                continue;
            }
            //此处核心维护栈逻辑和402题差不多
            //这里除了在当前遍历到的元素的相对顺序小于栈顶元素之外
            //还要保证该栈顶元素在未遍历的元素后面还有同样的栈顶元素（即栈顶元素并非最后一次出现）
            while (!stack.isEmpty() && currentChar < stack.peekLast() && lastIndex[stack.peekLast() - 'a'] > i) {
                char top = stack.removeLast();
                // 在出栈、入栈的时候，都需要维护 visited 数组的定义
                visited[top - 'a'] = false;
            }
            stack.addLast(currentChar);
            visited[currentChar - 'a'] = true;
        }
        // 第 3 步：此时 Stack 就是题目要求字典序最小的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : stack) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }


    /**
     * lastIndex换成记录元素出现次数
     */
    public String removeDuplicateLetters1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        char[] charArray = s.toCharArray();
        //第1步：统计字符出现的次数
        int[] lastIndex = new int[26];
        for (int i = 0; i < len; i++) {
            ++lastIndex[charArray[i] - 'a'];
        }
        // 第 2 步：使用栈得到题目要求字典序最小的字符串
        Deque<Character> stack = new ArrayDeque<>(len);
        // 栈中有的字符记录在这里
        boolean[] visited = new boolean[26];
        for (int i = 0; i < len; i++) {
            char currentChar = charArray[i];
            --lastIndex[currentChar - 'a'];
            //栈中存在该字符，跳过该字符
            if (visited[currentChar - 'a']) {
                continue;
            }
            //此处核心维护栈逻辑和402题差不多
            while (!stack.isEmpty() && currentChar < stack.peekLast() && lastIndex[stack.peekLast() - 'a'] > 0) {
                char top = stack.removeLast();
                // 在出栈、入栈的时候，都需要维护 visited 数组的定义
                visited[top - 'a'] = false;
            }
            stack.addLast(currentChar);
            visited[currentChar - 'a'] = true;
        }
        // 第 3 步：此时 Stack 就是题目要求字典序最小的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : stack) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

}
