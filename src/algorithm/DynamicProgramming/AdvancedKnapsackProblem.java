package algorithm.DynamicProgramming;

public class AdvancedKnapsackProblem {
    public static void main(String[] args) {
   /*     //有限书包的例子
        int[] weight = {1, 4, 3, 2};//物品的重量
        int[] value = {1500, 3000, 2000,1800}; //物品的价值 这里val[i] 就是前面讲的v[i]
        int[] num={3,1,2,2};  //每件商品对应的限购数量
        int V = 10; //背包的容量
        int N = value.length; //物品的价值的数组长度
        System.out.println( manyPack(V,N,weight,value,num));*/

        //完全书包的例子
        int[] weight = {1, 4, 3, 2};//物品的重量
        int[] value = {1500, 6200, 2000,1800}; //物品的价值 这里val[i] 就是前面讲的v[i]
        int V = 10; //背包的容量
        int N = value.length; //物品的价值的数组长度
        System.out.println( completePack(V,N,weight,value));
    }

    /**
     * 0-1背包问题---要么不选要么只能选一件
     * @param V 背包容量
     * @param N 物品种类
     * @param weight 物品重量
     * @param value 物品价值
     */
    public static String ZeroOnePack(int V,int N,int[] weight,int[] value){
        int[][] dp = new int[N+1][V+1];
        //定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){
            for(int j=1;j<V+1;j++){
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if(weight[i-1] > j)
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i-1]]+value[i-1]);
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        //逆推找出装入背包的所有商品的编号
        int j=V;
        String numStr="";
        for(int i=N;i>0;i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if(dp[i][j]>dp[i-1][j]){
                numStr = i+" "+numStr;
                j=j-weight[i-1];
            }
            if(j==0)
                break;
        }
        return numStr;
    }

    /**
     * 0-1背包的优化解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用逆序来实现
     */
    public static int ZeroOnePack2(int V,int N,int[] weight,int[] value){
        //动态规划
        int[] dp = new int[V+1];
        for(int i=0;i<N;i++){
            //逆序实现，逆序遍历是为了保证物品i只被放入一次。
            //但如果一旦正序遍历了，那么物品i就可能会被重复加入多次
            for(int j=V;j>=weight[i];j--){
                dp[j] = Math.max(dp[j-weight[i]]+value[i],dp[j]);
            }
        }
        return dp[V];
    }


    /**
     * 第二类背包：完全背包
     * 思路分析：
     * 01背包问题是在前一个子问题（i-1种物品）的基础上来解决当前问题（i种物品），
     * 向i-1种物品时的背包添加第i种物品；而完全背包问题是在解决当前问题（i种物品）
     * 向i种物品时的背包添加第i种物品。
     * 推公式计算时，f[i][y] = max{f[i-1][y], (f[i][y-weight[i]]+value[i])}，
     * 注意这里当考虑放入一个物品 i 时应当考虑还可能继续放入 i，
     * 因此这里是f[i][y-weight[i]]+value[i], 而不是f[i-1][y-weight[i]]+value[i]。
     */
    public static String completePack(int V,int N,int[] weight,int[] value){
        //初始化动态规划数组
        int[][] dp = new int[N+1][V+1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){
            for(int j=1;j<V+1;j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        int j=V;
        String numStr="";
        for(int i=N;i>0;i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            while(dp[i][j]>dp[i-1][j]){
                numStr = i+" "+numStr;
                j=j-weight[i-1];
            }
            if(j==0)
                break;
        }
        return numStr+ " 最大价值="+maxValue;
    }

    /**
     * 完全背包的第二种解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用顺序来实现
     */
    public static int completePack2(int V,int N,int[] weight,int[] value){
        //动态规划
        int[] dp = new int[V+1];
        for(int i=0;i<N;i++){
            //顺序实现，这里是唯一和01背包不同的地方，顺序实现可以允许添加重复物品
            //注意完全背包的起始是weight[i]和01背包的退出循环条件均为weight[i]
            //(小于weight[i]状态转移方程就无意义了--越界)
            for(int j=weight[i];j<V+1;j++){
                dp[j] = Math.max(dp[j-weight[i]]+value[i],dp[j]);
            }
        }
        return dp[V];
    }

    /**
     * 第三类背包：多重背包
     */
    public static int manyPack(int V,int N,int[] weight,int[] value,int[] num){
        //初始化动态规划数组
        int[][] dp = new int[N+1][V+1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){
            for(int j=1;j<V+1;j++){
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if(weight[i-1] > j)
                    dp[i][j] = dp[i-1][j];
                else{
                    //考虑物品的件数限制
                    int maxV = Math.min(num[i-1],j/weight[i-1]);
                    for(int k=0;k<maxV+1;k++){
                        //不断比较当前dp[i][j] 与加入k件i商品的背包价值大小，并把两者最大的赋值给dp[i][j]
                        dp[i][j]=dp[i][j]>Math.max(dp[i-1][j],dp[i-1][j-k*weight[i-1]]+k*value[i-1])?dp[i][j]:Math.max(dp[i-1][j],dp[i-1][j-k*weight[i-1]]+k*value[i-1]);
                    }
                }
            }
        }
        /*//则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        int j=V;
        String numStr="";
        for(int i=N;i>0;i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            while(dp[i][j]>dp[i-1][j]){
                numStr = i+" "+numStr;
                j=j-weight[i-1];
            }
            if(j==0)
                break;
        }*/
        return dp[N][V];
    }




}
