package Test.Test;


import java.util.LinkedList;
import java.util.Stack;


public class test3 {
    public static void main(String[] args) {
        String str = "())(";
        String str1 = "()))((()";
        String str2="(()";
        String str3=")(()";
        String str4="({}[])";
        isRight(str);
        isRight(str1);
        isRight(str2);
        isRight(str3);
        isValid(str4);
    }

    public static void isRight(String str) {
        boolean b = true;
        LinkedList<Character> list = new LinkedList<>();
     //   Stack<Character> stack=new Stack<>();
        char ch[] = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch.length % 2 != 0) {
                b = false;
                break;
            } else if (ch[0] != '(') {
                b = false;
                break;
            } else {
                if (ch[i] == '(') {
                    list.addLast(ch[i]);
                } else if (ch[i] == ')') {
                    if (list.size() > 0) {
                        list.removeLast();
                    } else {
                        b = false;
                    }
                }
            }
        }
        if(list.contains('(')){
            b=false;
        }
            System.out.println(b);
    }
    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.push(s.charAt(i));
            else {
                if (stack.size() == 0)
                    return false;

                Character c = stack.pop();

                Character match;
                if (s.charAt(i) == ')')
                    match = '(';
                else if (s.charAt(i) == ']')
                    match = '[';
                else {
                    assert s.charAt(i) == '}';
                    match = '{';
                }

                if (c != match)
                    return false;
            }

        return stack.size() == 0;
    }
}
