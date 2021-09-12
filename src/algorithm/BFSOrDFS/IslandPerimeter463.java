package algorithm.BFSOrDFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 463. 岛屿的周长
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地，
 * grid[i][j] = 0 表示水域。网格中的格子 水平和垂直 方向相连（对角线方向不相连）。
 * 整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * 示例 1：
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 * 示例 2：
 * 输入：grid = [[1]]
 * 输出：4
 * 示例 3：
 * 输入：grid = [[1,0]]
 * 输出：4
 * 提示：
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] 为 0 或 1
 */
public class IslandPerimeter463 {

    int n;
    int m;

    /**
     * dfs解法
     */
    public int islandPerimeter(int[][] grid) {
        n=grid.length;
        m=grid[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    //扫描到一个节点后进行深度优先查找
                    return dfs(grid,i,j);
                }
            }
        }
        return 0;
    }

    public int dfs(int[][] grid,int x,int y){
        if(x<0||x>n-1||y<0||y>m-1||grid[x][y]==0){
            return 1;
        }
        if(grid[x][y]==2){
            return 0;
        }
        //对已经扫描的节点做标记，之后递归到后可以特判
        grid[x][y]=2;
        int count=0;
        count+=dfs(grid,x,y+1);
        count+=dfs(grid,x,y-1);
        count+=dfs(grid,x+1,y);
        count+=dfs(grid,x-1,y);
        return count;
    }


    /**
     *  bfs做法
     */
    public int islandPerimeter1(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        //方向数组
        int [][]d=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int []> queue=new LinkedList<>();
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    //先扫描到一个节点
                    queue.add(new int[]{i,j});
                    grid[i][j]=2;
                    break;
                }
            }
        }
        while(!queue.isEmpty()){
            int []top=queue.poll();
            int i=top[0],j=top[1];
            for(int k=0;k<4;k++){
                int x=i+d[k][0];
                int y=j+d[k][1];
                if(x<0||x>n-1||y<0||y>m-1||grid[x][y]==0){
                    count++;
                    continue;
                }
                if(grid[x][y]==2) continue;
                queue.add(new int[]{x,y});
                grid[x][y]=2;
            }
        }
        return count;
    }


    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    /**
     * 使用迭代，遍历整个二维数组
     */
    public int islandPerimeter3(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }
}
