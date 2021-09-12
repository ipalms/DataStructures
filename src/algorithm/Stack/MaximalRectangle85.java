package algorithm.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * 输入：matrix = [["0","0"]]
 * 输出：0
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalRectangle85 {

    /**
     * 这题就是84的变形
     * 思路：求出每一层的heights[]然后传给84的题解（方法调用）就行了
     * 采用84题的优解，使用首尾哨兵省去处理栈为空或者最后有效元素还停留在栈内的的逻辑
     * 时间复杂度：O（mn） m == matrix.length  n == matrix[0].length
     * 空间复杂度：O（n）
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int []heights=new int[matrix[0].length+2];
        int ans=0;
        for(int i=0;i<matrix.length;++i){
            //heights[0]默认为0，为了压栈形成首哨兵
            for(int j=0;j<matrix[0].length;++j){
                if (matrix[i][j] == '1'){
                    ++heights[j+1];
                }else{
                    //遇到0就重置高度
                    heights[j+1]=0;
                }
            }
            //方法调用
            ans=Math.max(ans,largestRectangleArea(heights));
        }
        return ans;
    }

    /**
     * 与84题题解几乎一致
     */
    public int largestRectangleArea(int[] heights) {
        int res=0;
        Deque<Integer> stack=new LinkedList<>();
        stack.push(0);
        for(int i=1;i<heights.length;++i){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                int height=heights[stack.poll()];
                int width=i-stack.peek()-1;
                res=Math.max(res,height*width);
            }
            stack.push(i);
        }
        return res;
    }
}
