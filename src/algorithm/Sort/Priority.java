package algorithm.Sort;

import org.junit.Test;

public class Priority {

    /**
     * 大顶堆的编写--优先队列
     * 注意数组元素下标是否从 0 开始对具体编码单调实现具有影响
     * 如果0下标存元素 那么对于下标i的左子节点对应下标为2*i+1,右子节点下标为2*i+2
     * 如果0下标不存元素 那么对于下标i的左子节点对应下标为2*i,右子节点下标为2*i+1
     *
     * 小顶堆将数值比较中的 < 都换成 >  就行了
     */
    public static void main(String[] args) {
        Priority a=new Priority(100);
        a.add(10);
        a.add(9);
        a.add(8);
        a.add(16);
        a.add(15);
        for(int i=0;i<size;++i){
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
        System.out.println(a.poll());
        System.out.println(a.poll());
        a.add(7);
        a.add(1);
        System.out.println(a.poll());
        System.out.println(a.poll());
        System.out.println(a.poll());
        System.out.println(a.poll());
        System.out.println(a.poll());
    }

//    @Test
//    public void test(){
//
//    }

    static int []arr=new int[100];
    static int capacity=100;
    static int size=-1;

    public Priority(int capacity){
        arr=new int[capacity];
        this.capacity=capacity;
    }

    public Priority(int []nums){
        capacity=2*arr.length;
        arr=new int[capacity];
        for(int num:nums){
            add(num);
        }
    }

    public int peek(){
        if(size<0) throw  new RuntimeException();
        return arr[0];
    }


    //上浮操作
    public void add(int num){
        int i=++size;
        //条件是与的形式
        for(;i>0&&arr[(i-1)/2]<num;i=(i-1)/2){
            arr[i] = arr[(i - 1) / 2];
        }
        arr[i]=num;
    }

    public int poll(){
        if(size<0) throw new RuntimeException();
        int max=arr[0];
        arr[0]=arr[size];
        arr[size--]=0;
        adjust(0,size);
        return max;
    }

    //下沉操作
    private void adjust(int i,int tail){
        int tmp=arr[i];
        for(int j=2*i+1;j<=tail;j=2*j+1){
            if((j+1)<=tail&&arr[j]<arr[j+1]){
                ++j;
            }
            if(arr[j]<=tmp){
                break;
            }else{
                arr[i]=arr[j];
                i=j;
            }
        }
        arr[i]=tmp;
    }
}
