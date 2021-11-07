package algorithm.AcmMode;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * 设计一个数据结构，实现LRU Cache的功能(Least Recently Used – 最近最少使用缓存)。
 * 它支持如下2个操作：get 和 put。
 * int get(int key) – 如果key已存在，则返回key对应的值value（始终大于0）；如果key不存在，则返回-1。
 *
 * void put(int key, int value) – 如果key不存在，将value插入；
 * 如果key已存在，则使用value替换原先已经存在的值。
 * 如果容量达到了限制，LRU Cache需要在插入新元素之前，将最近最少使用的元素删除。
 *
 * 请特别注意“使用”的定义：新插入或获取key视为被使用一次；而将已经存在的值替换更新，不算被使用。
 * 限制：请在O(1)的时间复杂度内完成上述2个操作。
 *
 * 输入描述：
 * 第一行读入一个整数n，表示LRU Cache的容量限制。 从第二行开始一直到文件末尾，每1行代表1个操作。
 * 如果每行的第1个字符是p，则该字符后面会跟随2个整数，表示put操作的key和value。
 * 如果每行的第1个字符是g，则该字符后面会跟随1个整数，表示get操作的key。
 * 输出描述：
 * 按照输入中get操作出现的顺序，按行输出get操作的返回结果。
 * 示例1
 * 输入：
 * 2
 * p 1 1
 * p 2 2
 * g 1
 * p 2 102
 * p 3 3
 * g 1
 * g 2
 * g 3
 *
 * 输出：
 * 1
 * 1
 * -1
 * 3
 *
 * 说明：
 * 2        //Cache容量为2
 * p 1 1    //put(1, 1)
 * p 2 2    //put(2, 2)
 * g 1      //get(1), 返回1
 * p 2 102  //put(2, 102)，更新已存在的key，不算被使用
 * p 3 3    //put(3, 3)，容量超过限制，将最近最少使用的key=2清除
 * g 1      //get(1), 返回1
 * g 2      //get(2), 返回-1
 * g 3      //get(3), 返回3
 */
public class LRU {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity =scanner.nextInt();
        LRUCache instance = new LRUCache(capacity);
        while (scanner.hasNextLine()) {
            String []command = scanner.nextLine().trim().split(" ");
            if (command[0].equals("p")) {
                int key = Integer.parseInt(command[1]);
                int value = Integer.parseInt(command[2]);
                instance.put(key, value);
            }else if(command[0].equals("g")) {
                int key = Integer.parseInt(command[1]);
                System.out.println(instance.get(key));
                }
            }
        }
    }
    
    
    class LRUCache{
        Node head;
        Node tail;
        Map<Integer, Node> map=new HashMap<>();
        int n;
        public LRUCache(int capacity) {
            n=capacity;
            head=new Node(0,0);
            tail=new Node(0,0);
            head.after=tail;
            tail.pre=head;
        }

        public int get(int key) {
            if(map.containsKey(key)){
                Node curr=map.get(key);
                afterGet(curr);
                return curr.value;
            }
            return -1;
        }

        private void afterGet(Node curr){
            if(map.size()>n){
                map.remove(tail.pre.key);
                deleteTail(tail.pre);
            }
            if(curr.pre!=null){
                curr.pre.after=curr.after;
                curr.after.pre=curr.pre;
            }
            curr.after=head.after;
            head.after.pre=curr;
            curr.pre=head;
            head.after=curr;
        }

        private void deleteTail(Node del){
            Node left=del.pre;
            left.after=tail;
            tail.pre=left;
            del.pre=null;
            del.after=null;
        }

        public void put(int key, int value) {
            if(n==0) return;
            Node curr;
            if(map.containsKey(key)){
                curr=map.get(key);
                curr.value=value;
            }else{
                curr=new Node(key,value);
                map.put(key,curr);
                afterGet(curr);
            }
        }

        class Node{
            int key;
            int value;
            Node pre;
            Node after;
            public Node(int key,int value){
                this.key=key;
                this.value=value;
            }
        }
    }

