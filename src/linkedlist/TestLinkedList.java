package linkedlist;

import java.util.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<Object> a = new LinkedList<>();
        a.add(0,5);
        a.add(1,10);
        a.add(2,50);
        System.out.println(a);
        a.pop();
        a.removeLast();
        System.out.println(a);
    }
}
