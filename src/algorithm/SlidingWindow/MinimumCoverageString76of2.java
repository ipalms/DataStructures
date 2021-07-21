package algorithm.SlidingWindow;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
public class MinimumCoverageString76of2 {
    // 不正确
    public static void main(String[] args) {
/*        String s="ababacb";
        String t = "acb";*/
        String s="ADOBECODEBANC";
        String t="ABC";
        //System.out.println(s.substring(2,4));
        System.out.println(minWindow(s,t));
    }
    //使用HashMap(哈希表)
    public static String minWindow(String s, String t) {
        if (s.length()==0||t.length()>s.length()) return "";
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i = 0; i <t.length(); i++) {
            char c=t.charAt(i);
            map.put(c,map.getOrDefault(c,0).intValue()+1);
        }
        int count=0;
        HashMap<Character,Integer> map1=new HashMap<>();
        int left =0,right=0,start=-1;

        int len=Integer.MAX_VALUE;
        while (right<s.length()){
            char tmp=s.charAt(right);
            right++;
            if (map.containsKey(tmp)){
                map1.put(tmp,map1.getOrDefault(tmp,0)+1);
                if (map.get(tmp).intValue()==map1.get(tmp).intValue()){
                    count++;
                }
            }

            while (count==map.size()){
                if (len>right-left){
                    len=right-left;
                    start=left;
                }
                char tmp1=s.charAt(left);
                if (map.containsKey(tmp1)){
                    if (map.get(tmp1).intValue()==map1.get(tmp1).intValue()){
                        count--;
                    }
                    map1.put(tmp1,map1.get(tmp1).intValue()-1);
                }
                left++;
            }
        }
        return len == Integer.MAX_VALUE ?
                "" : s.substring(start, start+len);
    }

    // 不正确
    public static String minWindow2(String s, String t) {
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
        //getOrDefault方法
        //如果Map集合中有这个key，那么就返回这个key对应的value值；
        //如果Map集合中没有这个key，就返回getOrDefault()方法参数列表中的第二个参数，也就是我们指定的默认返回值。
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
