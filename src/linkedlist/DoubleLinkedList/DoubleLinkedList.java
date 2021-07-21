package linkedlist.DoubleLinkedList;


public class DoubleLinkedList {

    // 先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private Node2 head = new Node2(0, "", "");

    // 返回头节点
    public Node2 getHead() {
        return head;
    }

    // 遍历双向链表的方法
    // 显示链表[遍历]  顺序遍历
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        Node2 temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移， 一定小心
            temp = temp.next;
        }
    }

    // 显示链表[遍历]  倒叙遍历（检验pre有没有问题）
    public void reverseList() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 获得最后一个元素 temp
        Node2 temp = head;
        while (true) {
            // 判断是否到链表最后
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //获得最后一个元素 temp
        Node2 temp1 = temp;  //从temp开始向前遍历
        while (true) {
            System.out.println(temp1);
            if (temp1.pre.no == 0) {
                break;
            }
            //temp1前移
            temp1 = temp1.pre;
        }
    }

    // 添加一个节点到双向链表的最后.
    public void add(Node2 heroNode) {

        // 因为head节点不能动，因此我们需要一个辅助遍历 temp
        Node2 temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {//
                break;
            }
            // 如果没有找到最后, 将将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(Node2 heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，所以我们找的temp是位于添加位置的前一个节点，否则插入不了
        Node2 temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后,新节点的编号最大，直接将新节点插入temp后面即可
                break; //
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入.此时temp向当于要插入的节点前一个
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag 的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //heroNode.next = temp.next;   temp.next = heroNode;//单链表做法
            //对于temp是否为最后一个节点（即temp下个节点存不存在）而分类操作 这四个操作有先后顺序关系
            //对于链表来说，有相应的构造函数对于链接操作更快
            heroNode.pre = temp;
            Node2 nextNode = null;
            if (temp.next != null) {
                //定义一个temp临时的上一节点
                nextNode = temp.next;
                temp.next.pre = heroNode;
                temp.next = heroNode;
                heroNode.next = nextNode;
            } else {
                temp.next = heroNode;
            }
        }
    }

    // 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
    // 只是 节点类型改成 Node2
    public void update(Node2 newHeroNode) {
        // 判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点, 根据no编号
        // 定义一个辅助变量
        Node2 temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { // 没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    // 从双向链表中删除一个节点,
    // 说明
    // 1 对于双向链表，我们可以直接找到要删除的这个节点
    // 2 找到后，自我删除即可
    public void del(int no) {

        // 判断当前链表是否为空
        if (head.next == null) {// 空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        Node2 temp = head.next; // 辅助变量(指针)
        boolean flag = false; // 标志是否找到待删除节点的
        while (true) {
            if (temp == null) { // 已经到链表的最后
                break;
            }
            if (temp.no == no) {
                // 找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        // 判断flag
        if (flag) { // 找到
            // 可以删除
            // temp.next = temp.next.next;[单向链表]
            //先让上一个元素的next指向temp的next
            temp.pre.next = temp.next;
            // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}
