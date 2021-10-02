package algorithm.Math;

import java.util.Random;

/**
 * 470. 用 Rand7() 实现 Rand10()
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数
 * 试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 * 不要使用系统的 Math.random() 方法。
 * 示例 1:
 * 输入: 1
 * 输出: [7]
 * 示例 2:
 * 输入: 2
 * 输出: [8,4]
 * 示例 3:
 * 输入: 3
 * 输出: [8,1,10]
 * 提示:
 * rand7 已定义。
 * 传入参数: n 表示 rand10 的调用次数。
 * 进阶:
 * rand7()调用次数的 期望值 是多少 ?
 * 你能否尽量少调用 rand7() ?
 */
public class Rand10UseRand7 {


    /**
     * 要求等概率的出现1-10就需要采取一些拒绝样本措施--拒绝采样
     */


    /**
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */
    public int rand7(){
        return new Random().nextInt(7);
    }

    /**
     * 思路1：--拒绝采样+古典概型（这种方法实现其他的rand11()等也适用）
     * rand7() 构造 rand10()
     * 构造2次采样，分别有2和5种结果，组合起来便有10种概率相同的结果。
     * 把这10种结果映射到[1,10]即可。
     * 第一步具体要如何构造采样是自由的，比如rand7()拒绝7,然后对[1, 6]采样,把奇数和偶数作为2种结
     * 果，这2种结果的概率均为0.5。rand7() 拒绝6,7,然后对[1,5]采样，有5种结果，每种概率均为0.2。
     *
     * 构造rand()11思路：第一步构造等概率奇偶数字a不变，第二步拒绝样本为7，乘积拒绝样本积为12的即可
     */
    public int rand10() {
        int a=rand7(),b=rand7();
        //a负责生成奇偶个数相等的，拒绝样本为7
        while(a==7){
            a=rand7();
        }
        //b负责生成数小于等于5的--即拒绝样本为6和7的
        while(b>5){
            b=rand7();
        }
        //a&1判断该数是奇数还是偶数
        return (a&1)==1?b:5+b;
    }

    /**
     * 拒绝采样2：乘积构造合适样本区间
     * 一个事实：(randX() - 1)*Y + randY() 可以等概率的生成[1, X * Y]范围的随机数
     *
     * 生成rand12()思路：想用上面事实构造res乘积为【1，49】等概率出现，然后拒绝样本49
     * 随后res%12+1构造1-12的均匀分布
     */
    public int rand10Way2() {
        int res;
        do {
            //构造1~49的均匀分布
            res = (rand7() - 1) * 7 + rand7();
            //剔除大于40的值，1-40等概率出现。---即拒绝41-49的样本
        } while (res > 40);
        //res%10结果是【0，9】 +1 构造1-10的均匀分布
        return res%10+1;
    }

}
