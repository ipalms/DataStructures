package algorithm.String;

import org.junit.Test;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，
 * 它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class StringMultiply43 {

    @Test
    public void test() {
        String num1="123";
        String num2="456";
        System.out.println(multiply(num1,num2));
    }

    /**
     * 大数相乘
     */

    /**
     * 利用乘法规律
     * 乘数num1位数为 M，被乘数num2位数为N，num1 * num2 结果 res 最大总位数为 M+N
     * num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)
     * 其第一位位于 res[i+j]，第二位位于 res[i+j+1]。
     */
    public String multiply2(String num1, String num2) {
        //特殊情况
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                //相乘也要加上进位数
                //i j是从大到小的 所以数组存储的位是按进制递增的
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        //将结果转化位字符串
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            //判断最高位是否为0
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }

    /**
     * 复杂一点的 拆分+字符串相加
     * 借用了415题的字符串相加
     * 即一个字符串数的每一位与另一个字符串数的一位相乘结果类积--对与相应位的数要进行补0
     */
    public String multiply(String num1, String num2) {
        //特殊情况
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //记录总的累计大小
        String res="0";
        for(int i=num1.length()-1;i>=0;i--){
            //记录一个字符串与一位相乘的结果
            StringBuilder sb=new StringBuilder();
            //补零
            for(int j=0;j<num1.length()-i-1;j++){
                sb.append(0);
            }
            int n1=num1.charAt(i)-'0';
            int carry=0;
            //相乘(也要考虑到乘法的进位)
            for(int j=num2.length()-1;j>=0||carry!=0;j--){
                int n2= j>=0 ?num2.charAt(j)-'0':0;
                int sum=n1*n2+carry;
                sb.append(sum%10);
                carry=sum/10;
            }
            //进行字符串累加操作
            res=addTwoString(res,sb.reverse().toString());
        }
        return res;
    }

    public String addTwoString(String str1,String str2){
        StringBuilder sb=new StringBuilder();
        int i=str1.length()-1,j=str2.length()-1,carry=0;
        while(i>=0||j>=0||carry!=0){
            if(i>=0){
                carry+=str1.charAt(i--)-'0';
            }
            if(j>=0){
                carry+=str2.charAt(j--)-'0';
            }
            sb.append(carry%10);
            carry=carry/10;
        }
        return sb.reverse().toString();
    }


    /**
     * 错误做法  不能直接通过转化成数字相乘，数据较大时会溢出（超过long最大范围）
     */
    public String multiply1(String num1, String num2) {
        int len=num1.length();
        int len1=num2.length();
        int a=0,b=0;
        for(Character c:num1.toCharArray()){
            int pow = (c - '0')*(int) Math.pow(10, len - 1);
            a+=pow;
            len--;
        }
        for(Character c:num2.toCharArray()){
            int pow = (c - '0')*(int) Math.pow(10, len1 - 1);
            b+=pow;
            len1--;
        }
        return String.valueOf((long) a*b);
    }
}
