package algorithm.BackTrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 * 提示：
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class NQueens51 {

    /**
     * 我自己版本的N皇后题解---思路是仿照算法笔记中的N皇后的做法
     */
    List<List<String>> res=new ArrayList<>();
    char[] tmp;
    public List<List<String>> solveNQueens(int n) {
        //一维数组，存放这N个皇后分别放置在什么位置（下标为皇后所在行，值为皇后所在列）
        int []dir=new int[n];
        //初始化临时数组用于输出解
        tmp=new char[n];
        for(int i=0;i<n;++i){
            tmp[i]='.';
        }
        backtrace(0,n,dir,new ArrayList<String>());
        return res;
    }

    public void backtrace(int start, int n, int []dir, ArrayList<String> path){
        if(start==n){
            //输出结果
            res.add(new ArrayList(path));
            return;
        }
        for(int i=0;i<n;++i){
            //判断start行的皇后插入第i列是否合法（是否与已经插入的皇后碰撞）
            if(judgeLegality(start,i,dir)){
                tmp[i]='Q';
                //加入path路径
                path.add(new String(tmp));
                tmp[i]='.';
                //深层递归
                backtrace(start+1,n,dir,path);
                //回溯状态
                path.remove(path.size()-1);
                dir[start]=0;
//                if(start==n-1){
//                    break;
//                }
            }
        }
    }

    //判断start行的皇后插入第j列是否合法（是否与已经插入的皇后碰撞）
    public boolean judgeLegality(int start, int j, int []dir){
        for(int i=0;i<=start-1;++i){
            //与前面的皇后同列或者连线斜率为1都是不合法的
            if(dir[i]==j||Math.abs(dir[i]-j)==Math.abs(i-start)){
                return false;
            }
        }
        dir[start]=j;
        return true;
    }

    @Test
    public void test(){
        System.out.println(new NQueens52().totalNQueens(8));
    }

    //52题求解的是N皇后排列的组合数，其他不变
    class NQueens52{

        int count=0;
        int n;
        public int totalNQueens(int n) {
            this.n=n;
            //一维数组，存放这N个皇后分别放置在什么位置（下标为皇后所在行，值为皇后所在列）
            int []dir=new int[n];
            backtrace(0,dir);
            return count;
        }

        public void backtrace(int start,int []dir){
            if(start==n){
                count++;
                return;
            }
            for(int i=0;i<n;++i){
                //判断start行的皇后插入第i列是否合法（是否与已经插入的皇后碰撞）
                if(judgeLegality(start,i,dir)){
                    //深层递归
                    backtrace(start+1,dir);
                    //回溯状态
                    dir[start]=0;
//                if(start==n-1){
//                    break;
//                }
                }
            }
        }

        //判断start行的皇后插入第j列是否合法（是否与已经插入的皇后碰撞）
        public boolean judgeLegality(int start, int j, int []dir){
            for(int i=0;i<=start-1;++i){
                //与前面的皇后同列或者连线斜率为1都是不合法的
                if(dir[i]==j||Math.abs(dir[i]-j)==Math.abs(i-start)){
                    return false;
                }
            }
            dir[start]=j;
            return true;
        }
    }
}
