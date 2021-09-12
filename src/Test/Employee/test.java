package Test.Employee;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List <Integer>k=new ArrayList();
        k.add(2);
        k.add(3);
        List <Integer>j=new ArrayList();
        j.add(4);
        j.add(5);
        Employee a=new Employee(1,5,k);
        Employee b=new Employee(2,3,null);
        Employee c=new Employee(3,3,j);
        Employee e=new Employee(4,1,null);
        Employee f=new Employee(5,1,null);
        List<Employee>all=new ArrayList<Employee>();
        all.add(a);
        all.add(b);
        all.add(c);
        all.add(e);
        all.add(f);
        Solutions solution1=new Solutions();
        System.out.println(solution1.getImportance(all,1));
    }
}
