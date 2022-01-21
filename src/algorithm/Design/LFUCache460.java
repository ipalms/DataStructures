package algorithm.Design;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 460. LFU 缓存
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 实现 LFUCache 类：
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。
 * 当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。
 * 对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 示例：
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // 返回 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // 返回 4
 *                  // cache=[3,4], cnt(4)=2, cnt(3)=3
 * 提示：
 * 0 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * 最多调用 2 * 105 次 get 和 put 方法
 */
public class LFUCache460 {

    /**
     * LFU实现是 2个Map+N个双向链表---实现所有操作O（1）时间复杂度
     * 一个Map记录key-->使用频率节点  一个Map记录key-->具体节点
     * 一条双向链表连接所有的频率节点
     * N条双向链表连接所有的使用频率该频率的具体节点
     * 即双向链表之间的关系是嵌套的数据结构
     *
     * 使用优先队列可以实现O（log*n）的时间复杂度，容易实现
     */
    public static void main(String[] args) {
        LFUCache lfu=new LFUCache(2);
        lfu.put(1,1);
        lfu.put(2,2);
        lfu.get(1);
        lfu.put(3,3);
        lfu.get(2);
        lfu.get(3);
        lfu.put(4,4);
        lfu.get(1);
        lfu.get(3);
        lfu.get(4);
    }

    /**
     * 自己写的不能全部透过，但大概是对的，细节非常多
     */
    static class LFUCache{
        //具体节点类
        class Node{
            Node l,r;
            int key,value;
            public Node(int key,int value){
                this.key=key;
                this.value=value;
            }
        }

        //频率节点类，可以自己实现也可以使用LinkedList
        class Bucket{
            //频率节点对应的前后节点
            Bucket l,r;
            //首尾节点--哨兵节点
            Node head,tail;
            //代表频率
            int idx;
            //频率节点双向链表中元素个数
            int size=0;
            public Bucket(int idx){
                this.idx=idx;
                head=new Node(-1,-1);
                tail=new Node(-1,-1);
                head.r=tail;
                tail.l=head;
            }

            public void put(int key,int value){
                //已近存在,使用频率+1，先断开节点
                Node cur=new Node(key,value);
                cur.r=head.r;
                cur.l=head;
                head.r.l=cur;
                head.r=cur;
                map.put(key,cur);
                ++size;
            }

            //移除某个的节点
            public Node delete(int key){
                Node del=map.get(key);
                del.l.r=del.r;
                del.r.l=del.l;
                map.remove(del.key);
                --size;
                return del;
            }

            //判断该频率下链表节点个数，为0要释放该双向链表
            public boolean isEmpty(){
                return size==0;
            }
        }

        //记录key-->具体节点
        Map<Integer,Node> map=new HashMap<>();
        //记录key-->使用频率节点
        Map<Integer,Bucket> feq=new HashMap<>();
        Bucket head,tail;
        //总的节点个数
        int cnt=0;
        int capacity;
        public LFUCache(int capacity) {
            this.capacity=capacity;
            head=new Bucket(-1);
            tail=new Bucket(-1);
            head.r=tail;
            tail.l=head;
        }

        public int get(int key) {
            //map中存在该key才进行下一步操作
            if(map.containsKey(key)){
                Bucket cur=feq.get(key);
                //频率链表移除该key
                Node target=cur.delete(key);
                Bucket next;
                //相邻频度节点还没有创建
                if(cur.idx+1!=cur.r.idx){
                    next=new Bucket(cur.idx+1);
                    next.r=cur.r;
                    next.l=cur;
                    cur.r.l=next;
                    cur.r=next;
                }else{
                    next=cur.r;
                }
                next.put(target.key,target.value);
                feq.put(target.key,next);
                if(cur.isEmpty()){
                    cur.r.l=cur.l;
                    cur.l.r=cur.r;
                }
                return target.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            Bucket cur,pre=head;
            int times=1;
            //该key已经放置进去了，需要更新对应value值
            if(map.containsKey(key)){
                //先移除
                pre=feq.get(key);
                times=pre.idx;
                pre.delete(key);
                --cnt;
            }
            if(cnt==capacity){
                Bucket bucket=head.r;
                bucket.delete(bucket.tail.l.key);
                //这个频率对应的双向链表已经没有元素了，移除这个频率对应节点
                if(bucket.isEmpty()){
                    bucket.r.l=bucket.l;
                    bucket.l.r=bucket.r;
                }
                --cnt;
            }
            if(pre.r.idx!=times){
                cur=new Bucket(times);
                cur.r=pre.r;
                cur.l=pre;
                pre.r.l=cur;
                pre.r=cur;
            }else{
                cur=pre.r;
            }
            cur.put(key,value);
            feq.put(key,cur);
            ++cnt;
        }
    }

    /**
     * O(logN)解法---实现简单
     * 使用小根堆找到freq 最小，因为Java中的PriorityQueue默认就是小根堆,实现最简单
     * 每次将访问频次freq 最小的且最先访问的上浮到堆顶，下面用全局自增idx表示访问的先后
     * 或者可以直接改成idx =System.nanoTime()用以比较访问时间的先后。
     * 自定义排序规则就可以
     */
    class LFU{
        Map<Integer, Node> cache;
        Queue<Node> queue;
        int capacity;
        int size;
        int idx = 0;

        public LFU(int capacity) {
            cache = new HashMap<>(capacity);
            if (capacity > 0) {
                queue = new PriorityQueue<>(capacity);
            }
            this.capacity = capacity;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            node.freq++;
            node.idx = idx++;
            queue.remove(node);
            queue.offer(node);
            return node.value;

        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                node.freq++;
                node.idx = idx++;
                queue.remove(node);
                queue.offer(node);
            } else {
                if (size == capacity) {
                    cache.remove(queue.peek().key);
                    queue.poll();
                    size--;
                }
                Node newNode = new Node(key, value, idx++);
                cache.put(key, newNode);
                queue.offer(newNode);
                size++;
            }
        }
    }

    class Node implements Comparable<Node> {
        int key;
        int value;
        int freq;
        int idx;

        public Node() {}

        public Node(int key, int value, int idx) {
            this.key = key;
            this.value = value;
            freq = 1;
            this.idx = idx;
        }

        public int compareTo(Node node) {
            int diff = freq - node.freq;
            return diff != 0? diff: idx - node.idx;
        }
    }

    class Item {
        Item l, r;
        int k, v;
        public Item(int _k, int _v) {
            k = _k;
            v = _v;
        }
    }

    class Bucket {
        Bucket l, r;
        int idx;
        Item head, tail;
        //不需要每个Bucket都创建一个Map，一个统一的Map记录key-->Item也行
        Map<Integer, Item> map = new HashMap<>();
        public Bucket(int _idx) {
            idx = _idx;
            head = new Item(-1, -1);
            tail = new Item(-1, -1);
            head.r = tail;
            tail.l = head;
        }
        void put(int key, int value) {
            Item item = null;
            if (map.containsKey(key)) {
                item = map.get(key);
                // 更新值
                item.v = value;
                // 在原来的双向链表位置中移除
                item.l.r = item.r;
                item.r.l = item.l;
            } else {
                item = new Item(key, value);
                // 添加到哈希表中
                map.put(key, item);
            }
            // 增加到双向链表头部
            item.r = head.r;
            item.l = head;
            head.r.l = item;
            head.r = item;
        }
        Item remove(int key) {
            if (map.containsKey(key)) {
                Item item = map.get(key);
                // 从双向链表中移除
                item.l.r = item.r;
                item.r.l = item.l;
                // 从哈希表中移除
                map.remove(key);
                return item;
            }
            return null; // never
        }
        Item clear() {
            // 从双向链表尾部找到待删除的节点
            Item item = tail.l;
            item.l.r = item.r;
            item.r.l = item.l;
            // 从哈希表中移除
            map.remove(item.k);
            return item;
        }
        boolean isEmpty() {
            return map.size() == 0;
        }
    }

    Map<Integer, Bucket> map = new HashMap<>();
    Bucket head, tail;
    int n;
    int cnt;
    public LFUCache460(int capacity) {
        n = capacity;
        cnt = 0;
        head = new Bucket(-1);
        tail = new Bucket(-1);
        head.r = tail;
        tail.l = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Bucket cur = map.get(key);

            Bucket target = null;
            if (cur.r.idx != cur.idx + 1) {
                // 目标桶空缺
                target = new Bucket(cur.idx + 1);
                target.r = cur.r;
                target.l = cur;
                cur.r.l = target;
                cur.r = target;
            } else {
                target = cur.r;
            }

            // 将当前键值对从当前桶移除，并加入新的桶
            Item remove = cur.remove(key);
            target.put(remove.k, remove.v);
            // 更新当前键值对所在桶信息
            map.put(key, target);

            // 如果在移除掉当前键值对后，当前桶为空，则将当前桶删除（确保空间是 O(n) 的）
            // 也确保调用编号最小的桶的 clear 方法，能够有效移除掉一个元素
            deleteIfEmpty(cur);

            return remove.v;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (n == 0) return;
        if (map.containsKey(key)) {
            // 元素已存在，修改一下值
            Bucket cur = map.get(key);
            cur.put(key, value);
            // 调用一下 get 实现「使用次数」+ 1
            get(key);
        } else {
            // 容器已满，需要先删除元素
            if (cnt == n) {
                // 从第一个桶（编号最小、使用次数最小）中进行清除
                Bucket cur = head.r;
                Item clear = cur.clear();
                map.remove(clear.k);
                cnt--;

                // 如果在移除掉键值对后，当前桶为空，则将当前桶删除（确保空间是 O(n) 的）
                // 也确保调用编号最小的桶的 clear 方法，能够有效移除掉一个元素
                deleteIfEmpty(cur);
            }

            // 需要将当前键值对增加到 1 号桶
            Bucket first = null;

            // 如果 1 号桶不存在则创建
            if (head.r.idx != 1) {
                first = new Bucket(1);
                first.r = head.r;
                first.l = head;
                head.r.l = first;
                head.r = first;
            } else {
                first = head.r;
            }

            // 将键值对添加到 1 号桶
            first.put(key, value);
            // 更新键值对所在桶信息
            map.put(key, first);
            // 计数器加一
            cnt++;
        }
    }

    void deleteIfEmpty(Bucket cur) {
        if (cur.isEmpty()) {
            cur.l.r = cur.r;
            cur.r.l = cur.l;
            cur = null; // help GC
        }
    }
}
