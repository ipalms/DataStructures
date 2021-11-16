package algorithm.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 71. 简化路径
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头）
 * 请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；
 * 此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
 * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
 * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 *
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 * 示例 1：
 * 输入：path = "/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * 输入：path = "/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * 示例 3：
 * 输入：path = "/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 * 输入：path = "/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 * 输入：path = "/a../b"
 * 输出："/a../b"
 * 提示：
 * 1 <= path.length <= 3000
 * path 由英文字母，数字，'.'，'/' 或 '_' 组成。
 * path 是一个有效的 Unix 风格绝对路径。
 */
public class SimplifyPath71 {


    /**
     * 这题正确的应该是按照 / 进行拆分，具体有可以使用split（）函数拆分或者手动拆分
     * 这题需要栈，因为遇到  .. 时要弹栈
     */

    /**
     * 不使用split()函数的方法
     */
    public String simplifyPath(String path) {
        Deque<String> stack=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        //这里为了解决遍历完sb中还有元素的情况，将for条件往后移动一位
        for(int i=1;i<=path.length();++i){
            if(i==path.length()||path.charAt(i)=='/'){
                if(sb.length()>0){
                    //按照中间的/  /  中夹杂的内容进行判断
                    String tmp=sb.toString();
                    if(tmp.equals(".")){
                        sb.deleteCharAt(0);
                    }else if(tmp.equals("..")){
                        sb.delete(0,2);
                        stack.pollLast();
                    }else{
                        // ... 也同样视为目录名称
                        stack.addLast(tmp);
                        sb=new StringBuilder();
                    }
                }
            }else{
                sb.append(path.charAt(i));
            }
        }
        //这里就不用担心sb中还有元素的情况
        //开始拼接结果
        StringBuilder res=new StringBuilder("/");
        while(!stack.isEmpty()){
            res.append(stack.pollFirst());
            if(!stack.isEmpty()){
                res.append("/");
            }
        }
        return res.toString();
    }


    /**
     * 使用split（）函数
     */
    public String simplifyPath1(String path) {
        String []str=path.split("/");
        Deque<String> stack=new LinkedList<>();
        //这里为了解决遍历完sb中还有元素的情况，将for条件往后移动一位
        for(String s:str){
            if(s.equals("")||s.equals(".")) continue;
            else if(s.equals("..")) stack.pollLast();
            else stack.addLast(s);
        }
        //开始拼接结果
        StringBuilder res=new StringBuilder("/");
        while(!stack.isEmpty()){
            res.append(stack.pollFirst());
            if(!stack.isEmpty()){
                res.append("/");
            }
        }
        return res.toString();
    }


    /**
     * 重点去判断  含有 . 号的字串了，使得问题（情况）边复杂了
     */
    public String simplifyPathMy(String path) {
        Deque<String> stack=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        int countPoint=0,n=path.length();
        for(int i=1;i<n;++i){
            if(path.charAt(i)=='.'){
                ++countPoint;
                if(path.charAt(i+1)!='.'){
                    if(countPoint>2){
                        sb.append(path.substring(i-countPoint+1,i+1));
                    }
                    if(sb.length()>0){
                        stack.addLast(sb.toString());
                        sb=new StringBuilder();
                    }
                    if(countPoint==2&&!stack.isEmpty()){
                        stack.pollLast();
                    }
                    countPoint=0;
                }
            }else if(path.charAt(i)=='/'){
                if(sb.length()>0){
                    stack.addLast(sb.toString());
                    sb=new StringBuilder();
                }
            }else{
                sb.append(path.charAt(i));
            }
        }
        StringBuilder res=new StringBuilder("/");
        while(!stack.isEmpty()){
            res.append(stack.pollFirst());
            if(!stack.isEmpty()){
                res.append("/");
            }
        }
        return res.toString();
    }
}
