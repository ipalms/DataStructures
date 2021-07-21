package sequencelist;

public class SequenceListDemo2 {
    public static void main(String[] args) {
        //创建顺序表对象
        SequenceList2<String> squence2 = new SequenceList2<>(5);
        squence2.insert(0, "姚明");
        squence2.insert(1, "科比");
        squence2.insert(2, "麦迪");
        squence2.insert(3, "艾佛森");
        squence2.insert(4, "卡特");
        System.out.println("初始数组大小已满情况");
        squence2.showEles();
        squence2.insert(3, "kk");
        squence2.showEles();
        /*System.out.println("扩容后数组");
        squence2.insert(5, "aa");
        squence2.insert(6, "aa");
        squence2.insert(7, "aa");
        squence2.insert(8, "aa");
        squence2.insert(9, "aa");
        squence2.showEles();
        System.out.println(squence2.capacity());
        squence2.remove(1);
        squence2.remove(1);
        squence2.remove(1);
        squence2.remove(1);
        squence2.remove(1);
        squence2.remove(1);
        squence2.remove(1);
        squence2.remove(1);
        System.out.println(squence2.capacity());
        squence2.showEles();*/
    }
}
