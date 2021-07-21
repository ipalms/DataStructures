package Test.Test;

import java.util.Arrays;

public class test2 {
    public static void main(String[] args) {
        int a[] = {0, 12, 0, 3, 5};
        arrayNumber(a);
    }
    public static void arrayNumber(int []a){
        for (int i = 0; i <a.length ; i++) {
            if(a[i]==0&&i!=a.length-1){
                for (int j = i; j <a.length ; j++) {
                    if (a[j]!=0){
                        int temp=0;
                        temp=a[i];
                        a[i]=a[j];
                        a[j]=temp;
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}