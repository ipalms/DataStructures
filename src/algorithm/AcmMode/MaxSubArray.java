package algorithm.AcmMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 一个非空整数数组，选择其中的两个位置，使得两个位置之间的数和最大。
 * 如果最大的和为正数，则输出这个数；如果最大的和为负数或0，则输出0
 * 输入描述（字符串，以 , 号作为分隔）:
 * 3,-5,7,-2,8
 * 输出描述:
 * 13
 * 示例1
 * 输入
 * -6,-9,-10
 * 输出
 * 0
 */
public class MaxSubArray {


    //多行输入或输入大量数据时使用Buffer(缓冲类)更快
    //但是使用这个类要在main方法中抛出异常 throw IOException
    public static void main(String[]args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[]str=bf.readLine().split(",");
        int max=0,sum=0;
        for(String s:str){
            int num=Integer.parseInt(s);
            sum=Math.max(sum+num,num);
            max=Math.max(max,sum);
        }
        System.out.println(max);
    }
}
