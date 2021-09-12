package algorithm.SlidingWindow;

import java.util.Arrays;

/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 * 提示：
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */
public class CheckInclusion567 {

    /**
     * 438的衍生题
     * 滑动窗口+双指针
     */
    public boolean checkInclusion(String s1, String s2) {
        int n1=s1.length(),n2=s2.length();
        if(n2<n1) return false;
        int []need=new int[26];
        int []have=new int[26];
        for(int i=0;i<n1;i++){
            need[s1.charAt(i)-'a']++;
        }
        int left=0,right=0;
        while(right<n2){
            char r=s2.charAt(right);
            have[r-'a']++;
            while(have[r-'a']>need[r-'a']){
                have[s2.charAt(left)-'a']--;
                left++;
            }
            if(right-left+1==n1){
                return true;
            }
            right++;
        }
        return false;
    }

    /**
     * 滑动窗口   固定窗口，一次性比较s1.length个长度的字符，不符合窗口右移
     */
    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        //Arrays.equals(cnt1, cnt2) 调用库函数比较
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            //维护固定窗口长度
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
}
