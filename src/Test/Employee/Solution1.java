package Test.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {
        Map<Integer,Employee> map = new HashMap<>();
        public int getImportance(List<Employee> employees, int id) {
            for(Employee e : employees) {
                map.put(e.id , e);
            }
            return dfs(id);
        }

        private int dfs(int id) {
            Employee employee = map.get(id);
            int sum = employee.importance;
            if(employee.subordinates!=null) {
                for (int o : employee.subordinates) {
                    sum += dfs(o);//方法递归，循环调用
                }
            }
            return sum;
        }
    }

