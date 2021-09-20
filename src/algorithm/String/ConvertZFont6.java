package algorithm.String;

import java.util.ArrayList;
import java.util.List;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class ConvertZFont6 {

    /**
     * 总结就是找规律的字符串题目，可以画个numRows=5的一个实例来找规律解题
     * 这个是逐个逐个的将char字符加入StringBuilder
     * 498对角线遍历（数组--二维）也可以直接找规律模拟出路径，但是代码更麻烦难写
     */
    public String convertMy(String s, int numRows) {
        int n=s.length();
        if(n<=numRows||numRows==1) return s;
        StringBuilder sb=new StringBuilder();
        int start=1;
        while(start<=numRows){
            int i=start-1;
            if(start==1||start==numRows){
                while(i<n){
                    sb.append(s.charAt(i));
                    i+=2*numRows-2;
                }
            }else{
                boolean isOdd=false;
                while(i<n){
                    sb.append(s.charAt(i));
                    if(!isOdd){
                        i+=2*numRows-2*start;
                    }else{
                        i+=2*start-2;
                    }
                    isOdd=!isOdd;
                }
            }
            ++start;
        }
        return sb.toString();
    }

    /**
     * 优解：在遍历字符的时候将字符添加到对应的下标的StringBuilder中--按行将字符加入StringBuilder
     * 最后将每行对应StringBuilder内容汇总起来
     */
    public String convert(String s, int numRows) {
        if(numRows ==1 ) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for(int i = 0; i < numRows; i++)
            rows.add(new StringBuilder());
        //stepLen是行索引移动的步长，在向下移动时步长为1，翻折后步长为-1
        int i = 0, stepLen = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) stepLen = - stepLen;
            i += stepLen;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) 
            res.append(row);
        return res.toString();
    }

}
