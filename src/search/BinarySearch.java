package search;

import sort.InsertSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//二分查找，查找的前提是数组为有序的状态
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8, 10, 1000,1000, 1000, 1000,1234};
        InsertSort.insertSort(arr);
        System.out.println(Arrays.toString(arr));
        //int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
/*
		int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
		System.out.println("resIndex=" + resIndex);*/
        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList=" + resIndexList);
    }



    // 二分查找算法(不能返回多个相同数的索引)
    /**
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        // 当 left > right 时，说明递归整个数组，但是没有找到。没有这个条件会造成无线递归，栈溢出
        if (left > right|| findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {

            return mid;
        }

    }

    //有多个相同的数值时，将所有的数值都查找到
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right|| findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
/*		 * 思路分析
         * 1. 在找到mid 索引值，不要马上返回
         * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
         * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
         * 4. 将ArrayList返回*/
            List<Integer> resIndexlist = new ArrayList<Integer>();
            resIndexlist.add(mid);  //把mid中间的索引放入list集合
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }
            return resIndexlist;
        }
    }
}

