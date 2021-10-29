package algorithm.PrefixTree;

/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 * 提示：
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
public class TrieTree208 {

    //设计节点
    class Node{
        Node []child;
        boolean isEnd;
        public Node(){
            child=new Node[26];
            isEnd=false;
        }
    }

    /**
     * 时间复杂度:初始化为0(1),其余操作为O(|S|)，其中|S|是每次插入或查询的字符串的长度。
     * 空间复杂度: O(|T|*E), 其中|T|为所有插入字符串的长度之和，E为字符集的大小，本题E= 26。
     */
    Node root;
    public TrieTree208() {
        root=new Node();
    }

    /**
     * 插入字符串
     * 我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：
     * 子节点存在。沿着指针移动到子节点，继续处理下一个字符。
     * 子节点不存在。创建一个新的子节点，记录在children数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
     */
    public void insert(String word) {
        Node curr=root;
        for(int i=0;i<word.length();++i){
            if(curr.child[word.charAt(i)-'a']==null){
                curr.child[word.charAt(i)-'a']=new Node();
            }
            curr=curr.child[word.charAt(i)-'a'];
        }
        curr.isEnd=true;
    }

    public boolean search(String word) {
        Node res=findWord(word);
        return res!=null&&res.isEnd;
    }

    public boolean startsWith(String prefix) {
        return findWord(prefix)!=null;
    }

    /**
     * 查找前缀
     * 我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
     * 子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
     * 子节点不存在。说明字典树中不包含该前缀，返回空指针。
     */
    private Node findWord(String word){
        Node curr=root;
        for(int i=0;i<word.length();++i){
            if(curr.child[word.charAt(i)-'a']==null){
                return null;
            }
            curr=curr.child[word.charAt(i)-'a'];
        }
        return curr;
    }
}
