package algorithm.BackTrace;

import java.util.*;

/**
 * 22. 括号生成
 * 难度中等1793
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 */
public class GenerateParenthesis22 {

    /**
     * 这道题相当于遍历的一颗隐藏树，即在括号有序的情况下优先遍历左子树
     * 直接使用String字符串当作拼接的中间状态就可以不进行状态撤销操作
     */
    List<String> res=new ArrayList<>();
    public List<String> generateParenthesisMy(int n) {
        int left=n;
        int right=n;
        backtrace(left,right,new StringBuilder());
        return res;
    }

    public void backtrace(int left,int right,StringBuilder sb){
        if(right<left){
            return;
        }
        if(right==0){
            res.add(sb.toString());
            return;
        }
        if(left>0){
            sb.append('(');
            backtrace(left-1,right,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if(right>0){
            sb.append(')');
            backtrace(left,right-1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    //第一种
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs("", n, n, res);
        return res;
    }
    //left right分别为左右括号还可用数
    private void dfs(String curStr, int left, int right, List<String> res) {
        //加入结果
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 剪枝（右括号用的多的请况）
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }


    List<String> res1 = new ArrayList<>();



    public List<String> generateParenthesis1(int n) {
        getParenthesis(new StringBuilder(""),n,n);
        return res1;
    }

    private void getParenthesis(StringBuilder sb,int left, int right) {
        //满足条件情况
        if(left == 0 && right == 0 ){
            res1.add(sb.toString());
            return;
        }
        if(left>right){
            return;
        }
        //优先放左括号
        if(left == right){
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(sb.append("("),left-1,right);
        }else {
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号--优先左括号
            if(left > 0){
                getParenthesis(sb.append("("),left-1,right);
                sb.deleteCharAt(sb.length()-1);
            }
            getParenthesis(sb.append(")"),left,right-1);
        }
        sb.deleteCharAt(sb.length()-1);
    }
}
