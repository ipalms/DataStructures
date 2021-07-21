package linkedlist.DoubleLinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        Node2 hero1 = new Node2(1, "宋江", "及时雨");
        Node2 hero2 = new Node2(2, "卢俊义", "玉麒麟");
        Node2 hero3 = new Node2(3, "吴用", "智多星");
        Node2 hero4 = new Node2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        //测试指向前一个节点的指针有无问题
        doubleLinkedList.reverseList();

        // 修改
        Node2 newHeroNode = new Node2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.reverseList();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.reverseList();

       //测试按序号添加节点
        Node2 newHero3 = new Node2(3, "wuyong", "zhiduoxing");
        doubleLinkedList.addByOrder(newHero3);
        System.out.println("测试按序号添加节点后的链表情况");
        doubleLinkedList.reverseList();
    }
}
