package algorithm.Sort;

public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[200000];
        for (int i = 0; i < 200000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
//        int []arr=new int[]{5,9,2000,4001,18,4001,4000,4000};
        insertSort(arr);
        for (int i = 1; i < arr.length ; i++) {
            if(arr[i]<arr[i-1]){
                System.out.println("wrong!!");
            }
        }
        System.out.println("success");
    }

    /**
     * 插入排序--由于交换时是比较相邻的元素，所以是稳定的排序算法
     * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，
     * 无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，
     * 把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
     * */
    public static void insertSort(int[] arr) {
        int len= arr.length;
        //外层i为当前需要插入到有序集合部分的索引
        for (int i = 0; i < len; i++) {
            //从后往比较相邻的数，不满足递增就交换，满足就break
            for (int j = i; j >0 ; j--) {
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }else{
                    break;
                }
            }
            //从前往后找到第一个大于待插入元素值所在索引虽然也可以，但是会造成排序稳定性丢失
//            for (int j = 0; j < i; j++) {
//                if(arr[j]>arr[i]){
//                    swap(arr,i,j);
//                    break;
//                }
//            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
