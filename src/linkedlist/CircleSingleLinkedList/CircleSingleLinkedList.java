package linkedlist.CircleSingleLinkedList;

public class CircleSingleLinkedList {
    // 创建一个first节点,当前没有编号
    private Node first = null;

    // 添加节点，构建成一个环形的链表（构建思想）
    public void addNode(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Node curNode = null; // 辅助指针，帮助构建环形链表
        // 使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Node node = new Node(i);
            // 如果是第一个节点
            if (i == 1) {
                first = node;
                first.setNext(first); // 构成环（当前只有第一个节点）
                curNode = first; // 让curNode指向第一个节点
            } else {
                curNode.setNext(node);//让辅助指针指向新加节点
                node.setNext(first);//让新加节点指向第一个指针，以构成环状链表
                curNode = node; //辅助指针后移
            }
        }
    }

    // 遍历当前的环形链表
    public void showNode() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Node curNode = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curNode.getNo());
            if (curNode.getNext() == first) {// 说明已经遍历完毕
                break;
            }
            curNode = curNode.getNext(); // curNode后移
        }
    }

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countNode(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建要给辅助指针,帮助完成小孩出圈
        Node helper = first;
        // 需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点(first后一个节点，
        // 因为时单向链表，所以删除链表节点需要借助前一个节点)
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让 first 和  helper 移动 startNo-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和 helper 指针同时 的移动 countNum-1 次, 然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (helper == first) { //说明圈中只有一个节点
                break;
            }
            //让 first 和 helper 指针同时 的移动 countNum - 1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);    //建立新的环形
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}
