package algorithm.TopKOrHeap;

import org.junit.Test;

import java.util.*;

/**
 * 692. 前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * 示例 1：
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 * 示例 2：
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 * 注意：
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * 扩展练习：
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 */
public class TopKFrequentWord692 {
    /**
     * 这题掌握优先队列写法就可以了
     */

    @Test
    public void test(){
        String []words={"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent3(words,1));
    }

    /**
     * 对于比较规则较为复杂的情况（在if语句中比较难写下，应该维护k+1个长度的优先队列让优先队列自己来比较）
     */
    public List<String> topKFrequent1(String[] words, int k) {
        Map<String,Integer>map=new HashMap<>();
        for(String s:words){
            map.put(s,map.getOrDefault(s,0)+1);
        }
        PriorityQueue<String> queue=new PriorityQueue<>(k+1, (a,b)->{
            if(map.get(a)==map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a)>map.get(b)?1:-1;
        });
        PriorityQueue<Integer> queue2 = new PriorityQueue<>((a, b) -> b-a);
        PriorityQueue<Integer> queue1=new PriorityQueue<>( (a,b) -> a-b);
        for(String s:map.keySet()){
            queue.offer(s);
            if(queue.size()>k){
                queue.poll();
            }
        }
        List<String> res=new LinkedList<>();
        while(!queue.isEmpty()){
            res.add(0,queue.poll());
        }
        return res;
    }

    /**
     * 对于比较规则较为复杂的情况（在if语句中比较难写下，应该维护k+1个长度的优先队列让优先队列自己来比较）
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map=new HashMap<>();
        List<String> res=new LinkedList<>();
        for(String word:words){
            map.put(word,map.getOrDefault(word,0)+1);
        }
        PriorityQueue<String> queue=new PriorityQueue(k+1, (Comparator<String>) (o1, o2) -> {
            int n1=map.get(o1);
            int n2=map.get(o2);
            if(n1!=n2){
                return n1-n2;
            }
            //比较两个字符串的字典序（可以使用String类自带的 o2.compareTo(o1)）完成
            int min=Math.min(o1.length(),o2.length());
            for(int i=0;i<min;i++){
                int differ=o2.charAt(i)-o1.charAt(i);
                if(differ==0) continue;
                return differ;
            }
            return o2.length()-o1.length();
        });
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            queue.offer(entry.getKey());
            if(queue.size()>k){
                queue.poll();
            }
        }
        while(!queue.isEmpty()){
            res.add(0,queue.poll());
        }
        return res;
    }

    /**
     * 堆2 --官方题解
     */
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        //以entry作为存储对象
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getValue() == entry2.getValue() ? entry2.getKey().compareTo(entry1.getKey()) : entry1.getValue() - entry2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> ret = new ArrayList<String>();
        while (!pq.isEmpty()) {
            ret.add(pq.poll().getKey());
        }
        Collections.reverse(ret);
        return ret;
    }

    /**
     * 类似桶排做法
     * 与357题异同：
     * 由于题目给出了相同情况取舍条件，所以不能不按顺序将值放入桶内
     * 也不能直接将桶内的值全部加入到res输出集合中
     */
    public List<String> topKFrequent3(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //使用LinkedList方便后续元素的插入移动
        LinkedList<String>[] list=new LinkedList[words.length+1];
        for(Map.Entry<String, Integer> entry:map.entrySet()){
            if(list[entry.getValue()]==null){
                //桶中无元素可直接添加
                list[entry.getValue()]=new LinkedList<String>();
                list[entry.getValue()].add(entry.getKey());
            }else{
                LinkedList list1 = list[entry.getValue()];
                int index=0;
                //使字母顺序排序小的放在重合链表前端
                for(String o:list[entry.getValue()]){
                    if(entry.getKey().compareTo(o)>0){
                        index++;
                    }else {
                        break;
                    }
                }
                list1.add(index,entry.getKey());
            }
        }
        List<String> res=new ArrayList<>(k);
        for(int i=words.length;res.size()<k;i--){
            if(list[i]==null) continue;
            if(res.size()+list[i].size()<=k){
                res.addAll(list[i]);
            }else{
                while (res.size()<k){
                    res.add(list[i].poll());
                }
            }
        }
        return res;
    }

    /**
     * 哈希表加排序
     */
    public List<String> topKFrequent4(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        List<String> rec = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            rec.add(entry.getKey());
        }
        //排序
        Collections.sort(rec, new Comparator<String>() {
            public int compare(String word1, String word2) {
                return cnt.get(word1) == cnt.get(word2) ? word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1);
            }
        });
        return rec.subList(0, k);
    }

}
