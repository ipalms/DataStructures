package algorithm.BackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum39 {
    public static void main(String[] args) {
        System.out.println(getPermutation(4,9));
    }
    static int  count=0;
    static  String word="";
    public static String getPermutation(int n, int k) {
        //List<String> res=new ArrayList<String>();
        boolean[] visited =new boolean[n];
        backtrack(new StringBuilder(),n,k,visited);
        return word;
    }

    private static boolean backtrack(StringBuilder tmp, int n, int k, boolean[] visited){
        if(tmp.length()==n){
            count++;
        }
        if(count==k){
            word=tmp.toString();
            return true;
        }
        for(int i=1;i<=n;i++){
            if(visited[i-1]) continue;
            tmp.append(i);
            visited[i-1]=true;
            if(backtrack(tmp,n,k,visited)){
                return true;
            }
            visited[i-1]=false;
            tmp.delete(tmp.length()-1,tmp.length());
        }
        return false;
    }
}
