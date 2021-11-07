package algorithm.Greedy;

/**
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 * 给定数字的范围是 [0, 108]
 */
public class MaximumSwap670 {


    /**
     * 这题直觉会与31. 下一个排列相似，实际上做法不太一致
     * 首先要利用库函数对数字进行转换--String、Integer相关api
     */
    public int maximumSwap(int num) {
        if(num == 0)
            return 0;
        char[] chars = String.valueOf(num).toCharArray();
        //记录从右到左过程中右边出现最大数的索引
        int[] maxIndex = new int[chars.length];
        int max = chars.length - 1;
        //倒过来寻找，当前位置往右，最大的数的下标
        for(int j = chars.length - 1;j>=0;j--){
            if(chars[j] - '0' > chars[max] - '0'){
                max = j;
            }
            maxIndex[j] = max;
        }
        //正序遍历，找到第一个不是最大的数，将该位置和右边最大数换位置
        for(int i = 0;i<chars.length;i++){
            int iValue = chars[i] - '0';
            int maxValue = chars[maxIndex[i]] - '0';
            if(maxValue != iValue){
                chars[i] = (char) (maxValue + '0');
                chars[maxIndex[i]] = (char) (iValue + '0');
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }
}
