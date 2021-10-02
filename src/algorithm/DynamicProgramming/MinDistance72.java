package algorithm.DynamicProgramming;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class MinDistance72 {

    /**
     * 这一题其实也算是1143 最长公共子序列的思想类似题--可以使用1143去理解此题（空间优化思路也一致）
     * dp[i][j]中保存的是修改成一样的最小操作数至于字符串具体改成什么样子了不用费心（要求具体改成什么样子的是不涉及的算法）
     * https://leetcode-cn.com/problems/longest-common-subsequence/solution/1143-zui-chang-gong-gong-zi-xu-lie-dong-zde2v/
     * https://leetcode-cn.com/problems/edit-distance/solution/dong-tai-gui-hua-java-by-liweiwei1419/
     */
    // 只讨论 word1 → word2
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        //dp含义我们有word1和word2，我们定义dp[i][j]的含义为：
        // word1的前i个字符和word2的前j个字符的编辑距离。
        // 意思就是word1的前i个字符，变成word2的前j个字符，最少需要这么多步。
        // 多开一行一列是为了保存边界条件，即字符长度为 0 的情况，这一点在字符串的动态规划问题中比较常见
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化：当 word2 长度为 0 时，将 word1 的全部删除即可
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 当 word1 长度为 0 时，插入所有 word2 的字符即可
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        // 由于 word1.charAt(i) 操作会去检查下标是否越界，因此在 Java 里，将字符串转换成字符数组是常见额操作
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();
        // 递推开始，注意：填写 dp 数组的时候，由于初始化多设置了一行一列，横纵坐标有个偏移
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 这是最佳情况
                if (word1Array[i - 1] == word2Array[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    // 结论（结果由左上角三个节点之一的状态转移而来）
                    // 变形题：如果三种操作的代价不一样就需要加不同的数值（默认条件下就相当于三种操作的代价都量化为1）
                    // 删除操作：dp[i - 1][j]+1
                    // 增加操作：dp[i][j - 1]+1
                    // 替换操作：dp[i - 1][j - 1]+1

                    // 否则在以下三种情况中选出步骤最少的，这是「动态规划」的「最优子结构」
                    // 1、在下标 i 处插入一个字符
                    int insert = dp[i][j - 1] + 1;
                    // 2、替换一个字符
                    int replace = dp[i - 1][j - 1] + 1;
                    // 3、删除一个字符
                    int delete = dp[i - 1][j] + 1;
                    dp[i][j] = Math.min(Math.min(insert, replace), delete);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 可以进行空间优化---用变量保存左上角的变化次数
     */

    /**
     * 仿写的
     */
    public int minDistanceMy(String word1, String word2) {
        int n=word1.length(),m=word2.length();
        int [][]dp=new int[n+1][m+1];
        for(int i=1;i<=n;++i){
            dp[i][0]=i;
        }
        for(int j=1;j<=m;++j){
            dp[0][j]=j;
        }
        for(int i=1;i<=n;++i){
            char c1=word1.charAt(i-1);
            for(int j=1;j<=m;++j){
                if(c1==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
                }
            }
        }
        return dp[n][m];
    }
}
