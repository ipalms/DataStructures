package algorithm.DynamicProgramming;

import org.testng.annotations.Test;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 提示：
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * */
public class MatchString10 {
    @Test
    public void test(){
        isMatch("aaa","a*");
    }

    /**
     * 十分相似的一题：44. 通配符匹配（初始化逻辑，转移方程的逻辑都大致一样）
     * 我们用f[i][j]示s的前i个字符与p中的前j个字符是否能够匹配。在进行状态转移时
     * 我们考虑p的第j个字符的匹配情况:
     * ● 如果p的第j个字符是一个小写字母，那么我们必须在s中匹配一个相同的小写字母，即
     * f[i][j]= f[i-1][j-1]， s[i]= p[i]
     *          false,s[i]≠p[i]
     * 也就是说，如果s的第i个字符与p的第j个字符不相同，那么无法进行匹配;
     * 否则我们可以匹配两个字符串的最后一个字符， 完整的匹配结果取决于两个字符串前面的部分。
     * ●如果p的第j个字符是*，那么就表示我们可以对p的第j- 1个字符匹配任意自然数次。
     * 在匹配0次的情况下，我们有:f[i][j]= f[i[j- 2]
     * 也就是我们「浪费」了一个字符+星号的组合,没有匹配任何s中的字符。
     * 在匹配1, 2,3,...次的情况下，类似地我们有
     * f[i][j]= f[i-1][j- 2]，
     * f[i][j]= f[i-2][j - 2]
     * 如果我们通过这种方法进行转移，那么我们就需要枚举这个组合到底匹配了s中的几个字符
     * 会增导致时间复杂度增加，代码编写起来十分麻烦。
     * 我们不妨换个角度考虑这个问题:星号的组合在匹配的过程中，本质上只会有两种情况:
     *     匹配s机的一个字符，将该字符扔掉，而该组合还可以继续进行匹配;
     *     不匹配字符，将该组合扔掉,不再进行匹配。
     * 如果按照这个角度进行思考，我们可以写出很精巧的状态转移方程:
     * f[i][j]=   f[i- 1][j] or f[i][j-2]， s[i]=p[i-1]
     *            f[i][j - 2],s[i]≠p[j- 1]
     * ●在任意情况下，只要p[j]是.，那么p[j]一定成功匹配s中的任意一个小写字母。
     * https://leetcode.cn/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode-solution/
     */

    //初始化情况不同的解法
    public boolean isMatch1(String s, String p) {
        int n=s.length(),m=p.length();
        boolean [][]match=new boolean[n+1][m+1];
        match[0][0]=true;
        //当p的长度为0，s长度不为0，那么一定是false
        //当s的长度为0，p的长度不为0，需要判断有无 * 进行初始化
        for(int i=2;i<=m;i++){
            if (p.charAt(i-1)=='*'){
                match[0][i]=match[0][i-2];
            }
        }
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.'){
                    match[i][j]=match[i-1][j-1];
                }else if (p.charAt(j-1)=='*'){
                    // * 前字符取0个的情况
                    // j-2不会小于零，因为测试用例保证了  *号前必有字符
                    match[i][j]=match[i][j-2];
                    if (s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.'){
                        match[i][j]=match[i][j]||match[i-1][j];
                    }
                }
            }
        }
        return match[n][m];
    }


    public boolean isMatch(String s, String p) {
        int n=s.length(),m=p.length();
        boolean [][]match=new boolean[n+1][m+1];
        //初始化操作  这里的初始化操作把n==0||m!=0的情况一并初始化
        //而是留到了下面for循环一并考虑
        match[0][0]=true;
        //最外层循环从0开始
        for (int i=0;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (p.charAt(j-1)=='*'){
                    // * 字符取0个的情况
                    match[i][j]=match[i][j-2];
                    //传入的应该是j-1   比较的是*前的字符的比较情况
                    if (judge(s,p,i,j-1)){
                        match[i][j]=match[i][j]||match[i-1][j];
                    }
                }else{
                    if (judge(s,p,i,j)){
                        match[i][j]=match[i-1][j-1];
                    }
                }
            }
        }
        return match[n][m];
    }

    public boolean judge(String s, String p, int i, int j){
        //考虑i=0的特殊情况  j不会取到零，因为测试用例保证了  *号前必有字符
        if (i==0) return false;
        if (p.charAt(j-1)=='.') return true;
        return s.charAt(i-1)==p.charAt(j-1);
    }



}
