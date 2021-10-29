package algorithm.DynamicProgramming;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 * 提示：
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 */
public class LongestValidParentheses32 {

    /**
     * dp + 栈
     */

    /**
     * dp做法
     * dp含义：dp[i]表示以下标i字符结尾的最长有效括号的长度。
     * 初始化：我们将dp数组全部初始化为0
     * 遍历方向：我们从前往后遍历字符串求解dp值
     * 最优解的取值过程时在dp推移过程中得出的，而非dp[len-1]
     * 状态转移方程推导：
     * 所以，我们先看第i个位置,这个位置的元素s[j]可能有如下两种情况:
     * s[i]=='(':
     * 这时，s[i] 无法和其之前的元素组成有效的括号对，所以，dp[i]= 0
     * s[j==')':
     * 这时，需要看其前面对元素来判断是否有有效括号对。
     *   ***情况1:   s[i-1]=='('
     *   即s[i]和s[i- 1]组成-对有效括号,有效括号长度新增长度2,泣置对最长有效括号长度为其
     *   之前2个位置的最长括号长度加_上当前位置新增的2,我们无需知道i - 2位置对字符是否可以组成
     *   有效括号对。
     *   那么有:dp[i]= dp[i-2]+2
     *   ***情况2:   s[i- 1]==')'
     *   这种情况下，如果前面有和s[]组成有效括号对的字符,即形如...)，这样的话，就要求s[i- 1]
     *   位置必然是有效的括号对，否则s[i]无法和前面对字符组成有效括号对。
     *   这时，我们只需要找到和s[i]配对对位置，并判断其是否是(即可。和其配对对位置为: i-dp[i- 1]- 1。
     *   如果: s[i-dp[i- 1]-1]==' (':
     *   有效括号长度新增长度2，泣置对最长有效括号长度为i-1位置的最长括号长度加上当前位置新增的2，那么有:
     *   dp[i]=dp[i- 1]+2
     *   值得注意的是，i-dp[i-1]- 1和i组成了有效括号对，这将是一段独立的有效括号序列，如果
     *   之前的子序列是形如(..).这种序列，那么当前位置的最长有效括号长度还需要加上这一段。所以:
     *   dp[i]= dp[i- 1]+dp[i-dp[i- 1]-2]+2
     * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
     */
    public int longestValidParentheses(String s) {
        int maxLen=0;
        int []dp=new int[s.length()];
        for(int i=1;i<s.length();++i){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i]=(i>=2?dp[i-2]:0)+2;
                }else if(i-dp[i-1]-1>=0&&s.charAt(i-1-dp[i-1])=='('){
                    dp[i]=(i-dp[i-1]>=2?dp[i-2-dp[i-1]]:0)+dp[i-1]+2;
                }
            }
            maxLen=Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }


    /**
     * 具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
     * 按照这个定义这个「最后一个没有被匹配的右括号的下标」后面的都应该是能匹配的
     * 这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标:
     * ●对于遇到的每个'('，我们将它的下标放入栈中
     * ●对于遇到的每个')'，我们先弹出栈顶元素表示匹配了当前右括号:
     *    如果栈为空,说明当前的右括号为没有被匹配的右括号,我们将其下标放入栈中来更新我们之前
     *    提到的「最后一个没有被匹配的右括号的下标」。
     *    如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     * 我们从前往后遍历字符串并更新答案即可。
     *
     * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中
     * 这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
     * 为了保持统一，我们在一开始的时候往栈中放入一个值为 -1−1 的元素。
     */
    public int longestValidParentheses1(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                //走这个分支只有一种情况就是连续的两个')' ')'且第一个')'也没能和前面匹配上
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}
