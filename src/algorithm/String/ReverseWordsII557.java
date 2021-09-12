package algorithm.String;

public class ReverseWordsII557 {

    /**
     * 双指针
     */
    public String reverseWords(String s) {
        int n=s.length();
        char []res=s.toCharArray();
        int start=0;
        for(int i=0;i<n;i++){
            if(res[i]==' '){
                reverse(res,start,i-1);
                start=i+1;
            }
            if(i==n-1){
                reverse(res,start,i);
            }
        }
        return new String(res);
    }

    public void reverse(char []res,int start,int end){
        while(start<end){
            char tmp=res[end];
            res[end--]=res[start];
            res[start++]=tmp;
        }
    }

    /**
     * 使用StringBuilder
     * 慢于直接操作char数组
     */
    public String reverseWords2(String s) {
        StringBuffer ret = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }
}
