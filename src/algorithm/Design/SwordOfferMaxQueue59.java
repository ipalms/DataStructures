package algorithm.Design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值
 * 要求函数max_value、push_back和 pop_front的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 */
public class SwordOfferMaxQueue59 {

    /**
     * 此问题的一个坑--关于== 和 equals的
     * 由于队列中存储的须是Integer对象，所以不能直接使用两个队列的max.peekFirst()==vals.peekFirst()
     * 来比较两个队头元素是否相等，因为这样比较的是两个Integer对象是否相等（这样如果不是添加的同一个对象进两个队列是不相等的）
     * 所以要使用的话需要是max.peekFirst().equals(vals.peekFirst())来比较两个数据是否相等
     * 因为Integer重写了equals方法，比较的是存储的数据（其他基础数据类型的包装类比较同理）、
     * String也要使用equals比较（默认的Object.equals方法是比较对象的地址 == 号）
     * 基础数据类型的包装类如果不使用equals比较的话，也可以先拆箱一个基础类型
     * 然后再使用这个基础类型与另一个包装类进行比较，这样就会使这个包装类也自动拆箱进行比较
     * 以这题来看就是：
     * int res=vals.pollFirst()  先拆箱一个包装类
     * max.peekFirst()==res      ==号比较时，另一个包装类也会自动拆箱（自动拆箱属于语法糖--编译器层面）
     */

    /**
     * 双队列
     * 一个存储数据，一个单调队列（双向队列）存放与最大值相关的值
     *
     * 本算法基于问题的一个重要性质：
     * 当一个元素进入队列的时候，它前面所有比它小的元素就不会再对答案产生影响。
     * 按照上面的思路，我们可以设计这样的方法：从队列尾部插入元素时，
     * 我们可以提前取出队列中所有比这个元素小的元素，使得队列中只保留对结果有影响的数字。
     * 这样的方法等价于要求维持队列单调递减，即要保证每个元素的前面都没有比它小的元素。
     *
     * 注意题目需要处理队列为空的时候的返回值的情况
     */


    Deque<Integer> max;
    Deque<Integer>vals;
    public SwordOfferMaxQueue59() {
        max=new LinkedList<>();
        vals=new LinkedList<>();
    }

    public int max_value() {
        if(vals.isEmpty()) return -1;
        return max.peekFirst();
    }

    public void push_back(int value) {
        while(!max.isEmpty()&&value>max.peekLast()){
            max.pollLast();
        }
        vals.addLast(value);
        max.addLast(value);
    }

    public int pop_front() {
        if(vals.isEmpty()) return -1;
        //这里注意 ==  和equals
        int res=vals.pollFirst();
        if(max.peekFirst()==res){
            max.pollFirst();
        }
        return res;
    }
}
