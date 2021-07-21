package skiplist.skip2;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(5);
        skipList.insert(7);
        skipList.insert(9);
        String string = skipList.toString();
        System.out.println(string);
    }
}
