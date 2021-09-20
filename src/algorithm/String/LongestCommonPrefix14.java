package algorithm.String;

import java.util.Arrays;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class LongestCommonPrefix14 {

    /**
     * 横向逐个比较字符
     */
    public String longestCommonPrefix(String[] strs) {
        String example=strs[0];
        int n=example.length(),i=0,s=strs.length;
        while(i<n){
            char c=example.charAt(i);
            int j=1;
            while(j<s){
                if(i>=strs[j].length()){
                    return strs[j];
                }
                if(strs[j].charAt(i)!=c){
                    return example.substring(0,i);
                }
                ++j;
            }
            ++i;
        }
        return example;
    }

    /**
     * 按字典序进行排序，比较排序后第一个和最后一个单词，有多少前缀相同
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder res = new StringBuilder();
        Arrays.sort(strs);
        // 字符串转数组
        char[] a = strs[0].toCharArray();
        char[] b = strs[strs.length - 1].toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (i < b.length && a[i] == b[i]) {
                res.append(a[i]);
            }
            else{
                break;
            }
        }
        return res.toString();
    }

    /**
     * 还可以二分做但是时间复杂度更高一点
     */
}
