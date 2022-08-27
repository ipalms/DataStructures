package algorithm.BackTrace;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSumII40 {

    /**
     * 39的改编
     * 此题的去重逻辑与47有点相似
     */
    int n,t;
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        n=candidates.length;
        t=target;
        Arrays.sort(candidates);
        //boolean []used=new boolean[n];  可以使用used解题，但是没必要会增加空间占用
        backtrace(candidates,new ArrayDeque<>(),0,0);
        return res;
    }


    public void backtrace(int[] nums, Deque<Integer> path, int start, int sum){
        if(sum==t){
            res.add(new ArrayList(path));
            return;
        }
        for(int i=start;i<n;++i){
            if(sum+nums[i]>t) return;
            //利用i>start 加 nums[i-1]==nums[i] 就可以排除同一层抉择中后面元素重复的情况
            //过滤掉i==start并且nums[i-1]==nums[i]的上下层元素相等情况
            //联想下决策树，start与start-1是上下层关系（i==start），i与i-1是同层关系（i>start）
            if(i>start&&nums[i]==nums[i-1]) continue;
            sum+=nums[i];
            path.add(nums[i]);
            //这里 start 是变成i+1
            backtrace(nums,path,i+1,sum);
            path.removeLast();
            sum-=nums[i];
        }
    }
}
