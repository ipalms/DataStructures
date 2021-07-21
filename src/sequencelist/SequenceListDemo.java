package sequencelist;

import java.util.ArrayList;
import java.util.Iterator;

public class SequenceListDemo {
    public static void main(String[] args) {
        //创建顺序表对象
        SequenceList<String> sl = new SequenceList<>(10);
        //测试插入
        sl.insert("姚明");
        sl.insert("科比");
        sl.insert("麦迪");
        sl.showEles();
        sl.insert(1,"詹姆斯");
        for (String s : sl) {
            System.out.println(s);
        }
        System.out.println("\n"+"另一种迭代形式    两种形式等价");
        Iterator<String> iterator = sl.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        //测试获取
        String getResult = sl.get(1);
        System.out.println("获取索引1处的结果为："+getResult);
        //测试删除
        String removeResult = sl.remove(3);
        System.out.println("删除的元素是："+removeResult);
        sl.showEles();
        //测试清空
        sl.clear();
        System.out.println("清空后的线性表中的元素个数为:"+sl.length());
    }
}
