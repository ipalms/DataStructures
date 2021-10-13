package algorithm.Stack;

import java.util.*;

/**
 * 227. 基本计算器 II
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 */
public class CalculateII227 {

    /**
     * 一个关于api的点
     * Deque的实现类，push是从头加入，add是从尾部加入，offer是从尾部加入，poll是从头部弹出一个元素，pop也是从头部弹出
     * 所以对于栈的使用 push搭配pop或poll
     * 对于双向队列的使用 add或offer搭配poll|pop|remove使用
     */

    /**
     * 对于这一题的解法：可以只维护数栈，不用维护符号栈
     * 由于乘除优先于加减计算，因此不妨考虑先进行所有乘除运算,并将这些乘除运算后的整数值放回原表达式
     * 的相应位置，则随后整个表达式的值，就等于一系列整数加减后的值。
     * 基于此，我们可以用一个栈，保存这些(进行乘除运算后的)整数的值。
     * 对于加减号后的数字,将其直接压入栈中
     * 对于乘除号后的数字，可以直接与栈顶元素计算,并替换栈顶元素为计算后的结果。
     * 具体来说，遍历字符串s,并用变量preSign记录每个数字之前的运算符
     * 对于第一个数字，其之前的运算符视为加号。每次遍历到数字末尾时,根据preSign来决定计算方式:
     * ●加号:将数字压入栈;
     * ●减号:将数字的相反数压入栈;
     * ●乘除号:计算数字与栈顶元素,并将栈顶元素替换为计算结果。
     * 代码实现中，若读到一个运算符，或者遍历到字符串末尾，即认为是遍历到了数字末尾。
     * 处理完该数字后，更新preSign为当前遍历的字符。
     * 遍历完字符串s后,将栈中元素累加，即为该字符串表达式的值。
     */
    public int calculate1(String s) {
        Deque<Integer> stack = new LinkedList<Integer>();
        // 保存上一个符号，初始为 +
        char preSign = '+';
        // 保存当前数字，如：12是两个字符，需要进位累加
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            //数字累加
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            //到达了字符串尾部要将数字（num）入数栈
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                // 判断上一个符号是什么
                switch (preSign) {
                    // 当前符号前的数字直接压栈
                    case '+':
                        stack.push(num);
                        break;
                    // 当前符号前的数字取反压栈
                    case '-':
                        stack.push(-num);
                        break;
                    //数字栈栈顶数字出栈，与当前符号前的数字相乘，结果值压栈
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    //数字栈栈顶数字出栈，除于当前符号前的数字，结果值压栈
                    default:
                        stack.push(stack.pop() / num);
                }
                //更新上一个操作符
                preSign = s.charAt(i);
                //数字清零
                num = 0;
            }
        }
        int ans = 0;
        //数栈遍历计数
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    /**
     * 仿写
     */
    public int calculate2(String s) {
        char preOp='+';
        Deque<Integer> nums=new ArrayDeque<>();
        int num=0,len=s.length();
        for(int i=0;i<len;++i){
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                num=num*10+s.charAt(i)-'0';
            }
            if((!Character.isDigit(c)&&c!=' ')||i==len-1){
                switch(preOp){
                    case '+':
                        nums.add(num);
                        break;
                    case '-':
                        nums.add(-num);
                        break;
                    case '*':
                        nums.add(nums.pollLast()*num);
                        break;
                    case '/':
                        nums.add(nums.pollLast()/num);
                        break;
                }
                preOp=c;
                num=0;
            }
        }
        int res=0;
        while(!nums.isEmpty()){
            res+=nums.pollLast();
        }
        return res;
    }



    /**
     * 通用的计算字符串数值结果的计算器--能处理带有括号()以及 +，-，*，/，%，^等任意类型的运算符的计算器
     * https://leetcode-cn.com/problems/basic-calculator-ii/solution/shi-yong-shuang-zhan-jie-jue-jiu-ji-biao-c65k/
     */

    // 使用 map 维护一个运算符优先级
    // 这里的优先级划分按照「数学」进行划分即可
    Map<Character, Integer> map = new HashMap<>(){{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};

    public int calculate(String s) {
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        char[] cs = s.toCharArray();
        int n = s.length();
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 存放所有「非数字以外」的操作
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNumber(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        // 将剩余的计算完
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();
    }
    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/')  ans = a / b;
        else if (op == '^') ans = (int)Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.addLast(ans);
    }
    boolean isNumber(char c) {
        return Character.isDigit(c);
    }
}
