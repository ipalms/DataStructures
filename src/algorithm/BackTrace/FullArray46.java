package algorithm.BackTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class FullArray46 {

    /**
     * 回溯做法：
     * 1.DFS 和回溯算法区别
     * DFS 是一个劲的往某一个方向搜索，而回溯算法建立在 DFS 基础之上的，但不同的是在搜索过程中，
     * 达到结束条件后，恢复状态，回溯上一层，再次搜索。因此回溯算法与 DFS 的区别就是有无状态重置
     * 2.何时使用回溯算法
     * 当问题需要 "回头"，以此来查找出所有的解的时候，使用回溯算法。
     * 即满足结束条件或者发现不是正确路径的时候(走不通)，要撤销选择，
     * 回退到上一个状态，继续尝试，直到找出所有解为止
     * 3.怎么样写回溯算法(从上而下，※代表难点，根据题目而变化)
     * ①画出递归树，找到状态变量(回溯函数的参数)，这一步非常重要※
     * ②根据题意，确立结束条件
     * ③找准选择列表(与函数参数相关),与第一步紧密关联※
     * ④判断是否需要剪枝
     * ⑤作出选择，递归调用，进入下一层
     * ⑥撤销选择
     * 链接：https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/
     * 回溯题的限制要求一般都是数据出现的的范围不大（因为即使剪枝时间复杂度还是很高）
     * 回溯还有一个特征就是一般会获得集合般的结果，比如问题的不同情况的解的排列组合
     */

    /**
     * 回溯的做法二：
     * 做题的时候，先画树形图，画图能帮助我们想清楚递归结构，想清楚如何剪枝。
     * 在画图的过程中思考清楚：
     * 1.分支如何产生；
     * 2.题目需要的解在哪里？是在叶子结点、还是在非叶子结点、还是在从跟结点到叶子结点的路径？
     * 3.哪些搜索会产生不需要的解的
     * 例如：产生重复是什么原因，如果在浅层就知道这个分支不能产生需要的结果
     * 应该提前剪枝，剪枝的条件是什么，代码怎么写
     * 当然回溯算法本质也是函数的递归调用（本质上也是三个阶段）
     * 1.递归的终止条件是什么，在回溯算法中递归到题目需要的解时终止
     * 2.递归的返回参数（回溯算法多为无参）
     * 3.一层递归要做什么事。回溯算法主要操作在这，比如在浅层标识元素已经访问过并添加节点到临时结果集
     * 回溯时清除掉节点已访问过的标志，并将结果集此元素清除掉
     * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     */


    int n;
    public List<List<Integer>> permute(int[] nums) {
        n=nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Boolean[] visited = new Boolean[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, List<Integer> tmp, Boolean[] visited) {
        if (tmp.size()==n) {
            //在Java中，参数传递是值传递，对象类型变量在传参的过程中，复制的是变量的地址。这些地址被添加到 res 变量，但实际上指向的是同一块内存地址。所以在res.add(path)这里做一次拷贝即可
            res.add(new ArrayList<>(tmp));
            return;
        }
        //借助for循环遍历其他值
        for (int i = 0; i < n; i++) {
            //排除不合法选择
            if (visited[i]) continue;
            visited[i] = true;
            // 做选择
            tmp.add(nums[i]);
            // 进⼊下⼀⾏决策
            backtrack(res, nums, tmp, visited);
            visited[i] = false;
            // 取消选择
            tmp.remove(tmp.size() - 1);
        }
    }
}
