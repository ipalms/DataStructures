package algorithm.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 * 提示:
 * 1 <= numRows <= 30
 */
public class GenerateTriangle118 {

    /**
     * 简单模拟
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> one=new ArrayList<>(){{add(1);}};
        res.add(one);
        if(numRows==1){
            return res;
        }
        List<Integer> two=new ArrayList<>(){{add(1);add(1);}};
        res.add(two);
        for(int i=2;i<numRows;++i){
            List<Integer> tmp=new ArrayList<>(){{add(1);}};
            for(int j=1; j<i; ++j){
                tmp.add(res.get(i-1).get(j-1)+res.get(i-1).get(j));
            }
            tmp.add(1);
            res.add(tmp);
        }
        return res;
    }

    /**
     * 更简短代码
     */
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }
}
