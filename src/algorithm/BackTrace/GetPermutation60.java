package algorithm.BackTrace;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 60. 排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 示例 1：
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 * 输入：n = 3, k = 1
 * 输出："123"
 * 提示：
 * 1 <= n <= 9
 * 1 <= k <= n!
 */
public class GetPermutation60 {

    public static void main(String[] args) {
        System.out.println(getPermutation(4,9));
    }


    static int  count=0;
    static  String word="";
    public static String getPermutation(int n, int k) {
        //List<String> res=new ArrayList<String>();
        boolean[] visited =new boolean[n];
        backtrack(new StringBuilder(),n,k,visited);
        return word;
    }

    /**
     * 回溯但是没有剪枝的逻辑（一个一个统计到第k个排列）
     */
    private static boolean backtrack(StringBuilder tmp, int n, int k, boolean[] visited){
        if(tmp.length()==n){
            count++;
        }
        if(count==k){
            word=tmp.toString();
            return true;
        }
        for(int i=1;i<=n;i++){
            if(visited[i-1]) continue;
            tmp.append(i);
            visited[i-1]=true;
            if(backtrack(tmp,n,k,visited)){
                return true;
            }
            visited[i-1]=false;
            tmp.delete(tmp.length()-1,tmp.length());
        }
        return false;
    }


    /**
     * 进行剪枝的逻辑的版本--这样做法就不是回溯，而是深度优先遍历的
     * 所求排列一定在叶子结点处得到，进入每一个分支，可以根据已经选定的数的个数，进而计算还未选定的数的个数
     * 然后计算阶乘，就知道这一个分支的叶子结点的个数：
     * 如果k大于这一个分支将要产生的叶子结点数，直接跳过这个分支，这个操作叫「剪枝」；
     * 如果k小于等于这一个分支将要产生的叶子结点数
     * 那说明所求的全排列一定在这一个分支将要产生的叶子结点里，需要递归求解。
     */

    /**
     * 记录数字是否使用过
     */
    private boolean[] used;
    /**
     * 阶乘数组
     */
    private int[] factorial;
    private int n;
    private int k;
    public String getPermutation1(int n, int k) {
        this.n = n;
        this.k = k;
        calculateFactorial(n);
        // 查找全排列需要的布尔数组
        used = new boolean[n + 1];
        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }

    /**
     * @param index 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     */
    private void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }
        //计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            //排除不合法的分治
            if (used[i]) {
                continue;
            }
            //剪枝逻辑
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 计算阶乘数组
     */
    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        //0!=1，它表示了没有数可选的时候，即表示到达叶子结点了，排列数只剩下 11 个
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }


    /**
     * 数学+ 缩小问题规模
     */
    public String getPermutation2(int n, int k) {
        // 注意：相当于在 n 个数字的全排列中找到下标为 k - 1 的那个数，因此 k 先减 1
        k --;

        int[] factorial = new int[n];
        factorial[0] = 1;
        // 先算出所有的阶乘值
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // 这里使用数组或者链表都行
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        StringBuilder stringBuilder = new StringBuilder();

        // i 表示剩余的数字个数，初始化为 n - 1
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i] ;
            stringBuilder.append(nums.remove(index));
            k -= index * factorial[i];
        }
        return stringBuilder.toString();
    }
}
