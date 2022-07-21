package algorithm.BinarySearch;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 */
public class SearchMatrixII240 {
    /**
     * 其实这也相当于搜索空间的缩减
     * 从有序矩阵左下角或右上角出发查找：
     * （相当于从一颗BST树进行查找一样，例如左下角开始，上面的元素小于当前节点，右边的元素大于当前节点）
     * 从矩阵的左上或右下视角来看，矩阵排列的像一个小顶堆|大顶堆
     *  （假设从左上角出发，那么其右边或下面节点会均大于该节点，搜索时不能确定往那边查找）
     *  时间复杂度 O（M+N） M、N为矩阵的长宽
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

    // 更直观简单的二分
    public boolean searchMatrix4(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * 时间复杂度：O(min(M,N)*(logM+logN))，M是这个矩阵的行数，N是这个矩阵的列数。min(M,N) 是主对角线上元素的个数。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        // 特判
        int rows = matrix.length;
        int cols = matrix[0].length;
        int minVal = Math.min(rows, cols);
        // 沿着对角线搜索第 1 个大于等于target的数的索引
        int index = diagonalBinarySearch(matrix, minVal - 1, target);
        if (matrix[index][index] == target) {
            return true;
        }
        //沿着对角线朝两边搜索 i<=index相当于做了一次剪枝，即[index,minVal-1]范围不用再搜寻
        //两种情况，一是target小于全部矩阵内数，二是target大于全部矩阵内数。这时index分别为0，minVal-1
        for (int i = 0; i <= index; i++) {
            //行搜索传入列总数 - 1
            boolean rowSearch = rowBinarySearch(matrix, i, cols - 1, target);
            //列搜索传入行总数 - 1
            boolean colSearch = colBinarySearch(matrix, i, rows - 1, target);
            if (rowSearch || colSearch) {
                return true;
            }
        }
        return false;
    }

    /**
     * 二分查找法--沿着对角线搜索第1个大于等于target的数的索引
     */
    private int diagonalBinarySearch(int[][] matrix, int diagonal, int target) {
        int left = 0;
        int right = diagonal;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (matrix[mid][mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 水平方向二分查找
     */
    private boolean rowBinarySearch(int[][] matrix, int begin, int cols, int target) {
        int left = begin;
        int right = cols;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (matrix[begin][mid] == target) {
                return true;
            } else if (matrix[begin][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 垂直方向二分查找
     */
    private boolean colBinarySearch(int[][] matrix, int begin, int rows, int target) {
        // 这里可以 + 1
        int left = begin + 1;
        int right = rows;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (matrix[mid][begin] == target) {
                return true;
            } else if (matrix[mid][begin] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
