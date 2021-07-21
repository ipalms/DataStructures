package Test.Test;

import java.util.HashMap;
import java.util.Hashtable;

//leetcode submit region begin(Prohibit modification and deletion)
public class kkk {
    // 不正确
    public static void main(String[] args) {
/*        String s="ababacb";
        String t = "acb";*/
        String s="ADOBECODEBANC";
        String t="ABC";
        //System.out.println(s.substring(2,4));
        System.out.println(minWindow(s,t));
    }
    public static String minWindow(String s, String t) {
        if(s.length()==0||s.length()<t.length()){
            return "";
        }
        String source ="";
        int length1=0;
        char[] chars = s.toCharArray();
        int left=0;
        int count=0;
        HashMap<Character,Integer> map=new HashMap<>();
        //统计模板字符串各字符的出现次数
        for (int i = 0; i <t.length(); i++) {
            char c=t.charAt(i);
            map.put(c,map.getOrDefault(c,0).intValue()+1);
        }
        for (int i = 0; i <chars.length ; i++) {
            int length=i-left;
            if(!map.containsKey(chars[i])&&length!=0) {
                length++;
                continue;
            }
            if(map.containsKey(chars[i])){
                if(length==0) {
                    left = i;
                    count = 1;
                    length = 1;
                }else if(map.get(chars[i])==0){
                    length=1;
                    left=i;
                    count=1;
                    for (int j = 0; j <t.length(); j++) {
                        char c=t.charAt(j);
                        map.put(c,map.getOrDefault(c,0).intValue()+1);
                    }
                }else {
                    length++;
                    count++;
                }
                map.put(chars[i], map.getOrDefault(chars[i], 0).intValue() - 1);
            }
            if(count==t.length()&&(length1>length||length1==0)){
                source=s.substring(left,left+length);
                length1=length;
                length=0;
                left=i+1;
               /*
                left=i;
                count=1;
                length=1;*/
                for (int j = 0; j <t.length(); j++) {
                    char c=t.charAt(j);
                    map.put(c,map.getOrDefault(c,0).intValue()+1);
                }
            }
        }
        return source;
    }
}
