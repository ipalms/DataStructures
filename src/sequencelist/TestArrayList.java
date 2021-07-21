package sequencelist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> a=new ArrayList<>();
        a.add(2);
        a.add(2);
        a.add(2);
        a.add(2);
        a.add(2,5);
        a.iterator();
        for (Integer integer : a) {
            System.out.println(integer);
        }
    }
}
