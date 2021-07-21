package sort;

import java.text.SimpleDateFormat;
import java.util.Date;


//插入排序
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 1, 119, -1, 89};
        int[] arr = new int[200000];
        for (int i = 0; i < 200000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("插入排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr); //调用插入排序算法

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
     /*   不太符合插入排序思想
        int temp=0;
        int temp2=0;
        for (int i = 0; i <arr.length-1 ; i++) {
            temp=arr[i+1];
            for (int j=i; j >=0 ; j--) {
                temp2=arr[j];
                 if(temp2>temp){
                     arr[j]=arr[j+1];
                     arr[j+1]=temp2;
                 }
            }
        }*/
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        //使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];   //定义待插入的数
            insertIndex = i - 1; // 初始化为待插入数前一个数的下标

            // 给insertVal 找到插入的位置
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
                    insertIndex--;
                }
            // 当退出while循环时，说明插入的位置找到, insertIndex + 1
            // 举例：理解不了，我们一会 debug
            //这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
