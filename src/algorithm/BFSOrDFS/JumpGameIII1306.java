package algorithm.BFSOrDFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1306. 跳跃游戏 III
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。
 * 当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 * 示例 1：
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 2：
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 3：
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class JumpGameIII1306 {
    /**
     * 直接使用深度优先或广度优先遍历这个数组
     * 前两个跳跃游戏使用的是贪心算法
     */

    /**
     * 深度优先代码
     */
    public boolean canReach(int[] arr, int start) {
        //int count=0;

        //标记数组
        boolean []visited=new boolean[arr.length];
        return travelArr(arr,visited,start);
    }

    public boolean travelArr(int[] arr,boolean []visited, int start){
        if(arr[start]==0) return true;
        if(visited[start]) return false;
        visited[start]=true;
        if(start-arr[start]>=0&&travelArr(arr,visited,start-arr[start])){
            return true;
        }
        if(start+arr[start]<arr.length&&travelArr(arr,visited,start+arr[start])){
            return true;
        }
        return false;
    }

    /**
     * 不使用标记数组的深度优先代码
     */
    public boolean canReach2(int[] arr, int start) {
        if (start >= arr.length || start < 0) return false;
        if (arr[start] < 0) return false;
        if (arr[start] == 0) return true;
        int l = arr[start] + start;
        int r = start - arr[start];
        //对遍历过的数据取反，使得下次遍历到该位置时可判断已遍历
        arr[start] *= -1;
        return canReach(arr, l) || canReach(arr,r);
    }

    /**
     * 广度优先代码
     * 优先遍历完子节点
     */
    public boolean canReach3(int[] arr, int start) {
        int len=arr.length;
        //标记数组
        boolean[] dp=new boolean[len];
        //队列
        Queue<Integer> queue=new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            //弹出一个队列首节点--遍历其左右节点（左右可达索引元素）
            int x=queue.poll();
            if(x+arr[x]<len&&!dp[x+arr[x]]){
                if(arr[x+arr[x]]==0)return true;
                dp[x+arr[x]]=true;
                queue.add(x+arr[x]);
            }
            if(x-arr[x]>=0&&!dp[x-arr[x]]){
                if(arr[x-arr[x]]==0)return true;
                dp[x-arr[x]]=true;
                queue.add(x-arr[x]);
            }
        }
        return false;
    }
}
