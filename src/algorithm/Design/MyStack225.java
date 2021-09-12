package algorithm.Design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 示例：
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 * 提示：
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 * 进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，
 * 尽管其中某个操作可能需要比其他操作更长的时间。你可以使用两个以上的队列。
 */
public class MyStack225 {

    Deque<Integer> queue1;
    Deque<Integer> queue2;
    /**
     * 此题正好与232题的要求相反
     * 主要做法：
     * 一个队列为主队列，一个为辅助队列，当入栈操作时，我们先将该值入辅助队列，然后将主队列的值全部入到辅助队列
     * 这样辅助队列的值均为先进后出形式，主队列为空。最后将主队列和辅助队列的引用对调
     * 故入栈操作时间复杂度为O（N） 其他操作时间复杂度均为O（1）
     *
     * 因为辅助队列全程只起到了存储临时元素作用，所以使用一个主队列仍然可以完成题目要求（只有push方法会改变）
     */
    /** Initialize your data structure here. */
    public MyStack225() {
        queue1=new LinkedList<>();
        queue2=new LinkedList<>();
    }

    /** Push element x onto stack. */
    /**
     * 使用两个队列的版本
     */
    public void push(int x) {
        queue2.offer(x);
        while(!queue1.isEmpty()){
            queue2.offer(queue1.pollFirst());
        }
        Deque<Integer> tmp=queue1;
        queue1=queue2;
        queue2=tmp;
    }

    /**
     * 只使用一个队列的版本
     */
    public void push1(int x) {
        queue1.offer(x);
        int n=queue1.size();
        //将前面的元素出对后添加到尾部
        for(int i=0;i<n-1;++i){
            queue1.offer(queue1.pollFirst());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.pollFirst();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peekFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
