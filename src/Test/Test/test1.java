package Test.Test;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        int []price={7,5,5,4,3};
        getProfitmost(price);
    }
    public static void getProfitmost(int []price) {
        Scanner c = new Scanner(System.in);
        int startday = c.nextInt();
        if (startday >= 1 && startday <= price.length ) {
            int startprice = price[startday - 1];
            int max = startprice;
            if (startday == price.length) {
                System.out.println("最大利润为0");
            }
            else {
                for (int i = startday; i <= price.length; i++) {
                    if (price[startday] > max) {
                        max = price[startday];
                    }
                }
                if (max == startprice) {
                    System.out.println("最大利润为0");
                } else {
                    System.out.println("最大利润为" + (max - startprice));
                }
            }
        }
    }
}
