package algorithm.BackTrace;

import java.util.*;

/**
 * 39. 组合总和
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，
 * 找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * 示例 1：
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum39 {

    int n,t;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        n=candidates.length;
        t=target;
        //先排序有利于进行剪枝
        Arrays.sort(candidates);
        List<List<Integer>> res=new ArrayList<>();
        backtrace(candidates,new ArrayDeque<>(),0,0,res);
        return res;
    }


    public void backtrace(int[] nums, Deque<Integer> path, int start, int sum, List<List<Integer>> res){
        if(sum==t){
            res.add(new ArrayList(path));
            return;
        }
        //借助for循环遍历其他值，起点为start(即不在搜索前面出现的值)
        for(int i=start;i<n;++i){
            sum+=nums[i];
            //剪枝--大于target可以直接退出此层递归
            if(sum>t) return;
            path.add(nums[i]);
            //由于每一个元素可以重复使用，下一轮搜索的起点依然是 i
            backtrace(nums,path,i,sum,res);
            path.removeLast();
            sum-=nums[i];
        }
    }
}
