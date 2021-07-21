package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    //构造器 （并进行初始化操作）
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个邻接结点的下标 w
    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for(int j = 0; j < vertexList.size(); j++) {
            if(edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点（这个邻接结点是被访问过的）的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for(int j = v2 + 1; j < vertexList.size(); j++) {
            if(edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //对dfs 进行一个重载, 遍历我们所有的结点，并进行 dfs
    //(对于连通图，传入一个初始结点就能遍历完，在这里是针对非连通图和单向图遍历的重载操作）
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs[回溯]
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }
    //深度优先遍历算法1
    //i 第一次就是 0
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该结点,输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while(w != -1) {//说明i结点有邻接结点
            if(!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 深度优先遍历（回溯算法）
     * @param  n 每次递归中要进行操作的顶点索引
     */
    public void DFS(int n){
        if (n==getNumOfVertex()){
            return;
        }
        System.out.print(getValueByIndex(n)+"->");
        isVisited[n]=true;
        for (int i=n+1;i<getNumOfVertex();i++){//回溯算法
            if (edges[n][i]==1 && !isVisited[i]){
                DFS(i);
            }
        }
    }
    //深度优先遍历算法2  类似八皇后的回溯算法
    //对dfs 进行一个重载, 遍历我们所有的结点
    //(对于连通图，传入一个初始结点就能遍历完，在这里是针对非连通图和单向图遍历的重载操作）
    public void DFS() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs[回溯]
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                DFS(i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u ; // 表示队列的头结点对应下标
        int w ; // 邻接结点w
        //队列(尾部加入，头部取出)，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while( !queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer)queue.removeFirst();
            //得到第一个邻接结点的下标 w
            w = getFirstNeighbor(u);
            while(w != -1) {//找到
                //是否访问过
                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻结点
                w = getNextNeighbor(u, w); //体现出我们的广度优先
            }
        }
    }
    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
    //对一个结点进行广度优先遍历的方法
    private void BFS(int index) {
        //一开始，给我一个结点,我根据这个结点作为开始结点进行广度优先遍历
        //我先把这个结点放入队列
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(index);
        //然后标记为已访问
        isVisited[index] = true;
        //然后对队列的头节点进行广度优先遍历
        while (queue.size() > 0) {
            //取得队列的头结点
            Integer integer = queue.getFirst();
            for (int i = 0; i < vertexList.size(); i++) {
                if (edges[integer][i] == 1 && !isVisited[i]) {
                    queue.addLast(i);
                    isVisited[i] = true;
                }
            }
            System.out.print(vertexList.get(queue.removeFirst())+"=>");
        }
    }

    //遍历所有的结点，都进行广度优先搜索
    public void BFS() {
        isVisited = new boolean[vertexList.size()];
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                BFS(i);
            }
        }
    }
    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边
    /**
     *
     * @param v1 表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2 第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph() {
        for(int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }
}
