package algorithm.Sort;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

//@RunWith(Parameterized.class)

public class HeapWithType<T> {

    public static void main(String[]args){
        Test();
    }

    /**
     * 大顶堆的编写--优先队列  带范型的版本
     * 注意数组元素下标是否从 0 开始对具体编码单调实现具有影响
     * 如果0下标存元素 那么对于下标i的左子节点对应下标为2*i+1,右子节点下标为2*i+2
     * 如果0下标不存元素 那么对于下标i的左子节点对应下标为2*i,右子节点下标为2*i+1
     * <p>
     * 小顶堆将数值比较中的 < 都换成 >  就行了
     * <p>
     * 补全了更多方法的优先队列--包括数组扩容情况
     */
    //@Test   使用JUint4单元测试测试类中带有非空构造器需要进行复杂的数据初始化
    public static void Test() {
//        Heap a=new Heap(100);
        Integer[] nums = new Integer[]{10, 9, 8, 16, 15};
        HeapWithType<Integer> max = new HeapWithType<>(nums,(a,b)->a-b);
        max.ShowData();
        System.out.println();
//        System.out.println(max.poll());
//        System.out.println(max.poll());
        max.add(7);
        max.add(1);
        max.add(1);
        max.add(1);
        max.add(1);
        max.add(96);
        max.add(1);
        max.add(1);
        max.add(1);
        max.add(1009);
        while (!max.isEmpty()) {
            System.out.println(max.poll());
        }
    }

    Comparator<T> comparator;
    int capacity;
    int size = -1;
    T[] arr;

    public HeapWithType(int capacity,Comparator<T> comparator) {
        this.capacity = capacity;
        this.comparator = comparator;
        arr = (T[])new  Object[capacity];
    }


    public void ShowData(){
        for (int i = 0; i <= size; ++i) {
            System.out.print(arr[i] + "\t");
        }
    }

    public HeapWithType(T[] nums,Comparator<T> comparator) {
        this.capacity = 2 * nums.length;
        this.comparator = comparator;
        arr = (T[])new  Object[capacity];
        size = nums.length - 1;
        System.arraycopy(nums, 0, arr, 0, nums.length);
        adjustHeap();
    }

    public void add(T num) {
        if (size == capacity - 1) {
            T[] tmp = (T[])new Object[2 * capacity];
            System.arraycopy(arr, 0, tmp, 0, capacity);
            this.capacity = 2 * capacity;
            arr = tmp;
        }
        int pos = ++size;
        for (; pos > 0 && comparator.compare(arr[(pos - 1) / 2],num)>0; pos = (pos - 1) / 2) {
            arr[pos] = arr[(pos - 1) / 2];
        }
        arr[pos] = num;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException();
        return arr[0];
    }

    public boolean isEmpty() {
        return size < 0;
    }

    public int size() {
        return size + 1;
    }

    public T poll() {
        if (isEmpty()) throw new RuntimeException();
        T val = arr[0];
        arr[0] = arr[size--];
        siftDown(0);
        return val;
    }

    private void adjustHeap() {
        for (int i = size / 2 - 1; i >= 0; --i) {
            siftDown(i);
        }
    }

    private void siftDown(int i) {
        T tmp = arr[i];
        for (int k = 2 * i + 1; k <= size; k = 2 * k + 1) {
            if ((k + 1) <= size && comparator.compare(arr[k],arr[k+1])>0) {
                ++k;
            }
            if (comparator.compare(arr[k],tmp)<0) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = tmp;
    }
}
