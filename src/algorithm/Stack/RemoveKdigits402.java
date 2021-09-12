package algorithm.Stack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 402. 移掉 K 位数字
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 请你以字符串形式返回这个最小的数字。
 * 示例 1 ：
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 示例 2 ：
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3：
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 * 提示：
 * 1 <= k <= num.length <= 105
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 */
public class RemoveKdigits402 {
    @Test
    public void test(){
        System.out.println(removeKdigits("10",1));
    }

    /**
     * 维护一个递增的单调栈
     * 贪心的原则：如果遍历到某一数x,设该数的上一个数是y，若y>x那么移除y会使得最终值会大
     * 举个例子：k=1  num=1432 当遍历到元素3时，可知移除4的效益最大（132一定要比143、142等组合小）
     * 即若要使得剩下的数字最小，需要保证靠前的数字尽可能小。
     * 统计出栈元素个数，元素出栈情况：
     * 1.栈顶元素的值大于当前遍历的元素值并且移除元素的个数没有达到要求 k，则栈顶元素出栈
     * 2.若遍历完整个字符串而 count < k（移除的元素个数没有达到要求，示例：num = "123456", k = 3），
     * 此时直接将栈中的前三个元素依次出栈，即"654"出栈还剩下的"321"（为了不需要使用反转操作，可以使用pollFirst操作）
     * 若当前遍历的元素值为 " 0 " 并且栈为空，则直接跳过这次循环，要保证栈底的元素不能为 " 0 "
     * 时间 O（N） 空间O（N）
     */
    public String removeKdigits(String num, int k) {
        int n=num.length();
        if(n==k) return "0";
        Deque<Character> stack=new ArrayDeque<>();
        for(int i=0;i<n;++i){
            char r=num.charAt(i);
            while(!stack.isEmpty()&&k>0&&stack.peekLast()>r){
                stack.pollLast();
                k--;
            }
            //栈为空，且当前位为0时，我们不需要将其入栈
            if(!stack.isEmpty()||r!='0'){
                stack.addLast(r);
            }
        }
        //弹栈数不足k位，从后面出栈剩下的元素（栈内元素已成递增）
        for(int i=0;i<k;++i){
            stack.pollLast();
        }
        if(stack.size()==0) return "0";
        StringBuilder sb=new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }
}
