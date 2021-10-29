package algorithm.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 498. 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，
 * 对角线遍历如下图所示。
 * 示例:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * 解释:
 * 说明:
 * 给定矩阵中的元素总数不会超过 100000 。
 */
public class FindDiagonalOrder498 {
    @Test
    public void test(){
        int [][]a={{ 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }};
//        int [][]a={{1,2,3}};
        System.out.println(Arrays.toString(findDiagonalOrder(a)));
    }


    /**
     * 更好解法
     *      找规律:
     *      注意左下(+1, -1)和右上移动(-1, +1)不会改变当前(行 + 列)的和, 即判断方式为行 + 列的和
     *      if (行 + 列)是偶数, 就是右上, 是奇数就是左下
     *
     *      左下 = [1, -1]
     *      如果在最后一行 => 向右 [0, 1]  (先判断行, 防止在左下角的时候向下则越界)
     *      如果在第一列 => 向下 [1, 0]
     *
     *      右上 = [-1, 1]
     *      如果在最后一列 => 向下 [1, 0]   (先判断列, 防止在右上角时候向右则越界)
     *      如果在第一行 => 向右 [0, 1]
     */
    public int[] findDiagonalOrder1(int[][] mat) {
        int n=mat.length,m=mat[0].length,num=n*m;
        int []res=new int[num];
        int i=0,j=0,t=0;
        while(t<num){
            res[t++]=mat[i][j];
            //判断奇偶顺序很重要
            if((i+j)%2==0){
                //同时判断边界也要顺序（不然可能会造成索引下标越界）
                if(j==m-1){
                    ++i;
                }else if(i==0){
                    ++j;
                }else{
                    --i;
                    ++j;
                }
            }else{
                if(i==n-1){
                    ++j;
                }else if(j==0){
                    ++i;
                }else{
                    ++i;
                    --j;
                }
            }
        }
        return res;
    }


    /**
     * 模拟路径，在遇到右上或左下的边界时候会掉转遍历方向
     * 类似第6题--Z字形变换也可以直接按照题意模拟路径进行遍历--但还是有更巧妙的规律能描述出路径
     */
    public int[] findDiagonalOrder(int[][] mat) {
        boolean up = true;
        int m = mat.length, n = mat[0].length;
        int size = m * n, count = 0, sum = 0;
        int[] res = new int[size];
        //row 行  col列
        int row = 0, col = 0;
        //一共只会变化m+n-1次遍历方向  写成sum<m+n也没问题
        while(sum < m + n-1){
            //为true时要变化遍历方向，斜向上（遍历原点可看作向上遍历方向的）
            if(up){
                //靠sum重新定位边界坐标
                row = Math.min(m - 1, sum);
                col = sum - row;
                while(row >= 0 && col <= n - 1){
                    res[count++] = mat[row--][col++];
                }
            } else {
                col = Math.min(n - 1, sum);
                row = sum - col;
                while(col >= 0 && row <= m - 1){
                    res[count++] = mat[row++][col--];
                }
            }
            sum++;
            //遍历方向改变
            up = !up;
        }
        return res;
    }

    /**
     * 变形题：斜45度打印，即方向都是从左下往右上，不需要交替方向
     */
    public int[] findDiagonalOrder3(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int size = m * n, count = 0, sum = 0;
        int[] res = new int[size];
        //row 行  col列
        int row = 0, col = 0;
        //一共只会变化m+n-1次遍历方向  写成sum<m+n也没问题
        while(sum < m + n-1){
            //靠sum重新定位边界坐标
            row = Math.min(m - 1, sum);
            col = sum - row;
            while(row >= 0 && col <= n - 1){
                res[count++] = mat[row--][col++];
            }
            sum++;
        }
        return res;
    }

}

