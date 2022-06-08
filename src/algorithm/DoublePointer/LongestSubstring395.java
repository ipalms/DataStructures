package algorithm.DoublePointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 395. 至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。
 * 返回这一子串的长度。
 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 10^5
 */
public class LongestSubstring395 {


    /**
     * 双指针加枚举（此题还可以使用分治算法解）
     * 题目说明了只包含小写字母（26 个，为有限数据），我们可以枚举最大长度所包含的字符类型数量，
     * 答案必然是 [1, 26]，即最少包含 1 个字母，最多包含 26 个字母。
     * 当确定了长度所包含的字符种类数量时，区间重新具有了二段性质。
     * 当我们使用双指针的时候：
     * 右端点往右移动必然会导致字符类型数量增加（或不变）
     * 左端点往右移动必然会导致字符类型数量减少（或不变）
     * 当然，我们还需要记录有多少字符符合要求（出现次数不少于 k），当区间内所有字符都符合时更新答案。
     * 时间复杂度：枚举 26 种可能性，每种可能性会扫描一遍数组。复杂度为 O(n)
     */
    public int longestSubstring(String s, int k) {
        int ans = 0;
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for (int p = 1; p <= 26; p++) {
            Arrays.fill(cnt, 0);
            // tot 代表 [j, i] 区间所有的字符种类数量；sum 代表满足「出现次数不少于 k」的字符种类数量
            // 注意，窗口的左移或者右移都要实时更新这两个变量
            for (int i = 0, j = 0, tot = 0, sum = 0; i < n; i++) {
                int u = cs[i] - 'a';
                cnt[u]++;
                // 如果添加到 cnt 之后为 1，说明字符总数 +1
                if (cnt[u] == 1) tot++;
                // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                if (cnt[u] == k) sum++;
                // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 p，那么我们要删除掉一些字母，即「左指针」右移
                while (tot > p) {
                    int t = cs[j++] - 'a';
                    cnt[t]--;
                    // 如果添加到 cnt 之后为 0，说明字符总数-1
                    if (cnt[t] == 0) tot--;
                    // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                    if (cnt[t] == k - 1) sum--;
                }
                // 当所有字符都符合要求，更新答案
                if (tot == sum) ans = Math.max(ans, i - j + 1);
            }
        }
        return ans;
    }


    //分治代码  没看
    public int longestSubstring2(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    public int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }


    /**
     * 自己写的失败例子
     * 这题不能使用纯滑动窗口
     * 假设我们已经画出来一段长度为 t 的区间满足要求（且此时 k > 1），那么当我们将长度扩成 t + 1 的时候
     * 如果新位置的字符在原有区间出现过，那必然还是满足出现次数大于 k，这时候 t + 1 的长度满足要求
     * 如果新位置的字符在原有区间没出现过，那新字符的出现次数只有一次，这时候 t + 1 的长度不满足要求
     * 同样的窗口缩小也会出现问题
     *
     * 题目说明了只包含小写字母（26 个，为有限数据），我们可以枚举最大长度所包含的字符类型数量
     * 答案必然是 [1, 26]，即最少包含 1 个字母，最多包含 26 个字母。
     * 你会发现，当确定了长度所包含的字符种类数量时，区间重新具有了二段性质。
     */
    public int longestSubstring1(String s, int k) {
        int n = s.length();
        if (n < k) return 0;
        int[] have = new int[26];
        int[] pass = new int[26];
        int[] win = new int[26];
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < n; i++) {
            have[s.charAt(i) - 'a']++;
        }
        int left = 0, right = 0, max = 0, count = 0, size = 0;
        while (right < n) {
            char r = s.charAt(right);
            if (have[r - 'a'] - pass[r - 'a'] < k) {
                right++;
                while (left < right) {
                    pass[s.charAt(left) - 'a']++;
                    win[s.charAt(left) - 'a']--;
                    if (win[s.charAt(left) - 'a'] == 0) {
                        count--;
                    }
                    left++;
                    if (count == size && (right - left - 1) >= size * k) {
                        max = Math.max(max, right - left - 1);
                    }
                }
                size = 0;
                count = 0;
                continue;
            }
            win[r - 'a']++;
            if (win[r - 'a'] == 1)
                count++;
            if (win[r - 'a'] == k) {
                size++;
            }
            if (count == size) {
                max = Math.max(max, right - left + 1);
            }
            right++;
        }
        return max;
    }
}
