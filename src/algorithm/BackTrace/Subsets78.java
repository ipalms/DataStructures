package algorithm.BackTrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Subsets78 {

    /**
     * 回溯做法
     */
    int n;
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        n=nums.length;
        backtrace(nums,new ArrayDeque<>(),0);
        return res;
    }

    public void backtrace(int[] nums, Deque<Integer> tmp, int start){
        //非叶子节点也可以输出结果的类型（组合、全排列都是叶子节点才输出结果）
        res.add(new ArrayList(tmp));
        if(start==n) return;
        //如果循环都是从0开始就是包含重复的子集组合
        for(int i=start;i<n;++i){
            tmp.add(nums[i]);
            backtrace(nums,tmp,i+1);
            tmp.removeLast();
        }
    }

    /**
     * 非回溯做法
     * 遍历到每个数就让之前的子集加上此数组成新的子集
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        //先加入 "" 空集
        result.add(new ArrayList());
        for(int i=0;i<nums.length;i++){
            int size = result.size();
            //遍历结果集，对结果集的每个元素加上这个新元素再放到新的结果集当中
            for (int j = 0; j < size; j++) {
                List<Integer> temp = new ArrayList<Integer>(result.get(j));
                temp.add(nums[i]);
                result.add(temp);
            }
        }
        return result;
    }

    /**
     * 基于选择与不选择的回溯
     */
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets3(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        //考虑选择当前位置
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        //考虑不选择当前位置
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }

}
