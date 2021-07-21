package linkedlist.SingleLinkedList;

//定义HeroNode ， 每个HeroNode 对象就是一个节点      向当于每个节点存入对象所在的实体类
public class HeroNode {
    //下面三个属性可以定义一个实体类封装
    public int no;    //代表序号
    public String name;
    public String nickname; //代表昵称
    public HeroNode next; //指向下一个节点
    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
