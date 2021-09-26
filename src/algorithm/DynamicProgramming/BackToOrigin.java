package algorithm.DynamicProgramming;

import org.junit.Test;

/**
 * 补充题：
 * 圆环上有10个点[也可以是任意个点]，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
 * 输入: 2
 * 输出: 2
 * 解释：有2种方案。分别是0->1->0和0->9->0
 */
public class BackToOrigin {

    @Test
    public void test(){
        System.out.println(backToOrigin1(10,4));
    }

    /**
     * 这题与70题爬楼梯很相似，都是求路径（到达某一点）的方案数
     * @param size 圆环包含的点数 0...1...size-1（这题可以假定传入的size为10）
     * @param n 可以走的步数
     *
     * dp含义：dp[i][j]为从0点出发走i步到j点的方案数
     * 初始化：dp[0][0]---走0步到原点的方案为1
     * 状态转移方程：dp[i][j]=dp[i-1][(j+size-1)%size]+dp[i-1][(j+1)%size]
     *          公式之所以取余是因为j-1或j+1可能会超过圆环0~9（size）的范围
     * 空间优化：由于状态只dp[i][j]只与第i-1行的两个值有关，所以可以维护两个长度为size的数组
     */
    public int backToOrigin(int size,int n){
        int [][]dp=new int[n+1][size];
        //初始化
        dp[0][0]=1;
        for(int i=1;i<=n;++i){
            for(int j=0;j<size;++j){
                dp[i][j]=dp[i-1][(j+size-1)%size]+dp[i-1][(j+1)%size];
            }
        }
        return dp[n][0];
    }

    /**
     * 空间优化版本
     */
    public int backToOrigin1(int size,int n){
        int []dp=new int[size];
        //存储当前行dp数值变化的辅助数组
        int []tmp=new int[size];
        //初始化
        dp[0]=1;
        for(int i=1;i<=n;++i){
            for(int j=0;j<size;++j){
                tmp[j]=dp[(j+size-1)%size]+dp[(j+1)%size];
            }
            int []t=tmp;
            tmp=dp;
            dp=t;
        }
        return dp[0];
    }
}
