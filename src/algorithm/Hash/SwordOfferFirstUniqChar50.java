package algorithm.Hash;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SwordOfferFirstUniqChar50 {

    // 哈希表，存储索引加记录.需要两边遍历
    public char firstUniqChar(String s) {
        char []chs=s.toCharArray();
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0;i<chs.length;i++){
            if (map.containsKey(chs[i])){
                map.put(chs[i],-1);
            }else{
                map.put(chs[i],i);
            }
        }
        int min=chs.length;
        //这一步可以继续遍历哈希表也可以遍历HashMap,依据测试用例不同所耗时间不同
        for(char c:chs){
            if(map.get(c)!=-1){
                return c;
            }
        }
        return ' ';
    }

    //使用队列加Hash表实现，一次遍历就可以了
    //用了惰性删除的技巧，和239. 滑动窗口最大值 一样的技巧
    public char firstUniqChar2(String s) {
        Map<Character,Integer> map=new HashMap<>();
        Deque<Character> queue=new LinkedList<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,i);
                queue.addLast(c);
            }else{
                map.put(c,-1);
                while (!queue.isEmpty()&&map.get(queue.peek())==-1){
                    queue.pollFirst();
                }
            }
        }
        return queue.isEmpty()?' ':queue.peek();
    }
}
