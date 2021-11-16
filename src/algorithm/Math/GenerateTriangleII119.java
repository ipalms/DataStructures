package algorithm.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 * 提示:
 * 0 <= rowIndex <= 33
 * 进阶：
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 */
public class GenerateTriangleII119 {

    /**
     * 要求O(rowIndex) 空间，则需要利用动态规划中类似的滚动数组的思想
     */
    public List<Integer> getRow(int rowIndex) {
        Integer []res=new Integer[rowIndex+1];
        int pre;
        for(int i=1;i<=rowIndex+1;++i){
            pre=1;
            for(int j=0;j<i;++j){
                if(j==0||j==i-1){
                    res[j]=1;
                }else{
                    int tmp=res[j];
                    res[j]+=pre;
                    pre=tmp;
                }
            }
        }
        return Arrays.asList(res);
    }

    /**
     * 第二轮循环逆向遍历----背包问题中0-1背包的空间优化思路
     */
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            //每一轮开始都要先加一个数
            row.add(0);
            //逆向遍历
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

}
