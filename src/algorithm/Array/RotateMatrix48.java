package algorithm.Array;

/**
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：[[1]]
 * 示例 4：
 * 输入：matrix = [[1,2],[3,4]]
 * 输出：[[3,1],[4,2]]
 *
 * 提示：
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateMatrix48 {

    /**
     * 旋转矩阵90度，189是旋转数组
     * 本质的思想都是环状替代（当前数替代下一个数，循环到环首尾相连时，这时可只用一个tmp变量保存环头节点的值）
     * 还有一种通用的思路就是多次反转（翻折）数组或矩阵
     * 重要的是找到反转后的坐标对应规律：(i,j)->(j,n-i)
     *
     * 扩展--顺时针转 180 度:先上下镜像，再左右镜像（先左右再上下也可）
     * 顺时针270度：(i,j)->(n-j,i)  先左右镜像再沿着对角线交换
     */


    /**
     * 矩阵先上下反转，再对角反转---这种比较容易写
     * 当然也可以先转置（按照对角线反转）再以矩阵中线进行左右反转
     *
     * 以上两种方案的两次翻折起到的总体作用就是matrix[row][col]-->matrix[col][n−row−1]
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 一次性旋转四个节点（正好首尾相连）
     * 重要规律：对于矩阵中第i行的第j个元素，在旋转后，它出现在倒数第j行的第i个位置。
     * 因此对于矩阵中的元素matrix[row][col]，在旋转后它的新位置为matrix[col][n−row−1]。
     * 旋转起始点是矩阵的左上方，这样正好旋转后覆盖整个矩阵（如果矩阵长度为奇数，那么中心节点也不需要旋转）
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //i , j代表行和列
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                //一次性旋转四个节点
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }



    /**
     * 自己做的-- 一次旋转一个点
     * 重要规律：对于矩阵中第i行的第j个元素，在旋转后，它出现在倒数第i列的第j个位置。
     * 即由于矩阵中的行列从0开始计数，因此对于矩阵中的元素matrix[row][col]，
     * 在旋转后它的新位置为matrix[col][n−row−1]。
     * 旋转起点x，y沿着对角线
     */
    public void rotateMy(int[][] matrix) {
        int n=matrix.length;
        for(int i=0;i<n/2;++i){
            int x=i;
            for(int y=i;y<n-1-i;++y){
                int xk=i,yk=i,pre=matrix[x][y];
                for(int k=0;k<4;++k){
                    //规律的体现
                    xk=y;
                    yk=n-x-1;
                    int tmp=matrix[xk][yk];
                    matrix[xk][yk]=pre;
                    pre=tmp;
                    x=xk;
                    y=yk;
                }
            }
        }
    }
}
