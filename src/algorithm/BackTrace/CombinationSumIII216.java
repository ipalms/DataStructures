package algorithm.BackTrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，
 * 并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII216 {

    /**
     * 回溯
     */
    int k;
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k=k;
        backtrace(n,0,new ArrayDeque<Integer>());
        return res;
    }


    private void backtrace(int n,int start,ArrayDeque<Integer>path){
        if(n<=0||path.size()==k){
            if(path.size()==k&&n==0){
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if(k-path.size()>9-start) return;
        for(int i=start+1;i<10;++i){
            path.add(i);
            backtrace(n-i,i,path);
            path.removeLast();
        }
    }
}
