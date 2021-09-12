package algorithm.BFSOrDFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 */
public class MaxAreaOfIsland695 {

    /**
     * 思路 dfs 、 bfs 、 并查集
     */

    int n;
    int m;

    /**
     * dfs 题解
     */
    public int maxAreaOfIsland(int[][] grid) {
        n=grid.length;
        m=grid[0].length;
        int max=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    max=Math.max(max,dfs(grid,i,j));
                }
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int x, int y){
        if(x<0||x>n-1||y<0||y>m-1||grid[x][y]==0){
            return 0;
        }
        grid[x][y]=0;
        int count=1;
        count+=dfs(grid,x,y+1);
        count+=dfs(grid,x,y-1);
        count+=dfs(grid,x+1,y);
        count+=dfs(grid,x-1,y);
        return count;
    }

    /**
     * bfs题解
     */
    public int maxAreaOfIsland1(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int [][]d=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int max=0;
        Queue<int []> queue=new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    queue.add(new int[]{i,j});
                    int count=1;
                    grid[i][j]=0;
                    while(!queue.isEmpty()){
                        int []top=queue.poll();
                        int mx=top[0];
                        int my=top[1];
                        for(int k=0;k<4;k++){
                            int x=mx+d[k][0];
                            int y=my+d[k][1];
                            if(x<0||x>n-1||y<0||y>m-1||grid[x][y]==0){
                                continue;
                            }
                            queue.add(new int[]{x,y});
                            count++;
                            grid[x][y]=0;
                        }
                    }
                    max=Math.max(max,count);
                }
            }
        }
        return max;
    }
}
