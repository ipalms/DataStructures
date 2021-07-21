package tree.HuffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
/*        //测试压缩文件
		String srcFile = "d://Uninstall.xml";
		String dstFile = "d://Uninstall.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩文件ok~~");*/

        //测试解压文件
        String zipFile = "d://Uninstall.zip";
        String dstFile = "d://Uninstall2.xml";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功!");


        /*String content = "i like like like java do you like a javada";
        //String content = "i";
        //获得字符串对应的字节数组 长度为40
        byte[] contentBytes = content.getBytes();
        *//*System.out.println(contentBytes.length);
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);

        //测试一把，创建的赫夫曼树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        //测试一把是否生成了对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("~生成的赫夫曼编码表= " + huffmanCodes);

        //测试压缩后的字节数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));//字符数组长度为17*//*
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + " 字节数组长度= " + huffmanCodesBytes.length);
        //测试一把byteToBitString方法
        //System.out.println(byteToBitString((byte)1));
        byte[] sourceBytes = decode(huffmanCodesBytes, huffmanCodes);

        System.out.println("原来的字符串=" + new String(sourceBytes)); // "i like like like java do you like a java"*/
    }

     //编写一个方法，完成对压缩文件的解压
    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            //读取编码后文件的二进制数据长度
            Integer wordLength=(Integer) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanBytes,huffmanCodes,wordLength);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }
    }

    //编写方法，将一个文件进行压缩
    /**
     *
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //获得文件压缩后的二进制字符串长度
            Integer wordLength=stringBuilder1.length();
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
            oos.writeObject(wordLength);

        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
        }
    }


    //完成数据的解压
    //思路
    //1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    //2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"


    //编写一个方法，完成对压缩数据的解码

    /**
     * @param pathCodes    赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    public static byte[] decode(byte[] huffmanBytes, Map<Byte, String> pathCodes,int wordLength) {
        // 首先转化成二进制符号，然后拼接成字符串-注意末尾byte的处理
        StringBuilder str = new StringBuilder();
        boolean flag;
        byte b;
        for (int i = 0; i < huffmanBytes.length - 1; i++) {
            // 判断是否被8整除，如果整除就不用判断最后一个数据，全部需要补位截取
            b = huffmanBytes[i];
            str.append(byteToBitString(true, b));
        }
        // 单独处理一下最后一位byte
        b = huffmanBytes[huffmanBytes.length - 1];
        String lastByteStr = byteToBitString(false, b);
        // 如果长度相等，那正好。拼接上去
        if (str.length() + lastByteStr.length() == wordLength) {
            str.append(lastByteStr);
        } else {
            // 如果长度不够，那就先补0，直到总长度相等，再拼接
            while (str.length() + lastByteStr.length() < wordLength) {
                str.append(0);
            }
            str.append(lastByteStr);
        }
        System.out.println("解码后的哈夫曼编码字符串为：" + str.toString() + "长度为：" + str.length());
        // 然后对应原先的map表 i=100 反转 制定新的map表 100 =i
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : pathCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 扫描上面的code字符串，对应出单词空格保存到list中
        List<Byte> list = new ArrayList<>();
        for (int j = 0; j < str.length(); ) {
            // i只是前进所用，真正扫描寻找到有为止，还需要一个索引
            int count = 1;
            // 遍历查找的结束标识
            boolean flag2 = true;
            String subStr = null;
            // 注意越界判断
            while (flag2 && (j + count) <= str.length()) {
                // 截取子串，进行map中key值比较
                subStr = str.substring(j, j + count);
                // b2 = map.get(subStr);
                if (map.containsKey(subStr)) {
                    flag2 = false;
                } else {
                    // 没有的话继续遍历后移
                    count++;
                }
            }
            // 将这个byte添加到list中
            list.add(map.get(subStr));
            // 查找到一个后，后移到下一个j起始处.此处后移了j那么for中就不要再后移了
            j += count;
        }
        // 扫描完所有的字符串，将list中的数据转化成byte[]
        byte[] bt = new byte[list.size()];
        for (int i = 0; i < bt.length; i++) {
            bt[i] = list.get(i);
        }
        return bt;
    }
/*    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {

        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 --> 100->a
        Map<String, Byte>  map = new HashMap<String,Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        String match=new String();
        int start=0;
        for(int i=0;i<=stringBuilder.length();i++){//之前一直输出不了最后一个，因为我是用的i作为end下标，所以它还得超出长度1个
            //因此是i<=,而不是i<
            match=stringBuilder.substring(start,i);
            if(map.containsKey(match)){
                list.add(map.get(match));
                start=i;
            }
        }
       *//* //i 可以理解成就是索引,扫描 stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag) {
                //1010100010111...
                //递增的取出 key
                if ((i+count)>stringBuilder.length()) {
                    String key =stringBuilder.substring(i);
                   // b = map.get(key);
                    break;
                }else {
                    String key = stringBuilder.substring(i, i + count);//i 不动，让count移动，指定匹配到一个字符  here
                    b = map.get(key);
                    if (b == null) {//说明没有匹配到
                        count++;
                    } else {
                        //匹配到
                        flag = false;
                    }
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }*//*
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }*/

    /**
     * 将一个byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的Java基础 二进制的原码，反码，补码
     *
     * @param b    传入的 byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256; //按位或 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        //如果压缩生成的最后一个byte数为负值，会多出24位1,解决方法：这一段判断条件加一个temp<0
        if (flag || temp < 0) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    //使用一个方法，将前面的方法封装起来，便于我们的调用.

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //获得各个节点（数据域+权重）
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建的赫夫曼树（返回根节点）
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码(根据赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    //编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]

    /**
     * @param bytes        这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    //存放编码过的二进制数据文件（字符串形式） 在判断文件是否达到最后时有用
    static StringBuilder stringBuilder1 = new StringBuilder();
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        /*//1.利用 huffmanCodes 将  bytes 转成  赫夫曼编码对应的字符串
        StringBuilder stringBuilder1 = new StringBuilder();*/
        //遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder1.append(huffmanCodes.get(b));
        }

        //System.out.println("测试 stringBuilder~~~=" + stringBuilder.toString());

        //将 "1010100010111111110..." 转成 byte[]

        //统计返回  byte[] huffmanCodeBytes 长度
        //一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder1.length() % 8 == 0) {
            len = stringBuilder1.length() / 8;
        } else {
            len = stringBuilder1.length() / 8 + 1;
        }
        //创建 存储压缩后的 byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder1.length(); i += 8) { //因为是每8位对应一个byte,所以步长 +8
            String strByte;
            if (i + 8 > stringBuilder1.length()) {//不够8位
                strByte = stringBuilder1.substring(i);
            } else {
                strByte = stringBuilder1.substring(i, i + 8);
            }
            //将strByte转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        System.out.println("stringBuilder1    "+stringBuilder1   +"长度:"+stringBuilder1.length());
        return huffmanCodeBytes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    //   生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();


    //为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入结点
     * @param code          路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { //如果node == null不处理
            //判断当前node 是叶子结点还是非叶子结点
            if (node.data == null) { //非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { //说明是一个叶子结点(不再递归调用getCodes方法)
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


    //可以通过List 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //排序, 从小到大（Node类实现了Comparable接口）
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点 没有data, 只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);

        }
        //nodes 最后的结点，就是赫夫曼树的根结点
        return nodes.get(0);

    }

    /**
     * @param bytes 接收字节数组
     * @return 返回的就是 List 形式   [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
     */
    private static List<Node> getNodes(byte[] bytes) {

        //1创建一个ArrayList date
        ArrayList<Node> nodes = new ArrayList<Node>();

        //遍历 bytes , 统计 每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();  //HashMap的key值不重复，添加key值相同的会覆盖掉之前的
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map还没有这个字符数据,第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个Node 对象，并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}
