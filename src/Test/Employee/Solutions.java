package Test.Employee;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solutions {
    public int getImportance(List<Employee> employees, int id) {
        Queue<Employee> que = new LinkedList<Employee>();
        int imp = 0;
        for (int n = 0; n < employees.size(); ++n)
            if (employees.get(n).id == id) {
                que.add(employees.get(n));
                imp += employees.get(n).importance;
                break;
            }
        while (!que.isEmpty()) {
            Employee e = que.poll();
            if (e.subordinates != null) {
                for (int m = 0; m < e.subordinates.size(); ++m) {
                    for (int n = 0; n < employees.size(); ++n) {
                        if (employees.get(n).id == e.subordinates.get(m)) {
                            que.add(employees.get(n));
                            imp += employees.get(n).importance;
                            break;
                        }
                    }
                }
            }
        }
        return imp;
    }
}