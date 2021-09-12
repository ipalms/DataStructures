package algorithm.BFSOrDFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例 1：
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class IslandsNums200 {
    int n;
    int m;

    /**
     * 解法有深度|广度优先遍历、并查集
     * 网格问题的dfs，要考虑四个旁边节点  类似二叉树需要遍历左右子节点
     * 网格问题dfs有 200、463、695
     */


    /**
     * 深度优先遍历一
     * 申明了访问数组记录
     */
    public int numIslands(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int res = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //判断这个节点是否访问过，如果访问过，就跳过
                if (!visited[i][j]) {
                    //没访问过就深度遍历所有相邻的1
                    if (grid[i][j] == '1') {
                        dfs(grid, visited, i, j);
                        res++;
                    }
                    visited[i][j] = true;
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x > n - 1 || y < 0 || y > m - 1 || visited[x][y] || grid[x][y] == '0') {
            return;
        }
        visited[x][y] = true;
        dfs(grid, visited, x, y + 1);
        dfs(grid, visited, x + 1, y);
        dfs(grid, visited, x, y - 1);
        dfs(grid, visited, x - 1, y);
    }


    /**
     * 深度优先遍历二
     * 没有申明访问数组 改变原了二维数组
     */
    public int numIslands1(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs1(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs1(char[][] grid, int x, int y) {
        //判断处改成grid[x][y]!='1'
        if (x < 0 || x > n - 1 || y < 0 || y > m - 1 || grid[x][y] != '1') {
            return;
        }
        //对原数组值进行改变起到标记作用
        grid[x][y] = '2';
        dfs1(grid, x, y + 1);
        dfs1(grid, x + 1, y);
        dfs1(grid, x, y - 1);
        dfs1(grid, x - 1, y);
    }

    /**
     * 广度优先
     */
    public int numIslands2(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] ad = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    queue.add(new int[]{i, j});
                    grid[i][j] = '0';
                    while (!queue.isEmpty()) {
                        int[] top = queue.poll();
                        int mx = top[0], my = top[1];
                        for (int k = 0; k < 4; k++) {
                            int x = mx + ad[k][0];
                            int y = my + ad[k][1];
                            if (x < 0 || x > n - 1 || y < 0 || y > m - 1 || grid[x][y] == '0') {
                                continue;
                            }
                            queue.add(new int[]{x, y});
                            grid[x][y] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }
}
