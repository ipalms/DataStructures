package algorithm.Array;

/**
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
 * 请使用 原地 算法。
 * 进阶：
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * 提示：
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 */
public class SetZeroes73 {

    /**
     * 如果一次遍历数组或矩阵的题找不到思路可以试试两次遍历
     */

    /**
     * 我们可以用两个标记数组分别记录每一行和每一列是否有零出现。
     * 具体地，我们首先遍历该数组一次，如果某个元素为0
     * 那么就将该元素所在的行和列所对应标记数组的位置置为true。
     * 最后我们再次遍历该数组，用标记数组更新原数组即可。
     * 空间O(m + n)
     */
    public void setZeroes(int[][] matrix) {
        boolean []row=new boolean[matrix.length];
        boolean []col=new boolean[matrix[0].length];
        for(int i=0;i<matrix.length;++i){
            for(int j=0;j<matrix[0].length;++j){
                if(matrix[i][j]==0){
                    row[i]=true;
                    col[j]=true;
                }
            }
        }
        for(int i=0;i<matrix.length;++i){
            for(int j=0;j<matrix[0].length;++j){
                if(row[i]||col[j]){
                    matrix[i][j]=0;
                }
            }
        }
    }

    /**
     * 优化空间：空间O（1）--利用原矩阵解题
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到0(1)的额外空间。
     * 但这样会导致原数组的第一行和第一列被修改, 无法记录它们是否原本包含0。
     * 因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含0。
     * 在实际代码中，我们首先预处理出两个标记变量,接着使用其他行与列去处理第一行与第一列
     * 然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
     */
    public void setZeroes1(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean rowFlag = false;
        boolean colFlag = false;
        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }
        // 第一列是否有零
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
                break;
            }
        }
        // 把第一行第一列作为标志位
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowFlag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (colFlag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
