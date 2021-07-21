package tree.ArrBinaryTree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        //创建一个 ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        //前序遍历
        //arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
        //中序遍历
        arrBinaryTree.indexOrder(0); // 4,2,5,1,6,3,7
    }
}
