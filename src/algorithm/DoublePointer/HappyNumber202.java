package algorithm.DoublePointer;

import java.util.HashSet;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * 示例 1：
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 * 输入：n = 2
 * 输出：false
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class HappyNumber202 {

    /**
     * 给定一个数按题目规则求其各位平方和的变化趋势猜测
     * 1.最终会得到1。
     * 2.最终会进入循环。
     * 3.值会越来越大，最后接近无穷大。
     * 为什么不会出现值越来越大情况：
     * 因为int类型最大值为为‭‭2147483647‬‬，所以平方和最大的数是1999999999，平方和为1+81*9 = 724。
     * 任何数的平方和都在1到724之间，724次循环之内一定有重复的。
     * 平方和降到1就是个特殊的循环，因为下个平方和任然是1
     */

    /**
     * 题解思路：
     * 1.用哈希表存储那些出现过的平方和数字--当出现了之前添加的数就说明出现了环
     * 2.使用快慢指针，如果快指针满指针相遇说明遇到了环，如果快指针的值遇到1说明无环
     * 时间复杂度均为O(logN)--或者getNext（）方法内的循环最大不会超过724*3次
     */

    /**
     * 哈希表
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set=new HashSet<>();
        while(n!=1&&!set.contains(n)){
            set.add(n);
            n=getNext(n);
        }
        return n==1;
    }

    /**
     * 快慢指针
     * 判断是否回环141的变形
     */
    public boolean isHappy1(int n) {
        int slow=n,fast=getNext(n);
        while(fast!=1&&fast!=slow){
            fast=getNext(getNext(fast));
            slow=getNext(slow);
        }
        return fast==1;
    }

    //获得下一个平方和数
    public int getNext(int n){
        int res=0;
        while(n>0){
            int k=n%10;
            n=n/10;
            res+=k*k;
        }
        return res;
    }
}
