package algorithm.BackTrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combine77 {

    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        dfs(n,k,1,new ArrayDeque<Integer>());
        return res;
    }

    /**
     * 如果 n = 7, k = 4，从5开始搜索就已经没有意义了，这是因为：
     * 即使把5选上，后面的数只有6和7，一共就3个候选数，凑不出4个数的组合。
     * 因此，搜索起点有上界，归纳有
     * 搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
     * 搜索起点的上界 = n - (k - path.size()) + 1
     */
    public void backtrace(int n, int k, int start, Deque<Integer> path){
        if(path.size()==k){
            res.add(new ArrayList(path));
            return;
        }
        //i<=n没有进行剪枝
        for(int i=start;i<=n-(k-path.size())+1;++i){
            path.add(i);
            backtrace(n,k,i+1,path);
            path.removeLast();
        }
    }


    /**
     * 思路二
     * 按照每一个数选与不选画出二叉树（或者称位选择树）--回溯的不同写法
     */
    private void dfs(int n, int k, int start, Deque<Integer> path) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        //递归终止条件,因为k在变所以其就相当于之前k不变时还需要添加数的数量--  k-path.size()
        //这里的终止条件按另一种解释 即剩下数的集合长度应该要大于还需要的数据长度
        //在k不变的版本终止条件即  n-start+1<k-path.size()
        if (start > n - k + 1) {
            return;
        }
        //不选当前考虑的数start，直接递归到下一层
        dfs(n, k,start + 1, path);

        //选择当前考虑的数start，递归到下一层的时候 k - 1，这里 k 表示还需要选多少个数
        path.addLast(start);
        dfs(n, k-1,start + 1, path);
        // 深度优先遍历有回头的过程，因此需要撤销选择
        path.removeLast();
    }
}
