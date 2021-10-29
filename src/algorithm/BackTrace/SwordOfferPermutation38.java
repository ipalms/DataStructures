package algorithm.BackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 限制：
 * 1 <= s 的长度 <= 8
 */
public class SwordOfferPermutation38 {


    /**
     * 全排列字符串版----47题的改编
     */
    int len;
    List<String> res=new ArrayList<>();
    public String[] permutation(String s) {
        len=s.length();
        char [] c=s.toCharArray();
        //对字符数组进行排序
        Arrays.sort(c);
        boolean []visited=new boolean[len];
        backtrace(c,new StringBuilder(),visited);
        return res.toArray(new String[0]);
    }

    private void backtrace(char[] arr,StringBuilder sb,boolean []visited){
        if(sb.length()==len){
            res.add(sb.toString());
            return;
        }
        for(int i=0;i<len;++i){
            //去重
            if(i>0&&arr[i]==arr[i-1]&&!visited[i-1]) continue;
            if(!visited[i]){
                visited[i]=true;
                sb.append(arr[i]);
                backtrace(arr,sb,visited);
                sb.deleteCharAt(sb.length()-1);
                visited[i]=false;
            }
        }
    }
}
