package algorithm.Design;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * <p>
 * <p>
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 */
public class LRUCache146 {


    /**
     * 用到过哨兵的题：
     * 146. LRU 缓存机制（双向链表设置头尾哨兵）
     * 84. 柱状图中最大的矩形（单调栈压入哨兵省去一系列判空操作--可以压入首尾哨兵）
     */


    /**
     * 少量代码实现版本
     * 两个要提的点：
     *   1.不能自己去实现HashMap的逻辑，因为既要维护双向链表又要维护数组+链表的哈希结构是复杂的
     *   在put的逻辑中会触及到两者的同时维护，代码量大而且易错且哈希函数只能实现最简单的模哈希
     *   （所以哈希表就使用现成的HashMap，key--->Node的形式）
     *   2.为了减少双向链表左右节点的「判空」操作，我们预先建立两个「哨兵」节点 head 和 tail。
     *   初始化方法先new两个head和tail节点的同时将head与tail互连
     * https://leetcode-cn.com/problems/lru-cache/solution/gong-shui-san-xie-she-ji-shu-ju-jie-gou-68hv2/
     */
    int n;
    Node head, tail;
    Map<Integer, Node> map;
    public LRUCache146(int capacity) {
        n = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.r = tail;
        tail.l = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            refresh(node);
            return node.v;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.v = value;
        } else {
            if (map.size() == n) {
                Node del = tail.l;
                map.remove(del.k);
                delete(del);
            }
            node = new Node(key, value);
            map.put(key, node);
        }
        refresh(node);
    }

    // refresh 操作分两步：
    // 1. 先将当前节点从双向链表中删除（如果该节点本身存在于双向链表中的话）
    // 2. 将当前节点添加到双向链表头部
    void refresh(Node node) {
        delete(node);
        node.r = head.r;
        node.l = head;
        head.r.l = node;
        head.r = node;
    }

    // delete 操作：将当前节点从双向链表中移除
    // 由于我们预先建立 head 和 tail 两位哨兵，因此如果 node.l 不为空，则代表了 node 本身存在于双向链表（不是新节点）
    void delete(Node node) {
        if (node.l != null) {
            Node left = node.l;
            left.r = node.r;
            node.r.l = left;
        }
    }

    class Node {
        int k, v;
        Node l, r;
        Node(int k, int v) {
            this.k =k;
            this.v = v;
        }
    }

    //按照上面版本的仿写
    class CopyWrite{
        Node head;
        Node tail;
        Map<Integer,Node> map=new HashMap<>();
        int n;
        public CopyWrite(int capacity) {
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
            Node curr;
            if(map.containsKey(key)){
                curr=map.get(key);
                curr.value=value;
            }else{
                curr=new Node(key,value);
                map.put(key,curr);
            }
            afterGet(curr);
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


    //部分测试用例不通过，这个是自己写的哈希表的添加逻辑
    //大体参照LinkedHashMap的设计节点
    //所以要同时更新串联节点的双向链表以及维护哈希表的插入操作
    class wrongCode {
        Node head;
        Node tail;
        Node[] table;
        int capacity;
        int total = 0;

        public wrongCode(int capacity) {
            this.capacity = capacity;
            table = new Node[capacity];
            head = null;
            tail = null;
        }

        public int get(int key) {
            Node curr = table[getIndex(key)];
            while (curr != null && curr.key != key) {
                curr = curr.next;
            }
            if (curr == null) return -1;
            if (head == tail) return curr.val;
            //维护双向链表
            if (curr == tail) tail = curr.pre;
            if (curr != head) {
                curr.pre.after = curr.after;
                curr.pre = null;
                curr.after = head;
                head.pre = curr;
                head = curr;
            }
            return curr.val;
        }

        private int getIndex(int key) {
            return key % capacity;
        }

        public void put(int key, int value) {
            Node curr = table[getIndex(key)], newNode;
            //哈希表插入
            if (curr == null) {
                ++total;
                newNode = new Node(key, value);
                table[getIndex(key)] = newNode;
            } else if (curr.key == key) {
                curr.val = value;
                newNode = curr;
            } else {
                while (curr.next != null && curr.next.key != key) {
                    curr = curr.next;
                }
                if (curr.next == null) {
                    ++total;
                    newNode = new Node(key, value);
                    curr.next = newNode;
                } else {
                    curr.next.val = value;
                    newNode = curr.next;
                }
            }
            //查看双向链表元素是否溢出
            if (total > capacity) {
                //溢出要更新双向链表以及哈希表
                Node delete = table[getIndex(tail.key)];
                if (delete.key == tail.key) {
                    table[getIndex(tail.key)] = tail.next;
                } else {
                    while (delete.next.key != tail.key) {
                        delete = delete.next;
                    }
                    delete.next = delete.next.next;
                }
                if (head != tail) {
                    tail = tail.pre;
                    tail.after = null;
                } else {
                    head = null;
                    tail = null;
                }
                --total;
            }
            //更新插入|修改节点后的双向链表
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else if (head == tail) {
                tail.pre = newNode;
                newNode.after = tail;
                head = newNode;
            } else {
                if (newNode == tail) {
                    tail = tail.pre;
                    tail.after = null;
                }
                newNode.after = head;
                head.pre = newNode;
                head = newNode;
            }
        }

        class Node {
            int key;
            int val;
            Node next;
            Node pre;
            Node after;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }
}
