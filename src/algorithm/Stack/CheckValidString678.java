package algorithm.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 678. 有效的括号字符串
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。
 * 有效字符串具有如下规则：
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: True
 * 示例 2:
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 * 输入: "(*))"
 * 输出: True
 * 注意:
 * 字符串大小将在 [1，100] 范围内。
 */
public class CheckValidString678 {


    /**
     * 这题是20. 有效的括号的升级版（加入了 * 号可以任意匹配）
     * 这题有两个思路，模拟和栈（双栈---维护左符号栈和 *号栈 ）
     * https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-by-leetc-osi3/
     */


    /**
     * 最优+简单的---模拟思路
     * 从左到右遍历字符串，遍历过程中，未匹配的左括号数量可能会出现如下变化:
     * ●如果遇到左括号,则未匹配的左括号数量加1;
     * ●如果遇到右括号，则需要有一个左括号和右括号匹配，因此未匹配的左括号数量减1;
     * ●如果遇到星号，由于星号可以看成左括号、右括号或空字符串，因此未匹配的左括号数量可能加1.减1或不变。
     * 基于上述结论，可以在遍历过程中维护未匹配的左括号数量可能的最小值和最大值，根据遍历到的字符更新最小值和最大值:
     * ●如果遇到左括号，则将最小值和最大值分别加1;
     * ●如果遇到右括号,则将最小值和最大值分别减1;
     * ●如果遇到星号，则将最小值减1,将最大值加1。
     * 任何情况下，未匹配的左括号数量必须非负，因此当最大值变成负数时，说明没有左括号可以和右括号匹配，返回false。
     * 当最小值为0时,不应将最小值继续减少,以确保最小值非负。
     * 遍历结束时，所有的左括号都应和右括号匹配，因此只有当最小值为0时，字符串s才是有效的括号字符串。
     */
    public boolean checkValidString(String s) {
        // l: 左括号最少可能有多少个
        // r: 左括号最多可能有多少个
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            // 遇到'('所有可能性加一
            // 遇到')'所有可能性减一
            // 遇到'*'，最少的可能性可以变少，最多的可能性也同样可以变多，这取决于这个星号最终我们看成什么，但是可能性都在
            if (c == '(') {
                l++; r++;
            } else if (c == ')') {
                l--; r--;
            } else {
                l--; r++;
            }
            // 当前左括号最少个数不能为负
            l = Math.max(l, 0);
            // 这种情况其实发生在r本身是负数的时候，也就是我们常见的右括号太多了
            if (l > r) return false;
        }
        // 能取到0个左括号才是满足平衡的
        return l == 0;
    }


    /**
     * 栈思路：时间 O（N） 空间O（N）
     * 栈内存放的是索引
     * 如果字符串中没有星号，则只需要一个栈存储左括号，在从左到右遍历字符串的过程中检查括号是否匹配（20题思路）。
     * 在有星号的情况下，需要两个栈分别存储左括号和星号。
     * 从左到右遍历字符串，进行如下操作。
     * ●如果遇到左括号,则将当前下标存入左括号栈。
     * ●如果遇到星号,则将当前下标存入星号栈。
     * ●如果遇到右括号，则需要有一一个左括号或星号和右括号匹配，由于星号也可以看成右括号或者空字符串
     * 因此当前的右括号应优先和左括号匹配，没有左括号时和星号匹配:
     * 1.如果左括号栈不为空，则从左括号栈弹出栈顶元素;
     * 2.如果左括号栈为空且星号栈不为空,则从星号栈弹出栈顶元素;
     * 3.如果左括号栈和星号栈都为空,则没有字符可以和当前的右括号匹配，返回false。
     * 遍历结束之后，左括号栈和星号栈可能还有元素。为了将每个左括号匹配，需要将星号看成右括号
     * 且每个左括号必须出现在其匹配的星号之前。当两个栈都不为空时，每次从左括号栈和星号栈分别弹出栈顶元素
     * 对应左括号下标和星号下标，判断是否可以匹配，匹配的条件是左括号下标小于星号下标，如果左括号下标大于星号下标则返回false。
     * 最终判断左括号栈是否为空。如果左括号栈为空,则左括号全部匹配完毕，剩下的星号都可以看成空字符串
     * 此时s是有效的括号字符串,返回true。如果左括号栈不为空，则还有左括号无法匹配，此时s不是有效的括号字符串，返回false。
     */
    public boolean checkValidString1(String s) {
        Deque<Integer> leftStack = new LinkedList<Integer>();
        Deque<Integer> asteriskStack = new LinkedList<Integer>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                asteriskStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!asteriskStack.isEmpty()) {
                    asteriskStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!leftStack.isEmpty() && !asteriskStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int asteriskIndex = asteriskStack.pop();
            if (leftIndex > asteriskIndex) {
                return false;
            }
        }
        return leftStack.isEmpty();
    }

}
