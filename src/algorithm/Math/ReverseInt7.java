package algorithm.Math;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 * 提示：
 * -231 <= x <= 231 - 1
 */
public class ReverseInt7 {

    /**
     * 题目要求不得使用long临时存储中间数据
     * 所以需要提前做好数据溢出处理
     * 可以维护一个pre（赋值给没扩张时的数据）校验扩张一位后的数据/10等不等于pre
     * 第8题字符串转换整数 (atoi)也用到了这个小技巧
     */

    public int reverse(int x) {
        if(x==0) return 0;
        int res=0,pre;
        while (x!=0){
            pre=res;
            res=res*10+x%10;
            if(pre!=res/10){
                return 0;
            }
            x=x/10;
        }
        return res;
    }

    /**
     * 更标准解法
     * 与第八题其中一种一样的判断手法
     */
    public int reverse2(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            //其实题目限制了数据范围是int类型，那么反转后只可能触及到第一个溢出条件
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            //其实题目限制了数据范围是int类型，那么反转后只可能触及到第一个溢出条件
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    /**
     * 转存成字符串后处理--麻烦了
     */
    public int reverse1(int x) {
        if(x==0) return 0;
        //判断符号的一种方式
        int flag=1;
        if(x<0){
            flag=-1;
        }
        String tmp=String.valueOf(Math.abs(x));
        int res=0,i=tmp.length()-1,pre=0;
        while (i>=0){
            pre=res;
            res=res*10+tmp.charAt(i)-'0';
            if(pre!=res/10){
                return 0;
            }
            --i;
        }
        return res*flag;
    }
}
