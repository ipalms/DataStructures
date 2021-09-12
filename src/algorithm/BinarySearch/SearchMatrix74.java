package algorithm.BinarySearch;

import org.junit.Test;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= martix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 */
public class SearchMatrix74 {

    @Test
    public void test(){
        int[][] matrix={{1,2,3,4},{7,8,9,10},{12,13,14,15},{16,17,18,19}};
        System.out.println(searchMatrix2(matrix,11));
    }

    /**
     * 从有序矩阵左下角或右上角出发查找：
     * （相当于从一颗BST树进行查找一样，例如左下角开始，上面的元素小于当前节点，右边的元素大于当前节点）
     * 从矩阵的左上或右下视角来看，矩阵排列的像一个小顶堆|大顶堆
     *  （假设从左上角出发，那么其右边或下面节点会均大于该节点，搜索时不能确定往那边查找）
     *  时间复杂度 O（M+N） M、N为矩阵的长宽
     *  同240做法
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i=matrix.length-1,j=0;i>=0&&j<matrix[0].length;){
            if(matrix[i][j]<target){
                j++;
            }else if(matrix[i][j]>target){
                i--;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 二分做法
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
        //试图找到第一列中第一个大于等于target的数
        //两种特殊情况，一是target小于全部矩阵内的数，二是target大于全部矩阵内的数
        int index=findMaxOne(matrix,m-1,target);
        //特判target小于全部矩阵内的数，此时返回的index为-1
        if(index<0) return false;
        if(matrix[index][0]==target) return true;
        //在指定行内查找该数是否存在
        return findOne(matrix,index,n-1,target);
    }

    /**
     * 二分查找法--沿着第一列搜索第最后一个不大于target的数
     */
    private int findMaxOne(int[][] matrix, int right,int target){
        int left=0;
        while(left<right){
            int mid=left+(right-left+1)/2;
            //mid对应的已经大于了target，其右侧也一定大于所以right=mid-
            if(matrix[mid][0]>target){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return left;
    }

    /**
     * 二分查找法--沿着第一列搜索第1个大于等于target的数的索引
     */
    private int findMaxOne1(int[][] matrix, int right,int target){
        int left=0;
        while(left<right){
            int mid=left+(right-left)/2;
            if(matrix[mid][0]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        //当这个二分查找的数任然小于等于target直接返回查找的坐标right
        //  （对应两种情况：一、矩阵只有一行 二、矩阵中所有的数都小于target）
        //否则返回查找的坐标减一表示目标所在的行
        return matrix[right][0]<=target?right:right-1;
    }

    /**
     * 在指定行查找目标数是否存在
     */
    private boolean findOne(int[][] matrix, int index, int right,int target){
        int left=0;
        // 如果判断条件为left<=right 在mid索引对应值大于target时需要该表右边界的值（right=mid-1）
        // 如果不改变的话会可能会导致while死循环
        while(left<=right){
            int mid=left+(right-left)/2;
            if(matrix[index][mid]<target){
                left=mid+1;
            }else if(matrix[index][mid]>target){
                right=mid-1;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 若将矩阵每一行拼接在上一行的末尾，则会得到一个升序数组，我们可以在该数组上二分找到目标元素。
     * 代码实现时，可以二分升序数组的下标，将其映射到原矩阵的行和列上。
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
