package algorithm.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 * 提示：
 * 0 <= n <= 5 * 106
 */
public class CountPrimes204 {

    /**
     * 暴力一点的 --超时
     * 对于奇数去与质数集合的前一半相与，如果没有余0的奇数就认定为质数
     */
    public int countPrimesMy(int n) {
        if(n<3) return 0;
        List<Integer> list=new ArrayList<>();
        list.add(2);
        for(int i=3;i<n;i+=2){
            if(check(i,list)){
                list.add(i);
            }
        }
        return list.size();
    }

    public boolean check(int i, List<Integer> list){
        for(int j=0;j<list.size()/2+1;j++){
            if(i%list.get(j)==0){
                return false;
            }
        }
        return true;
    }

    /**
     * 纯数学--埃氏筛，筛选质数
     * 如果x是质数，那么大于x的x的倍数2x,3x,… 一定不是质数，因此我们可以从这里入手。
     */
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        //从2开始遍历
        for (int i = 2; i < n; ++i) {
            //遍历到质数
            if (isPrime[i] == 1) {
                ans += 1;
                //如果其平方小于n就将 i*i开始的数，以i为倍数的位置全部置为0
                //因为这些数不可能是质数了
                //为什么从i*i开始？因为当2i,3i,… 这些数一定在i之前就被其他数的倍数标记过了
                //例如2的所有倍数(2*i是2的倍数，会先被标记)，3的所有倍数等
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}
