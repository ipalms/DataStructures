package sort;

import java.util.Arrays;

//简介版归并排序(定义了更小的零时数组长度  将自加操作写在一行)
public class MergeSort2 {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        System.out.println(Arrays.toString(sort(arr)));
    }

    public static int[] sort(int[] arr){
        int left=0;
        int right=arr.length-1;
        //调用归并函数
        mergeSort(arr,left,right);
        return arr;
    }
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            //合并
            merge(arr, left, mid, right);
        }
    }
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right-left+1];
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //拷贝剩余
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //把临时数组元素拷贝回arr
        for(int k=0;k<temp.length;k++){
            arr[left+k] = temp[k];
        }
    }
}


