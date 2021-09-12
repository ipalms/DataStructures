package sort;

import java.util.Arrays;

public class QuickSort2 {
    public static void main(String[] args) {
        int [] array={5,1,7,9,4,6};
        quickSort(array,0,5);
        System.out.println(Arrays.toString(array));
    }
    public static void quickSort(int[] array, int begin, int end) {
        //递归截至条件
        if (end <= begin) {
            return;
        }
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }
    //第二种快排（使用尾部元素作为基准） pivot标杆选择尾部元素  这种是只在一端比较交换的快排
    private static int partition(int[] array, int begin, int end) {
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                if(counter<i){
                    int temp = array[counter];
                    array[counter] = array[i];
                    array[i] = temp;
                }
                counter++;
            }
        }
        //将基准数放到中间的位置（基准数归位）
        //counter位置之前的数皆小于基准数，counter位置之后的数皆大于等于基准数
        int temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;
        //返回此时基准数的位置
        return counter;
    }

    /**
     * 第二种快排（使用头部元素作为基准） pivot标杆选择头部元素
     */
    private static int partition2(int[] array, int begin, int end) {
        int pivot = begin, counter = begin+1;
        for (int i = begin+1; i <= end; i++) {
            if (array[i] < array[pivot]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        //将基准数放到中间的位置（基准数归位）
        //counter位置之前的数皆小于基准数，counter位置之后的数皆大于等于基准数
        int temp = array[pivot];
        array[pivot] = array[counter-1];
        array[counter-1] = temp;
        //返回此时基准数的位置
        return counter-1;
    }
}
