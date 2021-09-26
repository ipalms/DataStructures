package algorithm.String;

import org.junit.Test;

/**
 *  补充题：
 *  大数相加36进制版
 *  36进制由0-9，a-z，共36个字符表示。
 *  要求按照加法规则计算出任意两个36进制正整数的和，如1b + 2x = 48  （解释：47+105=152）
 *  要求：不允许使用先将36进制数字整体转为10进制，相加后再转回为36进制的做法
 */
public class TwoBigStringAddBase36 {

    @Test
    public void test(){
        System.out.println(addStrings16("ab","1c"));
    }


    /**
     * 在原415两个十进制string相加的基础上只要多加两个36进制字符和10进制数值对换的方法就好了
     * 同理16进制的互换也是如此
     */
    public String addStrings36(String num1, String num2) {
        StringBuilder sb=new StringBuilder();
        int n1=num1.length(),n2=num2.length();
        int i=n1-1,j=n2-1,mod=0,x,y;
        while(i>=0||j>=0||mod>0){
            x=i>=0?toInt(num1.charAt(i)):0;
            y=j>=0?toInt(num2.charAt(j)):0;
            int tmp=x+y+mod;
            sb.append(toChar(tmp%36));
            mod=tmp/36;
            --i;
            --j;
        }
        return sb.reverse().toString();
    }

    private int toInt(char c){
        if(c>='0'&&c<='9'){
            return c-'0';
        }else {
            return 10+c-'a';
        }
    }

    private char toChar(int n){
        if(n<=9){
            return (char)('0'+n);
        }else{
            return (char)('a'+n-10);
        }
    }


    /**
     * 大数16进制相加
     * 16进制只需要改变除余，32进制也同样
     */
    public String addStrings16(String num1, String num2) {
        StringBuilder sb=new StringBuilder();
        int n1=num1.length(),n2=num2.length();
        int i=n1-1,j=n2-1,mod=0,x,y;
        while(i>=0||j>=0||mod>0){
            x=i>=0?toInt(num1.charAt(i)):0;
            y=j>=0?toInt(num2.charAt(j)):0;
            int tmp=x+y+mod;
            sb.append(toChar(tmp%16));
            mod=tmp/16;
            --i;
            --j;
        }
        return sb.reverse().toString();
    }

}
