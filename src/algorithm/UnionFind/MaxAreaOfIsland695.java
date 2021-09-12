package algorithm.UnionFind;

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
    int max=0;
    class UnionFind{
        int[] parent;
        //依据题意这里的并查集做法就要维护一个size数组
        int[] size;

        public UnionFind(int n){
            parent=new int[n];
            size=new int[n];
            for (int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }
        
        public void union(int a,int b){
            int rootA=find(a);
            int rootB=find(b);
            if(rootA==rootB) return;
            //维护max
            if(size[rootA]>size[rootB]){
                parent[rootB]=rootA;
                size[rootA]+=size[rootB];
                max=Math.max(max,size[rootA]); //统计最大值
            }else{
                parent[rootA]=rootB;
                size[rootB]+=size[rootA];
                max=Math.max(max,size[rootB]);
            }
        }

        public int find(int p){
            if(parent[p]==p) return p;
            parent[p]=find(parent[p]); //路径压缩（函数递归形式）
            return parent[p];
        }
    }
   

    public int maxAreaOfIsland(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        UnionFind uf=new UnionFind(n*m);
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(grid[i][j]==1){
                    //特判一下
                    max=Math.max(max,1);
                    //和前面,上面的合并 因为这里不修改原二维数组，可以只向左边上面合并
                    if(i>0 && grid[i-1][j]==1) uf.union(i*n+j,(i-1)*n+j);
                    if(j>0 && grid[i][j-1]==1) uf.union(i*n+j,i*n+j-1);
                }
            }
        }
        return max;
    }
}
