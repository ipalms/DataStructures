package algorithm;

import org.junit.Test;

import java.util.*;

public class test1 {



    //利用栈的后进先出--可以将括号关系放入hashmap存储映射关系
    public boolean isValid(String s) {
        if(s.length() % 2 == 1||s.charAt(0)=='}'||s.charAt(0)==']'||s.charAt(0)==')'){
            return false;
        }
        Stack<Character> res=new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char k=s.charAt(i);
            if(k=='{'||k=='('||k=='['){
                res.push(k);
            }
            if(res.isEmpty()){
                return false;
            }
            if((k=='}'&&res.pop()!='{')||(k==']'&&res.pop()!='[')||(k==')'&&res.pop()!='(')){
                return false;
            }
        }
        return res.isEmpty();
    }

    //hashmap存储映射关系
    public boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
