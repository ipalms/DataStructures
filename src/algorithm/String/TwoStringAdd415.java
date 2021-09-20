package algorithm.String;

import org.junit.Test;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 提示：
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 */
public class TwoStringAdd415 {
    @Test
    public void test() {
        String num1="123";
        String num2="11";
        System.out.println(addStrings(num1,num2));
    }

    /**
     * 大数相加
     */

    /**
     * 简洁写法  --类似leetcode第二题两数相加
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder sb=new StringBuilder();
        int i=num1.length()-1,j=num2.length()-1,carry=0;
        //采用并结构循环   --可以把carry!=0放到循环外单独处理
        while(i>=0||j>=0||carry!=0){
            //也相当于后面没有数时，默认为0处理  如下
            //int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            if(i>=0){
                carry+=num1.charAt(i--)-'0';
            }
            if(j>=0){
                carry+=num2.charAt(j--)-'0';
            }
            sb.append(carry%10);
            carry=carry/10;
        }
        return sb.reverse().toString();
    }
    /**
     * 没有简化操作 没有合并共同操作
     */
    public String addStringsMy(String num1, String num2) {
        StringBuilder sb=new StringBuilder();
        int len1=num1.length();
        int len2=num2.length();
        int min=Math.min(len1,len2);
        int mod=0,i=0;
        while(i<min){
            int n1=num1.charAt(len1-i-1)-'0';
            int n2=num2.charAt(len2-i-1)-'0';
            sb.append((n1+n2+mod)%10);
            mod=(n1+n2+mod)/10;
            i++;
        }
        if(len2>len1){
            while(min<len2){
                sb.append((num2.charAt(len2-min-1)-'0'+mod)%10);
                mod=(num2.charAt(len2-min-1)-'0'+mod)/10;
                min++;
            }
        }
        if(len2<len1){
            while(min<len1){
                sb.append((num1.charAt(len1-min-1)-'0'+mod)%10);
                mod=(num1.charAt(len1-min-1)-'0'+mod)/10;
                min++;
            }
        }
        if(mod!=0){
            sb.append(mod);
        }
        return sb.reverse().toString();
    }
}
