package algorithm.Design;

import java.util.*;

public class RandomizedSet380 {

    Map<Integer,Integer> m;
    List<Integer> l;
    Random r;


    /**
     * 这题关键是删除操作，哈希表放置的是val 对应其 在数组（list）的下标
     * 删除操作时把list的末尾元素和待删除的元素换位并维护末尾元素在哈希表中的下标就可以
     */
    public RandomizedSet380() {
        m=new HashMap<>();
        l=new ArrayList<>();
        r=new Random();
        r.setSeed(System.currentTimeMillis());
    }

    public boolean insert(int val) {
        if (m.containsKey(val)){
            return false;
        }
        m.put(val,l.size());
        l.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!m.containsKey(val)){
            return false;
        }
        int id=m.get(val),last=l.size()-1;
        //list 按下标设置值
        l.set(id,l.get(last));
        m.put(l.get(last),id);
        //list 按下标移除
        l.remove(last);
        m.remove(val);
        return true;
    }

    public int getRandom() {
        return l.get(r.nextInt(l.size()));
    }
}
