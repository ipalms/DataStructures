package linkedlist.DoubleLinkedList2;

public class DoubleLinkedListDemo2 {
    public static void main(String[] args) {
        DoubleLinkedList2<String> list = new DoubleLinkedList2<>();
        list.insert("乔峰");
        list.insert("虚竹");
        list.insert("段誉");
        list.insert(1,"鸠摩智");
        list.insert(4,"叶二娘");
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("----------------------");
        String tow = list.get(2);
        System.out.println(tow);
        String remove = list.remove(4);
        System.out.println(remove);
        System.out.println("------------删除结点后-------------");
        System.out.println(list.length());
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("链表最后一个元素 "+list.getLast());
        System.out.println("--------------------");
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
    }
}
