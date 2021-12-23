package algorithm.PrefixTree;

import org.junit.Test;

/**
 * 440. 字典序的第K小数字
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 * 注意：1 ≤ k ≤ n ≤ 109。
 * 示例 :
 * 输入:
 * n: 13   k: 2
 * 输出:
 * 10
 * 解释:
 * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 */
public class FindKthNumber440 {
    @Test
    public  void test(){
        System.out.println(findKthNumber(22,12));
    }


    /**
     * 前缀树--十叉树
     */
    public int findKthNumber(int n, int k) {
        int pre = 1;
        //扣除数字1
        //这个扣除是和下面的判断以及getCount函数相关的，当k==now时，由于先扣除了一个数，那么要找的第k小应该正好是pre++的数
        k--;
        while (k > 0) {
            int now = getCount(n, pre, pre + 1);
            //说明第k个数不在这个前缀区间里 注意有等号
            //取等说明，当k==now时，由于先扣除了一个数，那么要找的第k小应该正好是pre++的数
            //如果没有扣除最初的1，那么k==now时，要取的数是 pre 分支下最右的节点而非pre++这个数，这样求得那个数比较麻烦
            if (k >= now) {
                pre++;  // 找下一个字典序前缀
                k -= now;  // 扣除这个前缀的所有数
            } else {  //说明第k个数在当前前缀下
                pre *= 10;  // 往下找
                k--; // 扣除当前这个数
            }
        }
        return pre;
    }

    /**
     * 确定指定前缀下所有子节点数（当前子树下的节点个数）
     * n 原函数传入数据范围
     * x 当前前缀的节点值
     * y 当前节点后的（右边兄弟节点）节点值
     */
    public int getCount(int n, long x, long y) {
        int sum = 0;
        //这里也有等号
        while (x <= n) {
            sum += Math.min(n + 1, y) - x;  // 如n=15,则sum+=min(16,20)-10
            x *= 10;
            y *= 10;
        }
        return sum;
    }
}
