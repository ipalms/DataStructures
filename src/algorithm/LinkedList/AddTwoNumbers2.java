package algorithm.LinkedList;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中,它们各自的位数是按照逆序的方式存储的，
 * 并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers2 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(4,node1);
        ListNode node3 = new ListNode(2,node2);

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6,node4);
        ListNode node6 = new ListNode(5,node5);
        ListNode node = addTwoNumbers(node3, node6);
        System.out.println(node);
    }

    /**
     *整合了重复操作  注意这里的链表已经逆序存储了，不需要你再次进行链表反转操作
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        //采用并结构循环
        while (l1 != null || l2 != null) {
            //  = 的符号优先级小于三位运算符
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    /**
     *自己写的没有整合重复的代码
     */
    public ListNode addTwoNumbers4(ListNode l1, ListNode l2) {
        ListNode ahead=new ListNode(0);
        ListNode next=ahead;
        int mode=0;
        while(l1!=null||l2!=null){
            if(l1!=null&&l2!=null){
                int value=(mode+l1.val+l2.val)%10;
                mode=(mode+l1.val+l2.val)/10;
                ListNode k=new ListNode(value);
                next.next=k;
                next=k;
                l1=l1.next;
                l2=l2.next;
                continue;
            }
            if(l1!=null){
                int value=(mode+l1.val)%10;
                mode=(mode+l1.val)/10;
                ListNode k=new ListNode(value);
                next.next=k;
                next=k;
                l1=l1.next;
            }
            if(l2!=null){
                int value=(mode+l2.val)%10;
                mode=(mode+l2.val)/10;
                ListNode k=new ListNode(value);
                next.next=k;
                next=k;
                l2=l2.next;
            }
        }
        if(mode!=0){
            next.next= new ListNode(mode);
        }
        return ahead.next;
    }


    //误写了链表反转的题解
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode pre=null,pre1=null;
        ListNode curr=l1,curr1=l2;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=pre;
            pre=curr;
            curr=next;
        }
        while(curr1!=null){
            ListNode next=curr1.next;
            curr1.next=pre1;
            pre1=curr1;
            curr1=next;
        }
        ListNode u1=pre,u2=pre1;
        ListNode ahead=new ListNode(0);
        ListNode next=ahead;
        int mode=0;
        while(u1!=null||u2!=null){
            if(u1!=null&&u2!=null){
                int value=(mode+u1.val+u2.val)%10;
                mode=(mode+u1.val+u2.val)/10;
                ListNode k=new ListNode(value);
                next.next=k;
                next=k;
                u1=u1.next;
                u2=u2.next;
                continue;
            }
            if(u1!=null){
                int value=(mode+u1.val)%10;
                mode=(mode+u1.val)/10;
                ListNode k=new ListNode(value);
                next.next=k;
                next=k;
                u1=u1.next;
            }
            if(u2!=null){
                int value=(mode+u2.val)%10;
                mode=(mode+u2.val)/10;
                ListNode k=new ListNode(value);
                next.next=k;
                next=k;
                u2=u2.next;
            }
        }
        if(mode!=0){
            ListNode k=new ListNode(mode);
            next.next=k;
        }
        return ahead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
