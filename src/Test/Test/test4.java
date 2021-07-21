package Test.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test4 {
    public static void main(String[] args) {
        int[] appetite = {5, 8, 13, 17, 55, 60};
        int[] size = {1, 6, 9, 55, 17, 19, 11};
        max(appetite, size);
    }

    public static void max(int[] appetite, int[] size) {
        int count = 0;
        Arrays.sort(appetite);
        Arrays.sort(size);
        List<Integer> list = new ArrayList<>();
        for (int ele : size) {
            list.add(ele);
        }
        for (int i = appetite.length - 1; i >= 0; i--) {
            if (list.size() > 0) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) >= appetite[i]) {
                        list.remove(j);
                        count++;
                        break;
                    }
                }
            } else {
                break;
            }
        }
        System.out.println(count);
    }

}
