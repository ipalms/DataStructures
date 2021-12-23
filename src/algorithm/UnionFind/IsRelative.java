package algorithm.UnionFind;

import java.util.Scanner;

/**
 * 若某个家族人员过于庞大，要判断两个是否是亲戚，确实还很不容易，现在给出某个亲戚关系图，
 * 求任意给出的两个人是否具有亲戚关系。 规定：x和y是亲戚，y和z是亲戚，那么x和z也是亲戚。
 * 如果x,y是亲戚，那么x的亲戚都是y的亲戚，y的亲戚也都是x的亲戚。
 *
 * 输入文件
 * 第一行：三个整数n,m,p，（n<=5000,m<=5000,p<=5000），分别表示有n个人，m个亲戚关系询问p对亲戚关系。
 * 以下m行：每行两个数Mi，Mj，1<=Mi，Mj<=N，表示Ai和Bi具有亲戚关系。
 * 接下来p行：每行两个数Pi，Pj，询问Pi和Pj是否具有亲戚关系。
 * 输出格式
 * P行，每行一个’Yes’或’No’。表示第i个询问的答案为“具有”或“不具有”亲戚关系。
 * 样例格式
 * 6 5 3
 * 1 2
 * 1 5
 * 3 4
 * 5 2
 * 1 3
 * 1 4
 * 2 3
 * 5 6
 * 样例输出
 * Yes
 * Yes
 * No
 */
public class IsRelative {

    static int []parent;
    static int []size;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String []first=sc.nextLine().split(" ");
        int all=Integer.parseInt(first[0]);
        int relation=Integer.parseInt(first[1]);
        int query=Integer.parseInt(first[2]);
        parent=new int[all+1];
        size=new int[all+1];
        for(int i=1;i<=all;++i){
            parent[i]=i;
            size[i]=1;
        }
        for(int i=0;i<relation;++i){
            String []tmp=sc.nextLine().split(" ");
            union(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]));
        }
        for(int i=0;i<query;++i){
            String []tmp=sc.nextLine().split(" ");
            System.out.println(isConnected(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]))?"yes":"no");
        }
    }

    private static void union(int a,int b){
        int rootA=find(a),rootB=find(b);
        if(rootA==rootB) return;
        if(size[rootA]<size[rootB]){
            parent[rootA]=rootB;
        }else if(size[rootA]>size[rootB]){
            parent[rootB]=rootA;
        }else{
            parent[rootA]=rootB;
            ++size[rootB];
        }
    }

    private static int find(int num){
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

    private static boolean isConnected(int a,int b){
        return find(a)==find(b);
    }

}
