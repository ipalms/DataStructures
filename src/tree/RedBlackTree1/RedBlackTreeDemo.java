package tree.RedBlackTree1;

import java.util.TreeMap;

public class RedBlackTreeDemo {
    public static void main(String[] args) {
        //创建红黑树
        RedBlackTree<String, String> tree = new RedBlackTree<>();

        //往树中插入元素
        tree.put("3","E");
        tree.put("2","C");
        tree.put("1","A");
   /*     tree.put("10","S");
        tree.put("1","A");
        tree.put("9","R");
        tree.put("7","H");*/
        //从树中获取元素
        //System.out.println(tree.getRoot());
        tree.infixOrder();
    }
}
