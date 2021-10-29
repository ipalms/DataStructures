package algorithm.Tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 提示：
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
public class Codec297 {

    /**
     * bfs采用层序遍历的方法进行二叉树的序列化与反序列化（细节可以参考JSON字符串的序列化字符串格式）
     * dfs采用先序遍历存储（序列化）并反序列化二叉树
     * 这两种序列化形式都要记录叶子节点后的左右子树为null的情况（不然不能确定唯一二叉树）
     *     1
     *   /    \
     *  2      3
     *   \
     *   4
     * bfs序列化结果  [1,2,3,null,4,null,null,null,null]
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                res.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("[]")) return null;
        //要使用split函数获得树的节点值
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        //反序列化同样需要队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!vals[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if(!vals[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    /**
     * dfs版本 前序遍历
     */
    public String serialize2(TreeNode root) {
        if(root == null){
            return "null";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        Deque<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return dfs(queue);
    }

    private TreeNode dfs(Deque<String> queue) {
        String val = queue.pollFirst();
        if("null".equals(val)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = dfs(queue);
        root.right = dfs(queue);
        return root;
    }


    /**
     * 仿写dfs版本
     */
    public String serialize3(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        encode(root,sb);
        return sb.toString();
    }

    private void encode(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append("N,");
            return;
        }
        sb.append(root.val+",");
        encode(root.left,sb);
        encode(root.right,sb);
    }

    public TreeNode deserialize3(String data) {
        //注意要使用split方法获得解码
        Deque<String> queue=new LinkedList<>(Arrays.asList(data.split(",")));
        return decode(queue);
    }

    private TreeNode decode(Deque<String> queue){
        String curr=queue.pollFirst();
        if("N".equals(curr)){
            return null;
        }
        TreeNode root=new TreeNode(Integer.parseInt(curr));
        root.left=decode(queue);
        root.right=decode(queue);
        return root;
    }

    /**
     * 仿写一遍
     */
    public String serialize1(TreeNode root) {
        if(root==null) return "[]";
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node!=null){
                sb.append(node.val+",");
                queue.offer(node.left);
                queue.offer(node.right);
            }else {
                sb.append("N,");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    public TreeNode deserialize1(String data) {
        if(data.equals("[]")) return null;
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        TreeNode root=new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue=new LinkedList<>();
        int i=1;
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!nodes[i].equals("N")){
                TreeNode left=new TreeNode(Integer.parseInt(nodes[i]));
                node.left=left;
                queue.offer(left);
            }
            ++i;
            if(!nodes[i].equals("N")){
                TreeNode right=new TreeNode(Integer.parseInt(nodes[i]));
                node.right=right;
                queue.offer(right);
            }
            ++i;
        }
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
