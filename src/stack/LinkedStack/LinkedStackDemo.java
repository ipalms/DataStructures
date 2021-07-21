package stack.LinkedStack;



public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        for (String str : stack) {
            System.out.print(str+" ");
        }
        System.out.println("-----------------------------");
        String result = stack.pop();
        System.out.println("弹出了元素："+result);
        System.out.println(stack.size());
    }
}
