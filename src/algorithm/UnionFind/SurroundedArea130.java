package algorithm.UnionFind;

/**
 *130. 被围绕的区域
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
    //并查集类
    static class UF{
        private int[] parents;
        private int[] treeSize;

        public UF(int N){
            parents = new int[N];
            treeSize = new int[N];
            for(int i = 0; i < N; i++){
                parents[i] = i;
                treeSize[i] = 1;
            }
        }

        public int find(int i){
            //查找当前树的根节点
            int root = i;
            while(root != parents[root])
                root = parents[root];

            //路径压缩
            int next;
            while(i != parents[i]){
                next = parents[i];
                parents[i] = root;
                i = next;
            }
            return root;
        }

        public boolean connected(int p, int q){
            return find(p) == find(q);
        }

        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;
            if(treeSize[rootP] < treeSize[rootQ]){ //小树链接到大树上
                parents[rootP] = rootQ;
                treeSize[rootQ]+=treeSize[rootP];
            }
            else {
                parents[rootQ] = rootP;
                treeSize[rootP]+=treeSize[rootQ];
            }
        }
    }

    //将二维坐标转化为一维坐标, 便于并查集使用
    //ｘ为二维数组的一维索引，　ｙ为二维数组的二维索引
    private int conversion(int x, int y, int width){
        return x * width + y;
    }

    public void solve(char[][] board) {
        if(board.length == 0) return;
        int len = board.length;
        int width = board[0].length;
        //boardSize 代表虚拟节点的位置
        int boardSize = len * width;
        UF uf = new UF(boardSize + 1);
        //添加一个虚拟节点，所有位于边界的Ｏ节点均与该虚拟节点相连接
        int i, j;
        for(i = 0; i < board.length; i++){
            for(j = 0; j < board[0].length; j++){
                if((i == 0 || i == board.length-1 || j == 0 || j == board[0].length-1) && board[i][j]=='O')
                    uf.union(conversion(i, j, width), boardSize);
            }
        }

        //遍历搜索相邻的Ｏ，添加到并查集中
        for(i = 0; i < board.length; i++){
            for(j = 0;j < board[0].length; j++){
                if(board[i][j] == 'O'){
                    //将当前Ｏ点与其上下左右四个方向的Ｏ点相连接
                    if(i-1 >=0 && board[i-1][j] == 'O')
                        uf.union(conversion(i-1, j, width), conversion(i, j, width));
                    if(i+1 < board.length && board[i+1][j] == 'O')
                        uf.union(conversion(i+1, j, width), conversion(i, j, width));
                    if(j-1 >= 0 && board[i][j-1] == 'O')
                        uf.union(conversion(i, j-1, width), conversion(i, j, width));
                    if(j+1 <= board[0].length && board[i][j] == 'O')
                        uf.union(conversion(i, j+1, width), conversion(i, j, width));
                }
            }
        }

        //将所有与边界节点不相连的＇Ｏ＇点替换为＇Ｘ＇
        for(i = 0; i < board.length; i++){
            for(j = 0; j < board[0].length; j++){
                //判断内部节点是否和虚拟节点相连
                if(board[i][j] == 'O' && !uf.connected(conversion(i, j, width), boardSize))
                    board[i][j] = 'X';
            }
        }
    }

    /**
     * 并查集版本二
     */
    public void solve1(char[][] board) {
        int row = board.length;
        if (row == 0) return;
        int col = board[0].length;
        int dummy = row * col;
        int[][] d = new int[][]{{1,0}, {0,1}, {0,-1}, {-1,0}};
        UF uf = new UF(dummy+1);

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (board[i][j] == 'O') {
                    //边界O
                    if (i==0 || i==row-1 || j==0 || j==col-1) {
                        uf.union(i*col+j, dummy);
                    } else {
                        //内部O
                        for (int k=0; k<4; k++) {
                            int x = i+d[k][0];
                            int y = j+d[k][1];
                            if (board[x][y] == 'O') uf.union(x*col+y, i*col+j);
                        }
                    }
                }
            }
        }
        for (int i=1; i<row-1; i++) {
            for (int j=1; j<col-1; j++) {
                if (board[i][j] == 'O' &&!uf.connected(i*col+j, dummy))
                    board[i][j] = 'X';
            }
        }
    }
}
