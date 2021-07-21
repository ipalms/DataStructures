package linkedlist.SingleLinkedList2;

public class SingleLinkedListDemo2 {
    public static void main(String[] args) {
        SingleLinkedList2<String> list = new SingleLinkedList2<>();
        list.insert("李四");
        list.insert(1,"王五");
        list.insert(2,"赵六");
        list.insert("赵三");
        //测试length方法
        for (String s : list) {
            System.out.println(s);
        }
        list.reverse();
        System.out.println("测试反转");
        for (String i : list) {
            System.out.println(i+" "); }
        System.out.println(list.length());
        list.insert(2,"kk");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.length());
        System.out.println("-------------------");
        //测试get方法
        System.out.println(list.get(2));
        System.out.println("------------------------");
        //测试remove方法
        String remove = list.remove(1);
        System.out.println(remove);
        System.out.println(list.length());
        System.out.println("----------------");;
        for (String s : list) {
            System.out.println(s);
        }
    }
}
