package algorithm;

import org.junit.Test;

import java.util.*;

public class test1 {

    @Test
    public void test(){
        int []a=new int[100];
        int []b=new int[]{5,1,3,2,4};
        System.arraycopy(b,0,a,1,4);
        System.out.println(Arrays.toString(a));
        
        
        
        boolean is=false;
    }




    public int nthSuperUglyNumber(int n, int[] primes) {
        int []dp=new int[n+2];
        dp[1]=1;
        int k=primes.length;
        int []size=new int[k];
        Arrays.fill(size,1);
        PriorityQueue<Integer>queue=new PriorityQueue<>();
        for(int i=0;i<k;++i){
            queue.offer(primes[i]);
        }
        for(int i=2;i<=n;++i){
            int x=queue.poll();
            dp[i]=x;
            for(int j=0;j<k;++j){
                if(primes[j]*dp[size[j]]==x){
                    size[j]++;
                    queue.offer(dp[size[j]]*primes[j]);
                }
            }
        }
        return dp[n];
    }

    public String reverseWords(String s) {
        StringBuilder sb=new StringBuilder();
        int i=s.length()-1;
        while(i>=0){
            if(isValid(s.charAt(i))){
                if(sb.length()>0)
                    sb.append(" ");
                int left=i;
                i--;
                while(i>=0&&isValid(s.charAt(i))){
                    i--;
                }
                //substing()方法的两个参数是要截取数据的首尾索引，且截取的是左区间闭合右区间开的部分
                //即右边的为索引应该比实际要截取的索引大1
                sb.append(s, i+1, left+1);
            }
            i--;
        }
        return sb.toString();
    }

    public boolean isValid(char r){
        return (r>='a'&&r<='z')||(r>='A'&&r<='Z')||(r>='0'&&r<='9');
    }

    public boolean isPalindrome(String s) {
        int p1=0,p2=s.length()-1;
        while(p1<p2){
            char l=s.charAt(p1);
            if(!valid(l)){
                ++p1;
                continue;
            }
            char r=s.charAt(p2);
            if(!valid(r)){
                --p2;
                continue;
            }
            if(l==r||(r>='A'&&l>='A'&&Math.abs(r-l)==32)){
                ++p1;
                --p2;
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean valid(char k){
        return (k>='a'&&k<='z')||(k>='A'&&k<='Z')||(k>='0'&&k<='9');
    }


    /**
     * 二分求数num第一次出现的位置
     */
    public int binarySearch(int []pre,int right,int target){
        if(target<0) return -1;
        int left=0;
        while(left<right){
            int mid=left+(right-left)/2;
            if(pre[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }

    //利用栈的后进先出--可以将括号关系放入hashmap存储映射关系
    public boolean isValid(String s) {
        if(s.length() % 2 == 1||s.charAt(0)=='}'||s.charAt(0)==']'||s.charAt(0)==')'){
            return false;
        }
        Stack<Character> res=new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char k=s.charAt(i);
            if(k=='{'||k=='('||k=='['){
                res.push(k);
            }
            if(res.isEmpty()){
                return false;
            }
            if((k=='}'&&res.pop()!='{')||(k==']'&&res.pop()!='[')||(k==')'&&res.pop()!='(')){
                return false;
            }
        }
        return res.isEmpty();
    }

    //hashmap存储映射关系
    public boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
