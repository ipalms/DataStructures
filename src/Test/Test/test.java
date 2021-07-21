package Test.Test;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
    char []c={'h','e','l','l','o'};
        System.out.println(Arrays.toString(reverseString(c)));
}
    public static char[]  reverseString(char [] word) {
        char temp;
        for (int i = 0 ,j = word.length -1; i <word. length / 2 ; i++,j--) {
            temp=word[i];
            word[i]=word[j];
            word[j]=temp;
        }
        return word;
    }
}
