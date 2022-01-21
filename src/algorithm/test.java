package algorithm;

import org.junit.Test;

import java.util.*;

public class test {

    static int []a=new int[]{10,9,5,199,0,-1,5,15,3,35};
    static Random ran=new Random();
    public static void main(String []args){
//        Scanner sc=new Scanner(System.in);
//        String []first=sc.nextLine().split(" ");
//        int []nums=new int[first.length];
//        for(int i=0;i<nums.length;++i){
//            nums[i]=Integer.parseInt(first[i]);
//        }
        //int []temp=new int[a.length];
        //mergeSort(a,0,a.length-1,temp);
        heapSort(a);
        for(int i=0;i<a.length;++i){
            System.out.print(a[i]+"\t");
        }
        List<List<Integer>> res=new ArrayList<>();
        //这样初始化不能使用变量i,j等等 必须添加常量
        res.add(new ArrayList<>(){{add(1);add(2);add(3);}});
    }

    public int countBinarySubstrings(String s) {
        int all=0;
        for(int i=0;i<s.length();++i){
            int count=0;
            for(int j=i;j<s.length();++j){
                if(s.charAt(j)=='1'){
                    ++count;
                }
                if(j-i+1==2*count){
                    ++all;
                }
            }
        }
        return all;
    }

    @Test
    public void test(){
        System.out.println("a".compareTo("b"));
    }

    private static void quickSort(int []nums,int left,int right){
        if(left>=right) return;
        int pivot=findPivot(nums,left,right);
        quickSort(nums,left,pivot-1);
        quickSort(nums,pivot+1,right);
    }


    private static int findPivot(int []nums,int left,int right){
        int s=left+ran.nextInt(right-left)+1;
        swap(nums,right,s);
        int pivotNum=nums[right],curr=left;
        for(int i=left;i<right;++i){
            if(nums[i]<pivotNum){
                swap(nums,curr,i);
                ++curr;
            }
        }
        swap(nums,right,curr);
        return curr;
    }

    private static void swap(int []nums,int a,int b){
        int tep=nums[a];
        nums[a]=nums[b];
        nums[b]=tep;
    }


    public static void mergeSort(int []nums,int left,int right,int []temp){
        if(left>=right) return;
        int mid=left+((right-left)>>1);
        mergeSort(nums,left,mid,temp);
        mergeSort(nums,mid+1,right,temp);
        if(nums[mid]<=nums[mid+1]) return;
        merge(nums,left,mid,right,temp);
    }

    private static void merge(int []nums,int left,int mid,int right,int []temp){
        System.arraycopy(nums,left,temp,left,right-left+1);
        int r=mid+1,k=left;
        while(left<=mid&&r<=right){
            if(temp[left]<=temp[r]){
                nums[k++]=temp[left++];
            }else{
                nums[k++]=temp[r++];
            }
        }
        while(left<=mid){
            nums[k++]=temp[left++];
        }
        while(r<right){
            nums[k++]=temp[r++];
        }
    }



    private static void heapSort(int []nums){
        int len=nums.length;
        for(int i=len/2-1;i>=0;--i){
            adjustHeap(nums,i,len);
        }
        for(int i=len-1;i>=1;--i){
            swap(nums,0,i);
            adjustHeap(nums,0,i);
        }
    }

    private static void adjustHeap(int []nums,int i,int end){
        int temp=nums[i];
        for(int k=2*i+1;k<end;k=2*k+1){
            if(k+1<end&&nums[k]<nums[k+1]){
                ++k;
            }
            if(nums[k]>temp){
                nums[i]=nums[k];
                i=k;
            }else{
                break;
            }
        }
        nums[i]=temp;
    }
}
