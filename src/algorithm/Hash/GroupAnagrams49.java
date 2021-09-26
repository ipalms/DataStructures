package algorithm.Hash;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class GroupAnagrams49 {

    /**
     * 三道与 字母异位词有关的题
     * 本题与242.有效的字母异位词都可以使用哈希+排序解（字符串转字符数组进行排序）
     * 438.找到字符串中所有字母异位词则是使用滑动窗口解题
     */


    /**
     * 哈希表+字符数组排序
     * 时间复杂度：O(n*k*logk)，其中n是strs中的字符串的数量，k是strs中的字符串的的最大长度。
     * 需要遍历n个字符串，对于每个字符串，需要 O(klogk) 的时间进行排序以及O(1)的时间更新哈希表，因此总时间复杂度是O(nklogk)。
     * 空间复杂度：O(nk)。需要用哈希表存储全部字符串。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        //特别的HashMap的key不能是字符数组，因为每次new的数组对象就会是另一个内存，hash值不一样
        //而String作为key在String的字面值一样时hash结果一致
        Map<String,ArrayList<String>> map=new HashMap<>();
        for(String s:strs){
            char []tmp=s.toCharArray();
            Arrays.sort(tmp);
            ArrayList<String> list=map.getOrDefault(new String(tmp),new ArrayList<String>());
            list.add(s);
            map.put(new String(tmp),list);
        }
        return new ArrayList<List<String>>(map.values());
    }


    /**
     * 哈希表计数  采用26长的int数组统计字符情况
     * 时间复杂度：O(n*（k+26）)。26代表统计每个字符串的每个字母出现次数的操作耗用。
     * 空间复杂度：O(n*（k+26）)。26代表申请存储字符串字符出现次数结果的数组所用空间。
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        //特别的HashMap的key不能是字符数组，因为每次new的数组对象就会是另一个内存，hash值不一样
        //而String作为key在String的字面值一样时hash结果一致
        Map<String,ArrayList<String>> map=new HashMap<>();
        for(String s:strs){
            int []count=new int[26];
            for(int i=0;i<s.length();++i) {
                ++count[s.charAt(i) - 'a'];
            }
            StringBuilder sb=new StringBuilder();
            //将每个出现次数大于0的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            for(int i=0;i<26;++i){
                sb.append((char)(count[i]+'a'));
                sb.append(count[i]);
            }
            ArrayList<String> list=map.getOrDefault(sb.toString(),new ArrayList<String>());
            list.add(s);
            map.put(sb.toString(),list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
