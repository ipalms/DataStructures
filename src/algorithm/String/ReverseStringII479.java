package algorithm.String;

/**
 * 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 示例 1：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 示例 2：
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文组成
 * 1 <= k <= 104
 */
public class ReverseStringII479 {

    /**
     * 题目的意思其实概括为 每隔2k个反转前k个，尾数不够k个时候全部反转
     * 将字符串转成字符数组临时存储字符串数据
     * 转换成字符数组处理数据（反转数据）比使用StringBuilder效率高的多
     * 官方的使用for循环交换数组
     */
    public String reverseStr2(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            //j的取法可以使用于不满k的情况
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }

    /**
     * 使用的StringBuilder临时存储字符串数据
     */
    public String reverseStr(String s, int k) {
        StringBuilder sb=new StringBuilder(s);
        int n=s.length();
        int i=0,j=2*k-1;
        while(j<n){
            int right=i+k-1;
            while(i<right){
                char tmp=sb.charAt(right);
                sb.setCharAt(right--,sb.charAt(i));
                sb.setCharAt(i++,tmp);
            }
            j+=2*k;
            i=j-2*k+1;
        }
        //不是整除的情况要计算边界的情况
        if(n%(2*k)!=0){
            int end=n-i<k?n-1:i+k-1;
            while(i<end){
                char tmp=sb.charAt(end);
                sb.setCharAt(end--,sb.charAt(i));
                sb.setCharAt(i++,tmp);
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串转成字符数组临时存储字符串数据--while循环置换
     * 转换成字符数组处理数据（反转数据）比使用StringBuilder效率高的多
     */
    public String reverseStr1(String s, int k) {
        char []res=s.toCharArray();
        int n=s.length();
        int i=0,j=2*k-1;
        while(j<n){
            int right=i+k-1;
            while(i<right){
                char tmp=res[right];
                res[right--]=res[i];
                res[i++]=tmp;
            }
            j+=2*k;
            i=j-2*k+1;
        }
        if(n%(2*k)!=0){
            int end=n-i<k?n-1:i+k-1;
            while(i<end){
                char tmp=res[end];
                res[end--]=res[i];
                res[i++]=tmp;
            }
        }
        return new String(res);
    }
}
