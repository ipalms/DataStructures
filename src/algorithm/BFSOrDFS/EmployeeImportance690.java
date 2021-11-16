package algorithm.BFSOrDFS;


import java.util.*;

/**
 * 690. 员工的重要性
 * 给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。
 * 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。
 * 那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]]
 * 员工 3 的数据结构是 [3, 5, []] 。
 * 注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。
 * 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
 * 示例：
 * 输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * 输出：11
 * 解释：
 * 员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。
 * 因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
 * 提示：
 * 一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
 * 员工数量不超过 2000 。
 */
public class EmployeeImportance690 {

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }

    /**
     * 由题意可知这样以某个员工为根节点的结构是一颗N叉树
     * 可以通过深度优先|广度优先遍历得到结果
     * 遍历前需要先将id-->employee的映射放到HashMap中
     */

    /**
     * dfs
     */
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer,Employee> map=new HashMap<>();
        for(Employee e:employees){
            map.put(e.id,e);
        }
        return dfs(map,id);
    }

    private int dfs(Map<Integer,Employee>map,int id){
        int count=map.get(id).importance;
        for(int num:map.get(id).subordinates){
            count+=dfs(map,num);
        }
        return count;
    }

    /**
     * bfs
     */
    public int getImportance1(List<Employee> es, int id) {
        int n = es.size();
        Map<Integer, Employee> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(es.get(i).id, es.get(i));
        int ans = 0;
        Deque<Employee> d = new ArrayDeque<>();
        d.addLast(map.get(id));
        while (!d.isEmpty()) {
            Employee poll = d.pollFirst();
            ans += poll.importance;
            for (int oid : poll.subordinates) {
                d.addLast(map.get(oid));
            }
        }
        return ans;
    }

}
