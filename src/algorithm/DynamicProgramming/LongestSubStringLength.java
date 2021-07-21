package algorithm.DynamicProgramming;

public class LongestSubStringLength {
    public static void main(String[] args) {
        //最长子序列长度例子
        System.out.println(findLCS("android", "random"));
    }

    public static int findLCS(String A, String B) {
        int n = A.length();
        int m = B.length();
        //返回一个字符数组，该字符数组中存放了当前字符串中的所有字符
        //返回的是字符数组char[]a
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        //创建一个二维矩阵，用来推到公共子序列
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            //如果找到第一列其中一个字符等于第一行第一个字符
            if (a[i] == b[0]) {
                //找到第一列与第一行b[0]的相等的值，把其变成1
                dp[i][0] = 1;
                //并将其后面的字符都变成1
                for (int j = i + 1; j < n; j++) {
                    dp[j][0] = 1;
                }
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            //如果找到第一列其中一个字符等于第一行第一个字符
            if (b[i] == a[0]) {
                //则把第一列后面的字符都变成1
                dp[0][i] = 1;
                for (int j = i + 1; j < m; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }
        //从1开始是因为横向和纵向下标为0的都遍历过了
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                //横向和纵向有相等的值
                if (a[i] == b[j]) {
                    //当前位置左边的值+1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //取当前位置（左边的值，上边的值）的最大值
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dp[i][j] + "  ");
            }
            System.out.println();
        }
        return dp[n - 1][m - 1];
    }
}
