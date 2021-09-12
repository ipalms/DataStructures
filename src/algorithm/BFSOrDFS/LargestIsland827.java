package algorithm.BFSOrDFS;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 827. 最大人工岛
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 * 示例 1:
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 示例 2:
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 */
public class LargestIsland827 {

    int n;
    int m;

    /**
     * 思路1：
     * 对于每个 0，我们将它暂时变成 1，然后统计这个连通块的大小。
     * 对每个0使用两次dfs算法
     * 这样时间复杂度将高达O（N^4）
     * 空间复杂度 O（N^2）
     */
    public int largestIsland(int[][] grid) {
        n=grid.length;
        m=grid[0].length;
        int max=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==0){
                    //将节点暂时视为1
                    grid[i][j]=1;
                    //深度遍历可串连的节点数
                    max=Math.max(max,dfs(grid,i,j));
                    //深度遍历清除标记
                    dfs1(grid,i,j);
                    //回复最初节点
                    grid[i][j]=0;
                }
            }
        }
        return max==0?n*m:max;
    }

    public int dfs(int[][] grid, int x ,int y){
        if(x<0||x>n-1||y<0||y>m-1||grid[x][y]!=1){
            return 0;
        }
        //做标记，防止重复访问
        grid[x][y]=2;
        int res=1;
        res+=dfs(grid,x,y+1);
        res+=dfs(grid,x,y-1);
        res+=dfs(grid,x+1,y);
        res+=dfs(grid,x-1,y);
        return res;
    }

    public void dfs1(int[][] grid, int x ,int y){
        if(x<0||x>n-1||y<0||y>m-1||grid[x][y]!=2){
            return;
        }
        //清除标记
        grid[x][y]=1;
        dfs1(grid,x,y+1);
        dfs1(grid,x,y-1);
        dfs1(grid,x+1,y);
        dfs1(grid,x-1,y);
    }


    /**
     * 思路2：
     * 第一遍 DFS 遍历陆地格子，计算每个岛屿的面积并标记岛屿；
     * 第二遍 DFS 遍历海洋格子，观察每个海洋格子相邻的陆地格子。
     */
    public int largestIsland1(int[][] grid) {
        int res = 0;
        int index = 2;//0是海洋1是陆地，为了避免冲突，从2开始
        HashMap<Integer, Integer> indexAndAreas = new HashMap<>();//岛屿编号->岛屿面积
        //计算每个岛屿的面积，并标记是第几个岛屿
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    //返回每个岛屿的面积
                    int area = area(grid, r, c, index);
                    //存入hash表中
                    indexAndAreas.put(index, area);
                    index++;
                    //记录最大的岛屿面积
                    res = Math.max(res, area);
                }
            }
        }
        if (res == 0) return 1;//如果没有陆地，那么就造1块
        //遍历海洋格子，假设这个格子填充，那么就把上下左右是陆地的格子所在的岛屿连接起来
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {//遍历海洋格子
                    HashSet<Integer> hashSet = findNeighbour(grid, r, c);//把上下左右四邻放入set里，set的目的是去重
                    if (hashSet.size() < 1) continue;//周围没有陆地就不必再继续算了，所以continue
                    int twoIsland = 1;//起始是1，直接把我们造出来的1计算进去
                    for (Integer i : hashSet)
                        twoIsland += indexAndAreas.get(i);//通过序号找到面积
                    //比较得到最大的面积
                    res = Math.max(res, twoIsland);
                }
            }
        }
        return res;
    }

    /**
     * 对于海洋格子，找到上下左右
     * 每个方向，都要确保有效inArea以及是陆地格子，则表示是该海洋格子的陆地邻居
     */
    private HashSet<Integer> findNeighbour(int[][] grid, int r, int c) {
        HashSet<Integer> hashSet = new HashSet<>();
        if (inArea(grid, r - 1, c) && grid[r - 1][c] != 0)
            hashSet.add(grid[r - 1][c]);
        if (inArea(grid, r + 1, c) && grid[r + 1][c] != 0)
            hashSet.add(grid[r + 1][c]);
        if (inArea(grid, r, c - 1) && grid[r][c - 1] != 0)
            hashSet.add(grid[r][c - 1]);
        if (inArea(grid, r, c + 1) && grid[r][c + 1] != 0)
            hashSet.add(grid[r][c + 1]);
        return hashSet;
    }


    private int area(int[][] grid, int r, int c, int index) {
        if (!inArea(grid, r, c)) return 0;
        if (grid[r][c] != 1) return 0;
        grid[r][c] = index; //设置当前格子为某个岛屿编号
        return 1 + area(grid, r - 1, c, index) + area(grid, r + 1, c, index) + area(grid, r, c - 1, index) + area(grid, r, c + 1, index);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
