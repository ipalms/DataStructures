package algorithm.Design;

/**
 * 705. 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * 实现 MyHashSet 类：
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * 示例：
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 *
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 * 提示：
 * 0 <= key <= 106
 * 最多调用 104 次 add、remove 和 contains 。
 * 进阶：你可以不使用内建的哈希集合库解决此问题吗？
 */
public class MyHashSet705 {

    /**
     * 与706设计思路差不多
     */

    Node []table;
    int size;
    int total;
    int max;
    //扩容因子
    float factor=0.8f;
    /** Initialize your data structure here. */
    public MyHashSet705() {
        table=new Node[13];
        size=13;
        total=0;
        max=(int)(size*factor);
    }

    public void add(int key) {
        if(!contains(key)){
            Node newNode=new Node(key);
            //数据插入使用头插法
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
                //数据插入使用头插法
                curr.next=newTable[getIndex(curr.key)];
                newTable[getIndex(curr.key)]=curr;
                curr=next;
            }
        }
        table=newTable;
    }

    //取hash是对数组长度进行取模
    private int getIndex(int key){
        return key%size;
    }

    public void remove(int key) {
        if(contains(key)){
            Node curr=table[getIndex(key)];
            if(curr.key==key){
                table[getIndex(key)]=curr.next;
            }else{
                while(curr.next.key!=key){
                    curr=curr.next;
                }
                curr.next=curr.next.next;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        Node curr=table[getIndex(key)];
        while(curr!=null&&curr.key!=key){
            curr=curr.next;
        }
        return curr!=null;
    }

    class Node{
        int key;
        Node next;
        public Node(int key){
            this.key=key;
        }
    }
}
