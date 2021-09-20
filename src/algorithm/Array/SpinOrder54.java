package algorithm.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpinOrder54 {

    /**
     * 更佳的遍历方式：
     * 按层模拟遍历(或者称之为转圈遍历，从矩阵外层逐渐遍历到矩阵内层)
     * 可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。
     */
    public List<Integer> spiralOrder1(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int left=0,right=m-1;
        int top=0,bottom=n-1;
        List<Integer> res = new ArrayList<>();
        int all = n*m;
        while(all>=1){
            //实际上只有最后两个循环需要加上all>=1的扩展条件，加上all>=1的目的是避免遍历到重复元素
            //如果是n*n矩阵就不需要考虑会遍历到循环元素，因为内层四个循环遍历结束后才会触发外层all<1退出外部循环
            for(int i=left;i<=right;++i){
                res.add(matrix[top][i]);
                --all;
            }
            ++top;
            for(int i=top;i<=bottom;++i){
                res.add(matrix[i][right]);
                --all;
            }
            --right;
            for(int i=right;i>=left&&all>=1;--i){
                res.add(matrix[bottom][i]);
                --all;
            }
            --bottom;
            for(int i=bottom;i>=top&&all>=1;--i){
                res.add(matrix[i][left]);
                --all;
            }
            ++left;
        }
        return res;
    }

    /**
     * 模拟路径遍历（按边模拟路径，不修改输入，使用visited数组）
     * 判断路径是否进入之前访问过的位置需要使用一个与输入矩阵大小相同的辅助矩阵visited，
     * 其中的每个元素表示该位置是否被访问过。当一个元素被访问时，将visited中的对应位置的元素设为已访问。
     */
    public List<Integer> spiralOrderMy(int[][] matrix) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        res.add(matrix[0][0]);
        int start = 0, x = 0, y = 0, total = 1, all = m * n;
        while (total < all) {
            int[] d = dir[start];
            x += d[0];
            y += d[1];
            while (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && !visited[x][y]) {
                res.add(matrix[x][y]);
                visited[x][y] = true;
                ++total;
                x += d[0];
                y += d[1];
            }
            x -= d[0];
            y -= d[1];
            start = (start + 1) % 4;
        }
        return res;
    }

    /**
     * 上面思路的简易写法（修改原输入数组做标记）
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        int n = matrix.length, m = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        //i ,j为遍历过程的横纵坐标，d为遍历的边的累计
        int i = 0, j = 0, d = 0;
        for (int k = 0; k < n * m; k++) {
            res.add(matrix[i][j]);
            matrix[i][j] = 101;
            int a = i + dx[d], b = j + dy[d];
            //越界或者已近遍历过了说明当前需要转向
            if (a < 0 || a >= n || b < 0 || b >= m || matrix[a][b] == 101) {
                d = (d + 1) % 4;
                //重新计算转向后的i ,j坐标位置
                a = i + dx[d];
                b = j + dy[d];
            }
            i = a;
            j = b;
        }
        return res;
    }
}
