package algorithm.String;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class TwoBinaryAdd67 {
    /**
     * 因为是二进制的特殊原因 可以使用2、43、415题之类的接替方式也可使用位运算进行解题
     */

    /**
     * 字符串相加题解
     */
    public String addBinary(String a, String b) {
        StringBuilder sb=new StringBuilder();
        int i=a.length()-1,j=b.length()-1,carry=0;
        while(i>=0||j>=0||carry!=0){
            if(i>=0){
                carry+=a.charAt(i--)-'0';
            }
            if(j>=0){
                carry+=b.charAt(j--)-'0';
            }
            sb.append(carry&1);
            carry=carry/2;
        }
        return sb.reverse().toString();
    }
}
