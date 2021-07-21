package sequencelist;

import java.util.ArrayList;
import java.util.Iterator;

//顺序表代码   没有扩容机制（可能有bug）
public class SequenceList2<T> implements Iterable<T> {
    //存储元素的数组
    private T[] eles;
    //记录当前顺序表中的元素个数
    private int N;
    //构造方法
    public SequenceList2(int capacity) {
        System.out.println("构造器方法");
        eles = (T[]) new Object[capacity];
        N = 0;
    }

    //将一个线性表置为空表
    public void clear() {
       N = 0;
    }

    //判断当前线性表是否为空表
    public boolean isEmpty() {
        return N == 0;
    }


    //获取指定位置的元素
    public T get(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("当前元素不存在！");
        }
        return eles[i];
    }

    //向线型表中添加元素t
    public void insert(T t) {
        if (N == eles.length) {
            resize(eles.length*2);
        }
        eles[N++] = t;   //等同于else[N]=t  N=N+1;
    }

    //在i元素处插入元素t   指定位置插入
    public void insert(int i, T t) {
        if (i < 0 || i > N) {
            throw new RuntimeException("插入的位置不合法");
        }
        if (i == eles.length||(N+1)>eles.length) {
            resize(eles.length*2);
        }
        //把i位置空出来，i位置及其后面的元素依次向后移动一位
        for (int index = N; index > i; index--) {
            eles[index] = eles[index - 1];
        }
        //把t放到i位置处
        eles[i] = t;
        //元素数量+1
        N++;
    }

    //删除指定位置i处的元素，并返回该元素
    public T remove(int i) {
        if (i < 0 || i > N - 1) {
            throw new RuntimeException("当前要删除的元素不存在");
        }
        //记录i位置处的元素
        T result = eles[i];
        //把i位置后面的元素都向前移动一位
        for (int index = i; index < N - 1; index++) {
            eles[index] = eles[index + 1];
        }
        //当前元素数量-1
        N--;
        //当元素已经不足数组大小的1/4,则重置数组的大小
        if (N>0 && N<eles.length/4){
            resize(eles.length/2);
        }
        return result;
    }
    //改变数组容量
    private void resize(int newSize) {
        //记录旧数组
        T[] temp = eles;
        //创建新数组
        eles = (T[]) new Object[newSize];
        //把旧数组中的元素拷贝到新数组
        for (int i = 0; i < N; i++) {
            eles[i] = temp[i];
        }
    }

    //查找t元素第一次出现的位置
    public int indexOf(T t) {
        if (t == null) {
            throw new RuntimeException("查找的元素不合法");
        }
        for (int i = 0; i < N; i++) {
            if (eles[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }
    //获得数组的长度
    public int capacity(){
        return eles.length;
    }
    //打印所有元素
    public void showEles() {
        for (int i = 0; i < capacity(); i++) {
            System.out.println("数组第" + i + "个元素" + eles[i] + " ");
        }
        System.out.println();
    }

    //实现容器的遍历（foreach）
    @Override
    public Iterator iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T> {
        private int cur;  //记录遍历的索引

        public SIterator() {
            this.cur = 0;
        }

        @Override
        public boolean hasNext() {     //遍历终止条件
            return cur < capacity();
        }

        @Override
        public T next() {        //获取遍历的每个元素
            return eles[cur++];
        }
    }
}


