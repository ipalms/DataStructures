package algorithm.Design;

/**
 * 706. 设计哈希映射
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 * 实现 MyHashMap 类：
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * 示例：
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 * 提示：
 * 0 <= key, value <= 106
 * 最多调用 104 次 put、get 和 remove 方法
 * 进阶：你能否不使用内置的 HashMap 库解决此问题？
 */
public class MyHashMap706 {

    /**
     * 与705的设计思路差不多
     */

    Node []table;
    int size;
    int total;
    int max;
    float factor=0.8f;
    /** Initialize your data structure here. */
    public MyHashMap706() {
        table=new Node[10];
        size=10;
        total=0;
        max=(int)(size*factor);
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        Node curr=table[getIndex(key)];
        while(curr!=null&&curr.key!=key){
            curr=curr.next;
        }
        if(curr!=null){
            curr.value=value;
        }else{
            Node newNode=new Node(key,value);
            newNode.next=table[getIndex(key)];
            table[getIndex(key)]=newNode;
            if(++total>max){
                resize(size*2);
            }
        }
    }


    //数组扩容
    private void resize(int newSize){
        Node []newTable=new Node[newSize];
        size=newSize;
        max=(int)(size*factor);
        Node next;
        for(int i=0;i<size/2;++i){
            Node curr=table[i];
            while(curr!=null){
                next=curr.next;
                curr.next=newTable[getIndex(curr.key)];
                newTable[getIndex(curr.key)]=curr;
                curr=next;
            }
        }
        table=newTable;
    }


    private int getIndex(int key){
        return key%size;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        Node curr=table[getIndex(key)];
        while(curr!=null&&curr.key!=key){
            curr=curr.next;
        }
        return curr!=null?curr.value:-1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        Node curr=table[getIndex(key)];
        if(curr==null) return;
        if(curr.key==key){
            table[getIndex(key)]=curr.next;
        }else{
            while(curr.next!=null&&curr.next.key!=key){
                curr=curr.next;
            }
            if(curr.next!=null){
                curr.next=curr.next.next;
            }
        }
    }

    class Node{
        int key;
        int value;
        Node next;
        public Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
}
