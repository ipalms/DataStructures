package algorithm.String;

import org.junit.Test;

/**
 * 468. 验证IP地址
 * 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址。
 * 如果是有效的 IPv4 地址，返回 "IPv4" ；
 * 如果是有效的 IPv6 地址，返回 "IPv6" ；
 * 如果不是上述类型的 IP 地址，返回 "Neither" 。
 * IPv4 地址由十进制数和点来表示，每个地址包含 4 个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
 * 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。
 * IPv6 地址由 8 组 16 进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。
 * 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
 * 同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
 * 示例 1：
 * 输入：IP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * 示例 2：
 * 输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * 示例 3：
 * 输入：IP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 * 示例 4：
 * 输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
 * 输出："Neither"
 * 示例 5：
 * 输入：IP = "1e1.4.5.6"
 * 输出："Neither"
 */
public class ValidIPAddress468 {

    @Test
    public void test(){
        //String tmp=String.valueOf(Math.abs(123));
        //System.out.println(reverse(123));
        System.out.println(validIPAddress("172.16.254.1"));
    }

    /**
     * 思路就是分开验证ipv4 ipv6
     * 总之就是繁杂的对字符串的操作，我这里没有调用库函数解题
     * 两个String的API：
     * String[] res=spilt(".") 按照分隔符分割String字符串
     * String join=String.join(".",res) 按照分隔符合并字符串，
     *      第二个参数可以是String数组也可以是Collection的子类(实现了Iterable接口的都可)
     */
    int len;
    public String validIPAddress(String IP) {
        len=IP.length();
        if(checkIPv4WithApi(IP)){
            return "IPv4";
        }
        if(checkIPv6WithApi(IP)){
            return "IPv6";
        }
        return "Neither";
    }

    /**
     * 判断是否为ipv4
     */
    public boolean checkIPv4(String IP){
        if(len<7||len>15) return false;
        String[] tmp=new String[4];
        int pre=0,t=0;
        //截取String字符，可以使用String自带的spilt()代替
        for(int i=0;i<len;++i){
            if(t>=4){
                return false;
            }
            if(i==len-1){
                tmp[t++]=IP.substring(pre,len);
            }else if(IP.charAt(i)=='.'){
                tmp[t++]=IP.substring(pre,i);
                pre=i+1;
            }
        }
        if(t<4) return false;
        for(int i=0;i<4;++i){
            String ip4=tmp[i];
            int l=ip4.length(),j=0;
            //判断长度
            if(l<1||l>3||(l>1&&ip4.charAt(j)=='0')){
                return false;
            }
            int res=0;
            //累加数字
            while(j<l){
                char c=ip4.charAt(j);
                if(c<'0'||c>'9'){
                    return false;
                }
                res=res*10+c-'0';
                ++j;
            }
            if(res>255){
                return  false;
            }
        }
        return true;
    }

    /**
     * 判断是否为ipv6
     */
    public boolean checkIPv6(String IP){
        if(len<15||len>39) return false;
        String[] tmp=new String[8];
        int pre=0,t=0;
        for(int i=0;i<len;++i){
            if(t>=8){
                return false;
            }
            if(i==len-1){
                tmp[t++]=IP.substring(pre,len);
            }else if(IP.charAt(i)==':'){
                tmp[t++]=IP.substring(pre,i);
                pre=i+1;
            }
        }
        if(t<8) return false;
        for(int i=0;i<8;++i){
            String ip6=tmp[i];
            int l=ip6.length(),j=0;
            if(l<1||l>4){
                return false;
            }
            //Ipv6并不需要将16进制字符转化为10进制判断，只需要先判断这个ipv6中字符个数是否超过4
            //随后再判断每个字符是否合法（为16进制中出现过的字符）
            while(j<l){
                char c=ip6.charAt(j);
                if((c<'0'||c>'9')&&(c<'a'||c>'f')&&(c<'A'||c>'F')){
                    return false;
                }
                ++j;
            }
        }
        return true;
    }


    /**
     * 使用api简化代码
     */
    public boolean checkIPv4WithApi(String IP){
        if(len<7||len>15) return false;
        if(IP.endsWith(".")) return false;
        // . 号要进行转义
        String[] tmp = IP.split("\\.");
        if(tmp.length!=4) return false;
        for(int i=0;i<4;++i){
            String ip4=tmp[i];
            int l=ip4.length(),j=0;
            //判断长度
            if(l<1||l>3||(l>1&&ip4.charAt(j)=='0')){
                return false;
            }
            while(j<l){
                char c=ip4.charAt(j);
                if(c<'0'||c>'9'){
                    return false;
                }
                ++j;
            }
            if(Integer.parseInt(ip4)>255){
                return  false;
            }
        }
        return true;
    }

    public boolean checkIPv6WithApi(String IP){
        if(len<15||len>39) return false;
        if(IP.endsWith(":")) return false;
        String[] tmp = IP.split(":");
        if(tmp.length!=8) return false;
        for(int i=0;i<8;++i){
            String ip6=tmp[i];
            int l=ip6.length(),j=0;
            if(l<1||l>4){
                return false;
            }
            while(j<l){
                char c=ip6.charAt(j);
                if((c<'0'||c>'9')&&(c<'a'||c>'f')&&(c<'A'||c>'F')){
                    return false;
                }
                ++j;
            }
        }
        return true;
    }
}
