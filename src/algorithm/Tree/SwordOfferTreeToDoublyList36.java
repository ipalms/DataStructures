package algorithm.Tree;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 * 特别地，我们希望可以就地完成转换操作。
 * 当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
 * 还需要返回链表中的第一个节点的指针
 * 注意：本题与主站 426 题相同
 */
public class SwordOfferTreeToDoublyList36 {


    //利用BST树中序遍历的有序性
    Node head,prev;
    public Node treeToDoublyList(Node root) {
        if(root==null) return null;
        //head、prev节点的寻找可以在递归的过程中找到
        buildDoublyList1(root);
        head.left=prev;
        prev.right=head;
        return head;
    }

    private void buildDoublyList1(Node root){
        if(root==null) return;
        buildDoublyList1(root.left);
        if(head==null) head=root;
        if(prev!=null){
            prev.right=root;
        }
        root.left=prev;
        prev=root;
        buildDoublyList1(root.right);
    }

    //利用BST树中序遍历的有序性
    public Node treeToDoublyListMy(Node root) {
        if(root==null) return null;
        //head、tail节点的寻找可以在递归的过程中找到
        Node head=findHead(root);
        Node tail=findTail(root);
        buildDoublyList(root);
        head.left=tail;
        tail.right=head;
        return head;
    }

    Node pre=null;
    private void buildDoublyList(Node root){
        if(root==null) return;
        buildDoublyList(root.left);
        if(pre!=null){
            pre.right=root;
            root.left=pre;
        }
        pre=root;
        buildDoublyList(root.right);
    }


    private Node findHead(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }

    private Node findTail(Node root){
        while(root.right!=null){
            root=root.right;
        }
        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
