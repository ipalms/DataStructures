package algorithm.BackTrace;

import java.util.*;

public class SubsetsII90 {

    int n;
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        n=nums.length;
        Arrays.sort(nums);
        backtrace(nums,new ArrayDeque<>(),0);
        return res;
    }

    public void backtrace(int[] nums, Deque<Integer> tmp, int start){
        res.add(new ArrayList(tmp));
        if(start==n) return;
        for(int i=start;i<n;++i){
            //与47类似逻辑
            if(i>start&&nums[i]==nums[i-1]) continue;
            tmp.add(nums[i]);
            backtrace(nums,tmp,i+1);
            tmp.removeLast();
        }
    }
}
