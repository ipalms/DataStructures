package linkedlist;

import queue.ArrayQueue.ArrayQueue;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

//演示栈Stack基本使用
public class TestStack {

	public static void main(String[] args) {
		Stack<String> stack = new Stack();
		// 入栈（压栈）
		stack.add("jack");
		stack.add("tom");
		stack.add("smith");
		//出栈（弹栈）
		// smith, tom , jack
		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
}
