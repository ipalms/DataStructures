package algorithm.Array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 补充题
 * 问题描述
 * 　　78这个数可以表示为连续正整数的和，1+2+3，18+19+20+21，25+26+27。
 * 　　输入一个正整数 n(<=10000)
 * 　　输出 m 行(n有m种表示法)，每行是两个正整数a，b，表示a+(a+1)+…+b=n。
 * 　　对于多种表示法，a小的方案先输出。
 * 样例输入
 * 78
 * 样例输出
 * 1 12
 * 18 21
 * 25 27
 */
public class SumN {

    /**
     * 前缀和+哈希表   最优解
     */
    @Test
    public void main2() {
//        Scanner in=new Scanner(System.in);
//        int num=in.nextInt();
        int num=78;
        int sum=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,0);
        for(int j=1;j<num;j++) {
            sum+=j;
            if(sum>=num){
                if(map.containsKey(sum-num)){
                    System.out.println(map.get(sum-num)+1+"   "+j);
                }
            }
            map.put(sum,j);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int j = 0;                            // 初始个0 下面要用
        for (int i = 1; i < n / 2 + 1; i++) { // <它的一半+1
            int ans = 0;
            for (j = i; ans < n; j++) {
                ans += j;                     // 连续和的值给ans
            }
            if (ans == n) {
                System.out.println(i + " " + --j); //取首位和末位输出
            }
        }
    }



    /**
     * 未改进版前缀和
     * 前缀和求差
     */
    @Test
    public void main1() {
        Scanner in=new Scanner(System.in);
        int num=in.nextInt();
        //求前缀和
        int []ar=new int[num+1];
        for(int i=1;i<=num;i++) {
            ar[i]=ar[i-1]+i;
        }
        for(int j=0;j<num-1;j++) {
            for(int k=j+1;k<=num;k++) {
                if(ar[k]-ar[j]==num) {
                    System.out.println((j+1) + " " + k);
                }else if(ar[k]-ar[j]>num) {
                    break;
                }
            }
        }
    }


}
