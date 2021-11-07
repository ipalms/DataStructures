package algorithm;

import org.junit.Test;

import java.util.*;

public class test {

    @Test
    public void test(){
        System.out.println(100^1);
    }

    public static void main(String []args){
        System.out.println("1 0 1 0".replace(" ",""));
        /*Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        System.out.println(reverse(num));*/
    }

    private static String reverse(int num){
        char []tmp=String.valueOf(num).toCharArray();
        int left=0,right=tmp.length-1;
        while(left<right){
            char t=tmp[left];
            tmp[left]=tmp[right];
            tmp[right]=t;
        }
        return new String(tmp);
    }
}
