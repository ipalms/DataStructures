package algorithm.String;

import java.util.Arrays;

/**
 * 179. 最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 示例 3：
 * 输入：nums = [1]
 * 输出："1"
 * 示例 4：
 * 输入：nums = [10]
 * 输出："10"
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */
public class LargestNumber179 {

    /**
     * 经验：看到要求两个整数 x,y 如何拼接得到结果更大时，可以思考先转字符串，然后比较x+y和y+x。
     * 两个不等长的字符串比较其字典序是不能达到题目所要求的
     * 但是将这两个字符串拼接在一起等长就可以判断x还是y放在前面对结果更具有正向性了
     * 即由结果来判断x和y的排序关系
     */
    public String largestNumber(int[] nums) {
        int n=nums.length;
        String []tmp=new String[nums.length];
        for(int i=0;i<n;++i){
            tmp[i]=""+nums[i];
        }
        //compareTo()方法比较的时候是按照ASCII码逐位比较的--排序结果是按字符串字典序降序排序
        //通过比较(a+b)和(b+a)的大小，就可以判断出a,b两个字符串谁应该在前面
        //所以[3,30,34]排序后变为[34,3,30]
        Arrays.sort(tmp,(a, b)->(b+a).compareTo(a+b));
        //如果排序后的第一个元素是0，那后面的元素肯定小于或等于0，则可直接返回0
        if(tmp[0].equals("0")) return "0";
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;++i){
            sb.append(tmp[i]);
        }
        return sb.toString();
    }


    /**
     * 同理可以实现最小数--剑指 Offer 45. 把数组排成最小的数
     * 不要求去掉前导0
     */
    public String minNumber(int[] nums) {
        int n=nums.length;
        String []tmp=new String[nums.length];
        for(int i=0;i<n;++i){
            tmp[i]=""+nums[i];
        }
        //唯一有变于最大数的就是字符串数组按照升序排序
        Arrays.sort(tmp,(a,b)->(a+b).compareTo(b+a));
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;++i){
            sb.append(tmp[i]);
        }
        return sb.toString();
    }
}
