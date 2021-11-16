package algorithm.TopoSort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；
 * 并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * 提示：
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class CourseSchedule207 {


    /**
     * 衍生题：210. 课程表II
     * 相比210题这题就是判断该有向图释放是否含有环
     * 而210题则是在如果图中没有环则输出一个拓扑排序序列来
     * 循环依赖的检测同理，区别是可能输入的依赖关系不是数字对应的，而是二维字符串数组【String[][]】
     * 所以建立依赖关系要使用Map<String,List<String>>,入度表应该也要采用Map<String,Integer>
     */

    /**
     * 拓扑排序核心定义：顶点u到顶点v的每个有向边u->v，u在排序中都在v之前
     * 具体实现可采用深度优先或者广度优先进行
     *
     * 深度优先搜索是优先递归找到出度为0的点，将结果加入栈当中
     * 从栈顶到栈底的序列是符合拓扑排序定义的
     *
     * 广度优先搜索是优先以入度为0的点向周围扩散的，入度为0的点加入到结果数组当中
     * 广度优先的遍历后的解就是符合拓扑排序定义的
     *
     * dfs和bfs不同：
     * 首先就是思考的入口不同，bfs是找到入度为0的点，往后寻找。dfs是递归深入找出度为0的点放入栈中。
     * 但是这两种都能保证拓扑排序的定义
     * 其次通过以下两中算法可知，dfs在递归的过程中可能会遇到环，所以要定义访问状态数组。
     * 而bfs是通过入度为0的点作为入口（所以要使用每个节点入度数组），由于环的互相指向，这些循环的节点始终不会加入到队列中，
     * 所以队列中一共出现的元素一定小于或者等于课程数目
     */

    @Test
    public void test(){
        int [][]tmp=new int[][]{{1,0},{2,1},{3,4},{4,3}};
        canFinish(5,tmp);
    }

    /**
     * dfs实现---定义访问状态 int[]visited
     * 思路：
     * 假设我们当前搜索到了节点u，如果它的所有相邻节点都已经搜索完成，那么这些节点都已经在栈中了
     * 此时我们就可以把u入栈。可以发现，如果我们从栈顶往栈底的顺序看，由于u处于栈顶的位置，
     * 那么u出现在所有u的相邻节点的前面。因此对于u这个节点而言，它是满足拓扑排序的要求的。
     * 这样以来，我们对图进行一遍深度优先搜索。
     * 当每个节点进行回溯的时候，我们把该节点放入栈中。最终从栈顶到栈底的序列就是一种拓扑排序。
     * 具体实现：
     * 对于图中的任意一个节点，它在搜索的过程中有三种状态，即:
     * ●「未搜索」:我们还没有搜索到这个节点;
     * ●「搜索中」:我们搜索过这个节点，但还没有回溯到该节点，即该节点还没有入栈，还有相邻的节点没有搜索完成) ;
     * ●「已完成」:我们搜索过并且回溯过这个节点，即该节点已经入栈，并且所有该节点的相邻节点都出现在栈的更底部的位置，满足拓扑排序的要求。
     *
     * 通过上述的三种状态，我们就可以给出使用深度优先搜索得到拓扑排序的算法流程,在每一轮的搜索搜索开始时，我们任取一个「未搜索」的节点开始进行深度优先搜索。
     * ●我们将当前搜索的节点u标记为「搜索中」，遍历该节点的每一个相邻节点 v:
     *   如果v为「未搜索」，那么我们开始搜索v,待搜索完成回溯到u;
     *   如果v为「搜索中」，那么我们就找到了图中的一个环，因此是不存在拓扑排序的;
     *   如果v为「已完成」，那么说明v已经在栈中了，而u还不在栈中，因此u无论何时入栈都不会影响到(u, v)之前的拓扑关系，以及不用进行任何操作。
     * ●当u的所有相邻节点都为「已完成」时，我们将u放入栈中，并将其标记为「已完成」。
     * 在整个深度优先搜索的过程结束后，如果我们没有找到图中的环，那么栈中存储这所有的n个节点，从栈顶到栈底的顺序即为一种拓扑排序。
     *
     * 时间复杂度: O(n+m),其中n为课程数，m(边的个数)为先修课程的要求数。这其实就是对图进行深度优先搜，索的时间复杂度。
     * 空间复杂度: O(n + m)。题目中是以列表形式给出的先修课程关系，为了对图进行深度优先搜索,我们
     * 需要存储成邻接表的形式，空间复杂度为O(n + m)。在深度优先搜索的过程中，我们需要最多O(n)的栈空间(递归)进行深度优先搜索,因此总空间复杂度为O(n + m)。
     */

    //记录边----邻接表
    List<List<Integer>> edges;
    //记录每个点的访问状态
    int []visited;
    //记录图中是否存在环
    boolean valid =true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges=new ArrayList<>();
        for(int i=0;i<numCourses;++i){
            edges.add(new ArrayList<>());
        }
        //邻接表生成
        for(int []p:prerequisites){
            edges.get(p[1]).add(p[0]);
        }
        visited=new int[numCourses];
        //for循环条件应该在valid=true的情况进行下去
        for(int i=0;i<numCourses&&valid;++i){
            //没搜索过的才应该进入搜索状态
            if(visited[i]==0) {
                dfs(i);
            }
        }
        return valid;
    }

    private void dfs(int start){
        //先修改访问状态--搜素中
        visited[start]=1;
        for(int num:edges.get(start)){
            //遇到还没进行搜索的点
            if(visited[num]==0){
                dfs(num);
                //遇到了搜索中的点---即遇到可环
            }else if(visited[num]==1){
                valid=false;
                return;
            }
            //退出递归用
            if(!valid) return;
        }
        //修改为已搜索完的状态
        visited[start]=2;
    }


    /**
     * bfs  --借助入度数组  int []inEdges
     * 思路
     * dfs的深度优先搜索是一种「逆向思维」:最先被放入栈中的节点是在拓扑排序中最后面的节点。
     * 我们也可以使用正向思维，顺序地生成拓扑排序,这种方法也更加直观。
     * 我们考虑拓扑排序中最前面的节点，该节点一定不会有任何入边，也就是它没有任何的先修课程要求。
     * 当我们将一个节点加入答案中后，我们就可以移除它的所有出边，代表着它的相邻节点少了一门先修课程的要求。
     * 如果某个相邻节点变成了「没有任何入边的节点」，那么就代表着这门课可以开始学习了。
     * 按照这样的流程，我们不断地将没有入边的节点加入答案，直到答案中包含所有的节点(得到了一种拓扑排序)或者不存在没有入边的节点(图中包含环)。
     * 上面的想法类似于广度优先搜索，因此我们可以将广度优先搜索的流程与拓扑排序的求解联系起来。
     * 具体实现：
     * 我们使用一个队列来进行广度优先搜索。
     * 初始时，所有入度为0的节点都被放入队列中，它们就是可以作为拓扑排序最前面的节点，并且它们之间的相对顺序是无关紧要的。
     * 在广度优先搜索的每一步中， 我们取出队首的节点u:
     * ●我们将u放入答案中;
     * ●我们移除u的所有出边，也就是将u的所有相邻节点的入度减少1。如果某个相邻节点0的入度变为0那么我们就将V放入队列中。
     * 在广度优先搜索的过程结束后。如果答案中包含了这n个节点,那么我们就找到了一种拓扑排序
     * 否则说明图中存在环，也就不存在拓扑排序了。
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges=new ArrayList<>();
        //入度数组
        int []inEdges=new int[numCourses];
        for(int i=0;i<numCourses;++i){
            edges.add(new ArrayList<>());
        }
        //邻接表生成--并计算顶点的入度个数，多少个点指向该点
        for(int []p:prerequisites){
            ++inEdges[p[0]];
            edges.get(p[1]).add(p[0]);
        }
        Queue<Integer> queue=new LinkedList<>();
        //将入度为0的点放入队列中---即这些课程是没有被依赖的
        for(int i=0;i<numCourses;++i){
            if(inEdges[i]==0){
                queue.add(i);
            }
        }
        //计算有多少门课程可以符合拓扑排序的定义
        int count=0;
        while(!queue.isEmpty()){
            ++count;
            int q=queue.poll();
            for(int num:edges.get(q)){
                //如果入度为0说明可以加入队列中
                if(--inEdges[num]==0){
                    queue.add(num);
                }
            }
        }
        //判断是否能得到一个拓扑排序
        return count==numCourses;
    }
}
