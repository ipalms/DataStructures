package algorithm.BackTrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 难度中等681
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 *  输入：nums = [1,1,2]
 *  输出：
 *  [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 提示：
 * - 1 <= nums.length <= 8
 * - -10 <= nums[i] <= 10
 */
public class FullArrayII47 {


    /**
     * 回溯算法中对于无序的数组可以考虑排序（排序时间复杂度一般不占大头）
     */
    int n;
    public List<List<Integer>> permuteUnique(int[] nums) {
        n=nums.length;
        //不同点--需要先排序
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        boolean []visited=new boolean[nums.length];
        backtrace(nums,new ArrayList<>(),0,res,visited);
        return res;
    }

    public void backtrace(int[] nums, List<Integer> path, int count, List<List<Integer>> res, boolean []visited){
        if(n==count){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=0;i<n;++i){
            //与46题的唯一不同点点，多了此处的排除不合法解判断
            //剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            //写!visited[i - 1]是因为nums[i - 1]在深度优先遍历的过程中刚刚被撤销选择，
            //这时才不能再次选择一样的元素作为结果集的成员（通过递归树可以清除看的出来）
            if(i>0&&nums[i]==nums[i-1]&&!visited[i-1]) continue;
            if(!visited[i]){
                visited[i]=true;
                path.add(nums[i]);
                backtrace(nums,path,count+1,res,visited);
                //如果使用ArrayDeque可以直接使用removeLast()函数
                path.remove(path.size()-1);
                visited[i]=false;
            }
        }
    }
}
