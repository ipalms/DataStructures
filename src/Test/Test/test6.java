package Test.Test;

public class test6 {
    public static void main(String[] args) {
          int time[]={30,20,150,100,40};
        System.out.println(number(time));
    }

    public static int number(int time[]){
        int count=0;
        for (int i = 0; i <time.length ; i++) {
            for (int j = i+1; j <time.length ; j++) {
                if((time[i]+time[j])%60==0){
                    count++;
                }
            }
        }
        return count;
    }
}
