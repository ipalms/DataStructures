package algorithm.Tree;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 * （一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class LowestCommonAncestor235 {


    /**
     * 235（本题）是找二叉搜索树最近公共祖先
     * 236是找二叉树最近公共祖先
     */

    /**
     * 利用到了二叉搜索树的特性--能进行剪枝
     */
    TreeNode res1;
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        find(root,p,q);
        return res1;
    }

    public void find(TreeNode root, TreeNode p, TreeNode q){
        if(root==null) return;
        //如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。
        // 此时，p和q要么在当前节点的不同的子树中，要么其中一个就是当前节点
        if((p.val-root.val)*(q.val-root.val)<=0){
            res1=root;
            return;
        }
        if(res!=null) return;
        //如果当前节点的值小于p和q的值，说明p和q应该在当前节点的右子树，
        //因此将当前节点移动到它的右子节点
        if(p.val>root.val&&q.val>root.val){
            find(root.right,p,q);
        }else{//相反思路
            find(root.left,p,q);
        }
    }

    /**
     * 非递归版本
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    /**
     * 通用解法--不是二叉搜索树也可以  236题
     * 若root是p, q的最近公共祖先，则只可能为以下情况之一:
     * 1.p和q在root的子树中，盼列root的异侧(即分别在左、右子树中) ;
     * 2.p=root，且q在root的左或右子树中;
     * 3.q=root，且p在root的左或右子树中;
     */

    /**
     * 优解--代码段但是不能进行剪枝（要遍历了这一整颗树才能找到）
     * 1.终止条件:
     *   当越过叶节点，则直接返回null
     *   当root等于p,q,则直接返回root
     * 2.递推工作:
     *    1.开刑递归左子节点，回值记为left ;
     *    2.开启递归右子节点,返回值记为right ;
     * 3.返回值:根据left和right，可开为四种情况;
     *     1.当left和right同时为空:说明root的左1右子树中都不包含p,q,返迥null;
     *     2.当left和right同时不为空:说明p,q分列在root的异侧(分别在左1右子树)，因此root为最近公共祖先,返回root ;
     *     3.当left为空，right不为空: p, q都不在root的左子树中，接返回right。具体可分为两种情况:
     *          1.p,q其中一个在root的右子树中，此时right指向p (假设为p) ;
     *          2. p, q两节点都在root的右子树中，此时的right 指向最近公共祖先节点;
     *     4.当left不为空，right 为空:与情况3.同理;
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        //递归终止只有两种情况，一是到也只节点之后，而是遇到了目标p或q节点
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        if(left == null && right == null) return null; // 1.说明root节点下左右子树都没有p或q
        if(left == null) return right; // 3.
        if(right == null) return left; // 4.
        return root; // 2. if(left != null and right != null)
    }


    /**
     * my解法---代码长但是进行剪枝了
     */
    TreeNode res;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //要找两个节点
        find(root,p,q,2);
        return res;
    }

    public int find(TreeNode root, TreeNode p, TreeNode q,int all){
        if(root==null) return all;
        //剪枝操作终止递归
        if(res!=null) return -1;
        int start=all;
        if(root==p||root==q){
            --start;
            if(start==0){
                return 0;
            }
        }
        int left=find(root.left,p,q,start);
        if(left==0&&all==2){
            res=root;
            return -1;
        }
        int right=find(root.right,p,q,left);
        if(right==0&&all==2){
            res=root;
            return -1;
        }
        return right;
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
