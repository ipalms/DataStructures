package algorithm.BitOperation;

/**
 * 168. Excel表列名称
 * 给你一个整数 num ，返回它在 Excel 表中相对应的列名称。
 * 例如：
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1：
 * 输入：columnNumber = 1
 * 输出："A"
 * 示例 2：
 * 输入：columnNumber = 28
 * 输出："AB"
 * 示例 3：
 * 输入：columnNumber = 701
 * 输出："ZY"
 * 示例 4：
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 * 提示：
 * 1 <= columnNumber <= 231 - 1
 */
public class ConvertToTitle168 {

    /**
     * 本题实质就是10进制到26进制的转换
     * 但是不同于以往的转换方式的是以往的进制转换（假如转换x进制）都是逢x进1
     *
     * 对于一般性的进制转换题目，只需要不断地对num进行%运算取得最后一位，然后对num进行/运算
     * 将已经取得的位数去掉，直到num为0即可。
     * 一般性的进制转换题目无须进行额外操作，是因为我们是在「每一位数值范围在 [0,x)」的前提下进行「逢x进一」。
     * 但本题需要我们将从1开始，因此在执行「进制转换」操作前，我们需要先对columnNumber执行减一操作,从而实现整体偏移。
     * 另一段解释：
     * 所以a1, a2, a3, ..的取值范围是1-26 (对应A-Z)，而对26取模的范围是0-25。
     * 这就会导致26对26取模为0,并且两边同除26时，不能够消除掉最低项(26/26=1)。
     * 为了解决这个问题，可以在同模和同除操作中，先让n减去1。
     * 这样就可以保证上式不断获取和消去最低项过程的正确性了。
     * https://leetcode-cn.com/problems/excel-sheet-column-title/solution/26jin-zhi-zhuan-huan-zhu-yi-0-by-mfk4438-wdga/
     */
    public String convertToTitle(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            num--;
            sb.append((char)(num % 26 + 'A'));
            num /= 26;
        }
        return sb.reverse().toString();
    }


    public String convertToTitleMy(int columnNumber) {
        StringBuilder sb=new StringBuilder();
        while(columnNumber>0){
            int num=(columnNumber-1)%26;
            sb.append((char)(num+'A'));
            columnNumber=(columnNumber-num)/26;
        }
        return sb.reverse().toString();
    }
}
