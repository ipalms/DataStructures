package graph;

public class GraphDemo {
    public static void main(String[] args) {
        int n = 5;  //结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex: Vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A< - >B
		graph.insertEdge(0, 2, 1); // A< - >C
		graph.insertEdge(1, 2, 1); //
		graph.insertEdge(1, 3, 1); //
		graph.insertEdge(1, 4, 1); //

        //显示一把邻结矩阵
        //graph.showGraph();
        System.out.println("深度遍历");

//         graph.dfs();
//         graph.DFS(0);
        System.out.println("广度遍历");
        graph.BFS();
    }
}
