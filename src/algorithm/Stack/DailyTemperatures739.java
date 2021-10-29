package algorithm.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures739 {

    /**
     * 维护单调递减栈，栈内保存的是索引
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack=new LinkedList<>();
        int []res=new int[temperatures.length];
        for(int i=0;i<temperatures.length;++i){
            //遇到气温高于栈顶元素的就可以写入结果数组这一天的结果
            while(!stack.isEmpty()&&temperatures[stack.peekLast()]<temperatures[i]){
                int index=stack.pollLast();
                res[index]=i-index;
            }
            stack.addLast(i);
        }
        return res;
    }
}
