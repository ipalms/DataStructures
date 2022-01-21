package algorithm.DynamicProgramming;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class LongestPalindrome5 {

    /**
     * 区间dp的一些套路：
     * 区间dp在给定一个回文串的基础上，如果在回文串的边缘分别添加两个新的字符，可以通过判断两字符是否相等来得知新串是否回文
     * 通常区间 DP 问题都是，常见的基本流程为：
     * 从小到大枚举区间大小len
     * 枚举区间左端点l，同时根据区间大小len和左端点计算出区间右端点r=l+len−1
     * 通过状态转移方程求 dp[l][r]的值
     */


    /**
     * 516. 最长回文子序列 --也是区间dp,做法相似
     * 动态规划：大提思路是枚举回文串长度，嵌套枚举左节点---相应的就确定了右节点位置，在利用状态转移方程不断填充dp数组并更新最大回文子串位置
     * 维护二维dp数组就能利用到回文的性质：一个长度严格大于 22 的回文去掉头尾字符以后，剩下的部分依然是回文。反之，如果一个字符串头尾两个字符都不相等，那么这个字符串一定不是回文。
     * dp[i][j] 表示：子串 s[i..j] 是否为回文子串
     * dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
     * 填表应该遵守这样的原则：总是先得到小子串是否是回文的结果，然后大子串才能参考小子串的判断结果，所以填表顺序很重要
     * 时间O（N^2） 空间O（N^2）
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        //由于左边界小于等于右边界所以dp数组最终赋值是呈现左下三角形的
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        //注意这里是等于号 L <= len
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些i+L-1（右边界）<len，这样右边界就一定不会出界
            //当然这个上限可以直接换成
            for (int i = 0; L + i - 1 < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
//                // 如果右边界越界，就可以退出当前循环
//                if (j >= len) {
//                    break;
//                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    //当i到j之间只有两个或者三个元素，且charArray[i] == charArray[j]
                    //那么这一段[i,j]一定是回文的
                    if (L <= 3) {
                        dp[i][j] = true;
                    } else {
                        //区间大于三个元素就取决于左右边界各收缩一格的字串是否回文
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && L > maxLen) {
                    maxLen = L;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    /**
     * 自己后面仿写的中心扩散法
     * 中心扩散法的中心点要考虑要考虑一个和两个组成的情况
     */
    int len;
    public String longestPalindromeMy(String s) {
        len=s.length();
        int max=1,left=0;
        char []tmp=s.toCharArray();
        for(int i=0;i<len;++i){
            int []a=findMax(tmp,i,i);
            int []b=findMax(tmp,i,i+1);
            int []res=a[1]>b[1]?a:b;
            if(res[1]>max){
                max=res[1];
                left=res[0];
            }
        }
        return s.substring(left,left+max);
    }


    public int[] findMax(char []tmp,int left,int right){
        while(left>=0&&right<len&&tmp[left]==tmp[right]){
            --left;
            ++right;
        }
        return new int[]{left+1,right-left-1};
    }

    /**
     * 中心扩散法 --遍历每一个下标，以这个下标为中心，利用「回文串」中心对称的特点，往两边扩散，看最多能扩散多远
     * 枚举「中心位置」时间复杂度为 O(N)O(N)，从「中心位置」扩散得到「回文子串」的时间复杂度为O(N^2)
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if(len < 2) return s;
        int maxLen = 0;
        // 数组第一位记录起始位置，第二位记录长度
        int[] res = new int[2];
        for (int i = 0; i < s.length() - 1; i++) {
            //统计奇偶下的结果--取较大值
            int[] odd = centerSpread(s, i, i);
            int[] even = centerSpread(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    //中心扩散
    private int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        //这里的left 和 right都是在合法回文子串下的左右边界各偏离1，返回时要把这个偏差算在内
        return new int[]{left + 1, right - left - 1};
    }


    /**
     * 暴力解法
     * 根据回文子串的定义，枚举所有长度大于等于2的子串，依次判断它们是否是回文；
     * 可以只针对大于「当前得到的最长回文子串长度」的子串进行回文验证
     * 时间复杂度：O(N^3).这里N是字符串的长度，枚举字符串的左边界、右边界，然后继续验证子串是否是回文子串
     * 空间复杂度：O(1)O
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();
        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
