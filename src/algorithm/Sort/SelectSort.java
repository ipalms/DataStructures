package algorithm.Sort;

public class SelectSort {

    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = (int) (Math.random() * 20); //生成一个[0, 8000000) 数
        }
        selectSort(arr);
        for (int i = 0; i < 20; i++) {
            System.out.print(arr[i]+"\t");
        }
    }

    /**
     * 选择排序---由于是在无序的序列中挑选一个交换，会造成排序的不稳定
     * 例如有数据{5(1)，8 ，5(2)， 2， 9 },第一遍选择到的最小元素为2，所以5(1)会和2进行交换位置，此时5(1)到了5(2)后面，破坏了稳定性
     * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换
     * 第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换
     * 第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…
     * 第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
     * 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换
     * 总共通过n-1次，得到一个按排序码从小到大排列的有序序列。
     * */
    public static void selectSort(int[] arr) {
        int len= arr.length;
        for (int i = 0; i < len; i++) {
            int minIndex=i;
            int minValue=arr[i];
            for (int j = i+1; j < len; j++) {
                if(arr[j]<minValue){
                    minIndex=j;
                    minValue=arr[j];
                }
            }
            if(minIndex!=i){
                swap(arr,i,minIndex);
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
