package algorithm.Sort;

public class Heap {


    /**
     * 大顶堆的编写--优先队列
     * 注意数组元素下标是否从 0 开始对具体编码单调实现具有影响
     * 如果0下标存元素 那么对于下标i的左子节点对应下标为2*i+1,右子节点下标为2*i+2
     * 如果0下标不存元素 那么对于下标i的左子节点对应下标为2*i,右子节点下标为2*i+1
     *
     * 小顶堆将数值比较中的 < 都换成 >  就行了
     *
     * 补全了更多方法的优先队列--包括数组扩容情况
     */
    public static void main(String[] args) {
//        Heap a=new Heap(100);
        int []nums=new int[]{10,9,8,16,15};
        Heap a=new Heap(nums);
        for(int i=0;i<=size;++i){
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
//        System.out.println(a.poll());
//        System.out.println(a.poll());
        a.add(7);
        a.add(1);
        a.add(1);
        a.add(1);
        a.add(1);
        a.add(96);
        a.add(1);
        a.add(1);
        a.add(1);
        a.add(1009);
        while(!a.isEmpty()){
            System.out.println(a.poll());
        }
    }

    static int capacity;
    static int size=-1;
    static int []arr;
    public Heap(int capacity){
        this.capacity=capacity;
        arr=new int[capacity];
    }

    public Heap(int []nums){
        this.capacity=2*nums.length;
        arr=new int[capacity];
        size=nums.length-1;
        System.arraycopy(nums,0,arr,0,nums.length);
        heapify();
    }

    public void add(int num){
        if(size==capacity-1){
            int []tmp=new int[2*capacity];
            System.arraycopy(arr,0,tmp,0,capacity);
            this.capacity=2*capacity;
            arr=tmp;
        }
        int pos=++size;
        for(;pos>0&&arr[(pos-1)/2]<num;pos=(pos-1)/2){
            arr[pos]=arr[(pos-1)/2];
        }
        arr[pos]=num;
    }

    public int peek(){
        if(isEmpty()) throw new RuntimeException();
        return arr[0];
    }

    public boolean isEmpty(){
        return size<0;
    }

    public int size(){
        return size+1;
    }

    public int poll(){
        if(isEmpty()) throw new RuntimeException();
        int val=arr[0];
        arr[0]=arr[size--];
        siftDown(0);
        return val;
    }

    private void heapify(){
        for(int i=size/2-1;i>=0;--i){
            siftDown(i);
        }
    }

    private void siftDown(int i){
        int tmp=arr[i];
        for(int k=2*i+1;k<=size;k=2*k+1){
            if((k+1)<=size&&arr[k]<arr[k+1]){
                ++k;
            }
            if(arr[k]>tmp){
                arr[i]=arr[k];
                i=k;
            }else{
                break;
            }
        }
        arr[i]=tmp;
    }
}
