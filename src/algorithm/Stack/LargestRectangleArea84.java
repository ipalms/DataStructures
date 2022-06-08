package algorithm.Stack;

import java.util.*;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 * 输入： heights = [2,4]
 * 输出： 4
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class LargestRectangleArea84 {

    /**
     *  42也可单调栈解
     *  维护单调递增栈
     *  每当遍历到的一个小于当前栈顶值小的数就说明前面的数有部分值可以算出以这个值为高度的矩阵面积
     *  确定当前柱形对应的宽度的右边界的时候，往回头看的时候，一定要找到第一个严格小于我们要确定的那个柱形的高度的下标。
     *  这个时候中间那些相等的柱形其实就可以当做不存在一样
     *  使用Deque实现作为栈的实现类时，获得栈顶元素值方法是peekLast()而不是peek()【peek获得的是第一个加入栈元素的值】
     *  https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int res = 0;
        //这个测试使用LinkedList的空间复杂度比使用ArrayStack小的多
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < len; i++) {
            // 这个 while 很关键，因为有可能不止一个柱形的最大宽度可以被计算出来
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                //每轮循环都是计算栈顶高度对应的最大矩阵面积，记得要弹出栈顶元素
                int curHeight = heights[stack.pollLast()];
                //对于和栈顶元素高度相同的元素也一律跳过
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }
                //统计矩阵宽度
                int curWidth;
                //栈空说明数组前面的元素都没有比新遍历到的元素还小的
                if (stack.isEmpty()) {
                    curWidth = i;
                } else {
                    //计算宽度的时候，一定是需要先弹栈，然后取的是stack.peekLast()的值
                    //不能直接不弹栈取curWidth = i -Stack.peekLast() 再弹栈
                    //举个例子对数组[2,1,5,6,2,3,1]当计算到倒数第三个数对应最大矩阵时
                    //stack存放的索引情况 [1，4],以上面两种计算方式计算的宽度实际会有所不同
                    curWidth = i - stack.peekLast() - 1;
                }
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        //遍历结束后计算单调栈内这些元素对应的最大矩阵情况，逻辑与上面差不多
        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                stack.pollLast();
            }
            int curWidth;
            //区别是i同一换成len（数组总长）
            //接下来我们就要依次考虑还在栈里的柱形的高度
            //和刚才的方法一样，只不过这个时候右边没有比它高度还小的柱形了
            // 这个时候计算宽度应该假设最右边还有一个下标为 len（这里等于 6）的高度为 0的柱形
            if (stack.isEmpty()) {
                curWidth = len;
            } else {
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res, curHeight * curWidth);
        }
        return res;
    }

    /**
     * 以上代码需要考虑两种特殊的情况：
     * 弹栈的时候，栈为空；
     * 遍历完成以后，栈中还有元素；
     * 为此可以我们可以在输入数组的两端加上两个高度为0（只要比1严格小都行）的柱形，
     * 可以回避上面这两种分类讨论。
     * 这两个站在两边的柱形有一个很形象的名词，叫做哨兵（Sentinel）
     * 有了这两个柱形：
     * 左边的柱形（第 1 个柱形）由于它一定比输入数组里任何一个元素小，它肯定不会出栈，因此栈一定不会为空；
     * 右边的柱形（第 2 个柱形）也正是因为它一定比输入数组里任何一个元素小，它会让所有输入数组里的元素出栈（第 1 个哨兵元素除外）。
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int res = 0;
        //原数组数据拷贝到新数组
        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        //默认为0--只是显示的标识遍历到最后一个元素为0
        newHeights[len + 1] = 0;
        len += 2;
        heights = newHeights;
        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);
        //从新数组下标1开始遍历
        for (int i = 1; i < len; i++) {
            //因为加入了尾哨兵，遍历到最后一个元素后，原数组的每一个元素一定都弹栈了（即计算过了这个元素高度对应矩阵面积）
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                //因为加入了头哨兵所以可以确定弹栈之后栈一定不会空
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }


    /**
     * 官方题解使用了更多了空间
     */
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<Integer>();
        //遍历每个元素对应矩形高度，统计其左右边界能达到的范围
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        int ans = 0;
        //遍历获得最大解
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

}
