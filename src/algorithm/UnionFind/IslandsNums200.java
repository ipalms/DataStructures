package algorithm.UnionFind;

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

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //方向数组
        int[][] ad = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        UnionFind uf=new UnionFind(n*m);
        //计算0出现的次数
        int count0=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    //防止遍历过的节点重复与其他节点进行union操作
                    //如果不加这一行，每次都将当前节点向左边或上边两个节点union也行
                    grid[i][j] = '0';
                    for(int k=0;k<4;k++){
                        int mx=i+ad[k][0];
                        int my=j+ad[k][1];
                        if(mx<0||mx>n-1||my<0||my>m-1||grid[mx][my]=='0'){
                            continue;
                        }
                        uf.union(i*m+j,mx*m+my);
                    }
                }else{
                    count0++;
                }
            }
        }
        return uf.count()-count0;
    }

    static class UnionFind{
        int []parent;
        //连通分量的个数
        int count;
        int[] rank;

        public UnionFind(int n){
            parent=new int[n];
            rank = new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
                rank[i]=0;
            }
            count=n;
        }

        /**
         * 合并时集合根节点较大的指向集合根节点数字小的
         * （其实只要两个节点能进入union操作就证明这两个节点一定归属同一集合，所以集合的指向可以任意的）
         * 即这一题的元素为1的集合指向可以随意，我们最终只是去获取集合的个数
         * 所以这里也可以采取比较rank数组大小，进行按秩合并操作
         */
        public void union(int p,int q){
            int rootP=find(p);
            int rootQ=find(q);
            //如果两个节点最终指向根节点相同就不需要count--
            if(rootP==rootQ) return;
            if(rootP<rootQ){
                parent[rootQ]=rootP;
            }else{
                parent[rootP]=rootQ;
            }
            count--;
        }

        //按秩合并
        public void union1(int p,int q){
            int rootP=find(p);
            int rootQ=find(q);
            if (rootP != rootQ) {
                if (rank[rootP] > rank[rootQ]) {
                    parent[rootQ] = rootP;
                } else if (rank[rootP] < rank[rootQ]) {
                    parent[rootP] = rootQ;
                } else {
                    parent[rootQ] = rootP;
                    rank[rootP] += 1;
                }
                --count;
            }
        }

        public int find(int p){
            int root=p;
            while(root!=parent[root]){
                root=parent[root];
            }
            int k;
            //路径压缩操作
            while(p!=parent[p]){
                k=parent[p];
                parent[p]=root;
                p=k;
            }
            return root;
        }

        public int count(){
            return this.count;
        }
    }
}
