package tree.HeapSort;

public class HeapSort2 {
    /**
     * 使用小顶堆 --->从大到小排序
     * @param arr
     */
    public static void heapSort(int[] arr){
        for (int i = arr.length/2-1; i >=0 ; i--) {
            //从 arr.length/2-1 即倒数第一个非叶子节点开始，逐一下滤，形成小顶堆
            perDownMin(arr,i,arr.length);
        }
        for (int i = arr.length-1; i >0 ; i--) {
            //将根节点，即最小的节点与最后一个节点交换，让最小值逐一放在最后
            swap(arr,i,0);
            //从根节点开始重新生成小顶堆
            perDownMin(arr,0,i);
        }
    }
    /**
     * 小顶堆下滤
     * @param arr
     * @param i
     * @param length
     */
    private static void perDownMin(int[]arr, int i, int length){
        int child;
        int temp;

        for (temp=arr[i];leftChild(i)<length ;i=child) {
            child=leftChild(i);
            if (leftChild(i)!=length-1&&arr[child+1]<arr[child]){
                child++;
            }
            if (temp>arr[child]){
                arr[i]=arr[child];
            }else {
                break;
            }
        }
        arr[i]=temp;
    }
    private  static  void swap(int[] arr, int index1, int index2){
        int temp=arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }

    /**
     * 使用大顶堆 ----> 从小到大排序
     * @param <T>
     * @param arr
     */
    public static <T extends Comparable<? super T>>void  heapSort2(T[] arr){
        for (int i = arr.length/2-1; i >=0 ; i--) {
            perDownBig(arr,i,arr.length);
        }
        for (int i = arr.length-1; i >0 ; i--) {
            swap2(arr,0,i);
            perDownBig(arr,0,i);
        }
    }
    /**
     * 大顶堆下滤
     * @param arr
     * @param i
     * @param length
     * @param <T>
     */
    private static <T extends Comparable<? super T>>void perDownBig(T[] arr, int i, int length){
        int child;
        T temp;
        for(temp=arr[i];leftChild(i)<length;i=child){
            child=leftChild(i);
            if (child!= length-1 && arr[child].compareTo(arr[child+1])<0){
                child++;
            }
            if (temp.compareTo(arr[child])<0){
                arr[i]=arr[child];
            }else {
                break;
            }
        }
        arr[i]=temp;
    }

    private  static  <T extends Comparable<? super T>>void swap2(T[] arr, int index1, int index2){
        T temp=arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }
    private static int leftChild(int i){
        return 2*i+1;
    }

    public static void main(String[] args) {
/*         int[] array =new int[8000000];
        for (int i=0;i<8000000;i++){
            array[i]=(int)(Math.random()*8000000);
        }

        long begintime=System.currentTimeMillis();

        heapSort(array);
             long endtime=System.currentTimeMillis();
     System.out.println("用时："+(endtime-begintime)+"ms");*/
        int[] array =new int[10];
        for (int i=0;i<10;i++){
            array[i]=(int)(Math.random()*100);
        }
        heapSort(array);
        for (int i:array){
            System.out.println(i);
        }
    }
}
