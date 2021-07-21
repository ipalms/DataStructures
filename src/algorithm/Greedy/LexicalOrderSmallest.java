package algorithm.Greedy;

/**
 * 字典序最小问题
 * 问题描述：给定长度为N的字符串S，要构造一个长度为N字符串T。T是一个空串，反复执行下列任意操作：
 * ->从S的头部删除一个字符，加到T的尾部；
 * ->从S的尾部删除一个字符，加到T的尾部；
 *
 * 目标是要构造字典序尽可能小的字符串T。
 * PS：字典序是指从前到后比较两个字符串的大小的方法。
 * 首先比较第1个字符，如果不同则第1个字符较小的字符串更小，如果相同则继续比较第2个字符..
 * 反复继续，来比较整个字符串的大小。
 * 例如：
 * 输入   'ACDBCB',
 * 输出   'ABCBCD'
 */
public class LexicalOrderSmallest {
    public static void main(String[] args) {
        /**
         * 思路：
         * 从字符串性质上看，无论T的末尾多大，只要前面部分的较小即可。
         * 把 不断取S得开头和末尾中较小的一个字符放到T的末尾 作为贪心法所遵循的策略。
         * 我们可以有以下步骤：
         * 1.按照字典序比较S和将S反转后的字符串S'。
         * 2.如果S较小，从S的开头取出一个字，追加到T的末尾。
         * 3.如果S'较小，从S的末尾取出一个字，追加到T的末尾。（如果相同，则取谁都可以）
         */
        String word="ACDBCB";
        System.out.println(lexicalOrderSmallest(word));
    }
    public static String lexicalOrderSmallest(String word){
        if(word==null){
            return "";
        }
        int start=0;
        int end=word.length()-1;
        StringBuffer smallWord=new StringBuffer("");
        while(start<=end){
            if(word.charAt(start)<word.charAt(end)){
                smallWord.append(word.charAt(start));
                start++;
            }else {
                smallWord.append(word.charAt(end));
                end--;
            }
        }
        return smallWord.toString();
    }
}
