package algorithm.AcmMode;
import java.util.*;

class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val=val;
    }
}

public class ReorderList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String []vals=sc.nextLine().trim().split(",");
        ListNode dummy=new ListNode(0);
        ListNode curr=dummy;
        for(String v:vals){
            curr.next=new ListNode(Integer.parseInt(v));
            curr=curr.next;
        }
        ListNode head=dummy.next,fast=head,slow=head;
        if(head==null) return;
        if(head.next==null){
            System.out.print(head.val);
            return;
        }
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode tmp=slow.next,pre=null;
        slow.next=null;
        while(tmp!=null){
            ListNode next=tmp.next;
            tmp.next=pre;
            pre=tmp;
            tmp=next;
        }
        //输出的时候可以考虑StringBuilder拼接字符串，最后除去多余的一个逗号
        ListNode l1=head,l2=pre;
        while(l2!=null&&l2.next!=null){
            System.out.print(l1.val+","+l2.val+",");
            ListNode c=l1.next;
            l1.next=l2;
            l1=c;
            l2=l2.next;
        }
        if(l1.next!=null){
            l2.next=l1;
            System.out.print(l1.val+","+l2.val+","+l1.next.val);
        }else{
            System.out.print(l1.val+","+l2.val);
        }
    }
}
