package algorithm.Greedy;

import java.util.Arrays;

/**
 * 135. 分发糖果
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * 示例 1：
 * 输入：[1,0,2]
 * 输出：5
 * 解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 * 输入：[1,2,2]
 * 输出：4
 * 解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class GiveCandy135 {

    /**
     * 核心双向遍历数组+等大小空间存储遍历结果（容易写的版本）
     *
     * 规则定义:设学生A和学生B左右相邻，A在B左边;
     *  左规则:当ratingsB > ratingsa时， B的糖比A的糖数量多。
     *  右规则:当ratingsA > ratingsp时，A的糖比B的糖数量多。
     * 相邻的学生中，评分高的学生必须获得更多的糖果等价于所有学生满足左规则且满足右规则。
     * 算法流程:
     *   1.先从左至右遍历学生成绩ratings ，按照以下规则给糖，并记录在left数组中:
     *        1.先给所有学生1颗糖;
     *        2.若ratings> ratingsi-1,则第i名学生糖比第i- 1名学生多1个。
     *        3.若ratings<= ratingsi-1,则第i名学生糖数量不变。(交由从右向左遍历时处理)
     *   经过此规则分配后，可以保证所有学生糖数量满足左规则。
     *   2.同理，在此规则下从右至左遍历学生成绩并记录在right 中，可以保证所有学生糖数量满足右规则。
     *   3.最终,取以上2轮遍历left 和right 对应学生糖果数的最大值
     *   这样则同时满足左规则和右规则，即得到每个同学的最少糖果数量。
     */
    public int candy(int[] ratings) {
        int []left=new int[ratings.length];
        int []right=new int[ratings.length];
        Arrays.fill(left,1);
        Arrays.fill(right,1);
        for(int i=1;i<ratings.length;++i){
            if(ratings[i]>ratings[i-1]){
                left[i]=left[i-1]+1;
            }
        }
        int count=left[ratings.length-1];
        for(int i=ratings.length-2;i>=0;--i){
            if(ratings[i]>ratings[i+1]){
                right[i]=right[i+1]+1;
            }
            count+=Math.max(left[i],right[i]);
        }
        return count;
    }

    /**
     * 优化---一遍遍历空间O(1)   【代码细节很多，易写错】
     * 我们从左到右枚举每一个同学, 记前一个同学分得的糖果数量为pre:
     * 1.如果当前同学比上一个同学评分高，说明我们就在最近的递增序列中，直接分配给该同学pre+ 1个糖果即可。
     * 2.否则我们就在一个递减序列中，我们直接分配给当前同学一个糖果，并把该同学所在的递减序列中所有的同学都再多分配一个糖果，以保证糖果数量还是满足条件。
     *     我们无需显式地额外分配糖果，只需要记录当前的递减序列长度，即可知道需要额外分配的糖果数量。
     *     同时注意当当前的递减序列长度和上一个递增序列等长时，需要把最近的递增序列的最后一个同学也并进递减序列中。
     * 这样，我们只要记录当前递减序列的长度dec
     * 最近的递增序列的长度inc
     * 前一个同学分得的糖果数量pre。
     *
     * 重点就是不论进入递增或者递减序列中都要重置inc、dec、pre的一些变量值
     */
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        //inc记录递增元素个数  dec记录递减元素个数
        int inc = 1, dec = 0, pre = 1;
        //从第二个元素开始遍历
        for (int i = 1; i < n; i++) {
            //ratings[i]=ratings[i - 1]情况相当于重置一个递增序列
            if (ratings[i] >= ratings[i - 1]) {
                //递增过程重置dec
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                //如果递减元素等于递增，就要将递减元素个数多加一
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                //递减过程置pre=1
                pre = 1;
            }
        }
        return ret;
    }

    /**
     * 仿写方法二
     * 重点就是不论进入递增或者递减序列中都要重置inc、dec、pre的一些变量值
     */
    public int candy3(int[] ratings) {
        int inc=1,dec=0,pre=1,res=1;
        for(int i=1;i<ratings.length;++i){
            if(ratings[i]>=ratings[i-1]){
                pre=ratings[i]==ratings[i-1]?1:pre+1;
                dec=0;
                inc=pre;
                res+=inc;
            }else{
                if(++dec==inc){
                    ++dec;
                }
                pre=1;
                res+=dec;
            }
        }
        return res;
    }
}
