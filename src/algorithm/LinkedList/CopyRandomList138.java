package algorithm.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. 复制带随机指针的链表
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点
 * 并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
 * 那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 * 提示：
 * 0 <= n <= 1000
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 */
public class CopyRandomList138 {

    Map<Node,Node> have=new HashMap<>();

    /**
     * 递归加哈希表
     */
    public Node copyRandomListMy(Node head) {
        if(head==null){
            return null;
        }
        Node newCurr=new Node(head.val);
        have.put(head,newCurr);
        Node r=head.random;
        newCurr.random=have.containsKey(r)?have.get(r):copyRandomList(r);
        Node n=head.next;
        newCurr.next=have.containsKey(n)?have.get(n):copyRandomList(n);
        return newCurr;
    }

    /**
     * 哈希表
     * 两次遍历--一次遍历按照next指针创建新链表
     * 第二次遍历完善random指针指向（利用哈希表）
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1);
        Node tail = dummy, tmp = head;
        while (tmp != null) {
            Node node = new Node(tmp.val);
            map.put(tmp, node);
            tail.next = node;
            tail = tail.next;
            tmp = tmp.next;
        }
        tail = dummy.next;
        while (head != null) {
            if (head.random != null) tail.random = map.get(head.random);
            tail = tail.next;
            head = head.next;
        }
        return dummy.next;
    }

    /**
     * 迭代 + 节点拆分（省去哈希表的空间）
     * 使用「哈希表」的目的为了实现原节点和新节点的映射关系，更进一步的是为了快速找到某个节点 random 在新链表的位置。
     * 如何省去哈希表：
     * 1.对原链表的每个节点节点进行复制，并追加到原节点的后面；
     * 2.完成1操作之后，链表的奇数位置代表了原链表节点，链表的偶数位置代表了新链表节点，
     *   且每个原节点的 next 指针执行了对应的新节点。
     *   这时候，我们需要构造新链表的 random 指针关系，
     *   可以利用 link[i + 1].random = link[i].random.next，i为奇数下标，
     *   含义为 新链表节点的 random 指针指向旧链表对应节点的 random 指针的下一个值
     * 3.对链表按奇偶进行拆分操作。
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        //作用是重置到链表头
        Node dummy = new Node(-1);
        dummy.next = head;
        //复制链表每个节点--链表的奇数位置代表了原链表节点，链表的偶数位置代表了新链表节点，
        while (head != null) {
            Node node = new Node(head.val);
            node.next = head.next;
            head.next = node;
            head = node.next;
        }
        head = dummy.next;
        //复制原链表的random指针
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
        head = dummy.next;
        Node ans = head.next;
        //拆分链表
        while (head != null) {
            Node tmp = head.next;
            if (head.next != null) head.next = head.next.next;
            head = tmp;
        }
        return ans;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
