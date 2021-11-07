package algorithm.Array;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * 提示：
 * 1 <= n <= 20
 */
public class SpinOrderII59 {


    /**
     * 此题较54不同一点是n*n矩阵(行与列元素个数相等)
     * 更佳的遍历方式：
     * 按层模拟遍历(或者称之为转圈遍历，从矩阵外层逐渐遍历到矩阵内层)
     * 可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。
     */
    public int[][] generateMatrix(int n) {
        int left=0,right=n-1,top=0,bottom=n-1;
        int total=n*n,j=0;
        int [][]res=new int[n][n];
        while(j<total){
            for(int i=left;i<=right;++i){
                res[top][i]=++j;
            }
            ++top;
            for(int i=top;i<=bottom;++i){
                res[i][right]=++j;
            }
            --right;
            for(int i=right;i>=left;--i){
                res[bottom][i]=++j;
            }
            --bottom;
            for(int i=bottom;i>=top;--i){
                res[i][left]=++j;
            }
            ++left;
        }
        return res;
    }
}
