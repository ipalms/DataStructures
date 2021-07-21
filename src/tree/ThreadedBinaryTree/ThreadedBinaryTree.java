package tree.ThreadedBinaryTree;

//定义ThreadedBinaryTree 实现了线索化功能的二叉树(在原二叉树基础上增加了线索化功能)
public class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载一把threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

   //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node) {

        //如果node==null, 不能线索化
        if(node == null) {
            return;
        }

        //(一)先线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前结点[有难度]

        //处理当前结点的前驱结点
        //以8结点来理解
        //8结点的.left = null , 8结点的.leftType = 1
        if(node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }

        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点（因为pre时全局变量，即使改变后递归使用的任然是改变后的内容）
        pre = node;

        //(三)在线索化右子树
        threadedNodes(node.getRight());

    }

    //遍历线索化二叉树的方法（使用什么顺序序列化的二叉树，其遍历结果与序列化形式相同）
    public void threadedList() {
        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while(node != null) {
            //循环的找到leftType == 1的结点，第一个找到就是8结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while(node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();

        }
    }

    //编写对二叉树进行前序线索化的方法
    /**
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedBeforNodes(HeroNode node) {

        //如果node==null, 不能线索化
        if(node == null) {
            return;
        }
        //1,3,8,10,6,14
        if(node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
//每处理一个结点后，让当前结点是下一个结点的前驱结点（因为pre时全局变量，即使改变后递归使用的任然是改变后的内容）
        pre = node;
        //(二)先线索化左子树
        if(node.getLeftType()!=1) {
            threadedBeforNodes(node.getLeft());
        }
        if(node.getRightType()!=1) {
            //(三)在线索化右子树
            threadedBeforNodes(node.getRight());
        }
    }


    //前序遍历线索二叉树 1,3,8,10,6,14
    public void preOrderThreaded() {
        //定义一个变量，存储当前遍历的结点，从root开始
            HeroNode temp = root;
            while(temp != null) {
                System.out.println(temp + " ");
                //如果存在左子节点就往左走，否则往右走，此时右指针一定是前序遍历的下一个节点
                if(temp.getLeftType()==0) {
                    temp = temp.getLeft();
                }else {
                    temp = temp.getRight();
                }
        }
    }

    //编写对二叉树进行后序线索化的方法
    public void threadedAfterNodes(HeroNode node) {
        //如果node==null, 不能线索化
        if(node == null) {
            return;
        }
        //线索化左子树
        threadedAfterNodes(node.getLeft());
        //线索化右子树
        threadedAfterNodes(node.getRight());

        if(node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点（因为pre时全局变量，即使改变后递归使用的任然是改变后的内容）
        pre = node;
    }


    //删除结点
    public void delNode(int no) {
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }

    //前序遍历
    public void preOrder() {
        if(this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //中序遍历
    public HeroNode infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}
