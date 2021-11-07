package algorithm.Tree;

import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.*;

public class TreeTraversal {
    /**
     * leetcode中的树的普通四种遍历方式（包括层序遍历）
     * 前序、中序、后序遍历的区分就是父结点何时进行遍历的
     * 从另一种角度来看，前序、中序、后序遍历输出数据时遍历到该结点的时机不同
     * 前序是第一次遍历到该节点就输出（第一次遍历到）。
     * 中序是第一次遍历到某节点后，又往其左子树区域遍历，遍历完左子树区域时返回到该节点后输出（第二次遍历到）
     * 后序是第一次遍历到某节点后，又往其左、右子树区域遍历，遍历完左、右子树区域时返回到该节点后输出（第三次遍历到）
     */

    @Test
    public void test(){
        TreeNode a=new TreeNode(15);
        TreeNode b=new TreeNode(7);
        TreeNode c=new TreeNode(20,a,b);
        TreeNode d=new TreeNode(9);
        TreeNode e=new TreeNode(3,d,c);
        new LevelOrder().levelOrderMy(e);
    }



    /**
     * 树的中序遍历--94
     */
    class InOrderTraversal{
        /**
         * 递归形式的中序遍历
         * 时间O（N） 空间O（N）
         */
        List<Integer> res=new ArrayList<>();
        public List<Integer> inorderTraversal(TreeNode root) {
            if(root==null){
                return res;
            }
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
            return res;
        }

        /**
         * 非递归的形式--手动使用栈保存结点
         * 时间O（N） 空间O（N）
         */
        public List<Integer> inorderTraversal2(TreeNode root) {
            List<Integer> res=new ArrayList<>();
            if(root==null){
                return res;
            }
            Deque<TreeNode> stack=new LinkedList<>();
            //注意这个外层的while循环有两个条件
            while(!stack.isEmpty()||root!=null){
                //不断往左子树方向走，每走一次就将当前节点保存到栈中
                //这是模拟递归的调用
                while(root!=null){
                    stack.addLast(root);
                    root=root.left;
                }
                //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
                //然后转向右边节点，继续上面整个过程
                TreeNode tmp=stack.pollLast();
                //中序输出
                res.add(tmp.val);
                root=tmp.right;
            }
            return res;
        }

        /**
         * 莫里斯遍历--修改了原二叉树
         * 莫里斯遍历的优点是没有使用任何辅助空间。
         * 缺点是改变了整个树的结构，强行把一棵二叉树改成一段链表结构
         */
        public List<Integer> inorderTraversal3(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            TreeNode pre = null;
            while(root!=null) {
                //如果左节点不为空，就将当前节点连带右子树全部挂到
                //左节点的最右子树下面
                if(root.left!=null) {
                    //找左子树最右边的节点
                    pre = root.left;
                    while(pre.right!=null) {
                        pre = pre.right;
                    }
                    //将左子树的最右边节点的right置为root节点
                    pre.right = root;
                    //将root指向root的left
                    TreeNode tmp = root;
                    root = root.left;
                    tmp.left = null;
                    //左子树为空，则打印这个节点，并向右边遍历
                } else {
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

    /**
     * 前序遍历 --144
     */
    class PreOrderTraversal{
        //递归形式
        List<Integer> res=new ArrayList<>();
        public List<Integer> preorderTraversal(TreeNode root) {
            if(root==null){
                return res;
            }
            res.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
            return res;
        }


        //非递归形式，与中序遍历的非递归形式也很像
        public List<Integer> preorderTraversal1(TreeNode root) {
            List<Integer> res=new ArrayList<>();
            if(root==null){
                return res;
            }
            Deque<TreeNode> stack=new LinkedList<>();
            while(!stack.isEmpty()||root!=null){
                while(root!=null){
                    res.add(root.val);
                    stack.addLast(root);
                    root=root.left;
                }
                root=stack.pollLast().right;
            }
            return res;
        }
    }

    /**
     * 后序遍历 --145
     * 后序遍历的迭代解法稍微不同于前序|中序的非迭代那么直接
     */
    class PostOrderTraversal{
        //递归形式
        List<Integer> res=new ArrayList<>();
        public List<Integer> postorderTraversal(TreeNode root) {
            if(root==null){
                return res;
            }
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            res.add(root.val);
            return res;
        }


        //后序遍历，使用哈希表记录某个结点是否是已近第三次遇见，如果是就说明该结点的左右子节点均已经遍历过了
        //所以可以将该节点输出，不是的话向该节点的右子树区域遍历
        public List<Integer> postorderTraversal1(TreeNode root) {
            List<Integer> res=new ArrayList<>();
            if(root==null){
                return res;
            }
            Deque<TreeNode> stack=new LinkedList<>();
            Set<TreeNode> set=new HashSet<>();
            while(!stack.isEmpty()||root!=null){
                while(root!=null){
                    stack.addLast(root);
                    root=root.left;
                }
                TreeNode tmp=stack.peekLast();
                if(!set.contains(tmp)){
                    set.add(tmp);
                    root=tmp.right;
                }else{
                    res.add(stack.pollLast().val);
                }
            }
            return res;
        }

        //使用指针prev代替哈希表的作用
        public List<Integer> postorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                //判断右侧节点均已访问过了
                if (root.right == null || root.right == prev) {
                    res.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    //重新加入stack
                    stack.push(root);
                    root = root.right;
                }
            }
            return res;
        }

        //前序遍历相反操作思路，结果链表头插法
        public List<Integer> postorderTraversal3(TreeNode root) {
            LinkedList<Integer> result = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    //头插
                    result.addFirst(root.val);
                    //往右遍历
                    root = root.right;
                } else {
                    root = stack.pop();
                    root = root.left;
                }
            }
            return result;
        }
    }

    /**
     * 层序遍历 102 --广度优先遍历
     * 层序遍历II 107 --相较于102只需要将结果头插入链表就行
     */
    class LevelOrder{

        public List<List<Integer>> levelOrderMy(TreeNode root) {
            List<List<Integer>> res=new ArrayList<>();
            if(root==null){
                return res;
            }
            Queue<TreeNode> queue=new LinkedList<>();
            queue.offer(root);
            List<Integer> tmp=new ArrayList<>();
            int count=1,next=0;
            while(queue.size()>0){
                TreeNode node=queue.poll();
                tmp.add(node.val);
                --count;
                if(node.left!=null){
                    queue.offer(node.left);
                    ++next;
                }
                if(node.right!=null){
                    queue.offer(node.right);
                    ++next;
                }
                if(count==0){
                    res.add(new ArrayList(tmp));
                    tmp.clear();
                    count=next;
                    next=0;
                }
            }
            return res;
        }


        public List<List<Integer>> levelOrder2(TreeNode root) {
            if(root==null) {
                return new ArrayList<List<Integer>>();
            }

            List<List<Integer>> res = new ArrayList<List<Integer>>();
            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
            //将根节点放入队列中，然后不断遍历队列
            queue.add(root);
            while(queue.size()>0) {
                //获取当前队列的长度，这个长度相当于 当前这一层的节点个数
                int size = queue.size();
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                //将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时list中
                //如果节点的左/右子树不为空，也放入队列中
                for(int i=0;i<size;++i) {
                    TreeNode t = queue.remove();
                    tmp.add(t.val);
                    if(t.left!=null) {
                        queue.add(t.left);
                    }
                    if(t.right!=null) {
                        queue.add(t.right);
                    }
                }
                //将临时list加入最终返回结果中
                res.add(tmp);
            }
            return res;
        }

        //dfs版，虽然是深度优先遍历，但是依旧按照先左子节点，后右子节顺序（加之level代表层数）
        public List<List<Integer>> levelOrder3(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            traversal(root, res, 0);
            return res;
        }

        //level对应的就是结果集合的索引
        private void traversal(TreeNode root, List<List<Integer>> res, int level) {
            if (root == null) {
                return;
            }
            if (res.size() == level) {
                res.add(new ArrayList<Integer>());
            }
            res.get(level).add(root.val);
            traversal(root.left, res, level + 1);
            traversal(root.right, res, level + 1);
        }
    }




    static class TreeNode {
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
