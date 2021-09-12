package algorithm.BackTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 */
public class LetterCombinations17 {

    /**
     * 叶子节点才获得解集
     */
    public static void main(String[] args) {
        List<String> strings = letterCombinations("23");
        System.out.println(strings);
    }

    //使用String数组进行的映射
    public static List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<String>();
        if(digits.equals("")){
            return res;
        }
        String []map= {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        backTrace(res,new StringBuilder(""),map,digits,0);
        return res;
    }

    private static void backTrace(List<String> res,StringBuilder sb,String []map,String digits,int start){
        if(sb.length()==digits.length()){
            res.add(sb.toString());
            return;
        }
        //String curr=map[Integer.parseInt(digits.substring(start,start+1))-2];
        String curr=map[digits.charAt(start)-'2'];
        int clength=curr.length();
        for(int i=0;i<clength;i++){
            sb.append(curr.charAt(i));
            backTrace(res,sb,map,digits,start+1);
            sb.deleteCharAt(start);
        }
    }

    /**
     * 使用的list（数组）加char数组
     */
    List<String> res=new ArrayList<String>();
    int n;
    public List<String> letterCombinations1(String digits) {
        n=digits.length();
        if(n==0) return res;
        List<char[]> map=new ArrayList<>();
        addElement(map);
        backtrace(digits,map,new StringBuilder(),0);
        return res;
    }

    public void backtrace(String digits,List<char[]> map,StringBuilder sb,int start){
        if(start==n){
            res.add(sb.toString());
            return;
        }
        char []tmp=map.get(digits.charAt(start)-'2');
        for(int i=0;i<tmp.length;++i){
            sb.append(tmp[i]);
            backtrace(digits,map,sb,start+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public void addElement(List<char[]> map){
        map.add(new char[]{'a','b','c'});
        map.add(new char[]{'d','e','f'});
        map.add(new char[]{'g','h','i'});
        map.add(new char[]{'j','k','l'});
        map.add(new char[]{'m','n','o'});
        map.add(new char[]{'p','q','r','s'});
        map.add(new char[]{'t','u','v'});
        map.add(new char[]{'w','x','y','z'});
    }
}
