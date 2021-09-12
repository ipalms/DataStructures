package algorithm.SlidingWindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目描述
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，
 * 从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 示例：
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 * 提示：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * T中的字符串可以重复
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * 提示：
 * 1 <= s.length, t.length <= 10^5
 * s 和 t 由英文字母组成
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
public class MinimumCoverageString76 {

    @Test
    public void test(){
        String s="ADOBECODEBANC";
        //System.out.println(s.substring(9,3));
        System.out.println(minWindow(s,"ABC"));
    }

    //存储已有的元素使用数组的提交时间会大幅提高（int []have）--最优版本
    public String minWindow(String s, String t) {
        if(t.length()>s.length()) return "";
        int []map=new int[64];
        int []have=new int[64];
        for(int i=0;i<t.length();i++){
            map[t.charAt(i)-'A']++;
        }
        int left=0,right=0,start=0,need=t.length();
        int curr=Integer.MAX_VALUE;
        while(right<s.length()){
            char r=s.charAt(right);
            if(map[r-'A']!=0){
                have[r-'A']++;
                if(have[r-'A']<=map[r-'A']){
                    need--;
                }
            }
            while(need==0){
                char l=s.charAt(left);
                if(right-left+1<curr){
                    curr=right-left+1;
                    start=left;
                }
                if(map[l-'A']!=0){
                    if(have[l-'A']==map[l-'A']){
                        need++;
                    }
                    have[l-'A']--;
                }
                left++;
            }
            right++;
        }
        return curr==Integer.MAX_VALUE?"":s.substring(start,start+curr);
    }

    //多使用了set结构需要的字符--其实使用一个count变量就可以计算需要字符的出现次数
    public String minWindowMy(String s, String t) {
        if(t.length()>s.length()) return "";
        int []map=new int[63];
        Set<Character> set=new HashSet<>();
        for(int i=0;i<t.length();i++){
            map[t.charAt(i)-'A']++;
            set.add(t.charAt(i));
        }
        int left=0,right=0,start=0;
        int curr=Integer.MAX_VALUE;
        Map<Character,Integer> o=new HashMap<>();
        while(right<s.length()){
            if(map[s.charAt(right)-'A']!=0){
                o.put(s.charAt(right),o.getOrDefault(s.charAt(right),0)+1);
                int k=o.get(s.charAt(right));
                if(k==map[s.charAt(right)-'A']){
                    set.remove(s.charAt(right));
                }
            }
            while(set.size()==0){
                if(right-left+1<curr){
                    curr=right-left+1;
                    start=left;
                }
                if(map[s.charAt(left)-'A']!=0){
                    if(o.get(s.charAt(left))==map[s.charAt(left)-'A']){
                        set.add(s.charAt(left));
                    }
                    o.put(s.charAt(left),o.get(s.charAt(left))-1);
                }
                left++;
            }
            right++;
        }
        return curr==Integer.MAX_VALUE?"":s.substring(start,start+curr);
    }

    /**
     * 滑动窗口数组形式
     * 加的思路
     */
    public static String minWindow7(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128  向当于数据字典
        int[] need = new int[64];
        int[] have = new int[64];
        //分别为左指针，右指针，最小长度(//将目标字符串指定字符的出现次数记录  节省内存空间可用64长度 而非128
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i) - 'A']++;
        }
        //已有字符串中目标字符串指定字符的出现总频次以及最小覆盖子串在原字符串中的起始位置
        int left = 0,
                right = 0,
                min = s.length() + 1,   //记录窗口最小长度，最开始的值为窗口不可能达到的值
                count = 0,
                start = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            //说明该字符不被目标字符串需要，此时有两种情况
            // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
            // 2.循环已经开始一段时间，此处又有两种情况
            //  2.1 上一次条件不满足，已有字符串指定字符出现次数不满足目标字符串指定字符出现次数，那么此时
            //      如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
            //  2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
            if (need[r - 'A'] == 0) {
                right++;
                continue;
            }
            //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
            //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (have[r - 'A'] < need[r - 'A']) {
                count++;  //count是决定是否进行左移的关键
            }
            //已有字符串中目标字符出现的次数+1
            have[r - 'A']++;
            //移动右指针
            right++;
            //当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
            while (count == t.length()) {
                //挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                //如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
                if (need[l - 'A'] == 0) {
                    left++;
                    continue;
                }
                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (have[l - 'A'] == need[l - 'A']) {
                    count--;
                }
                //已有字符串中目标字符出现的次数-1
                have[l - 'A']--;
                //移动左指针
                left++;
            }
        }
        //如果最小长度还为初始值，说明没有符合条件的子串
        if (min == s.length() + 1) {
            return "";
        }
        //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + min);
    }

    /**
     * 滑动窗口数组形式
     * 减的思路
     */
    public String minWindow2(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        int[] need = new int[128];
        //记录需要的字符的个数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //l是当前左边界，r是当前右边界，size记录窗口大小，count是需求的字符个数，start是最小覆盖串开始的index
        int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
        //遍历所有字符
        while (r < s.length()) {
            char c = s.charAt(r);
            if (need[c] > 0) {//需要字符c
                count--;
            }
            need[c]--;//把右边的字符加入窗口
            if (count == 0) {//窗口中已经包含所有字符
                while (l < r && need[s.charAt(l)] < 0) {
                    need[s.charAt(l)]++;//释放右边移动出窗口的字符
                    l++;//指针右移
                }
                if (r - l + 1 < size) {//不能右移时候挑战最小窗口大小，更新最小窗口开始的start
                    size = r - l + 1;
                    start = l;//记录下最小值时候的开始位置，最后返回覆盖串时候会用到
                }
                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

    /**
     * 使用哈希表做法
     */
    public static String minWindow3(String s, String t) {
        if (s.length()==0||t.length()>s.length()) return "";
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i = 0; i <t.length(); i++) {
            char c=t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) +1);
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
}
