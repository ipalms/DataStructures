package algorithm.UnionFind;

/**
 * 547. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，
 * 且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1
 * 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class FindCircleNum547 {

    /**
     * 同时这题也可以使用深度有限广度有限去做，时间复杂度几乎差不多
     * 这题也是朋友圈的个数--属于并查集的入门解法之一
     */
    public int findCircleNum(int[][] isConnected) {
        int n=isConnected.length;
        UnionFind uf=new UnionFind(n);
        for(int i=0;i<n;++i){
            for(int j=i+1;j<n;++j){
                if(isConnected[i][j]==1){
                    uf.union(i,j);
                }
            }
        }
        return uf.size;
    }

    class UnionFind{
        int []parent;
        int []rank;
        int size;
        public UnionFind(int n){
            parent=new int[n];
            rank=new int[n];
            size=n;
            for(int i=1;i<n;++i){
                parent[i]=i;
                rank[i]=1;
            }
        }

        public void union(int a,int b){
            int rootA=find(a),rootB=find(b);
            if(rootA==rootB) return;
            if(rootA>rootB){
                parent[rootB]=rootA;
            }else if(rootA<rootB){
                parent[rootA]=rootB;
            }else{
                parent[rootA]=rootB;
                ++rank[rootB];
            }
            --size;
        }

        public int find(int num){
            int root=num;
            while(parent[root]!=root){
                root=parent[root];
            }
            while(parent[num]!=root){
                int next=parent[num];
                parent[num]=root;
                num=next;
            }
            return root;
        }
    }
}
