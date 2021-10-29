package algorithm.BFSOrDFS;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；
 * 否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class WordSearch79 {

    /**
     * 该题需要标记某个点是否访问过，所以可以看作是回溯问题
     * 题目形式与200题等类似，都是对于二维数组的搜索问题
     * 这里采取修改原board二维数组形式
     */
    int len,m,n;
    public boolean exist(char[][] board, String word) {
        len=word.length();
        m=board.length;
        n=board[0].length;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(board[i][j]==word.charAt(0)&&dfs(board,word,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 直接修改board数组--当然可以改成visited数组标记是否访问过
     */
    private boolean dfs(char[][] board, String word,int x,int y,int start){
        if(start==len) return true;
        if(x<0||x>=m||y<0||y>=n||board[x][y]=='0'||board[x][y]!=word.charAt(start)) return false;
        char tmp=board[x][y];
        //原地修改部分
        board[x][y]='0';
        if(dfs(board,word,x,y+1,start+1)) return true;
        if(dfs(board,word,x+1,y,start+1)) return true;
        if(dfs(board,word,x,y-1,start+1)) return true;
        if(dfs(board,word,x-1,y,start+1)) return true;
        //修改回去
        board[x][y]=tmp;
        return false;
    }
}
