package algorithm.BFSOrDFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
 * 并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 */
public class SurroundedArea130 {
    /**
     * 本题可以使用深度优先/广度优先实现，也可以使用并查集实现
     * 此题与200题相似，也可以使用如上三种解法
     */

    /**
     * 从边界进行DFS
     * 注意到题目解释中提到：任何边界上的 O 都不会被填充为 X。
     * 我们可以想到，所有的不被包围的 O 都直接或间接与边界上的 O 相连。
     * 我们可以利用这个性质判断 O 是否在边界上，具体地说：
     *
     * 对于每一个边界上的 O，我们以它为起点，标记所有与它直接或间接相连的字母O；
     * 最后我们遍历这个矩阵，对于每一个字母：
     * 如果该字母被标记过，则该字母为没有被字母 X 包围的字母 O，我们将其还原为字母 O；
     * 如果该字母没有被标记过，则该字母为被字母 X 包围的字母 O，我们将其修改为字母 X。
     */

    /**
     * 深度优先遍历
     * 我们把标记过的字母 O 修改为字母 A
     * 时间复杂度：O(n×m)，其中n和m分别为矩阵的行数和列数。深度优先搜索过程中，每一个点至多只会被标记一次。
     * 空间复杂度：O(n×m)，其中n和m分别为矩阵的行数和列数。主要为深度优先搜索的栈的开销。
     */
    int m;
    int n;
    public void solve(char[][] board) {
        m=board.length;
        n=board[0].length;
        for(int i=0;i<m;i++){
            dfs(board,i,0);
            dfs(board,i,n-1);
        }
        for(int i=1;i<n-1;i++){
            dfs(board,0,i);
            dfs(board,m-1,i);
        }
        //最后对标记和未标记的O元素进行处理
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='A'){
                    board[i][j]='O';
                }else if(board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
    }

    public void dfs(char[][] board,int x,int y){
        //排除超过边界的元素以及元素为X或者为已经标记的元素
        //board[x][y]!='O' 能够给已标记的元素进行筛选的作用
        if(x<0||x>m-1||y<0||y>n-1||board[x][y]!='O'){
            return;
        }
        board[x][y]='A';
        dfs(board,x-1,y);
        dfs(board,x,y-1);
        dfs(board,x+1,y);
        dfs(board,x,y+1);
    }

    /**
     * 广度优先进行遍历
     * 时间复杂度：O(n×m)，其中n和m分别为矩阵的行数和列数。广度优先搜索过程中，每一个点至多只会被标记一次。
     * 空间复杂度：O(n×m)，其中n和m分别为矩阵的行数和列数。主要为广度优先搜索的队列的开销。
     */
    static class BFS {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        public void solve(char[][] board) {
            int n = board.length;
            int m = board[0].length;
            Queue<int[]> queue = new LinkedList<int[]>();
            //先将边界上的所有O元素加入队列
            for (int i = 0; i < n; i++) {
                if (board[i][0] == 'O') {
                    queue.offer(new int[]{i, 0});
                    board[i][0] = 'A';
                }
                if (board[i][m - 1] == 'O') {
                    queue.offer(new int[]{i, m - 1});
                    board[i][m - 1] = 'A';
                }
            }
            for (int i = 1; i < m - 1; i++) {
                if (board[0][i] == 'O') {
                    queue.offer(new int[]{0, i});
                    board[0][i] = 'A';
                }
                if (board[n - 1][i] == 'O') {
                    queue.offer(new int[]{n - 1, i});
                    board[n - 1][i] = 'A';
                }
            }
            //遍历每个边界上节点的相邻节点
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; i++) {
                    int mx = x + dx[i], my = y + dy[i];
                    if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
                        continue;
                    }
                    queue.offer(new int[]{mx, my});
                    board[mx][my] = 'A';
                }
            }
            //最后对标记和未标记的O元素进行处理
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
}
