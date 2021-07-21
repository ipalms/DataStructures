package tree.RedBlackTree;

public class RedBlackTreeDemo {
    public static void main(String[] args) {
        RedBlackTree<Integer,String> tree= new RedBlackTree<>();
        tree.insert(3,"jack");
        tree.insert(2,"tom");
        tree.insert(10,"marry");
        tree.insert(1,"july");
        tree.insert(9,"jack");
        tree.insert(7,"smith");
        //System.out.println(tree.getRoot());
        System.out.println("  " +tree.get(10));
        tree.infixOrder();
    }
}
