package algorithm.Greedy;

import java.util.Arrays;

/**
 * 区间调度问题
 * 有N项工作，每项工作分别在Si时间开始，在Ti时间结束。
 * 对于每项工作，你都可以选择参与与否。如果选择了参与，那么自始至终都必须全程参与。
 * 此外，参与工作的时间段不能重叠（即使是开始的瞬间和结束的瞬间重叠也是不允许的）。
 * 　　目标是尽可能参与可能多的工作，那么最多能参与多少项工作？
 *
 * 样例输入
 * n = 5, s= {1,2,4,6,8}, t={3,5,7,9,10}
 * 样例输出 ：3 (选取工作1、3、5)
 */
public class IntervalScheduling {
    public static void main(String[] args) {
        int count=1;//最多可参与的工作数
        Task[] t=new Task[] {new Task(1, 7),new Task(8,10),
                new Task(4,7),new Task(6,9),new Task(2,5)};
        Arrays.sort(t);
        for(int i=0;i<t.length;++i) {
            System.out.println(t[i]);
        }
        int end=0;
        for(int i=1;i<t.length;++i) {
            //如果前一个任务结束的时间在后一个任务开始之前，数量加一
            if(t[end].et<t[i].st) {
                count+=1;
                end=i;
            }
        }
        System.out.println(count);
    }
}
class Task implements Comparable<Task>{

    int st;
    int et;

    public Task(int st, int et) {
        super();
        this.st = st;
        this.et = et;
    }

    //按结束时间从小到大排序
    @Override
    public int compareTo(Task o) {
        return this.et-o.et;
    }
    @Override
    public String toString() {
        return st+","+et;
    }

}
