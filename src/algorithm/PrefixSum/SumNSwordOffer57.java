package algorithm.PrefixSum;

import org.junit.Test;

import java.util.*;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 补充题
 * 问题描述
 * 　　78这个数可以表示为连续正整数的和，1+2+3，18+19+20+21，25+26+27。
 * 　　输入一个正整数 n(<=10000)
 * 　　输出 m 行(n有m种表示法)，每行是两个正整数a，b，表示a+(a+1)+…+b=n。
 * 　　对于多种表示法，a小的方案先输出。
 * 样例输入
 * 78
 * 样例输出
 * 1 12
 * 18 21
 * 25 27
 */
public class SumNSwordOffer57 {

    /**
     * 前缀和+哈希表
     * 这题除了前缀和，还可以使用滑动窗口解
     * 这两种解的输入可以是任意递增正整数序列，前缀和解法不是递增的也行
     */
    @Test
    public void main2() {
//        Scanner in=new Scanner(System.in);
//        int num=in.nextInt();
        int num=78;
        int sum=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,0);
        for(int j=1;j<num;j++) {
            sum+=j;
            if(sum>=num){
                if(map.containsKey(sum-num)){
                    System.out.println(map.get(sum-num)+1+"   "+j);
                }
            }
            map.put(sum,j);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int j = 0;                            // 初始个0 下面要用
        for (int i = 1; i < n / 2 + 1; i++) { // <它的一半+1
            int ans = 0;
            for (j = i; ans < n; j++) {
                ans += j;                     // 连续和的值给ans
            }
            if (ans == n) {
                System.out.println(i + " " + --j); //取首位和末位输出
            }
        }
    }

    public int[][] findContinuousSequence(int target) {
        int i = 1; // 滑动窗口的左边界
        int j = 1; // 滑动窗口的右边界
        int sum = 0; // 滑动窗口中数字的和
        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += j;
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= i;
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j-i];
                for (int k = i; k < j; k++) {
                    arr[k-i] = k;
                }
                res.add(arr);
                // 左边界向右移动
                sum -= i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }



    /**
     * 未改进版前缀和
     * 前缀和求差
     */
    @Test
    public void main1() {
        Scanner in=new Scanner(System.in);
        int num=in.nextInt();
        //求前缀和
        int []ar=new int[num+1];
        for(int i=1;i<=num;i++) {
            ar[i]=ar[i-1]+i;
        }
        for(int j=0;j<num-1;j++) {
            for(int k=j+1;k<=num;k++) {
                if(ar[k]-ar[j]==num) {
                    System.out.println((j+1) + " " + k);
                }else if(ar[k]-ar[j]>num) {
                    break;
                }
            }
        }
    }


}
