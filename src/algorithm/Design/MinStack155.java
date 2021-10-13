package algorithm.Design;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * 提示：
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 */
public class MinStack155 {


    Deque<Integer> xStack;
    //使用辅助栈去存储每存入一个元素时对应当前栈内最小值
    Deque<Integer> minStack;
    //使用优先队列也可以维护最小栈值，但是插入删除时间复杂度更高
    //PriorityQueue<Integer> queue;

    public MinStack155() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        //辅助栈先存入一个最大值方便比较
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    //使用链表实现栈及O(1)查找栈内最小值，没有使用额外的空间单位
    class MinStack {

        //栈顶
        private Node head;

        public MinStack(){}

        public void push(int x) {
            if(head == null)
                head = new Node(x, x);
            else
                //每个节点都保存该节点值以及当前栈内最小值
                head = new Node(x, Math.min(x, head.min), head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            int val;
            int min;
            Node next;
            private Node(int val, int min) {
                this(val, min, null);
            }
            private Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }

    //不使用辅助栈，栈内容保存的是差值
    class MinStack1 {
        long min;
        //由于我们保存的是差值，所以可能造成溢出，所以我们用了数据范围更大的long类型。
        Stack<Long> stack;

        public MinStack1(){
            stack=new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                min = x;
                stack.push(x - min);
            } else {
                //在x<min情况加入的是个负值
                stack.push(x - min);
                if (x < min){
                    min = x; // 更新最小值
                }
            }
        }

        public void pop() {
            if (stack.isEmpty())
                return;
            long pop = stack.pop();
            //弹出的是负值，要更新 min
            if (pop < 0) {
                min = min - pop;
            }
        }

        public int top() {
            long top = stack.peek();
            //负数的话，出栈的值保存在 min 中
            if (top < 0) {
                return (int) (min);
                //出栈元素加上最小值即可
            } else {
                return (int) (top + min);
            }
        }

        public int getMin() {
            return (int) min;
        }
    }

    //只使用一个栈，栈内存放int[]数组（当前值和栈中最小值）
    class MinStack3{
        Deque<int[]> numStack;
        public MinStack3() {
            numStack=new LinkedList<>();
            numStack.addFirst(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE});
        }

        public void push(int val) {
            numStack.addFirst(new int[]{val,Math.min(numStack.peekFirst()[1],val)});
        }

        public void pop() {
            numStack.pollFirst();
        }

        public int top() {
            return numStack.peekFirst()[0];
        }

        public int getMin() {
            return numStack.peekFirst()[1];
        }
    }
}
