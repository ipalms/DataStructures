package algorithm.BitOperation;

/**
 * 171. Excel 表列序号
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 * 例如，
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 * 输入: columnTitle = "ZY"
 * 输出: 701
 * 示例 4:
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 * 提示：
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 */
public class TitleToNumber171 {

    /**
     * 26进制转换回10进制
     */
    public int titleToNumber(String columnTitle) {
        int pow=1,sum=0;
        for(int i=columnTitle.length()-1;i>=0;--i){
            int p=columnTitle.charAt(i)-'A'+1;
            sum+=pow*p;
            pow*=26;
        }
        return sum;
    }

    /**
     * 从前往后遍历
     */
    public int titleToNumber1(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }
}
