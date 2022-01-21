package algorithm.DoublePointer;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下
 * 可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 10^4
 * 0 <= height[i] <= 10^5
 */
public class CatchRain42 {

    /**
     * 该题要能够想到怎样才是求接雨水最大值的即暴力法如何进行的，其他优化都是优化查询当前节点左右max值的过程
     * 该题最优解法是双指针--其次可以使用单调栈--再然后是动态规划--最后是暴力题解
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
     */


    /**
     * 暴力解法
     * 对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
     * 时间复杂度： O(n^2)
     * 空间复杂度： O(1)
     */
    public int trapViolence(int[] height) {
        int ans = 0;
        int size = height.length;
        //固定某个元素找两边的最大值
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //搜索左边最大的值
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //搜索右边最大的值
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    /**
     * 动态规划解法--可以看作是暴力解法的优化
     * 暴力解法时间复杂度较高是因为需要对每个下标位置都向两边扫描
     * 如果已经知道每个位置两边的最大高度，则可以在O(n)的时间内得到能接的雨水总量。
     * 使用动态规划的方法，可以在O(n)的时间内预处理得到每个位置两边的最大高度。
     * 创建两个长度为n的数组leftMax和rightMax。
     * 对于0<=i<n，leftMax[i]表示下标i及其左边的位置中height的最大高度
     * 对于0<i<=n，rightMax[i]表示下标i及其右边的位置中，height的最大高度。
     * 显然，leftMax[0]=height[0]，rightMax[n−1]=height[n−1]。两个数组的其余元素的计算如下：
     * 当1≤i≤n−1时，leftMax[i]=max(leftMax[i−1],height[i])；
     * 当0≤i≤n−2时，rightMax[i]=max(rightMax[i+1],height[i])。
     * 因此可以正向遍历数组height得到数组leftMax 的每个元素值，反向遍历数组height得到数组rightMax的每个元素值。
     * 时间复杂度：O(n)。计算数组leftMax和rightMax的元素值各需要遍历数组height一次，计算能接的雨水总量还需要遍历一次。
     * 空间复杂度：O(n)。需要创建两个长度为n的数组leftMax和rightMax
     */
    public int trapDynamic(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        //leftMax[i]表示下标i及其左边的位置中height的最大高度
        int[] leftMax = new int[n];
        //对于下标0就取 height[0]
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        //rightMax[i]表示下标i及其右边的位置中，height的最大高度
        int[] rightMax = new int[n];
        //对于下标n-1就取 height[n - 1]
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }


    /**
     * 单调栈题解--维护单调递减栈，栈内存放数组索引（并非数组索引对应值）
     * 时间复杂度：O(n)。从0到n−1 的每个下标最多只会入栈和出栈各一次。
     * 空间复杂度：O(n)。空间复杂度主要取决于栈空间，栈的大小不会超过n。
     * 84题也可以使用单调栈
     */
    public int trapStack(int[] height) {
        int ans = 0;
        //维护递减栈
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            //何时弹栈--栈不为空，并且栈顶元素小于当前遍历到的元素
            //需要使用while循环到这两个条件任何之一为false
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                //弹栈后的栈顶（一定是要先弹栈之后再取栈顶索引）
                int left = stack.peek();
                //计算雨水存储量
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            //入栈
            stack.push(i);
        }
        return ans;
    }

    /**
     * 双指针法 --相当于在动态规划基础上的改进版
     * 核心就是在一遍遍历的过程中用两个变量去维护左右两边的最大值
     * 而动态规划的某个索引左右两边最大值是需要两个数组加上两遍遍历得到的
     * 初始时left=0, right= n-1, leftMax =0, rightMax= 0。
     * 指针left 只会向右移动，指针right只会向左移动，在移动指针的过程中维护两个变量leftMax和rightMax的值。
     * 当两个指针没有相遇时，进行如下操作:
     * 1.使用height[left]和height[right]的值更新leftMax 和rightMax的值;
     * 2.如果height[left]< height[right],则必有leftMax<rightMax
     *   下标left处能接的雨水等于leftMax-height[left]
     *   将下标left处能接的雨水量加到能接的雨水总量,然后将left加1
     * 3.如果height[left]≥height[right]，则必有leftMax≥rightMax，下标right处能接的雨水量等于
     *   rightMax-height[right], 将下标right处能接的雨水加到能接的雨水总量，然后将right减1
     */
    public int trapDoublePointer(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            //因为在任一情况只有一个指针会移动
            //所以在任何时刻，height[left]或height[right]的较大值就是leftMax或rightMax中的较大值
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            //如果height[left]< height[right],则必有leftMax<rightMax
            //为了好理解可以将条件换成leftMax<rightMax
            if(leftMax<rightMax){
                //当前下标可以接雨水量
                ans += leftMax - height[left];
                ++left;
            } else {//如果height[left]≥height[right]，则必有leftMax≥rightMax
                //当前下标可以接雨水量
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    @Test
    public void test(){
        int []height=new int[]{3,2,6,5,4,10,11};
        //int []height=new int[]{12,10,8,6,5,1};
        //int []height=new int[]{1,5,6,8,10,12};
        System.out.println(getWater1(height));
    }

    /**
     * 变形题---柱子变为隔板，隔板可以视为没有体积仅仅能阻隔装水--即隔板没有宽度，只有高度
     * 3，2，6，5，4，10，11
     * 能装水 3*2+3*6+10*1
     * 单调栈解决
     */
    public int getWater(int[] height) {
        int sum=0;
        Deque<Integer> stack=new LinkedList<>();
        for(int i=0;i<height.length;++i){
            while (!stack.isEmpty()&&height[stack.peekLast()]<height[i]){
                int last =stack.pollLast();
//                //比当前遍历到的隔板高度小的都可以略过不影响结果
//                while (!stack.isEmpty()&&height[stack.peekLast()]<height[i]){
//                    last=stack.pollLast();
//                }
                //如果所有栈中元素都小于当前隔板高度，那就要计算栈中前面隔板接水量了
                if(stack.isEmpty()){
                    sum+=(i-last)*height[last];
                }
            }
            stack.addLast(i);
        }
        //单调（递减）栈中还存在元素
        //12,10,8,6,5,1
        if(!stack.isEmpty()){
            int peek=stack.pollLast();
            while(!stack.isEmpty()){
                int pre=stack.pollLast();
                sum+=(peek-pre)*height[peek];
                peek=pre;
            }
        }
        return sum;
    }

    /**
     * 变形题的双指针解法---更简单
     */
    public int getWater1(int[] height) {
        int left=0,right=height.length-1;
        int leftMax=0,rightMax=0;
        int sum=0;
        while(left<right){
            leftMax=Math.max(height[left],leftMax);
            rightMax=Math.max(height[right],rightMax);
            if(leftMax<rightMax){
                sum+=leftMax;
                ++left;
            }else{
                sum+=rightMax;
                --right;
            }
        }
        return  sum;
    }

    /**
     * 双指针2--细节容易漏
     */
    public int trapDoublePointer1(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left <= right) {
            //对于位置left而言，它左边最大值一定是leftMax，右边最大值大于等于rightMax
            //这时候如果leftMax<rightMax成立，那么它就知道自己能存多少水了。
            //无论右边将来会不会出现更大的rightMax，都不影响这个结果
            if (leftMax < rightMax) {
                ans += Math.max(0,leftMax - height[left]);
                //更新左侧较大值
                leftMax = Math.max(leftMax, height[left]);
                ++left;
            } else {
                ans += Math.max(0,rightMax - height[right]);
                //更新右侧较大值
                rightMax = Math.max(rightMax, height[right]);
                --right;
            }
        }
        return ans;
    }
}
