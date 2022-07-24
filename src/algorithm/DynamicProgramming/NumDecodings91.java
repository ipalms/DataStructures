package algorithm.DynamicProgramming;


/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
 * 例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，
 * 这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 * */
public class NumDecodings91 {

    /**
     爬楼梯类似
     如果连续的两位数符合条件，就相当于一个上楼梯的题目，可以有两种选法：
     1.一位数决定一个字母
     2.两位数决定一个字母
     就相当于dp(i) = dp[i-1] + dp[i-2];
     如果不符合条件，又有两种情况
     1.当前数字是0：
     不好意思，这阶楼梯不能单独走，
     dp[i] = dp[i-2]
     2.当前数字不是0
     不好意思，这阶楼梯太宽，走两步容易扯着步子，只能一个一个走
     dp[i] = dp[i-1];
     */
    public int numDecodingsMy(String s) {
        if(s.charAt(0)=='0'){
            return 0;
        }
        int []dp=new int[s.length()+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)=='0'){
                if(s.charAt(i-1)!='1'&&s.charAt(i-1)!='2'){
                    return 0;
                }
                dp[i+1]=dp[i-1];
            }else if(s.charAt(i-1)=='1'||(s.charAt(i-1)=='2'&&s.charAt(i)<='6')){
                dp[i+1]=dp[i-1]+dp[i];
            }else{
                dp[i+1]=dp[i];
            }
        }
        return dp[s.length()];
    }

    /**
    * 将if 语句合并起来---精简版
    * */
    public int numDecodings(String s) {
        if(s.charAt(0)=='0'){
            return 0;
        }
        int []dp=new int[s.length()+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=1;i<s.length();i++){
            dp[i+1]=s.charAt(i)=='0'?0:dp[i];
            if(s.charAt(i-1)=='1'||(s.charAt(i-1)=='2'&&s.charAt(i)<='6')){
                dp[i+1]+=dp[i-1];
            }
        }
        return dp[s.length()];
    }
}
