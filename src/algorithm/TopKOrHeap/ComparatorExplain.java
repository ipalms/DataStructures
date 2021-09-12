package algorithm.TopKOrHeap;

import org.junit.Test;

import java.util.*;

public class ComparatorExplain {

    /**
     * 关于对Comparator接口实现排序以及优先队列中的实现的一些说明
     * 要实现的排序方法 int compare(T o1, T o2);
     * 对于比较o2-o1来说就是降序排序，即如果o2-o1>0，o2排在o1之前，o2-o1==0两者相等，o2-o1<0，o2排在o1之后
     * 正相反o1-o2来说就是升序排序，即如果o1-o2>0，o1排在o2之后，o1-o2两者相等，o1-o2<0，o1排在o2之前
     * 但是直接返回o1-o2可能会遇到问题，如果T类型为Integer，那么意味着只有四个字节大小
     * 那么如果o1一个为正数，一个为负数相差大于4字节就会造成数据溢出，那么就会排序不准。
     * 对于一些题目而言结果不同 例如480题
     */
    @Test
    public void testComparator(){
        //匿名内部类写法
        /*new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };*/
        //申明大顶堆 使用Integer.compare(b, a) ->return (b < a) ? -1 : ((b == a) ? 0 : 1)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        //申明大顶堆 使用b-a 可能会造成数据溢出
        PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>((a, b) -> b-a);

        int c=-2147483648-2147483646;
        System.out.println(c);  //c打印结果为2
        int []num={-2147483648,-2147483647,2147483646,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        for(int i=0;i<num.length;i++){
            maxHeap.add(num[i]);
            maxHeap1.add(num[i]);
        }
        while(!maxHeap.isEmpty()){
            System.out.print(maxHeap.poll()+"    ");
        }
        System.out.println("\n"+"**************");
        while(!maxHeap1.isEmpty()){
            System.out.print(maxHeap1.poll()+"    ");
        }
        int []nums={5,6,1,8,-2,10,7};
        List<Integer> d =new ArrayList<>();
        for(int n:nums){
            d.add(n);
        }
        Collections.sort(d,(a, b)->b-a);
        System.out.println("\n"+d.toString());
    }

    @Test
    public void testComparable(){
        Person []people=new Person[]{new Person(10),new Person(8),new Person(9),new Person(10),new Person(3),new Person(12)};
        Arrays.sort(people);
        for(Person p:people){
            System.out.print(p.age+"   ");
        }
    }

    class Person implements Comparable<Person>{
        int age;
        public Person(int age){
            this.age=age;
        }

        //当前比较传入的是升序 this.age-o.age;
        //传入比较当前的是降序 o.age-this.age;
        @Override
        public int compareTo(Person o) {
            return o.age-this.age;
        }
    }
}
