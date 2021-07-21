package tree.RedBlackTree;

//key继承Comparable类，可以进行比较
public class RBNode<T extends Comparable<T>,Value> {
    boolean color;//颜色
    T key;//关键值
    Value value; //存储内容
    RBNode<T,Value> left;//左子节点
    RBNode<T,Value> right;//右子节点
    RBNode<T,Value> parent;//父节点

    public RBNode(boolean color,Value value,T key,RBNode<T,Value> parent,RBNode<T,Value> left,RBNode<T,Value> right){
        this.color = color;
        this.key = key;
        this.value=value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    //中序遍历
    public void infixOrder() {
        if(this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null) {
            this.right.infixOrder();
        }
    }

    //获得节点的关键值
    public T getKey(){
        return key;
    }
    //获得节点的内容
    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RBNode{" +
                "key=" + key +
                " ,value=" + value +
                " ,color=" + (this.color == true ? " RED":" BLACK") +
                '}';
    }
}
