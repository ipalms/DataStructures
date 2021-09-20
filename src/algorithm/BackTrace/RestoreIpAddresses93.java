package algorithm.BackTrace;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。
 * 你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），
 * 整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
 * 但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 提示：
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
public class RestoreIpAddresses93 {

    @Test
    public void test(){
        System.out.println(restoreIpAddresses("1111"));
    }

    /**
     * 此题虽然是考察回溯做法，但是实际上很考验对字符串的操作
     * 和对边界的处理
     */
    int len;
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        len = s.length();
        //去除不和逻辑的长度部分
        if (len > 12 || len < 4) {
            return res;
        }
        Deque<String> path = new ArrayDeque<>(4);
        dfs(s, 0, 4, path);
        return res;
    }

    /**
     * @param begin 下一层循环的起点
     * @param residue 记录剩余多少段还没被分割
     * @param path 这里添加的路径是一段ip--即0~255的8字节数字，而不是一个一个数加进去的
     */
    private void dfs(String s, int begin, int residue, Deque<String> path) {
        if (begin == len) {
            //当遍历到最后一个字符且剩余段数为0时，将此时的path添加到结果中
            if (residue == 0) {
                //使用String的API来添加分隔符
                res.add(String.join(".", path));
            }
            return;
        }
        //每段最多只截取3个数
        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) {
                break;
            }
            //字符串剩余长度大于剩余分段所需最大长度或者小于剩余分段的最短长度，需要剪枝
            if (residue * 3 < len - i||residue>len-i) {
                continue;
            }
            //当截取的字符串满足条件
            if (judgeIpSegment(s, begin, i)) {
                String currentIpSegment = s.substring(begin, i + 1);
                path.addLast(currentIpSegment);
                dfs(s, i + 1, residue - 1, path);
                path.removeLast();
            }
        }
    }

    private boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        //当前为0开头的且长度大于1的数字需要剪枝
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }
        int res = 0;
        //将当前截取的字符串转化成数字（当然也可以使用Integer.parseInt()方法）
        //int res = len<=0 ? 0 : Integer.parseInt(s.substring(left, right+1));
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        //判断这段ip是否合法
        return res >= 0 && res <= 255;
    }

    //仿写的版本
    class imitate{
        int len;
        String word;
        List<String> res = new ArrayList<>();
        public List<String> restoreIpAddresses(String s) {
            len=s.length();
            word=s;
            if(len<4||len>12) return res;
            backtrace(0,4,new ArrayList<String>());
            return res;
        }

        public void backtrace(int start, int need, ArrayList<String> path){
            if(start==len){
                if(need==0){
                    res.add(String.join(".",path));
                }
                return;
            }
            for(int i=1;i<=3;++i){
                if(start+i>len) break;
                if(len-start<need||len-start>3*need){
                    continue;
                }
                if(check(start,i)){
                    path.add(word.substring(start,start+i));
                    backtrace(start+i,need-1,path);
                    path.remove(path.size()-1);
                }
            }
        }

        public boolean check(int start,int i){
            if(i!=1&&word.charAt(start)-'0'==0){
                return false;
            }
            int res=Integer.parseInt(word.substring(start,start+i));
            return res>=0&&res<=255;
        }
    }
}
