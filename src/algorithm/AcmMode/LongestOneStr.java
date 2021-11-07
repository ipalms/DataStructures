package algorithm.AcmMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 描述
 * 给你一个 01 字符串，定义答案为该串中最长的连续 1 的长度，现在你有至多 k 次机会，
 * 每次机会可以将串中的某个 0 改成 1 ，现在问最大的可能答案
 * 数据范围：字符串长度满足  ，保证输入的字符串只包含 0 和 1 ，
 * 输入描述：
 * 输入第一行两个整数 n , k ，表示字符串长度和机会次数
 * 第二行输入 n 个整数，表示该字符串的元素
 * 输出描述：
 * 输出一行表示答案
 * 示例1
 * 输入：
 * 10 2
 * 1 0 0 1 0 1 0 1 0 1
 * 输出：
 * 5
 * 复制
 * 示例2
 * 输入：
 * 10 5
 * 1 0 0 1 0 1 0 1 0 1
 * 输出：
 * 10
 */
public class LongestOneStr {

    //使用BufferedReader读取输入内容
    public static void main(String []args) throws IOException{
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        String []one=bf.readLine().split(" ");
        String str=bf.readLine().replace(" ","");
        int k=Integer.parseInt(one[1]);
        System.out.println(longestStr(str,k));
    }

    private static int longestStr(String str,int k){
        int left=0,right=0,len=str.length(),max=0,zero=0;
        while(right<len){
            char c=str.charAt(right);
            if(c=='0'){
                ++zero;
            }
            max=Math.max(max,right-left);
            while(zero>k){
                if(str.charAt(left++)=='0'){
                    --zero;
                }
            }
            ++right;
        }
        return Math.max(max,right-left);
    }


    class Two{
        //使用Scanner保存输入
        public void main(String []args){
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt(),k=sc.nextInt();
            StringBuilder sb=new StringBuilder();
            while(sc.hasNextInt()){
                sb.append(sc.nextInt());
            }
            System.out.println(longestStr(sb.toString(),k));
        }

        private int longestStr(String str,int k){
            int left=0,right=0,len=str.length(),max=0,zero=0;
            while(right<len){
                char c=str.charAt(right);
                if(c=='0'){
                    ++zero;
                }
                max=Math.max(max,right-left);
                while(zero>k){
                    if(str.charAt(left++)=='0'){
                        --zero;
                    }
                }
                ++right;
            }
            return Math.max(max,right-left);
        }
    }

}
