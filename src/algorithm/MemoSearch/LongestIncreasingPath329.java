package algorithm.MemoSearch;

/**
 * 329. 矩阵中的最长递增路径
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * 示例 1：
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 * 示例 2：
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：1
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPath329 {

    /**
     * 此题最朴素的思想是对每个节点进行一次dfs，但是这样会导致超时现象
     * 朴素深度优先搜索的时间复杂度过高的原因是进行了大量的重复计算，同一个单元格会被访问多次，每次访问都要重新计算。
     *
     * ****由于同一个单元格对应的最长递增路径的长度是固定不变的****，因此可以使用记忆化的方法进行优化。
     * 用矩阵memo作为缓存矩阵，已经计算过的单元格的结果存储到缓存矩阵中。
     * 使用记忆化深度优先搜索，当访问到一个单元格(i,j)时,如果memo[i][j]≠0
     * 说明该单元格的结果已经计算过，则直接从缓存中读取结果
     * 如果memo[i][j] = 0，说明该单元格的结果尚未被计算过，则进行搜索，并将计算得到的结果存入缓存中。
     * 遍历完矩阵中的所有单元格之后，即可得到矩阵中的最长递增路径的长度。
     */


    /**
     * dfs和记忆化
     */
    int [][]memo;
    int m,n;
    int [][]dir=new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int longestIncreasingPath(int[][] matrix) {
        m=matrix.length;
        n=matrix[0].length;
        memo=new int[m][n];
        int ans=0;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                //更新最大值
                ans=Math.max(ans,dfs(matrix,i,j));
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix,int x,int y){
        //记忆化的普遍步骤是先去查询缓存中是否存在过该点对应的记录
        if(memo[x][y]!=0){
            return memo[x][y];
        }
        //缓存中不存在该数据，则后面的步骤中一定要把这一步最终的结果写入到缓存当中去

        //首先应该被初始化成1----单个节点只能构成长度为1的序列
        memo[x][y]=1;
        for(int k=0;k<4;++k){
            int i=x+dir[k][0],j=y+dir[k][1];
            //二维数组边界判定  是否能构成递增判定
            if(i<0||i>m-1||j<0||j>n-1||matrix[x][y]>=matrix[i][j]){
                continue;
            }
            //更新缓存值
            memo[x][y]=Math.max(memo[x][y],dfs(matrix,i,j)+1);
        }
        return memo[x][y];
    }
}
